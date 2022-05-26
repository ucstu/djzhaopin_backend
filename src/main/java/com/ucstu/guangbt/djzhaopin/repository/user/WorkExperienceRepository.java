package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorkExperienceRepository extends JpaRepository<WorkExperience, UUID>,
        JpaSpecificationExecutor<WorkExperience> {

    Page<WorkExperience> findByUserInformation(UserInformation userInformation, Pageable pageable);

}
