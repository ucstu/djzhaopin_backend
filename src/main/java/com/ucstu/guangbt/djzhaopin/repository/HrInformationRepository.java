package com.ucstu.guangbt.djzhaopin.repository;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HrInformationRepository extends JpaRepository<HrInformation, UUID> {

}
