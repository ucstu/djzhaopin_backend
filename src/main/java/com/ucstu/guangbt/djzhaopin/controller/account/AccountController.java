package com.ucstu.guangbt.djzhaopin.controller.account;

import java.util.Map;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.model.account.ChangePasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.ForgetPasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.LoginAccountRequest;
import com.ucstu.guangbt.djzhaopin.model.account.RegisterAccountRequest;
import com.ucstu.guangbt.djzhaopin.service.AccountInformationService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/accountInfos")
public class AccountController {

    @Resource
    private AccountInformationService accountService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<AccountInformation>> registerAccount(
            @Valid @RequestBody RegisterAccountRequest registerAccountRequest) {
        return ResponseBody.handle(accountService
                .registerAccount(registerAccountRequest));
    }

    @DeleteMapping("/{accountInfoId}")
    public ResponseEntity<ResponseBody<AccountInformation>> deleteAccount(
            @PathVariable("accountInfoId") @NotNull UUID accountInformationId,
            @RequestParam("verificationCode") @NotBlank String verificationCode) {
        return ResponseBody.handle(accountService
                .deleteAccount(accountInformationId, verificationCode));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBody<Map<String, Object>>> loginAccount(
            @Valid @RequestBody LoginAccountRequest loginAccountRequest) {
        return ResponseBody.handle(accountService
                .loginAccount(loginAccountRequest));
    }

    @PutMapping("/{accountInfoId}")
    public ResponseEntity<ResponseBody<AccountInformation>> changePassword(
            @PathVariable("accountInfoId") @NotNull UUID accountInformationId,
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseBody.handle(accountService
                .changePassword(accountInformationId, changePasswordRequest));
    }

    @PutMapping("/forget")
    public ResponseEntity<ResponseBody<AccountInformation>> forgetPassword(
            @Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        return ResponseBody.handle(accountService
                .forgetPassword(forgetPasswordRequest));
    }

}
