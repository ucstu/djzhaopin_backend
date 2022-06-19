package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.repository.account.AccountAuthorityRepository;
import com.ucstu.guangbt.djzhaopin.repository.account.AccountGroupRepository;
import com.ucstu.guangbt.djzhaopin.repository.account.AccountInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.company.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.company.position.PositionInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.AdminService;

import jakarta.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AccountInformationRepository accountInformationRepository;

    @Resource
    private AccountGroupRepository accountGroupRepository;

    @Resource
    private AccountAuthorityRepository accountAuthorityRepository;

    @Resource
    private CompanyInformationRepository companyInformationRepository;

    @Resource
    private PositionInformationRepository positionInformationRepository;

    @Override
    public ServiceToControllerBody<AccountInformation> createAccount(AccountInformation accountInformation) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        if (accountInformationRepository.findByUserName(accountInformation.getUserName()).isPresent()) {
            return serviceToControllerBody.error("userName", "用户名已存在", accountInformation.getUserName());
        }
        return serviceToControllerBody.created(accountInformationRepository.save(accountInformation));
    }

    @Override
    public ServiceToControllerBody<AccountInformation> deleteAccountByAccountId(UUID accountId) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findById(accountId);
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("accountId", "用户不存在", accountId);
        }
        accountInformationRepository.deleteById(accountId);
        return serviceToControllerBody.success(accountInformationOptional.get());
    }

    @Override
    public ServiceToControllerBody<AccountInformation> updateAccountByAccountId(UUID accountId,
            AccountInformation accountInformation) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findById(accountId);
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("accountId", "用户不存在", accountId);
        }
        accountInformation.setAccountInformationId(accountId);
        return serviceToControllerBody.success(accountInformationRepository.save(accountInformation));
    }

    @Override
    public ServiceToControllerBody<PageResult<AccountInformation>> getAccounts(Pageable pageable) {
        ServiceToControllerBody<PageResult<AccountInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        PageResult<AccountInformation> pageResult = new PageResult<>();
        Page<AccountInformation> page = accountInformationRepository.findAll(pageable);
        pageResult.setContentsName("accountInformations");
        pageResult.setTotalCount(page.getTotalElements());
        pageResult.setContents(page.getContent());
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<AccountInformation> getAccountByAccountId(UUID accountId) {
        ServiceToControllerBody<AccountInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountInformation> accountInformationOptional = accountInformationRepository.findById(accountId);
        if (!accountInformationOptional.isPresent()) {
            return serviceToControllerBody.error("accountId", "用户不存在", accountId);
        }
        return serviceToControllerBody.success(accountInformationOptional.get());
    }

    @Override
    public ServiceToControllerBody<AccountGroup> createAccountGroup(AccountGroup accountGroup) {
        ServiceToControllerBody<AccountGroup> serviceToControllerBody = new ServiceToControllerBody<>();
        if (accountGroupRepository.findByGroupName(accountGroup.getGroupName()).isPresent()) {
            return serviceToControllerBody.error("groupName", "用户组名已存在", accountGroup.getGroupName());
        }
        return serviceToControllerBody.created(accountGroupRepository.save(accountGroup));
    }

    @Override
    public ServiceToControllerBody<AccountGroup> deleteAccountGroupByAccountGroupId(UUID accountGroupId) {
        ServiceToControllerBody<AccountGroup> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountGroup> accountGroupOptional = accountGroupRepository.findById(accountGroupId);
        if (!accountGroupOptional.isPresent()) {
            return serviceToControllerBody.error("accountGroupId", "用户组不存在", accountGroupId);
        }
        accountGroupRepository.deleteById(accountGroupId);
        return serviceToControllerBody.success(accountGroupOptional.get());
    }

    @Override
    public ServiceToControllerBody<AccountGroup> updateAccountGroupByAccountGroupId(UUID accountGroupId,
            AccountGroup accountGroup) {
        ServiceToControllerBody<AccountGroup> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountGroup> accountGroupOptional = accountGroupRepository.findById(accountGroupId);
        if (!accountGroupOptional.isPresent()) {
            return serviceToControllerBody.error("accountGroupId", "用户组不存在", accountGroupId);
        }
        accountGroup.setGroupId(accountGroupId);
        return serviceToControllerBody.success(accountGroupRepository.save(accountGroup));
    }

    @Override
    public ServiceToControllerBody<PageResult<AccountGroup>> getAccountGroups(Pageable pageable) {
        ServiceToControllerBody<PageResult<AccountGroup>> serviceToControllerBody = new ServiceToControllerBody<>();
        PageResult<AccountGroup> pageResult = new PageResult<>();
        Page<AccountGroup> page = accountGroupRepository.findAll(pageable);
        pageResult.setContentsName("accountGroups");
        pageResult.setTotalCount(page.getTotalElements());
        pageResult.setContents(page.getContent());
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<AccountGroup> getAccountGroupByAccountGroupId(UUID accountGroupId) {
        ServiceToControllerBody<AccountGroup> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountGroup> accountGroupOptional = accountGroupRepository.findById(accountGroupId);
        if (!accountGroupOptional.isPresent()) {
            return serviceToControllerBody.error("accountGroupId", "用户组不存在", accountGroupId);
        }
        return serviceToControllerBody.success(accountGroupOptional.get());
    }

    @Override
    public ServiceToControllerBody<AccountAuthority> createAccountAuthority(AccountAuthority accountAuthority) {
        ServiceToControllerBody<AccountAuthority> serviceToControllerBody = new ServiceToControllerBody<>();
        if (accountAuthorityRepository.findByAuthorityName(accountAuthority.getAuthorityName()).isPresent()) {
            return serviceToControllerBody.error("authorityName", "权限名已存在", accountAuthority.getAuthorityName());
        }
        return serviceToControllerBody.created(accountAuthorityRepository.save(accountAuthority));
    }

    @Override
    public ServiceToControllerBody<AccountAuthority> deleteAccountAuthorityByAccountAuthorityId(
            UUID accountAuthorityId) {
        ServiceToControllerBody<AccountAuthority> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountAuthority> accountAuthorityOptional = accountAuthorityRepository.findById(accountAuthorityId);
        if (!accountAuthorityOptional.isPresent()) {
            return serviceToControllerBody.error("accountAuthorityId", "权限不存在", accountAuthorityId);
        }
        accountAuthorityRepository.deleteById(accountAuthorityId);
        return serviceToControllerBody.success(accountAuthorityOptional.get());
    }

    @Override
    public ServiceToControllerBody<AccountAuthority> updateAccountAuthorityByAccountAuthorityId(UUID accountAuthorityId,
            AccountAuthority accountAuthority) {
        ServiceToControllerBody<AccountAuthority> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountAuthority> accountAuthorityOptional = accountAuthorityRepository.findById(accountAuthorityId);
        if (!accountAuthorityOptional.isPresent()) {
            return serviceToControllerBody.error("accountAuthorityId", "权限不存在", accountAuthorityId);
        }
        accountAuthority.setAuthorityId(accountAuthorityId);
        return serviceToControllerBody.success(accountAuthorityRepository.save(accountAuthority));
    }

    @Override
    public ServiceToControllerBody<PageResult<AccountAuthority>> getAccountAuthorities(Pageable pageable) {
        ServiceToControllerBody<PageResult<AccountAuthority>> serviceToControllerBody = new ServiceToControllerBody<>();
        PageResult<AccountAuthority> pageResult = new PageResult<>();
        Page<AccountAuthority> page = accountAuthorityRepository.findAll(pageable);
        pageResult.setContentsName("accountAuthorities");
        pageResult.setTotalCount(page.getTotalElements());
        pageResult.setContents(page.getContent());
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<AccountAuthority> getAccountAuthorityByAccountAuthorityId(UUID accountAuthorityId) {
        ServiceToControllerBody<AccountAuthority> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<AccountAuthority> accountAuthorityOptional = accountAuthorityRepository.findById(accountAuthorityId);
        if (!accountAuthorityOptional.isPresent()) {
            return serviceToControllerBody.error("accountAuthorityId", "权限不存在", accountAuthorityId);
        }
        return serviceToControllerBody.success(accountAuthorityOptional.get());
    }

}
