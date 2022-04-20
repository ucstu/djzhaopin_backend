package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.repository.UserInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Override
    public UserInformation updateUserInformation(UUID userinfoid, UserInformation userInformation) {
        userInformation.setUserId(userinfoid);
        return userInformationRepository.save(userInformation);
    }

}
