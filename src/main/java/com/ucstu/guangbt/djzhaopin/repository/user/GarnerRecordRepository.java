package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GarnerRecordRepository extends JpaRepository<GarnerRecord, UUID> {

}
