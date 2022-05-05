package com.ucstu.guangbt.djzhaopin.repository.account;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountAuthority;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountAuthorityRepository extends JpaRepository<AccountAuthority, UUID> {

}
