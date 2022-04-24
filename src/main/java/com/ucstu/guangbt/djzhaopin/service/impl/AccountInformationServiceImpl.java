package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.model.account.ChangePasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.ForgetPasswordRequest;
import com.ucstu.guangbt.djzhaopin.model.account.LoginAccountRequest;
import com.ucstu.guangbt.djzhaopin.model.account.RegisterAccountRequest;
import com.ucstu.guangbt.djzhaopin.repository.AccountInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.AccountInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountInformationServiceImpl implements AccountInformationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountInformationRepository accountInformationRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AccountInformation registerAccount(RegisterAccountRequest registerRequest) {
        AccountInformation accountInformation = new AccountInformation();
        accountInformation.setUserName(registerRequest.getUserName());
        accountInformation.setAccountType(registerRequest.getAccountType());
        accountInformation.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        if (accountInformation.getAccountType() == 1) {
            accountInformation.setUserInformation(new UserInformation());
        } else if (accountInformation.getAccountType() == 2) {
            accountInformation.setHrInformation(new HrInformation());
        }
        AccountInformation savedAccountInformation = accountInformationRepository.save(accountInformation);
        return savedAccountInformation;
    }

    @Override
    public AccountInformation deleteAccount(UUID accountId, String verificationCode) {
        Optional<AccountInformation> accountInformation = accountInformationRepository.findById(accountId);
        accountInformationRepository.deleteById(accountId);
        return accountInformation.get();
    }

    @Override
    public Map<String, Object> loginAccount(LoginAccountRequest loginAccountRequest) {
        Map<String, Object> responseBody = new HashMap<>();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginAccountRequest.getUserName(),
                loginAccountRequest.getPassword());
        authenticationManager.authenticate(token);
        AccountInformation accountInformation = accountInformationRepository
                .findByUserName(loginAccountRequest.getUserName());
        String tokenString = Jwts.builder().setSubject(accountInformation.getAccountInformationId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512, "djzhaopin123456").compact();
        responseBody.put("token", tokenString);
        responseBody.put("accountInfo", accountInformation);
        return responseBody;
    }

    @Override
    public AccountInformation changePassword(UUID accountId, @Valid ChangePasswordRequest changePasswordRequest) {
        AccountInformation accountInformation = accountInformationRepository.findById(accountId).get();
        accountInformation.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
        return accountInformationRepository.save(accountInformation);
    }

    @Override
    public AccountInformation forgetPassword(@Valid ForgetPasswordRequest forgetPasswordRequest) {
        AccountInformation accountInformation = accountInformationRepository
                .findByUserName(forgetPasswordRequest.getUserName());
        accountInformation.setPassword(passwordEncoder.encode(forgetPasswordRequest.getPassword()));
        return accountInformation;
    }

}
