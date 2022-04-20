package com.ucstu.guangbt.djzhaopin.controller;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/userinfos/{userinfoid}")
public class UserInformationController {

    @Autowired
    private UserInformationService userInformationService;

    @PutMapping("/{userinfoid}")
    public ResponseEntity<ResponseBody<UserInformation>> updateUserInformation(
            @PathVariable UUID userinfoid,
            @Valid UserInformation userInformation) {
        return ResponseBody.success(userInformationService.updateUserInformation(userinfoid, userInformation));
    }
}
