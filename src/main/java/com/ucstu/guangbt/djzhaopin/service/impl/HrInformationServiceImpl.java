package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.repository.hr.HrInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.hr.HrInspectionRecordRepository;
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

    @Resource
    private HrInspectionRecordRepository hrInspectionRecordRepository;

    @Override
    @Transactional
    public ServiceToControllerBody<HrInformation> createHrInformation(HrInformation hrInformation) {
        ServiceToControllerBody<HrInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        return serviceToControllerBody.created(hrInformationRepository.save(hrInformation));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInformation> deleteHrInformationByHrInfoId(UUID hrInformationId) {
        ServiceToControllerBody<HrInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
        }
        hrInformationRepository.delete(hrInformationOptional.get());
        return serviceToControllerBody.success(hrInformationOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInformation> updateHrInformationByHrInformationId(UUID hrInformationId,
            HrInformation hrInformation) {
        ServiceToControllerBody<HrInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
        }
        hrInformation.setHrInformationId(hrInformationId);
        hrInformation.setCreatedAt(hrInformationOptional.get().getCreatedAt());
        hrInformation.setCompanyInformation(hrInformationOptional.get().getCompanyInformation());
        hrInformation.setHrInspectionRecords(hrInformationOptional.get().getHrInspectionRecords());
        return serviceToControllerBody.success(hrInformationRepository.save(hrInformation));
    }

    @Override
    public ServiceToControllerBody<PageResult<HrInformation>> getHrInformations(Pageable pageable) {
        ServiceToControllerBody<PageResult<HrInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<HrInformation> hrInformations = hrInformationRepository.findAll(pageable);
        PageResult<HrInformation> pageResult = new PageResult<>();
        if (!hrInformations.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            pageResult.setContentsName("hrInformations");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(hrInformations.getTotalElements());
        pageResult.setContents(hrInformations.getContent());
        pageResult.setContentsName("hrInformations");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<HrInformation> getHrInformationByHrInformationId(UUID hrInformationId) {
        ServiceToControllerBody<HrInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
        }
        return serviceToControllerBody.success(hrInformationOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> createHrInspectionRecord(UUID hrInformationId,
            HrInspectionRecord hrHrInspectionRecord) {
        ServiceToControllerBody<HrInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
        }
        hrHrInspectionRecord.setHrInformation(hrInformationOptional.get());
        return serviceToControllerBody.created(hrInspectionRecordRepository.save(hrHrInspectionRecord));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> deleteHrInspectionRecordByHrInspectionRecordId(
            UUID inspectionRecordId) {
        ServiceToControllerBody<HrInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInspectionRecord> hrInspectionRecordOptional = hrInspectionRecordRepository
                .findById(inspectionRecordId);
        if (!hrInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("inspectionRecordId", "HR查看记录不存在", inspectionRecordId);
        }
        hrInspectionRecordRepository.delete(hrInspectionRecordOptional.get());
        return serviceToControllerBody.success(hrInspectionRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> updateHrInspectionRecordByHrInspectionRecordId(
            UUID inspectionRecordId, HrInspectionRecord hrHrInspectionRecord) {
        ServiceToControllerBody<HrInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInspectionRecord> hrInspectionRecordOptional = hrInspectionRecordRepository
                .findById(inspectionRecordId);
        if (!hrInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("inspectionRecordId", "HR查看记录不存在", inspectionRecordId);
        }
        hrHrInspectionRecord.setHrInspectionRecordId(inspectionRecordId);
        hrHrInspectionRecord.setCreatedAt(hrInspectionRecordOptional.get().getCreatedAt());
        return serviceToControllerBody.success(hrInspectionRecordRepository.save(hrHrInspectionRecord));
    }

    @Override
    public ServiceToControllerBody<PageResult<HrInspectionRecord>> getHrInspectionRecordByHrInformationId(
            UUID hrInformationId, Pageable pageable) {
        ServiceToControllerBody<PageResult<HrInspectionRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
        }
        Page<HrInspectionRecord> hrInspectionRecords = hrInspectionRecordRepository.findByHrInformation(
                hrInformationOptional.get(), pageable);
        PageResult<HrInspectionRecord> pageResult = new PageResult<>();
        if (!hrInspectionRecords.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            pageResult.setContentsName("hrInspectionRecords");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(hrInspectionRecords.getTotalElements());
        pageResult.setContents(hrInspectionRecords.getContent());
        pageResult.setContentsName("hrInspectionRecords");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<HrInspectionRecord> getHrInspectionRecordByHrInspectionRecordId(
            UUID inspectionRecordId) {
        ServiceToControllerBody<HrInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInspectionRecord> hrInspectionRecordOptional = hrInspectionRecordRepository
                .findById(inspectionRecordId);
        if (!hrInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("inspectionRecordId", "HR查看记录不存在", inspectionRecordId);
        }
        return serviceToControllerBody.success(hrInspectionRecordOptional.get());
    }

}
