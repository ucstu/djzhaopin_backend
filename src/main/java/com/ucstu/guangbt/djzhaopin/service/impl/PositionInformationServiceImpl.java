package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.repository.PositionInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.PositionInformationService;

import org.springframework.beans.factory.annotation.Autowired;

public class PositionInformationServiceImpl implements PositionInformationService {

    @Autowired
    private PositionInformationRepository positionInformationRepository;

    @Override
    public PositionInformation createPositionInformation(UUID companyinfoid, PositionInformation positionInformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PositionInformation deletePositionInformation(UUID positioninfoid) {
        return null;
    }

    @Override
    public PositionInformation updatePositionInformation(UUID positioninfoid, PositionInformation positionInformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PositionInformation getPositionInformationByPositionInfoId(UUID positioninfoid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<PositionInformation> getPositionInformations() {
        // TODO Auto-generated method stub
        return null;
    }

}
