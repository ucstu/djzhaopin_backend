package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.Date;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, UUID> {

    Integer countByCompanyInformationAndCreatedAt(CompanyInformation companyInformation, Date createdAt);

}
