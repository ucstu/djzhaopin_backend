package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.repository.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.HrInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.HrInformationService;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class HrInformationServiceImpl implements HrInformationService {

    @Resource
    private HrInformationRepository hrInformationRepository;

    @Resource
    private CompanyInformationRepository companyInformationRepository;

    @Override
    public Optional<HrInformation> queryHrInformationByHrInformationId(UUID hrInformationId) {
        return hrInformationRepository.findById(hrInformationId);
    }

    @Override
    public Optional<HrInformation> updateHrInformationByHrInformationId(UUID hrInformationId,
            HrInformation hrInformation) {
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (hrInformationOptional.isPresent()) {
            hrInformation.setHrInformationId(hrInformationId);
            return Optional.ofNullable(hrInformationRepository.save(hrInformation));
        }
        return Optional.empty();
    }

}
