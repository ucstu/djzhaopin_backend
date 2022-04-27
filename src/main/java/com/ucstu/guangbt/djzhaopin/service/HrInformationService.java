package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;

import org.springframework.data.domain.Pageable;

public interface HrInformationService {

    public ServiceToControllerBody<HrInformation> getHrInformationByHrInformationId(UUID hrInformationId);

    public ServiceToControllerBody<List<HrInformation>> getHrInformations(Pageable pageable);

    public ServiceToControllerBody<HrInformation> updateHrInformationByHrInformationId(UUID hrInformationId,
            HrInformation hrInformation);

}
