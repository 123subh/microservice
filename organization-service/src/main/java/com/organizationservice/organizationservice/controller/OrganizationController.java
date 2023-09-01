package com.organizationservice.organizationservice.controller;

import com.organizationservice.organizationservice.dto.OrganizationDto;
import com.organizationservice.organizationservice.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
public class OrganizationController {
    private OrganizationService organizationService;
    @PostMapping(path="save")
    private ResponseEntity<OrganizationDto> saveOrg(@RequestBody OrganizationDto organizationDto){
        OrganizationDto organizationDto1 = organizationService.saveOrganisation(organizationDto);
        return new ResponseEntity<>(organizationDto1, HttpStatus.CREATED);
    }
    @GetMapping(path="{code}")
    private ResponseEntity<OrganizationDto> getOrg(@PathVariable("code") String organizationCode){
        OrganizationDto organisationByCode = organizationService.getOrganisationByCode(organizationCode);
        return ResponseEntity.ok(organisationByCode);
    }
}
