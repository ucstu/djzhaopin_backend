package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.repository.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.DeliveryRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.PositionInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class CompanyInformationServiceImpl implements CompanyInformationService {

    @Resource
    private CompanyInformationRepository companyInformationRepository;

    @Resource
    private DeliveryRecordRepository deliveryRecordRepository;

    @Resource
    private PositionInformationRepository positionInformationRepository;

    @Override
    public ServiceToControllerBody<CompanyInformation> createCompanyInformation(CompanyInformation companyInformation) {
        ServiceToControllerBody<CompanyInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        return serviceToControllerBody.created(companyInformationRepository.save(companyInformation));
    }

    @Override
    public ServiceToControllerBody<CompanyInformation> updateCompanyInformationByCompanyInformationId(
            UUID companyInformationId,
            CompanyInformation companyInformation) {
        ServiceToControllerBody<CompanyInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            companyInformation.setCompanyInformationId(companyInformationId);
            return serviceToControllerBody.success(companyInformationRepository.save(companyInformation));
        }
        return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
    }

    @Override
    public ServiceToControllerBody<List<CompanyInformation>> getCompanyInformations(Pageable pageable) {
        ServiceToControllerBody<List<CompanyInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<CompanyInformation> companyInformations = companyInformationRepository.findAll(pageable);
        if (companyInformations.hasContent()) {
            return serviceToControllerBody.success(companyInformations.getContent());
        }
        return serviceToControllerBody.success(new ArrayList<>());
    }

    @Override
    public ServiceToControllerBody<CompanyInformation> getCompanyInformationByCompanyInformationId(
            UUID companyInformationId) {
        ServiceToControllerBody<CompanyInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            return serviceToControllerBody.success(companyInformationOptional.get());
        }
        return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
    }

    @Override
    public ServiceToControllerBody<List<DeliveryRecord>> getDeliveryRecordsByCompanyInformationId(
            UUID companyInformationId,
            Integer state,
            Integer workingYears, String sex, Integer age, UUID jobId, Date deliveryDate, String search,
            Pageable pageable) {
        // TODO 完善搜索功能
        ServiceToControllerBody<List<DeliveryRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<DeliveryRecord> deliveryRecords = deliveryRecordRepository.findAll(pageable);
        if (deliveryRecords.hasContent()) {
            return serviceToControllerBody.success(deliveryRecords.getContent());
        }
        return serviceToControllerBody.success(new ArrayList<>());
    }

    @Override
    public ServiceToControllerBody<List<PositionInformation>> getPositionInfos(Pageable pageable) {
        ServiceToControllerBody<List<PositionInformation>> serviceToControllerBody = new ServiceToControllerBody();
        Page<PositionInformation> positionInformations = positionInformationRepository.findAll(pageable);
        if (positionInformations.hasContent()) {
            return serviceToControllerBody.success(positionInformations.getContent());
        }
        return serviceToControllerBody.success(new ArrayList<>());
    }

    @Override
    public ServiceToControllerBody<PositionInformation> createPositionInformation(UUID companyInformationId,
            PositionInformation positionInformation) {
        ServiceToControllerBody<PositionInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            companyInformationOptional.get().getPositionInformations().add(positionInformation);
            return serviceToControllerBody.created(positionInformationRepository.save(positionInformation));
        }
        return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
    }

    @Override
    public ServiceToControllerBody<PositionInformation> deletePositionInformationByPositionInformationId(
            UUID companyInformationId,
            UUID positionInformationId) {
        ServiceToControllerBody<PositionInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            CompanyInformation companyInformation = companyInformationOptional.get();
            Optional<PositionInformation> positionInformationOptional = companyInformation.getPositionInformations()
                    .stream()
                    .filter(positionInformation -> positionInformation.getPositionInformationId()
                            .equals(positionInformationId))
                    .findFirst();
            if (positionInformationOptional.isPresent()) {
                companyInformation.getPositionInformations().remove(positionInformationOptional.get());
                return serviceToControllerBody
                        .success(positionInformationRepository.save(positionInformationOptional.get()));
            }
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在", positionInformationId);
        }
        return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
    }

    @Override
    public ServiceToControllerBody<PositionInformation> updatePositionInformationByPositionInformationId(
            UUID companyInformationId,
            UUID positionInformationId,
            PositionInformation positionInformation) {
        ServiceToControllerBody<PositionInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            CompanyInformation companyInformation = companyInformationOptional.get();
            Optional<PositionInformation> positionInformationOptional = companyInformation.getPositionInformations()
                    .stream()
                    .filter(positionInformation1 -> positionInformation1.getPositionInformationId()
                            .equals(positionInformationId))
                    .findFirst();
            if (positionInformationOptional.isPresent()) {
                PositionInformation positionInformation1 = positionInformationOptional.get();
                positionInformation1.setCeilingSalary(positionInformation.getCeilingSalary());
                positionInformation1.setCompanyInformationId(positionInformation.getCompanyInformationId());
                positionInformation1.setDepartmentName(positionInformation.getDepartmentName());
                positionInformation1.setDescription(positionInformation.getDescription());
                positionInformation1.setDirectionTags(positionInformation.getDirectionTags());
                positionInformation1.setEducation(positionInformation.getEducation());
                positionInformation1.setHighlights(positionInformation.getHighlights());
                positionInformation1.setHrInformationId(positionInformation.getHrInformationId());
                positionInformation1.setPositionName(positionInformation.getPositionName());
                positionInformation1.setPositionInterviewInfo(positionInformation.getPositionInterviewInfo());
                positionInformation1.setPositionType(positionInformation.getPositionType());
                positionInformation1.setPositionWorkingPlace(positionInformation.getPositionWorkingPlace());
                positionInformation1.setStartingSalary(positionInformation.getStartingSalary());
                positionInformation1.setWeekendReleaseTime(positionInformation.getWeekendReleaseTime());
                positionInformation1.setWorkCityName(positionInformation.getWorkCityName());
                positionInformation1.setWorkAreaName(positionInformation.getWorkAreaName());
                positionInformation1.setWorkTime(positionInformation.getWorkTime());
                positionInformation1.setWorkingYears(positionInformation.getWorkingYears());
                return serviceToControllerBody
                        .success(positionInformationRepository.save(positionInformation1));
            }
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在", positionInformationId);
        }
        return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
    }

    @Override
    public ServiceToControllerBody<List<PositionInformation>> getPositionInformationsByCompanyInformationId(
            UUID companyInformationId,
            Pageable pageable) {
        ServiceToControllerBody<List<PositionInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            return serviceToControllerBody.success(companyInformationOptional.get().getPositionInformations());
        }
        return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
    }

    @Override
    public ServiceToControllerBody<PositionInformation> getPositionInformationByPositionInformationId(
            UUID companyInformationId,
            UUID positionInformationId) {
        ServiceToControllerBody<PositionInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            CompanyInformation companyInformation = companyInformationOptional.get();
            Optional<PositionInformation> positionInformationOptional = companyInformation.getPositionInformations()
                    .stream()
                    .filter(positionInformation -> positionInformation.getPositionInformationId()
                            .equals(positionInformationId))
                    .findFirst();
            if (positionInformationOptional.isPresent()) {
                return serviceToControllerBody.success(positionInformationOptional.get());
            }
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在", positionInformationId);
        }
        return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
    }

}
