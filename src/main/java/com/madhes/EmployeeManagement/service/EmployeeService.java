package com.madhes.EmployeeManagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.madhes.EmployeeManagement.dto.request.EmployeeRequestDTO;
import com.madhes.EmployeeManagement.dto.response.EmployeeResponseDTO;
import com.madhes.EmployeeManagement.entity.Employee;
import com.madhes.EmployeeManagement.entity.Sector;
import com.madhes.EmployeeManagement.exception.ResourceNotFoundException;
import com.madhes.EmployeeManagement.repository.EmployeeRepository;
import com.madhes.EmployeeManagement.repository.SectorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final SectorRepository sectorRepository;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {

        Sector sector = sectorRepository.findById(requestDTO.getSectorId())
                                .orElseThrow(() -> new ResourceNotFoundException("Sector id not found"));

        Employee employee = new Employee();
        employee.setEmpId(requestDTO.getEmpId());
        employee.setName(requestDTO.getName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDomain(requestDTO.getDomain());
        employee.setGrade(requestDTO.getGrade());
        employee.setSector(sector);

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponseDTO(savedEmployee);
    }

    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {

        Page<Employee> employee = employeeRepository.findAll(pageable);
        return employee.map(this::mapToResponseDTO);
    }

    public List<EmployeeResponseDTO> getEmployeeBySector(String sectorName) {

        List<Employee> employees =  employeeRepository.findBySectorName(sectorName);
        return employees.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    public EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();

        dto.setId(employee.getId());
        dto.setEmpId(employee.getEmpId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDomain(employee.getDomain());
        dto.setGrade(employee.getGrade());
        dto.setSectorName(employee.getSector().getName());

        return dto;
    }

  
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found"));

        return mapToResponseDTO(employee);
    }

    public EmployeeResponseDTO updateEmployee(long id, EmployeeRequestDTO requestDTO) {

        Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Sector sector = sectorRepository.findById(requestDTO.getSectorId())
        .orElseThrow(() -> new ResourceNotFoundException("Sector not dound"));

        employee.setEmpId(requestDTO.getEmpId());
        employee.setName(requestDTO.getName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDomain(requestDTO.getDomain());
        employee.setGrade(requestDTO.getGrade());
        employee.setSector(sector);

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToResponseDTO(updatedEmployee);
    }

    public String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);

        return "Employee deleted successfully";
    }
}
