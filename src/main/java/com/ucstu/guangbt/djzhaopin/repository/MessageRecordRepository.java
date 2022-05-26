package com.ucstu.guangbt.djzhaopin.repository;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MessageRecordRepository extends JpaRepository<MessageRecord, UUID>,
        JpaSpecificationExecutor<MessageRecord> {

    List<MessageRecord> findByServiceId(UUID serviceId);

}
