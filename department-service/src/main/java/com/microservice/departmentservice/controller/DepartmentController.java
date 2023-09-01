package com.microservice.departmentservice.controller;

import com.microservice.departmentservice.dto.DepartmentDto;
import com.microservice.departmentservice.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Tag(
        name="Department Controller",
        description="Department controller exposes REST APIs for department service"
)
@RestController
@RequestMapping("api/department")
public class DepartmentController {
@Autowired
    private DepartmentService departmentService;

    @Operation(
            summary = "SAVE Department REST API",
            description = "Save Department REST API is used to save object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping(path="save")
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 = departmentService.saveDepartmentDto(departmentDto);
        return new ResponseEntity<>(departmentDto1, HttpStatus.CREATED);
    }
    @Operation(
            summary = "GET Department REST API",
            description = "Get Department REST API is used to get object from a database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping(path="{department-code}")
    public ResponseEntity<DepartmentDto> getByDepartmentCode(@PathVariable("department-code") String departmentCode){
        DepartmentDto departmentByCode = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentByCode,HttpStatus.OK);
    }
}
