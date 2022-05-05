package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.repository.hr.HrInformationRepository;
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
    public ServiceToControllerBody<PageResult<HrInformation>> getHrInformations(Pageable pageable) {
        ServiceToControllerBody<PageResult<HrInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<HrInformation> hrInformations = hrInformationRepository.findAll(pageable);
        PageResult<HrInformation> pageResult = new PageResult<>();
        if (hrInformations.hasContent()) {
            pageResult.setTotalCount(hrInformations.getTotalElements());
            pageResult.setContents(hrInformations.getContent());
            pageResult.setContentsName("hrInformations");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(0);
        pageResult.setContents(new ArrayList<>());
        return serviceToControllerBody.success(pageResult);
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
            HrInspectionRecord hrHrInspectionRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> deleteHrInspectionRecordByHrInspectionRecordId(
            UUID inspectionRecordId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> updateHrInspectionRecordByHrInspectionRecordId(
            UUID inspectionRecordId, HrInspectionRecord hrHrInspectionRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<PageResult<HrInspectionRecord>> getHrInspectionRecordByHrInformationId(
            UUID hrInformationId, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceToControllerBody<HrInspectionRecord> getHrInspectionRecordByHrInspectionRecordId(
            UUID inspectionRecordId) {
        // TODO Auto-generated method stub
        return null;
    }

}
