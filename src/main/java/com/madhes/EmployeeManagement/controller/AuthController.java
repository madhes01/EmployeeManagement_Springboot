package com.madhes.EmployeeManagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.madhes.EmployeeManagement.dto.auth.LoginRequestDTO;
import com.madhes.EmployeeManagement.dto.auth.RegiterRequestDTO;
import com.madhes.EmployeeManagement.security.JwtService;
import com.madhes.EmployeeManagement.service.AuthService;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public String register(@RequestBody RegiterRequestDTO request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

}
