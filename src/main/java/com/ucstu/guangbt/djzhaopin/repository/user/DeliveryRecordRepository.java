package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.Date;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeliveryRecordRepository
        extends JpaRepository<DeliveryRecord, UUID>, JpaSpecificationExecutor<DeliveryRecord> {

    Integer countByCompanyInformationAndCreatedAtAfterAndCreatedAtBefore(
            CompanyInformation companyInformation,
            Date createdAt,
            Date nextDate);

}
