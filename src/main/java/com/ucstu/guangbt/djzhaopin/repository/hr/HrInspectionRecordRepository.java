package com.ucstu.guangbt.djzhaopin.repository.hr;

import java.util.Date;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInspectionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HrInspectionRecordRepository
        extends JpaRepository<HrInspectionRecord, UUID>, JpaSpecificationExecutor<HrInspectionRecord> {

    Page<HrInspectionRecord> findByHrInformation(HrInformation hrInformation, Pageable pageable);

    Page<HrInspectionRecord> findByUserInformationAndCreatedAtBetween(UserInformation userInformation,
            Date startDate, Date endDate, Pageable pageable);
}
