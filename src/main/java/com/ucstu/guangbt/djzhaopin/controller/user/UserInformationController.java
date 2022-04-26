package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/userinfos")
public class UserInformationController {

    @Resource
    private UserInformationService userInformationService;

    @PutMapping("/{userinfoid}")
    public ResponseEntity<ResponseBody<UserInformation>> updateUserInformationByUserInfoId(
            @PathVariable("userinfoid") UUID userInformationId,
            @Valid UserInformation userInformation) {
        Optional<UserInformation> userInformationOptional = userInformationService
                .updateUserInformationByUserInfoId(userInformationId, userInformation);
        if (userInformationOptional.isPresent()) {
            return ResponseBody.success(userInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<UserInformation>>> getUserInformations(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Stream<UserInformation> userInformations = userInformationService.getUserInformations(pageable);
        if (userInformations.count() > 0) {
            return ResponseBody.success(userInformations.collect(Collectors.toList()));
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("/{userinfoid}")
    public ResponseEntity<ResponseBody<UserInformation>> getUserInformationByUserInfoId(
            @PathVariable("userinfoid") UUID userInformationId) {
        Optional<UserInformation> userInformationOptional = userInformationService
                .getUserInformationByUserInfoId(userInformationId);
        if (userInformationOptional.isPresent()) {
            return ResponseBody.success(userInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }
}
