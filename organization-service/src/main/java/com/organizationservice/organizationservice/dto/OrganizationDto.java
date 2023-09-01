package com.organizationservice.organizationservice.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationDto {

    private Long Id;

    private String organisationName;
    private String organisationDescription;

    private String organizationCode;

    private LocalDateTime createdDate;

}
