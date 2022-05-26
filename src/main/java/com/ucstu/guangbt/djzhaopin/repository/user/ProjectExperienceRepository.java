package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.ProjectExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjectExperienceRepository extends JpaRepository<ProjectExperience, UUID>,
        JpaSpecificationExecutor<ProjectExperience> {

    Page<ProjectExperience> findByUserInformation(UserInformation userInformation, Pageable pageable);

}
