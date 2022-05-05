package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInspectionRecordRepository extends JpaRepository<UserInspectionRecord, UUID> {

    Page<UserInspectionRecord> findByUserInformation(UserInformation userInformation, Pageable pageable);

}
