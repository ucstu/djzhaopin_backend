package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.repository.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.PositionInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.PositionInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PositionInformationServiceImpl implements PositionInformationService {

    @Autowired
    private PositionInformationRepository positionInformationRepository;
    @Autowired
    private CompanyInformationRepository companyInformationRepository;

    @Override
    public PositionInformation createPositionInformation(UUID companyinfoid, PositionInformation positionInformation) {
        if (companyInformationRepository.findById(companyinfoid) != null) {
            return positionInformationRepository.save(positionInformation);
        } else {
            return null;
        }
    }

    @Override
    public PositionInformation deletePositionInformation(UUID positioninfoid) {
        if (positionInformationRepository.existsById(positioninfoid)) {
            PositionInformation positionInformation = positionInformationRepository.findById(positioninfoid).get();
            positionInformationRepository.deleteById(positioninfoid);
            return positionInformation;
        } else {
            return null;
        }
    }

    @Override
    public PositionInformation updatePositionInformation(UUID positioninfoid, PositionInformation positionInformation) {
        if (positionInformationRepository.existsById(positioninfoid)) {
            return positionInformationRepository.saveAndFlush(positionInformation);
        } else {
            return null;
        }
    }

    @Override
    public PositionInformation getPositionInformationByPositionInfoId(UUID positioninfoid) {
        if (positionInformationRepository.existsById(positioninfoid)) {
            return positionInformationRepository.findById(positioninfoid).get();
        } else {
            return null;
        }
    }

    @Override
    public List<PositionInformation> getPositionInformations() {
        return positionInformationRepository.findAll();
    }

}
