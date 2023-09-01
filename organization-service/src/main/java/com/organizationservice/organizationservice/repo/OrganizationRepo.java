package com.organizationservice.organizationservice.repo;

import com.organizationservice.organizationservice.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization,Long> {
    Organization findByOrganizationCode(String organizationCode);
}
