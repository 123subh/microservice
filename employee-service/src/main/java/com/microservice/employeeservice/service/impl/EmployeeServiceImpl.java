package com.microservice.employeeservice.service.impl;

import com.microservice.employeeservice.dto.APIResponseDto;
import com.microservice.employeeservice.dto.DepartmentDto;
import com.microservice.employeeservice.dto.EmployeeDto;
import com.microservice.employeeservice.dto.OrganisationDto;
import com.microservice.employeeservice.entity.Employee;
import com.microservice.employeeservice.repo.EmployeeRepo;
import com.microservice.employeeservice.service.APIClient;
import com.microservice.employeeservice.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;
    private ModelMapper modelMapper;
    private WebClient webClient;
//    private RestTemplate restTemplate;
//    private APIClient apiClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
//        Employee entity = new Employee(
//                employeeDto.getId(),
//                employeeDto.getFirstName(),
//                employeeDto.getLastName(),
//                employeeDto.getEmail()
//        );
        Employee entity = modelMapper.map(employeeDto, Employee.class);
        Employee savedEntity = employeeRepo.save(entity);
//        EmployeeDto dto = new EmployeeDto(
//                savedEntity.getId(),
//                savedEntity.getFirstName(),
//                savedEntity.getLastName(),
//                savedEntity.getEmail()
//        );
        EmployeeDto dto = modelMapper.map(savedEntity, EmployeeDto.class);
        return dto;
    }
//    @CircuitBreaker(name="${spring.application.name}",fallbackMethod = "getByDepartmentCode")
    @Retry(name="${spring.application.name}",fallbackMethod = "getByDepartmentCode")
    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {
        LOGGER.info("inside getEmployeeById method");
        Optional<Employee> byId = employeeRepo.findById(employeeId);
        Employee employee = byId.get();
//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/department/" + employee.getDepartmentCode(), DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();
//        DepartmentDto departmentDto = apiClient.getByDepartmentCode(employee.getDepartmentCode());

        DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/department/" + employee.getDepartmentCode())
                .retrieve().bodyToMono(DepartmentDto.class).block();

        OrganisationDto organisationDto = webClient.get().uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                .retrieve().bodyToMono(OrganisationDto.class).block();

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        APIResponseDto apiResponseDto =new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        apiResponseDto.setOrganisationDto(organisationDto);
        return apiResponseDto;
    }
    public APIResponseDto getByDepartmentCode(Long employeeId,Exception exception) {
        LOGGER.info("inside getByDepartmentCode fallBackMethod");
        Optional<Employee> byId = employeeRepo.findById(employeeId);
        Employee employee = byId.get();

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("R&D Department");
        departmentDto.setDepartmentCode("RD001");
        departmentDto.setDepartmentDescription("Research and Development department");

        EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

        APIResponseDto apiResponseDto =new APIResponseDto();
        apiResponseDto.setEmployeeDto(employeeDto);
        apiResponseDto.setDepartmentDto(departmentDto);
        return apiResponseDto;
    }
}
