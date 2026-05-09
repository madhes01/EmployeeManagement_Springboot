package com.madhes.EmployeeManagement.security;

import com.madhes.EmployeeManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * SecurityConfig defines the entire security model for this application.
 *
 * Key decisions:
 * - CSRF disabled: REST APIs are stateless; CSRF protection is only needed for
 *   browser-based session flows. With JWT there are no cookies → no CSRF risk.
 * - SessionCreationPolicy.STATELESS: Spring Security will NOT create an HttpSession.
 *   Every request must carry its own JWT. This is the correct model for REST APIs.
 * - @EnableMethodSecurity: enables @PreAuthorize("hasRole('ADMIN')") on controller methods.
 *   Without this annotation, method-level security annotations are silently ignored.
 */
@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity // enables @PreAuthorize on controller/service methods
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserRepository userRepository;

    /**
     * UserDetailsService: Spring Security uses this to load a user by username.
     * JwtAuthFilter calls this to get the User + their roles from DB.
     *
     * We define it inline as a lambda bean here (no need for a separate class
     * for a simple setup like this).
     *
     * WHY "ROLE_" prefix: Spring Security's hasRole('ADMIN') checks for authority
     * named "ROLE_ADMIN". So we must prefix when building SimpleGrantedAuthority.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            com.madhes.EmployeeManagement.entity.User user =
                    userRepository.findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException(
                                    "User not found: " + username));

            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(List.of(
                            new SimpleGrantedAuthority("ROLE_" + user.getRole().name())))
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * SecurityFilterChain: defines which URLs are open vs protected,
     * and plugs in our JWT filter before Spring's default auth filter.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                    // Public endpoints — no token needed
                    .requestMatchers(
                            "/auth/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/swagger-ui.html"
                    ).permitAll()
                    // Everything else requires a valid JWT
                    .anyRequest().authenticated()
            )

            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Our JWT filter runs BEFORE Spring's UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}