package com.ucstu.guangbt.djzhaopin.repository.hr;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInspectionRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HrInspectionRecordRepository extends JpaRepository<HrInspectionRecord, UUID> {

}
