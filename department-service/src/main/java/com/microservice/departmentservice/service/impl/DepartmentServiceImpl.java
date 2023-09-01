package com.microservice.departmentservice.service.impl;

import com.microservice.departmentservice.dto.DepartmentDto;
import com.microservice.departmentservice.entity.Department;
import com.microservice.departmentservice.repo.DepartmentRepo;
import com.microservice.departmentservice.service.DepartmentService;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepo departmentRepo;
    private ModelMapper modelMapper;
//    public DepartmentServiceImpl(DepartmentRepo departmentRepo,ModelMapper modelMapper) {
//        this.departmentRepo = departmentRepo;
//        this.modelMapper=modelMapper;
//    }

    @Override
    public DepartmentDto saveDepartmentDto(DepartmentDto departmentDto) {
//        Department entity = new Department(
//                departmentDto.getId();
//                departmentDto.getDepartmentName(),
//        departmentDto.getDepartmentDescription(),
//        departmentDto.getDepartmentCode()
//        );
        Department entity = modelMapper.map(departmentDto, Department.class);
        Department savedEntity= departmentRepo.save(entity);
        DepartmentDto dto = modelMapper.map(entity, DepartmentDto.class);
//        DepartmentDto dto = new DepartmentDto(
//                savedEntity.getId(),
//        savedEntity.getDepartmentName(),
//        savedEntity.getDepartmentDescription(),
//        savedEntity.getDepartmentCode()
//        );
        return dto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String departmentCode) {
        Department byDepartmentCode = departmentRepo.findByDepartmentCode(departmentCode);
        DepartmentDto dto = new DepartmentDto(
                byDepartmentCode.getId(),
                byDepartmentCode.getDepartmentName(),
                byDepartmentCode.getDepartmentDescription(),
                byDepartmentCode.getDepartmentCode()
        );
        return dto;
    }
}
