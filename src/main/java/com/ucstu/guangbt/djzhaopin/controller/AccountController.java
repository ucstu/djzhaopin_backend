package com.ucstu.guangbt.djzhaopin.controller;

import java.util.Map;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.model.account.ChangePasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.ForgetPasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.LoginAccountRequest;
import com.ucstu.guangbt.djzhaopin.model.account.RegisterAccountRequest;
import com.ucstu.guangbt.djzhaopin.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<Map<String, Object>>> registerAccount(
            @Valid @RequestBody RegisterAccountRequest registerAccountRequest) {
        return ResponseBody.created(accountService.registerAccount(registerAccountRequest));
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<ResponseBody<AccountInformation>> deleteAccount(
            @PathVariable UUID accountId, @RequestParam String verificationCode) {
        return ResponseBody.success(accountService.deleteAccount(accountId, verificationCode));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBody<Map<String, Object>>> loginAccount(
            @Valid @RequestBody LoginAccountRequest loginAccountRequest) {
        return ResponseBody.success(accountService.loginAccount(loginAccountRequest));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<ResponseBody<AccountInformation>> changePassword(
            @PathVariable UUID accountId,
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        return ResponseBody.success(accountService.changePassword(accountId, changePasswordRequest));
    }

    @PutMapping("/forget")
    public ResponseEntity<ResponseBody<AccountInformation>> forgetPassword(
            @Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        return ResponseBody.success(accountService.forgetPassword(forgetPasswordRequest));
    }

}
