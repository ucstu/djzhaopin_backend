package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.model.account.ChangePasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.ForgetPasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.LoginAccountRequest;
import com.ucstu.guangbt.djzhaopin.model.account.RegisterAccountRequest;
import com.ucstu.guangbt.djzhaopin.repository.AccountInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.AccountInformationService;
import com.ucstu.guangbt.djzhaopin.utils.JwtUtil;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountInformationServiceImpl implements
        AccountInformationService {
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailServiceImpl userDetailsService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private AccountInformationRepository accountInformationRepository;

    @Override
    public ServiceToControllerBody<AccountInformation> registerAccount(RegisterAccountRequest registerRequest) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        if (accountInformationRepository.findByUserName(registerRequest.getUserName()).isPresent()) {
            return serviceToControllerBody.error("userName", "用户名已存在", "用户名已存在");
        }
        return serviceToControllerBody.success(accountInformationRepository.save(new AccountInformation()
                .setUserName(registerRequest.getUserName())
                .setPassword(passwordEncoder.encode(registerRequest.getPassword()))));
    }

    @Override
    public ServiceToControllerBody<AccountInformation> deleteAccount(UUID accountId, String verificationCode) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findById(accountId);
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("accountId", "用户不存在", accountId);
        }
        accountInformationRepository.delete(accountInformationOptional.get());
        return serviceToControllerBody.success(accountInformationOptional.get());
    }

    @Override
    public ServiceToControllerBody<Map<String, Object>> loginAccount(LoginAccountRequest loginAccountRequest) {
        ServiceToControllerBody<Map<String, Object>> serviceToControllerBody = new ServiceToControllerBody<>();
        Map<String, Object> responseBody = new HashMap<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginAccountRequest.getUserName(), loginAccountRequest.getPassword()));
        } catch (DisabledException e) {
            return serviceToControllerBody.error("userName", "用户名已被禁用", loginAccountRequest.getUserName());
        } catch (BadCredentialsException e) {
            return serviceToControllerBody.error("password", "密码错误", loginAccountRequest.getPassword());
        }
        UserDetails userdetails = userDetailsService.loadUserByUsername(loginAccountRequest.getUserName());
        String tokenString = jwtUtil.generateToken(userdetails);
        responseBody.put("token", tokenString);
        responseBody.put("accountInfo", accountInformationRepository
                .findByUserName(loginAccountRequest.getUserName()));
        return serviceToControllerBody.success(responseBody);
    }

    @Override
    public ServiceToControllerBody<AccountInformation> changePassword(UUID accountId,
            @Valid ChangePasswordRequest changePasswordRequest) {
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
            @Valid ForgetPasswordRequest forgetPasswordRequest) {
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
