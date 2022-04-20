package com.ucstu.guangbt.djzhaopin.service;

import java.util.Map;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.account.ChangePasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.ForgetPasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.LoginAccountRequest;
import com.ucstu.guangbt.djzhaopin.model.account.RegisterAccountRequest;

import jakarta.validation.Valid;

public interface AccountService {
    public Map<String, Object> registerAccount(RegisterAccountRequest registerAccountRequest);

    public AccountInformation deleteAccount(UUID accountId, String verificationCode);

    public Map<String, Object> loginAccount(LoginAccountRequest loginAccountRequest);

    public AccountInformation changePassword(UUID accountId, @Valid ChangePasswordRequest changePasswordRequest);

    public AccountInformation forgetPassword(@Valid ForgetPasswordRequest forgetPasswordRequest);

}
