package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.repository.HrInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.HrInformationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

@Service
public class HrInformationServiceImpl implements HrInformationService {

    @Resource
    private HrInformationRepository hrInformationRepository;

    @Override
    @Transactional
    public ServiceToControllerBody<HrInformation> createHrInformation(HrInformation hrInformation) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInformation> deleteHrInformationByHrInfoId(UUID hrInformationId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInformation> updateHrInformationByHrInformationId(UUID hrInformationId,
            HrInformation hrInformation) {
        ServiceToControllerBody<HrInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (hrInformationOptional.isPresent()) {
            hrInformation.setHrInformationId(hrInformationId);
            return serviceToControllerBody.success(hrInformationRepository.save(hrInformation));
        }
        return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
    }

    @Override
    public ServiceToControllerBody<List<HrInformation>> getHrInformations(Pageable pageable) {
        ServiceToControllerBody<List<HrInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<HrInformation> hrInformations = hrInformationRepository.findAll(pageable);
        if (hrInformations.hasContent()) {
            return serviceToControllerBody.success(hrInformations.getContent());
        }
        return serviceToControllerBody.success(new ArrayList<>());
    }

    @Override
    public ServiceToControllerBody<HrInformation> getHrInformationByHrInformationId(UUID hrInformationId) {
        ServiceToControllerBody<HrInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (hrInformationOptional.isPresent()) {
            return serviceToControllerBody.success(hrInformationOptional.get());
        }
        return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> createHrInspectionRecord(UUID hrInformationId,
            HrInspectionRecord hrInspectionRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> deleteHrInspectionRecordByInspectionRecordId(
            UUID inspectionRecordId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> updateHrInspectionRecordByInspectionRecordId(
            UUID inspectionRecordId, HrInspectionRecord hrInspectionRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<List<HrInspectionRecord>> getHrInspectionRecordByHrInformationId(
            UUID hrInformationId, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<HrInspectionRecord> getHrInspectionRecordByInspectionRecordId(
            UUID inspectionRecordId) {
        // TODO Auto-generated method stub
        return null;
    }

}
