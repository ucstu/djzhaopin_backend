package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
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
    public Optional<AccountInformation> registerAccount(RegisterAccountRequest registerRequest) {
        AccountInformation accountInformation = new AccountInformation();
        Set<AccountGroup> accountGroups = new HashSet<>();
        accountGroups.add(new AccountGroup().setGroupName("USER"));
        accountInformation.setUserName(registerRequest.getUserName());
        accountInformation.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        accountInformation.setGroups(accountGroups);
        accountInformation.setAccountType(registerRequest.getAccountType());
        if (registerRequest.getAccountType() == 1) {
            UserInformation userInformation = new UserInformation();
            accountInformation.setUserInformation(userInformation);
        } else if (registerRequest.getAccountType() == 2) {
            HrInformation hrInformation = new HrInformation();
            accountInformation.setHrInformation(hrInformation);
        }
        return Optional.ofNullable(accountInformationRepository.save(accountInformation));
    }

    @Override
    public Optional<AccountInformation> deleteAccount(UUID accountId, String verificationCode) {
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findById(accountId);
        if (accountInformationOptional.isPresent()) {
            AccountInformation accountInformation = accountInformationOptional.get();
            accountInformationRepository.delete(accountInformation);
            return accountInformationOptional;
        }
        return Optional.empty();
    }

    @Override
    public Map<String, Object> loginAccount(LoginAccountRequest loginAccountRequest) {
        Map<String, Object> responseBody = new HashMap<>();
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginAccountRequest.getUserName(), loginAccountRequest.getPassword()));
        } catch (DisabledException e) {
            log.error("用户被禁用");
            responseBody.put("status", "error user disabled");
        } catch (BadCredentialsException e) {
            log.error("密码错误");
            responseBody.put("status", "error password");
        }
        UserDetails userdetails = userDetailsService.loadUserByUsername(loginAccountRequest.getUserName());
        String tokenString = jwtUtil.generateToken(userdetails);
        responseBody.put("token", tokenString);
        responseBody.put("accountInfo", accountInformationRepository
                .findByUserName(loginAccountRequest.getUserName()));
        return responseBody;
    }

    @Override
    public Optional<AccountInformation> changePassword(UUID accountId,
            @Valid ChangePasswordRequest changePasswordRequest) {
        Optional<AccountInformation> accountInformation = accountInformationRepository.findById(accountId);
        if (accountInformation.isPresent()) {
            AccountInformation account = accountInformation.get();
            account.setPassword(passwordEncoder.encode(changePasswordRequest.getPassword()));
            return Optional.of(accountInformationRepository.save(account));
        }
        return Optional.empty();
    }

    @Override
    public Optional<AccountInformation> forgetPassword(@Valid ForgetPasswordRequest forgetPasswordRequest) {
        Optional<AccountInformation> accountInformation = accountInformationRepository
                .findByUserName(forgetPasswordRequest.getUserName());
        if (accountInformation.isPresent()) {
            AccountInformation account = accountInformation.get();
            account.setPassword(passwordEncoder.encode(forgetPasswordRequest.getPassword()));
            return Optional.of(accountInformationRepository.save(account));
        }
        return Optional.empty();
    }

}
