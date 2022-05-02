package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.model.account.ChangePasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.ForgetPasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.LoginAccountRequest;
import com.ucstu.guangbt.djzhaopin.model.account.RegisterAccountRequest;
import com.ucstu.guangbt.djzhaopin.repository.AccountInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.AccountInformationService;
import com.ucstu.guangbt.djzhaopin.utils.JsonWebTokenUtil;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class AccountInformationServiceImpl implements
        AccountInformationService {

    @Resource
    private JsonWebTokenUtil jwtUtil;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RedisTemplate<String, String> verificationCodeTemplate;

    @Resource
    private AccountInformationRepository accountInformationRepository;

    @Override
    public ServiceToControllerBody<AccountInformation> registerAccount(RegisterAccountRequest registerRequest) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        if (accountInformationRepository.findByUserName(registerRequest.getUserName()).isPresent()) {
            return serviceToControllerBody.error("userName", "用户名已存在", registerRequest.getUserName());
        }
        String verificationCode = verificationCodeTemplate.opsForValue().get(registerRequest.getUserName());
        if (verificationCode == null) {
            return serviceToControllerBody.error("verificationCode", "验证码已过期或未发送",
                    registerRequest.getVerificationCode());
        }
        if (!registerRequest.getVerificationCode().equals(verificationCode)) {
            return serviceToControllerBody.error("verificationCode", "验证码错误", registerRequest.getVerificationCode());
        }
        if (registerRequest.getAccountType() == 1) {
            verificationCodeTemplate.delete(registerRequest.getUserName());
            return serviceToControllerBody.created(accountInformationRepository.save(new AccountInformation()
                    .setUserName(registerRequest.getUserName())
                    .setPassword(passwordEncoder.encode(registerRequest.getPassword()))
                    .setAccountType(registerRequest.getAccountType())
                    .setUserInformation(
                            new UserInformation().setEmail(registerRequest.getUserName()).setAvatarUrl(
                                    "/image/heard2.jpg"))));
        } else {
            return serviceToControllerBody.created(accountInformationRepository.save(new AccountInformation()
                    .setUserName(registerRequest.getUserName())
                    .setPassword(passwordEncoder.encode(registerRequest.getPassword()))
                    .setAccountType(registerRequest.getAccountType())
                    .setHrInformation(new HrInformation().setAcceptEmail(registerRequest.getUserName()).setAvatarUrl(
                            "/image/heard1.jpg"))));
        }
    }

    @Override
    public ServiceToControllerBody<AccountInformation> deleteAccount(UUID accountId, String verificationCode) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findById(accountId);
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("accountId", "用户不存在", accountId);
        }
        String verificationCodeInRedis = verificationCodeTemplate.opsForValue()
                .get(accountInformationOptional.get().getUserName());
        if (verificationCodeInRedis != null) {
            return serviceToControllerBody.error("verificationCode", "验证码已过期或未发送",
                    verificationCode);
        }
        if (!verificationCode.equals(verificationCodeInRedis)) {
            return serviceToControllerBody.error("verificationCode", "验证码错误", verificationCode);
        }
        accountInformationRepository.delete(accountInformationOptional.get());
        return serviceToControllerBody.success(accountInformationOptional.get());
    }

    @Override
    public ServiceToControllerBody<Map<String, Object>> loginAccount(LoginAccountRequest loginAccountRequest) {
        ServiceToControllerBody<Map<String, Object>> serviceToControllerBody = new ServiceToControllerBody<>();
        Map<String, Object> responseBody = new HashMap<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository
                .findByUserName(loginAccountRequest.getUserName());
        if (accountInformationOptional.isPresent()) {
            if (passwordEncoder.matches(loginAccountRequest.getPassword(),
                    accountInformationOptional.get().getPassword())) {
                responseBody.put("token", jwtUtil.generateToken(accountInformationOptional.get()));
                responseBody.put("accountInfo", accountInformationOptional.get());
                return serviceToControllerBody.success(responseBody);
            } else {
                return serviceToControllerBody.error("password", "密码错误", loginAccountRequest.getPassword());
            }
        } else {
            return serviceToControllerBody.error("userName", "用户不存在", loginAccountRequest.getUserName());
        }
    }

    @Override
    public ServiceToControllerBody<AccountInformation> changePassword(UUID accountId,
            ChangePasswordRequest changePasswordRequest) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformation = accountInformationRepository.findById(accountId);
        if (accountInformation.isPresent()) {
            AccountInformation account = accountInformation.get();
            account.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
            return serviceToControllerBody.success(accountInformationRepository.save(account));
        }
        return serviceToControllerBody.error("accountId", "用户不存在", accountId);
    }

    @Override
    public ServiceToControllerBody<AccountInformation> forgetPassword(
            ForgetPasswordRequest forgetPasswordRequest) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformation = accountInformationRepository
                .findByUserName(forgetPasswordRequest.getUserName());
        if (accountInformation.isPresent()) {
            AccountInformation account = accountInformation.get();
            account.setPassword(passwordEncoder.encode(forgetPasswordRequest.getPassword()));
            return serviceToControllerBody.success(accountInformationRepository.save(account));
        }
        return serviceToControllerBody.error("userName", "用户不存在", forgetPasswordRequest.getUserName());
    }

}
