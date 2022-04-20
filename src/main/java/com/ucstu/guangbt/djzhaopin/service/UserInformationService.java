package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import org.springframework.data.domain.Pageable;

public interface UserInformationService {

    public UserInformation updateUserInformationByUserInfoId(UUID userinfoid, UserInformation userInformation);

    public List<UserInformation> getUserInformations(Pageable pageable);

    public UserInformation getUserInformationByUserInfoId(UUID userinfoid);

}
