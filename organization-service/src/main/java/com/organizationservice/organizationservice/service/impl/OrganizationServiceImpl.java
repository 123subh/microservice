package com.organizationservice.organizationservice.service.impl;

import com.organizationservice.organizationservice.dto.OrganizationDto;
import com.organizationservice.organizationservice.entity.Organization;
import com.organizationservice.organizationservice.repo.OrganizationRepo;
import com.organizationservice.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService  {
    private OrganizationRepo organizationRepo;
    private ModelMapper modelMapper;
    @Override
    public OrganizationDto saveOrganisation(OrganizationDto organizationDto) {
        Organization entity = modelMapper.map(organizationDto, Organization.class);
        Organization save = organizationRepo.save(entity);
        OrganizationDto dto = modelMapper.map(save, OrganizationDto.class);
        return dto;
    }

    @Override
    public OrganizationDto getOrganisationByCode(String organizationCode) {
        Organization organizationEntity = organizationRepo.findByOrganizationCode(organizationCode);
        OrganizationDto dto = modelMapper.map(organizationEntity, OrganizationDto.class);
        return dto;
    }
}
