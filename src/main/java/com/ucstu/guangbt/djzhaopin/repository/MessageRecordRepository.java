package com.ucstu.guangbt.djzhaopin.repository;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRecordRepository extends JpaRepository<MessageRecord, UUID> {

}
