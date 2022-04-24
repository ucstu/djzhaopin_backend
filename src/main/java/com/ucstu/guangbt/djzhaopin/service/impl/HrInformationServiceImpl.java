package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.repository.HrInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.HrInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HrInformationServiceImpl implements HrInformationService {

    @Autowired
    private HrInformationRepository hrInformationRepository;

    @Override
    public HrInformation addHrInformation(String accountInformationId, HrInformation hrInformation) {

        return hrInformationRepository.save(hrInformation);
    }

    @Override
    public HrInformation queryHrInformationByHrInfoId(UUID hrinfoid) {
        return hrInformationRepository.findById(hrinfoid).get();
    }

    @Override
    public HrInformation updateHrInformationByHrInfoId(UUID hrInformationId, HrInformation hrInformation) {
        hrInformation.setHrInformationId(hrInformationId);
        return hrInformationRepository.save(hrInformation);
    }

}
