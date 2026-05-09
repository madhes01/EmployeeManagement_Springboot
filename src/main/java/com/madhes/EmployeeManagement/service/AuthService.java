package com.madhes.EmployeeManagement.service;

import com.madhes.EmployeeManagement.dto.auth.LoginRequestDTO;
import com.madhes.EmployeeManagement.dto.auth.RegisterRequestDTO;
import com.madhes.EmployeeManagement.entity.User;
import com.madhes.EmployeeManagement.exception.AuthException;
import com.madhes.EmployeeManagement.repository.UserRepository;
import com.madhes.EmployeeManagement.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AuthService handles user registration and login.
 *
 * WHY AuthException instead of RuntimeException:
 *   RuntimeException is not handled by GlobalExceptionHandler → returns ugly 500.
 *   AuthException is a custom exception mapped to proper HTTP status in the handler.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public String register(RegisterRequestDTO request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AuthException("Username already exists"); // → 409 Conflict
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // BCrypt hash
        user.setRole(request.getRole());
        userRepository.save(user);

        logger.info("User registered: {}", request.getUsername());
        return "User registered successfully";
    }

    public String login(LoginRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AuthException("User not found")); // → 401

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException("Invalid password"); // → 401
        }

        logger.info("User logged in: {}", request.getUsername());
        return jwtService.generateToken(user.getUsername());
    }
}