package com.microservice.departmentservice.service;

import com.microservice.departmentservice.dto.DepartmentDto;

public interface DepartmentService {

    DepartmentDto saveDepartmentDto(DepartmentDto departmentDto);
    DepartmentDto getDepartmentByCode(String departmentCode);
}
