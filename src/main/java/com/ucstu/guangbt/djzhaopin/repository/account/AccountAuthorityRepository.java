package com.ucstu.guangbt.djzhaopin.repository.account;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;

public interface AccountAuthorityRepository
        extends JpaRepository<AccountAuthority, UUID>, JpaSpecificationExecutor<AccountAuthority> {

    Optional<AccountAuthority> findByAuthorityName(String authorityName);

}
