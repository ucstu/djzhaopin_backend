package com.ucstu.guangbt.djzhaopin.service;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;

import jakarta.validation.Valid;

public interface UserInformationService {

    public UserInformation updateUserInformation(UUID userinfoid, @Valid UserInformation userInformation);

}
