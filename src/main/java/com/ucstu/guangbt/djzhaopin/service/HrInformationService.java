package com.ucstu.guangbt.djzhaopin.service;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;

public interface HrInformationService {
    public HrInformation queryHrInformationByHrInfoId(UUID hrinfoid);

    public HrInformation updateHrInformationByHrInfoId(UUID hrinfoid, HrInformation hrInformation);

    public HrInformation addHrInformation(String accountInformationId, HrInformation hrInformation);
}
