package com.ucstu.guangbt.djzhaopin.repository.account;

import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.account.AccountInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountInformationRepository
        extends JpaRepository<AccountInformation, UUID>, JpaSpecificationExecutor<AccountInformation> {

    public Optional<AccountInformation> findByUserName(String userName);

}
