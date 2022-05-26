package com.ucstu.guangbt.djzhaopin.repository.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserInformationRepository extends JpaRepository<UserInformation, UUID>,
        JpaSpecificationExecutor<UserInformation> {

}
