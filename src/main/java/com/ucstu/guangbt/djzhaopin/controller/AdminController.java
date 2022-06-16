package com.ucstu.guangbt.djzhaopin.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;
import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.AdminService;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/accounts")
    public ResponseEntity<ResponseBody<AccountInformation>> createAccount(
            @Valid @RequestBody AccountInformation accountInformation) {
        return ResponseBody.handle(adminService.createAccount(accountInformation));
    }

    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<ResponseBody<AccountInformation>> deleteAccountByAccountId(
            @PathVariable("accountId") @NotNull UUID accountId) {
        return ResponseBody.handle(adminService.deleteAccountByAccountId(accountId));
    }

    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<ResponseBody<AccountInformation>> updateAccountByAccountId(
            @PathVariable("accountId") @NotNull UUID accountId,
            @Valid @RequestBody AccountInformation accountInformation) {
        return ResponseBody.handle(adminService.updateAccountByAccountId(accountId,
                accountInformation));
    }

    @GetMapping("/accounts")
    public ResponseEntity<ResponseBody<List<AccountInformation>>> getAccounts(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(adminService.getAccounts(pageable));
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<ResponseBody<AccountInformation>> getAccountByAccountId(
            @PathVariable("accountId") @NotNull UUID accountId) {
        return ResponseBody.handle(adminService.getAccountByAccountId(accountId));
    }

    @PostMapping("/accountgroups")
    public ResponseEntity<ResponseBody<AccountGroup>> createAccountGroup(
            @Valid @RequestBody AccountInformation accountInformation) {
        return ResponseBody.handle(adminService.createAccountGroup(accountInformation));
    }

    @DeleteMapping("/accountgroups/{accountgroupid}")
    public ResponseEntity<ResponseBody<AccountGroup>> deleteAccountGroupByAccountGroupId(
            @PathVariable("accountgroupid") @NotNull UUID accountGroupId) {
        return ResponseBody.handle(adminService.deleteAccountGroupByAccountGroupId(accountGroupId));
    }

    @PutMapping("/accountgroups/{accountgroupid}")
    public ResponseEntity<ResponseBody<AccountGroup>> updateAccountGroupByAccountGroupId(
            @PathVariable("accountgroupid") @NotNull UUID accountGroupId,
            @Valid @RequestBody AccountInformation accountInformation) {
        return ResponseBody.handle(adminService.updateAccountGroupByAccountGroupId(accountGroupId,
                accountInformation));
    }

    @GetMapping("/accountgroups")
    public ResponseEntity<ResponseBody<List<AccountGroup>>> getAccountGroups(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(adminService.getAccountGroups(pageable));
    }

    @GetMapping("/accountgroups/{accountgroupid}")
    public ResponseEntity<ResponseBody<AccountGroup>> getAccountGroupByAccountGroupId(
            @PathVariable("accountgroupid") @NotNull UUID accountGroupId) {
        return ResponseBody.handle(adminService.getAccountGroupByAccountGroupId(accountGroupId));
    }

    @PostMapping("/accountauthorities")
    public ResponseEntity<ResponseBody<AccountAuthority>> createAccountAuthority(
            @Valid @RequestBody AccountInformation accountInformation) {
        return ResponseBody.handle(adminService.createAccountAuthority(accountInformation));
    }

    @DeleteMapping("/accountauthorities/{accountauthorityid}")
    public ResponseEntity<ResponseBody<AccountAuthority>> deleteAccountAuthorityByAccountAuthorityId(
            @PathVariable("accountauthorityid") @NotNull UUID accountAuthorityId) {
        return ResponseBody.handle(adminService.deleteAccountAuthorityByAccountAuthorityId(accountAuthorityId));
    }

    @PutMapping("/accountauthorities/{accountauthorityid}")
    public ResponseEntity<ResponseBody<AccountAuthority>> updateAccountAuthorityByAccountAuthorityId(
            @PathVariable("accountauthorityid") @NotNull UUID accountAuthorityId,
            @Valid @RequestBody AccountInformation accountInformation) {
        return ResponseBody.handle(adminService.updateAccountAuthorityByAccountAuthorityId(accountAuthorityId,
                accountInformation));
    }

    @GetMapping("/accountauthorities")
    public ResponseEntity<ResponseBody<List<AccountAuthority>>> getAccountAuthorities(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(adminService.getAccountAuthorities(pageable));
    }

    @GetMapping("/accountauthorities/{accountauthorityid}")
    public ResponseEntity<ResponseBody<AccountAuthority>> getAccountAuthorityByAccountAuthorityId(
            @PathVariable("accountauthorityid") @NotNull UUID accountAuthorityId) {
        return ResponseBody.handle(adminService.getAccountAuthorityByAccountAuthorityId(accountAuthorityId));
    }
}
