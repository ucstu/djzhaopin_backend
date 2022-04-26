package com.ucstu.guangbt.djzhaopin.controller.account;

import java.util.Map;
import java.util.Optional;
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

@Validated
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Resource
    private AccountInformationService accountService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<AccountInformation>> registerAccount(
            @Valid @RequestBody RegisterAccountRequest registerAccountRequest) {
        Optional<AccountInformation> accountInformationOptional = accountService
                .registerAccount(registerAccountRequest);
        if (accountInformationOptional.isPresent()) {
            return ResponseBody.created(accountInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<ResponseBody<AccountInformation>> deleteAccount(
            @PathVariable("accountId") UUID accountInformationId, @RequestParam String verificationCode) {
        Optional<AccountInformation> accountInformationOptional = accountService
                .deleteAccount(accountInformationId, verificationCode);
        if (accountInformationOptional.isPresent()) {
            return ResponseBody.success(accountInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBody<Map<String, Object>>> loginAccount(
            @Valid @RequestBody LoginAccountRequest loginAccountRequest) {
        Map<String, Object> map = accountService.loginAccount(loginAccountRequest);
        if (map.get("status") != null) {
            return ResponseBody.notFound().build();
        }
        map.remove("status");
        return ResponseBody.success(map);
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<ResponseBody<AccountInformation>> changePassword(
            @PathVariable("accountId") UUID accountInformationId,
            @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        Optional<AccountInformation> accountInformationOptional = accountService
                .changePassword(accountInformationId, changePasswordRequest);
        if (accountInformationOptional.isPresent()) {
            return ResponseBody.success(accountInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @PutMapping("/forget")
    public ResponseEntity<ResponseBody<AccountInformation>> forgetPassword(
            @Valid @RequestBody ForgetPasswordRequest forgetPasswordRequest) {
        Optional<AccountInformation> accountInformationOptional = accountService
                .forgetPassword(forgetPasswordRequest);
        if (accountInformationOptional.isPresent()) {
            return ResponseBody.success(accountInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

}
