package com.madhes.EmployeeManagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhes.EmployeeManagement.dto.auth.LoginRequestDTO;
import com.madhes.EmployeeManagement.security.JwtService;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {
        if(request.getUsername().equals("admin") && request.getPassword().equals("admin123")) {
            return jwtService.generateToken(request.getUsername());
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

}
