package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<ResponseBody<UserInformation>> updateUserInformationByUserInfoId(
            @PathVariable UUID userinfoid,
            @Valid UserInformation userInformation) {
        return ResponseBody
                .success(userInformationService.updateUserInformationByUserInfoId(userinfoid, userInformation));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<UserInformation>>> getUserInformations(
            @PageableDefault(size = 10, page = 1, sort = "userId", direction = Direction.DESC) Pageable pageable) {
        return ResponseBody.success(userInformationService.getUserInformations(pageable));
    }

    @GetMapping("/{userinfoid}")
    public ResponseEntity<ResponseBody<UserInformation>> getUserInformationByUserInfoId(
            @PathVariable UUID userinfoid) {
        return ResponseBody.success(userInformationService.getUserInformationByUserInfoId(userinfoid));
    }
}
