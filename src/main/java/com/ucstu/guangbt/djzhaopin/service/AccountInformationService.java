package com.ucstu.guangbt.djzhaopin.service;

import java.util.Map;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.model.account.ChangePasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.ForgetPasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.LoginAccountRequest;
import com.ucstu.guangbt.djzhaopin.model.account.RegisterAccountRequest;

public interface AccountInformationService {
    public ServiceToControllerBody<AccountInformation> registerAccount(RegisterAccountRequest registerAccountRequest);

    public ServiceToControllerBody<AccountInformation> deleteAccount(UUID accountId, String verificationCode);

    public ServiceToControllerBody<Map<String, Object>> loginAccount(LoginAccountRequest loginAccountRequest);

    public ServiceToControllerBody<AccountInformation> changePassword(UUID accountId,
            ChangePasswordRequest changePasswordRequest);

    public ServiceToControllerBody<AccountInformation> forgetPassword(ForgetPasswordRequest forgetPasswordRequest);

}
