package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInspectionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.repository.hr.HrInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.hr.HrInspectionRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.UserInformationRepository;
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

    @Resource
    private UserInformationRepository userInformationRepository;

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
        hrInformation.setPositionInformations(hrInformationOptional.get().getPositionInformations());
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
            HrInspectionRecord hrInspectionRecord) {
        ServiceToControllerBody<HrInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        Optional<UserInformation> userInformationOptional = userInformationRepository
                .findById(hrInspectionRecord.getUserInformation().getUserInformationId());
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInspectionRecord.getHrInformation()
                    .getHrInformationId());
        }
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在",
                    hrInspectionRecord.getUserInformation()
                            .getUserInformationId());
        }
        hrInspectionRecord.setHrInformation(hrInformationOptional.get());
        return serviceToControllerBody.created(hrInspectionRecordRepository.save(hrInspectionRecord));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> deleteHrInspectionRecordByHrInspectionRecordId(
            UUID hrInformationId, UUID hrInspectionRecordId) {
        ServiceToControllerBody<HrInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        Optional<HrInspectionRecord> hrInspectionRecordOptional = hrInspectionRecordRepository
                .findById(hrInspectionRecordId);
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
        }
        if (!hrInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("hrInspectionRecordId", "HR查看记录不存在", hrInformationId);
        }
        hrInformationOptional.get().getHrInspectionRecords().remove(hrInspectionRecordOptional.get());
        hrInformationRepository.save(hrInformationOptional.get());
        return serviceToControllerBody.success(hrInspectionRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<HrInspectionRecord> updateHrInspectionRecordByHrInspectionRecordId(
            UUID hrInformationId, UUID hrInspectionRecordId, HrInspectionRecord hrInspectionRecord) {
        ServiceToControllerBody<HrInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInspectionRecord> hrInspectionRecordOptional = hrInspectionRecordRepository
                .findById(hrInspectionRecordId);
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        Optional<UserInformation> userInformationOptional = userInformationRepository
                .findById(hrInspectionRecord.getUserInformation().getUserInformationId());
        if (!hrInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("hrInspectionRecordId", "HR查看记录不存在", hrInspectionRecordId);
        }
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInspectionRecord.getHrInformation()
                    .getHrInformationId());
        }
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在",
                    hrInspectionRecord.getUserInformation().getUserInformationId());
        }
        hrInspectionRecord.setHrInspectionRecordId(hrInspectionRecordId);
        hrInspectionRecord.setHrInformation(hrInformationOptional.get());
        hrInspectionRecord.setCreatedAt(hrInspectionRecordOptional.get().getCreatedAt());
        return serviceToControllerBody.success(hrInspectionRecordRepository.save(hrInspectionRecord));
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
            UUID hrInformationId, UUID hrInspectionRecordId) {
        ServiceToControllerBody<HrInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<HrInformation> hrInformationOptional = hrInformationRepository.findById(hrInformationId);
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在", hrInformationId);
        }
        Optional<HrInspectionRecord> hrInspectionRecordOptional = hrInformationOptional.get()
                .getHrInspectionRecords().stream()
                .filter(hrInspectionRecord -> hrInspectionRecord.getHrInspectionRecordId()
                        .equals(hrInspectionRecordId))
                .findAny();
        if (!hrInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("hrInspectionRecordId", "HR查看记录不存在", hrInspectionRecordId);
        }
        return serviceToControllerBody.success(hrInspectionRecordOptional.get());
    }

}
