package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.repository.HrInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.HrInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class HrInformationServiceImpl implements HrInformationService {

    @Resource
    private HrInformationRepository hrInformationRepository;

    @Override
    public Optional<HrInformation> getHrInformationByHrInformationId(UUID hrInformationId) {
        return hrInformationRepository.findById(hrInformationId);
    }

    @Override
    public Optional<List<HrInformation>> getHrInformations(Pageable pageable) {
        return Optional.ofNullable(hrInformationRepository.findAll(pageable).getContent());
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
