package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.service.AdminService;

public class AdminServiceImpl implements AdminService {

    @Override
    public ServiceToControllerBody<AccountInformation> createAccount(AccountInformation accountInformation) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();

        return null;
    }

    @Override
    public ServiceToControllerBody<AccountInformation> deleteAccountByAccountId(UUID accountId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountInformation> updateAccountByAccountId(UUID accountId,
            AccountInformation accountInformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<List<AccountInformation>> getAccounts(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountInformation> getAccountByAccountId(UUID accountId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountGroup> createAccountGroup(AccountInformation accountInformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountGroup> deleteAccountGroupByAccountGroupId(UUID accountGroupId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountGroup> updateAccountGroupByAccountGroupId(UUID accountGroupId,
            AccountInformation accountInformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<List<AccountGroup>> getAccountGroups(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountGroup> getAccountGroupByAccountGroupId(UUID accountGroupId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountAuthority> createAccountAuthority(AccountInformation accountInformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountAuthority> deleteAccountAuthorityByAccountAuthorityId(
            UUID accountAuthorityId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountAuthority> updateAccountAuthorityByAccountAuthorityId(UUID accountAuthorityId,
            AccountInformation accountInformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<List<AccountAuthority>> getAccountAuthorities(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<AccountAuthority> getAccountAuthorityByAccountAuthorityId(UUID accountAuthorityId) {
        // TODO Auto-generated method stub
        return null;
    }

}
