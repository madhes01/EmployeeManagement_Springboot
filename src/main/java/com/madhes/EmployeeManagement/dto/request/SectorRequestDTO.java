package com.madhes.EmployeeManagement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorRequestDTO {

    @NotBlank(message = "Sector name is required")
    private String name;

}
