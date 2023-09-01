package com.microservice.departmentservice.repo;

import com.microservice.departmentservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department,Long> {
    Department findByDepartmentCode(String departmentCode);
}
