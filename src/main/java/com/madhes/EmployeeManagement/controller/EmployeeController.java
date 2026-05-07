package com.madhes.EmployeeManagement.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.madhes.EmployeeManagement.dto.request.EmployeeRequestDTO;
import com.madhes.EmployeeManagement.dto.response.EmployeeResponseDTO;
import com.madhes.EmployeeManagement.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create Employee")
    @PostMapping
    public EmployeeResponseDTO createEmployee(@Valid @RequestBody EmployeeRequestDTO requestDTO) {
        return employeeService.createEmployee(requestDTO);
    }

    @GetMapping
    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageble) {
        return employeeService.getAllEmployees(pageble);
    }

    @GetMapping("/sector/{sectorName}")
    public List<EmployeeResponseDTO> getEmployeeBySector(@PathVariable String sectorName) {
        return employeeService.getEmployeeBySector(sectorName);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO updateEmployee(
        @PathVariable Long id,
        @Valid @RequestBody EmployeeRequestDTO requestDTO) {

            return employeeService.updateEmployee(id, requestDTO);
        }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        return employeeService.deleteEmployee(id);
    }    


}
