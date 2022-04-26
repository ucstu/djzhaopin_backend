package com.ucstu.guangbt.djzhaopin.repository;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRecordRepository extends JpaRepository<DeliveryRecord, UUID> {

    // @Query("select d from DeliveryRecord d where d.companyInformationId = ?1 and
    // d.state = ?2 and d.workingYears = ?3")
    // public Page<DeliveryRecord> searchDeliveryRecord(UUID companyInformationId,
    // Integer state,
    // Integer workingYears, String sex, Integer age, UUID jobId, Date deliveryDate,
    // String search,
    // Pageable pageable);
}
