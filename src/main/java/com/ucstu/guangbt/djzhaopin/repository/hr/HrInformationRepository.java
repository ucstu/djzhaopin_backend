package com.ucstu.guangbt.djzhaopin.repository.hr;

import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HrInformationRepository extends JpaRepository<HrInformation, UUID> {

    public Optional<HrInformation> findByHrName(String hrName);

}
