package com.microservice.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrganisationDto {
    private Long Id;

    private String organisationName;
    private String organisationDescription;

    private String organizationCode;

    private LocalDateTime createdDate;
}
