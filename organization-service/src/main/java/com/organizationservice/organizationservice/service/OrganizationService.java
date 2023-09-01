package com.organizationservice.organizationservice.service;

import com.organizationservice.organizationservice.dto.OrganizationDto;

public interface OrganizationService {
    OrganizationDto saveOrganisation(OrganizationDto organizationDto);

    OrganizationDto getOrganisationByCode(String organizationCode);
}
