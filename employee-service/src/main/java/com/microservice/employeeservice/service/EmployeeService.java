package com.microservice.employeeservice.service;

import com.microservice.employeeservice.dto.APIResponseDto;
import com.microservice.employeeservice.dto.EmployeeDto;

public interface EmployeeService {
    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    APIResponseDto getEmployeeById(Long employeeId);
}
