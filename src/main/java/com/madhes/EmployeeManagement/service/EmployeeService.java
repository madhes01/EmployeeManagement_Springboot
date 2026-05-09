package com.madhes.EmployeeManagement.service;

import com.madhes.EmployeeManagement.dto.request.EmployeeRequestDTO;
import com.madhes.EmployeeManagement.dto.response.EmployeeResponseDTO;
import com.madhes.EmployeeManagement.entity.Employee;
import com.madhes.EmployeeManagement.entity.Sector;
import com.madhes.EmployeeManagement.exception.ResourceNotFoundException;
import com.madhes.EmployeeManagement.repository.EmployeeRepository;
import com.madhes.EmployeeManagement.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeRepository employeeRepository;
    private final SectorRepository sectorRepository;

    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        Sector sector = sectorRepository.findById(requestDTO.getSectorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sector not found with id: " + requestDTO.getSectorId()));

        Employee employee = new Employee();
        employee.setEmpId(requestDTO.getEmpId());
        employee.setName(requestDTO.getName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDomain(requestDTO.getDomain());
        employee.setGrade(requestDTO.getGrade());
        employee.setSector(sector);

        Employee saved = employeeRepository.save(employee);
        logger.info("Employee created: {}", saved.getEmpId());
        return mapToResponseDTO(saved);
    }

    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        logger.info("Fetching all employees - page: {}, size: {}",
                pageable.getPageNumber(), pageable.getPageSize());
        return employeeRepository.findAll(pageable).map(this::mapToResponseDTO);
    }

    public List<EmployeeResponseDTO> getEmployeeBySector(String sectorName) {
        List<Employee> employees = employeeRepository.findBySectorName(sectorName);
        logger.info("Found {} employees in sector: {}", employees.size(), sectorName);
        return employees.stream().map(this::mapToResponseDTO).collect(Collectors.toList());
    }

    public EmployeeResponseDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));
        logger.info("Fetched employee by ID: {}", id);
        return mapToResponseDTO(employee);
    }

    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));

        Sector sector = sectorRepository.findById(requestDTO.getSectorId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sector not found with id: " + requestDTO.getSectorId()));

        employee.setEmpId(requestDTO.getEmpId());
        employee.setName(requestDTO.getName());
        employee.setEmail(requestDTO.getEmail());
        employee.setDomain(requestDTO.getDomain());
        employee.setGrade(requestDTO.getGrade());
        employee.setSector(sector);

        Employee updated = employeeRepository.save(employee);
        logger.info("Employee updated: {}", updated.getEmpId());
        return mapToResponseDTO(updated);
    }

    public String deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));
        employeeRepository.delete(employee);
        logger.info("Employee deleted: {}", id);
        return "Employee deleted successfully";
    }

    // Private: internal mapping — no other layer needs direct access to this
    private EmployeeResponseDTO mapToResponseDTO(Employee employee) {
        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setEmpId(employee.getEmpId());
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setDomain(employee.getDomain());
        dto.setGrade(employee.getGrade());
        dto.setSectorName(employee.getSector().getName());
        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdatedAt(employee.getUpdatedAt()); // Fixed: was getUpdatetAt()
        return dto;
    }
}