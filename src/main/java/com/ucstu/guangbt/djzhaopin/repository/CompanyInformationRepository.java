package com.ucstu.guangbt.djzhaopin.repository;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyInformationRepository extends JpaRepository<CompanyInformation, UUID> {

}
