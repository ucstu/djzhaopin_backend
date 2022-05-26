package com.ucstu.guangbt.djzhaopin.repository.account;

import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountGroupRepository
        extends JpaRepository<AccountGroup, UUID>, JpaSpecificationExecutor<AccountGroup> {

    Optional<AccountGroup> findByGroupName(String string);

}
