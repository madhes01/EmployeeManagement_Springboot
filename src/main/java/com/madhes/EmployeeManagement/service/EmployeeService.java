package com.madhes.EmployeeManagement.service;

import org.springframework.stereotype.Service;

import com.madhes.EmployeeManagement.dto.request.EmployeeRequestDTO;
import com.madhes.EmployeeManagement.dto.response.EmployeeResponseDTO;
import com.madhes.EmployeeManagement.repository.EmployeeRepository;
import com.madhes.EmployeeManagement.repository.SectorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SectorRepository sectorRepository;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        
        return null;
    }
}
