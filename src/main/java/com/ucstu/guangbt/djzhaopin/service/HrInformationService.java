package com.ucstu.guangbt.djzhaopin.service;

import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;

public interface HrInformationService {
    public Optional<HrInformation> queryHrInformationByHrInformationId(UUID hrInformationId);

    public Optional<HrInformation> updateHrInformationByHrInformationId(UUID hrInformationId,
            HrInformation hrInformation);
}
