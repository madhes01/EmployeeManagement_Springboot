package com.madhes.EmployeeManagement.controller;

import com.madhes.EmployeeManagement.dto.request.EmployeeRequestDTO;
import com.madhes.EmployeeManagement.dto.response.EmployeeResponseDTO;
import com.madhes.EmployeeManagement.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * EmployeeController exposes all employee management endpoints.
 *
 * Access control:
 *   - GET endpoints: any authenticated user (ADMIN or USER)
 *   - POST, PUT, DELETE: ADMIN only (demonstrated with @PreAuthorize)
 *
 * WHY @CrossOrigin without parameters is risky in production:
 *   It allows ALL origins. For a Deloitte project, you'd restrict to specific origins.
 *   For this case study it is acceptable.
 */
@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Employee", description = "Employee CRUD operations")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create a new employee (ADMIN only)")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {

        EmployeeResponseDTO created = employeeService.createEmployee(requestDTO);
        return ResponseEntity.status(201).body(created); // 201 Created
    }

    @Operation(summary = "Get all employees with pagination")
    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployees(Pageable pageable) {
        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    @Operation(summary = "Get employees by sector name")
    @GetMapping("/sector/{sectorName}")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeeBySector(
            @PathVariable String sectorName) {

        return ResponseEntity.ok(employeeService.getEmployeeBySector(sectorName));
    }

    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @Operation(summary = "Update employee (ADMIN only)")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO requestDTO) {

        return ResponseEntity.ok(employeeService.updateEmployee(id, requestDTO));
    }

    @Operation(summary = "Delete employee (ADMIN only)")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.deleteEmployee(id));
    }
}