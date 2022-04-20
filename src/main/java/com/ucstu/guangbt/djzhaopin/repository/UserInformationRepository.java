package com.ucstu.guangbt.djzhaopin.repository;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepository extends JpaRepository<UserInformation, UUID> {

}
