package com.ucstu.guangbt.djzhaopin.repository.account;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountAuthorityRepository
        extends JpaRepository<AccountAuthority, UUID>, JpaSpecificationExecutor<AccountAuthority> {

}
