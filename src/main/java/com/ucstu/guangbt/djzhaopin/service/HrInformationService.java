package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;

import org.springframework.data.domain.Pageable;

public interface HrInformationService {
    public Optional<HrInformation> getHrInformationByHrInformationId(UUID hrInformationId);

    public Optional<List<HrInformation>> getHrInformations(Pageable pageable);

    public Optional<HrInformation> updateHrInformationByHrInformationId(UUID hrInformationId,
            HrInformation hrInformation);

}
