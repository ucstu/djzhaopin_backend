package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttentionRecordRepository extends JpaRepository<AttentionRecord, UUID> {

    Page<AttentionRecord> findByUserInformation(UserInformation userInformation, Pageable pageable);

}
