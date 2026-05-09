package com.madhes.EmployeeManagement.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthFilter runs ONCE per request (OncePerRequestFilter guarantee).
 *
 * Flow:
 * 1. Read "Authorization" header
 * 2. If missing or not "Bearer ...", skip (let Spring Security handle as
 * unauthenticated)
 * 3. Extract username from JWT
 * 4. Load UserDetails from DB (this gives us the actual ROLES)
 * 5. Validate token against UserDetails
 * 6. Set authentication into SecurityContext so Spring knows this request is
 * authenticated
 * 7. Pass to next filter
 *
 * WHY UserDetailsService instead of Collections.emptyList():
 * Without loading UserDetails, authorities list is empty
 * → @PreAuthorize("hasRole('ADMIN')")
 * will NEVER work. Loading from DB gives us real roles stored in the User
 * entity.
 */

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

        private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

        private final JwtService jwtService;
        private final UserDetailsService userDetailsService; // loads user + roles from DB

        @Override
        protected void doFilterInternal(
                        HttpServletRequest request,
                        HttpServletResponse response,
                        FilterChain filterChain)

                        throws ServletException, IOException {

                final String authHeader = request.getHeader("Authorization");

                // If no Authorization header or not Bearer token → skip, pass through
                // unauthenticated
                if (authHeader == null
                                || !authHeader.startsWith("Bearer ")) {
                        filterChain.doFilter(request, response);
                        return;
                }

                // Strip "Bearer " prefix (7 characters) to get raw JWT
                final String jwt = authHeader.substring(7);
                final String username = jwtService.extractUsername(jwt);

                // Only authenticate if username found AND no existing authentication in context
                // (prevents re-authenticating an already-authenticated request)
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                        // Load full UserDetails from DB — this includes GrantedAuthority list (roles)
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {

                                // Build authentication token with real authorities from DB
                                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                                userDetails,
                                                null,
                                                userDetails.getAuthorities()); // ← ROLES loaded here

                                // Attach request details (IP, session ID) to authentication
                                authToken.setDetails(
                                                new WebAuthenticationDetailsSource().buildDetails(request));

                                // Register authentication into SecurityContext
                                SecurityContextHolder.getContext().setAuthentication(authToken);

                                logger.info("JWT authenticated user: {} | path: {}",
                                                username, request.getRequestURI());
                        }
                }

                // Always continue the filter chain — even unauthenticated requests must reach
                // the security config which decides to permit or deny them
                filterChain.doFilter(request, response);
        }
}