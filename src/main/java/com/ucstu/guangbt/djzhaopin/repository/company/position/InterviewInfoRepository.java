package com.ucstu.guangbt.djzhaopin.repository.company.position;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.position.InterviewInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewInfoRepository extends JpaRepository<InterviewInfo, UUID> {

}
