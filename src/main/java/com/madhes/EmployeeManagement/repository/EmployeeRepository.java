package com.madhes.EmployeeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.madhes.EmployeeManagement.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
