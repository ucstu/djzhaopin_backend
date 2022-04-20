package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;

public interface PositionInformationService {

    public PositionInformation createPositionInformation(UUID companyinfoid,
            PositionInformation positionInformation);

    public PositionInformation deletePositionInformation(UUID positioninfoid);

    public PositionInformation updatePositionInformation(UUID positioninfoid,
            PositionInformation positionInformation);

    public PositionInformation getPositionInformationByPositionInfoId(UUID positioninfoid);

    public List<PositionInformation> getPositionInformations();

}
