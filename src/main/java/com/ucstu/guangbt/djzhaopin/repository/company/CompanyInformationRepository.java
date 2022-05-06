package com.ucstu.guangbt.djzhaopin.repository.company;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyInformationRepository
        extends JpaRepository<CompanyInformation, UUID>, JpaSpecificationExecutor<CompanyInformation> {

}
