package com.ucstu.guangbt.djzhaopin.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;

public interface AdminService {

    ServiceToControllerBody<AccountInformation> createAccount(AccountInformation accountInformation);

    ServiceToControllerBody<AccountInformation> deleteAccountByAccountId(UUID accountId);

    ServiceToControllerBody<AccountInformation> updateAccountByAccountId(UUID accountId,
            AccountInformation accountInformation);

    ServiceToControllerBody<PageResult<AccountInformation>> getAccounts(Pageable pageable);

    ServiceToControllerBody<AccountInformation> getAccountByAccountId(UUID accountId);

    ServiceToControllerBody<AccountGroup> createAccountGroup(AccountGroup accountGroup);

    ServiceToControllerBody<AccountGroup> deleteAccountGroupByAccountGroupId(UUID accountGroupId);

    ServiceToControllerBody<AccountGroup> updateAccountGroupByAccountGroupId(UUID accountGroupId,
            AccountGroup accountGroup);

    ServiceToControllerBody<PageResult<AccountGroup>> getAccountGroups(Pageable pageable);

    ServiceToControllerBody<AccountGroup> getAccountGroupByAccountGroupId(UUID accountGroupId);

    ServiceToControllerBody<AccountAuthority> createAccountAuthority(AccountAuthority accountAuthority);

    ServiceToControllerBody<AccountAuthority> deleteAccountAuthorityByAccountAuthorityId(UUID accountAuthorityId);

    ServiceToControllerBody<AccountAuthority> updateAccountAuthorityByAccountAuthorityId(
            UUID accountAuthorityId, AccountAuthority accountAuthority);

    ServiceToControllerBody<PageResult<AccountAuthority>> getAccountAuthorities(Pageable pageable);

    ServiceToControllerBody<AccountAuthority> getAccountAuthorityByAccountAuthorityId(UUID accountAuthorityId);

}
