package com.madhes.EmployeeManagement.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
