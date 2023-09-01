package com.microservice.employeeservice.service;

import com.microservice.employeeservice.dto.APIResponseDto;
import com.microservice.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/",value="DEPARTMENT-SERVICE")
public interface APIClient {

    @GetMapping(path="api/department/{department-code}")
    DepartmentDto getByDepartmentCode(@PathVariable("department-code") String departmentCode);
}
