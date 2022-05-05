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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public ServiceToControllerBody<AccountInformation> registerAccount(RegisterAccountRequest registerRequest) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        if (accountInformationRepository.findByUserName(registerRequest.getUserName()).isPresent()) {
            return serviceToControllerBody.error("userName", "用户名已存在", registerRequest.getUserName());
        }
        Optional<String> verificationCodeOptional = Optional.ofNullable(verificationCodeTemplate.opsForValue()
                .get(registerRequest.getUserName()));
        if (!verificationCodeOptional.isPresent()) {
            return serviceToControllerBody.error("verificationCode", "验证码已过期或未发送",
                    registerRequest.getVerificationCode());
        }
        if (!registerRequest.getVerificationCode().equals(verificationCodeOptional.get())) {
            return serviceToControllerBody.error("verificationCode", "验证码错误", registerRequest.getVerificationCode());
        }
        verificationCodeTemplate.delete(registerRequest.getUserName());
        if (registerRequest.getAccountType() == 1) {
            return serviceToControllerBody.created(accountInformationRepository.save(new AccountInformation()
                    .setUserName(registerRequest.getUserName())
                    .setPassword(passwordEncoder.encode(registerRequest.getPassword()))
                    .setAccountType(registerRequest.getAccountType())
                    .setUserInformation(new UserInformation().setEmail(registerRequest.getUserName()).setAvatarUrl(
                            "/image/heard1.jpg"))));
        }
        if (registerRequest.getAccountType() == 2) {
            return serviceToControllerBody.created(accountInformationRepository.save(new AccountInformation()
                    .setUserName(registerRequest.getUserName())
                    .setPassword(passwordEncoder.encode(registerRequest.getPassword()))
                    .setAccountType(registerRequest.getAccountType())
                    .setHrInformation(new HrInformation().setAcceptEmail(registerRequest.getUserName()).setAvatarUrl(
                            "/image/heard2.jpg"))));
        }
        return serviceToControllerBody.error("accountType", "账户类型错误", registerRequest.getAccountType());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<AccountInformation> deleteAccount(UUID accountId, String verificationCode) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findById(accountId);
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("accountId", "用户不存在", accountId);
        }
        Optional<String> verificationCodeOptional = Optional.ofNullable(verificationCodeTemplate.opsForValue()
                .get(accountInformationOptional.get().getUserName()));
        if (!verificationCodeOptional.isPresent()) {
            return serviceToControllerBody.error("verificationCode", "验证码已过期或未发送",
                    verificationCode);
        }
        if (!verificationCode.equals(verificationCodeOptional.get())) {
            return serviceToControllerBody.error("verificationCode", "验证码错误", verificationCode);
        }
        verificationCodeTemplate.delete(accountInformationOptional.get().getUserName());
        accountInformationRepository.delete(accountInformationOptional.get());
        return serviceToControllerBody.success(accountInformationOptional.get());
    }

    @Override
    public ServiceToControllerBody<Map<String, Object>> loginAccount(LoginAccountRequest loginAccountRequest) {
        ServiceToControllerBody<Map<String, Object>> serviceToControllerBody = new ServiceToControllerBody<>();
        Map<String, Object> responseBody = new HashMap<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository
                .findByUserName(loginAccountRequest.getUserName());
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userName", "用户不存在", loginAccountRequest.getUserName());
        }
        if (!passwordEncoder.matches(loginAccountRequest.getPassword(),
                accountInformationOptional.get().getPassword())) {
            return serviceToControllerBody.error("password", "密码错误", loginAccountRequest.getPassword());
        }
        responseBody.put("token", jwtUtil.generateToken(accountInformationOptional.get()));
        responseBody.put("accountInformation", accountInformationOptional.get());
        return serviceToControllerBody.success(responseBody);
    }

    @Override
    @Transactional
    public ServiceToControllerBody<AccountInformation> changePassword(UUID accountId,
            ChangePasswordRequest changePasswordRequest) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findById(accountId);
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("accountId", "用户不存在", accountId);
        }
        Optional<String> verificationCodeOptional = Optional.ofNullable(verificationCodeTemplate.opsForValue()
                .get(accountInformationOptional.get().getUserName()));
        if (!verificationCodeOptional.isPresent()) {
            return serviceToControllerBody.error("verificationCode", "验证码已过期或未发送",
                    changePasswordRequest.getVerificationCode());
        }
        if (!changePasswordRequest.getVerificationCode().equals(verificationCodeOptional.get())) {
            return serviceToControllerBody.error("verificationCode", "验证码错误",
                    changePasswordRequest.getVerificationCode());
        }
        verificationCodeTemplate.delete(accountInformationOptional.get().getUserName());
        accountInformationOptional.get().setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
        return serviceToControllerBody.success(accountInformationRepository.save(accountInformationOptional.get()));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<AccountInformation> forgetPassword(
            ForgetPasswordRequest forgetPasswordRequest) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository
                .findByUserName(forgetPasswordRequest.getUserName());
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userName", "用户不存在", forgetPasswordRequest.getUserName());
        }
        Optional<String> verificationCodeOptional = Optional.ofNullable(verificationCodeTemplate.opsForValue()
                .get(forgetPasswordRequest.getUserName()));
        if (!verificationCodeOptional.isPresent()) {
            return serviceToControllerBody.error("verificationCode", "验证码已过期或未发送",
                    forgetPasswordRequest.getVerificationCode());
        }
        if (!forgetPasswordRequest.getVerificationCode().equals(verificationCodeOptional.get())) {
            return serviceToControllerBody.error("verificationCode", "验证码错误",
                    forgetPasswordRequest.getVerificationCode());
        }
        verificationCodeTemplate.delete(accountInformationOptional.get().getUserName());
        accountInformationOptional.get().setPassword(passwordEncoder.encode(forgetPasswordRequest.getPassword()));
        return serviceToControllerBody.success(accountInformationRepository.save(accountInformationOptional.get()));
    }

}
