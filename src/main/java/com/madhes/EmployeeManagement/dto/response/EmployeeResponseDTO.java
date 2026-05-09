package com.madhes.EmployeeManagement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {

    private Long id;
    private String empId;
    private String name;
    private String email;
    private String domain;
    private String grade;
    private String sectorName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt; // Fixed: was updated_at / updatetAt
}