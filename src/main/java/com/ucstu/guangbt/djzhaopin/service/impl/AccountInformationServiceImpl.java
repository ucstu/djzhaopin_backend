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
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UserDetailServiceImpl userDetailsService;

    @Resource
    private AccountInformationRepository accountInformationRepository;

    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public AccountInformation registerAccount(RegisterAccountRequest registerRequest) {
        AccountInformation accountInformation = new AccountInformation();
        Set<AccountGroup> accountGroups = new HashSet<>();
        accountGroups.add(new AccountGroup().setGroupName("USER"));
        accountInformation.setUserName(registerRequest.getUserName());
        accountInformation.setAccountType(registerRequest.getAccountType());
        accountInformation.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        accountInformation.setGroups(accountGroups);
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
        // UsernamePasswordAuthenticationToken token = new
        // UsernamePasswordAuthenticationToken(
        // loginAccountRequest.getUserName(),
        // loginAccountRequest.getPassword());
        // authenticationManager.authenticate(token);
        // AccountInformation accountInformation = accountInformationRepository
        // .findByUserName(loginAccountRequest.getUserName());
        // String tokenString =
        // Jwts.builder().setSubject(accountInformation.getAccountInformationId().toString())
        // .setExpiration(new Date(System.currentTimeMillis() + 3600000))
        // .signWith(SignatureAlgorithm.HS512, "djzhaopin123456").compact();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginAccountRequest.getUserName(), loginAccountRequest.getPassword()));
        } catch (DisabledException e) {
            log.info("用户被禁用");
            // throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.info("密码错误");
            // throw new Exception("INVALID_CREDENTIALS", e);
        }

        UserDetails userdetails = userDetailsService.loadUserByUsername(loginAccountRequest.getUserName());
        String tokenString = jwtUtil.generateToken(userdetails);
        responseBody.put("token", tokenString);
        responseBody.put("accountInfo", accountInformationRepository
                .findByUserName(loginAccountRequest.getUserName()));
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
