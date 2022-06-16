package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;

public interface AdminService {

    ServiceToControllerBody<AccountInformation> createAccount(AccountInformation accountInformation);

    ServiceToControllerBody<AccountInformation> deleteAccountByAccountId(UUID accountId);

    ServiceToControllerBody<AccountInformation> updateAccountByAccountId(UUID accountId,
            AccountInformation accountInformation);

    ServiceToControllerBody<List<AccountInformation>> getAccounts(Pageable pageable);

    ServiceToControllerBody<AccountInformation> getAccountByAccountId(UUID accountId);

    ServiceToControllerBody<AccountGroup> createAccountGroup(AccountInformation accountInformation);

    ServiceToControllerBody<AccountGroup> deleteAccountGroupByAccountGroupId(UUID accountGroupId);

    ServiceToControllerBody<AccountGroup> updateAccountGroupByAccountGroupId(UUID accountGroupId,
            AccountInformation accountInformation);

    ServiceToControllerBody<List<AccountGroup>> getAccountGroups(Pageable pageable);

    ServiceToControllerBody<AccountGroup> getAccountGroupByAccountGroupId(UUID accountGroupId);

    ServiceToControllerBody<AccountAuthority> createAccountAuthority(AccountInformation accountInformation);

    ServiceToControllerBody<AccountAuthority> deleteAccountAuthorityByAccountAuthorityId(UUID accountAuthorityId);

    ServiceToControllerBody<AccountAuthority> updateAccountAuthorityByAccountAuthorityId(
            UUID accountAuthorityId, AccountInformation accountInformation);

    ServiceToControllerBody<List<AccountAuthority>> getAccountAuthorities(Pageable pageable);

    ServiceToControllerBody<AccountAuthority> getAccountAuthorityByAccountAuthorityId(UUID accountAuthorityId);

}
