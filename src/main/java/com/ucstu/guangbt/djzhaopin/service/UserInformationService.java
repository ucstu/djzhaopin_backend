package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.JobExpectation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.springframework.data.domain.Pageable;

import jakarta.validation.Valid;

public interface UserInformationService {

    public UserInformation updateUserInformationByUserInfoId(UUID userinfoid, UserInformation userInformation);

    public List<UserInformation> getUserInformations(Pageable pageable);

    public UserInformation getUserInformationByUserInfoId(UUID userinfoid);

    public JobExpectation createJobExpectation(UUID userinfoid, @Valid JobExpectation jobExpectation);

    public JobExpectation deleteJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid);

    public JobExpectation updateJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid,
            JobExpectation jobExpectation);

    public List<JobExpectation> getJobExpectations(UUID userinfoid);

    public JobExpectation getJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid);

}
