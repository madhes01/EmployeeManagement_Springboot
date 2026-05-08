package com.madhes.EmployeeManagement.dto.auth;



import com.madhes.EmployeeManagement.entity.enums.Role;

import lombok.Data;

@Data
public class RegiterRequestDTO {

    private String username;
    private String password;
    private Role role;
}
