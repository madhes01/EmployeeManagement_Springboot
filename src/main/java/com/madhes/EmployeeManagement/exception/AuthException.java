package com.madhes.EmployeeManagement.exception;

/**
 * AuthException is thrown for authentication/authorization failures.
 * Mapped to HTTP 401 Unauthorized in GlobalExceptionHandler.
 *
 * Separate from ResourceNotFoundException (404) because these are
 * fundamentally different failure scenarios.
 */
public class AuthException extends RuntimeException {

    public AuthException(String message) {
        super(message);
    }
}