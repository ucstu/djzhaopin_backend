package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.JobExpectation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobExpectationRepository extends JpaRepository<JobExpectation, UUID> {

}
