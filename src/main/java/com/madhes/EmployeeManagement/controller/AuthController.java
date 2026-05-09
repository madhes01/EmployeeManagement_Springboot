package com.madhes.EmployeeManagement.controller;

import com.madhes.EmployeeManagement.dto.auth.LoginRequestDTO;
import com.madhes.EmployeeManagement.dto.auth.RegisterRequestDTO;
import com.madhes.EmployeeManagement.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController handles public endpoints for registration and login.
 * These endpoints are explicitly permitted in SecurityConfig (no JWT needed).
 *
 * WHY ResponseEntity instead of plain String:
 *   ResponseEntity lets us control the HTTP status code explicitly.
 *   Returning plain String always gives 200 — even for a 201 Created or 401.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Register and login endpoints")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequestDTO request) {
        String message = authService.register(request);
        return ResponseEntity.status(201).body(message); // 201 Created
    }

    @Operation(summary = "Login and get JWT token")
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO request) {
        String token = authService.login(request);
        return ResponseEntity.ok(token); // 200 OK with JWT
    }
}