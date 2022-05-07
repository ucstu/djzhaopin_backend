package com.ucstu.guangbt.djzhaopin.service;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;

import org.springframework.data.domain.Pageable;

public interface HrInformationService {

    public ServiceToControllerBody<HrInformation> createHrInformation(HrInformation hrInformation);

    public ServiceToControllerBody<HrInformation> deleteHrInformationByHrInfoId(UUID hrInformationId);

    public ServiceToControllerBody<HrInformation> updateHrInformationByHrInformationId(UUID hrInformationId,
            HrInformation hrInformation);

    public ServiceToControllerBody<PageResult<HrInformation>> getHrInformations(Pageable pageable);

    public ServiceToControllerBody<HrInformation> getHrInformationByHrInformationId(UUID hrInformationId);

    public ServiceToControllerBody<HrInspectionRecord> createHrInspectionRecord(UUID hrInformationId,
            HrInspectionRecord hrInspectionRecord);

    public ServiceToControllerBody<HrInspectionRecord> deleteHrInspectionRecordByHrInspectionRecordId(
            UUID hrInformationId, UUID hrInspectionRecordId);

    public ServiceToControllerBody<HrInspectionRecord> updateHrInspectionRecordByHrInspectionRecordId(
            UUID hrInformationId, UUID hrInspectionRecordId, HrInspectionRecord hrInspectionRecord);

    public ServiceToControllerBody<PageResult<HrInspectionRecord>> getHrInspectionRecordByHrInformationId(
            UUID hrInformationId, Pageable pageable);

    public ServiceToControllerBody<HrInspectionRecord> getHrInspectionRecordByHrInspectionRecordId(
            UUID hrInformationId, UUID hrInspectionRecordId);

}
