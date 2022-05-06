package com.ucstu.guangbt.djzhaopin.repository.account;

import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountGroupRepository extends JpaRepository<AccountGroup, UUID> {

    Optional<AccountGroup> findByGroupName(String string);

}
