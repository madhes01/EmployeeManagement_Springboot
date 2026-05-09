package com.madhes.EmployeeManagement.dto.auth;

import com.madhes.EmployeeManagement.entity.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * RegisterRequestDTO carries the data for user registration.
 *
 * Validation added here because AuthController now uses @Valid.
 * Without @NotBlank, someone can register with an empty username/password.
 */
@Data
public class RegisterRequestDTO {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Role is required")
    private Role role;
}