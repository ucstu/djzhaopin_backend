package com.ucstu.guangbt.djzhaopin.repository.hr;

import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HrInformationRepository
        extends JpaRepository<HrInformation, UUID>, JpaSpecificationExecutor<HrInformation> {

    public Optional<HrInformation> findByHrName(String hrName);

}
