package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.model.company.BigData;
import com.ucstu.guangbt.djzhaopin.repository.company.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.company.position.PositionInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.DeliveryRecordRepository;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;

@Service
public class CompanyInformationServiceImpl implements CompanyInformationService {

    @Resource
    private CompanyInformationRepository companyInformationRepository;

    @Resource
    private DeliveryRecordRepository deliveryRecordRepository;

    @Resource
    private PositionInformationRepository positionInformationRepository;

    @Override
    @Transactional
    public ServiceToControllerBody<CompanyInformation> createCompanyInformation(CompanyInformation companyInformation) {
        ServiceToControllerBody<CompanyInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        return serviceToControllerBody.created(companyInformationRepository.save(companyInformation));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<CompanyInformation> deleteCompanyInformationByCompanyInfoId(
            UUID companyInformationId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @Transactional
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
    public ServiceToControllerBody<PageResult<CompanyInformation>> getCompanyInformationsByCompanyName(
            String companyName,
            Pageable pageable) {
        ServiceToControllerBody<PageResult<CompanyInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<CompanyInformation> companyInformations = companyInformationRepository.findAll(pageable);
        PageResult<CompanyInformation> pageResult = new PageResult<>();
        if (companyInformations.hasContent()) {
            pageResult.setTotalCount(companyInformations.getTotalElements());
            pageResult.setContents(companyInformations.getContent());
            pageResult.setContentsName("companyInformations");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(0);
        pageResult.setContents(new ArrayList<>());
        return serviceToControllerBody.success(pageResult);
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
    public ServiceToControllerBody<PageResult<DeliveryRecord>> getDeliveryRecordsByCompanyInformationId(
            UUID companyInformationId, Date createdAt, Date updatedAt, List<Integer> status,
            List<Integer> workingYears, List<String> sexs, List<Integer> ages,
            List<UUID> positionInformationIds, List<Date> deliveryDates, String search, Pageable pageable) {
        // TODO 完善搜索功能
        ServiceToControllerBody<PageResult<DeliveryRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<DeliveryRecord> deliveryRecords = deliveryRecordRepository.findAll(pageable);
        PageResult<DeliveryRecord> pageResult = new PageResult<>();
        if (deliveryRecords.hasContent()) {
            pageResult.setTotalCount(deliveryRecords.getTotalElements());
            pageResult.setContents(deliveryRecords.getContent());
            pageResult.setContentsName("deliveryRecords");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(0);
        pageResult.setContents(new ArrayList<>());
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<PageResult<PositionInformation>> getPositionInfos(String positionName, String salary,
            List<Integer> workingYears, List<Integer> educations, List<String> directionTags,
            List<String> workAreas, List<Integer> positionTypes, List<Integer> scales,
            List<Integer> financingStages, List<String> comprehensions, String workingPlace,
            Pageable pageable) {
        // TODO 完善搜索功能
        ServiceToControllerBody<PageResult<PositionInformation>> serviceToControllerBody = new ServiceToControllerBody();
        Page<PositionInformation> positionInformations = positionInformationRepository.findAll(pageable);
        PageResult<PositionInformation> pageResult = new PageResult<>();
        if (positionInformations.hasContent()) {
            pageResult.setTotalCount(positionInformations.getTotalElements());
            pageResult.setContents(positionInformations.getContent());
            pageResult.setContentsName("positionInformations");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(0);
        pageResult.setContents(new ArrayList<>());
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    @Transactional
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
    @Transactional
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
                PositionInformation positionInformation = positionInformationOptional.get();
                positionInformation.toString();
                companyInformation.getPositionInformations().remove(positionInformation);
                positionInformationRepository.delete(positionInformation);
                companyInformationRepository.save(companyInformation);
                return serviceToControllerBody.success(positionInformation);
            }
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在", positionInformationId);
        }
        return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
    }

    @Override
    @Transactional
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
                positionInformation1.setCompanyInformation(companyInformation);
                positionInformation1.setDepartmentName(positionInformation.getDepartmentName());
                positionInformation1.setDescription(positionInformation.getDescription());
                positionInformation1.setDirectionTags(positionInformation.getDirectionTags());
                positionInformation1.setEducation(positionInformation.getEducation());
                positionInformation1.setHighlights(positionInformation.getHighlights());
                positionInformation1.setHrInformation(positionInformation.getHrInformation());
                positionInformation1.setPositionName(positionInformation.getPositionName());
                positionInformation1.setInterviewInfo(positionInformation.getInterviewInfo());
                positionInformation1.setPositionType(positionInformation.getPositionType());
                positionInformation1.setExactAddress(positionInformation.getExactAddress());
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
    public ServiceToControllerBody<PageResult<PositionInformation>> getPositionInformationsByCompanyInformationId(
            UUID companyInformationId,
            String positionName, String salary,
            List<Integer> workingYears, List<Integer> educations, List<String> directionTags,
            List<String> workAreas, List<Integer> positionTypes, List<Integer> scales,
            List<Integer> financingStages, List<String> comprehensions, String workingPlace,
            Pageable pageable) {
        // TODO 完善搜索功能
        ServiceToControllerBody<PageResult<PositionInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        PageResult<PositionInformation> pageResult = new PageResult<>();
        if (companyInformationOptional.isPresent()) {
            CompanyInformation companyInformation = companyInformationOptional.get();
            List<PositionInformation> positionInformations = companyInformation.getPositionInformations();
            if (positionName != null) {
                positionInformations = positionInformations.stream()
                        .filter(positionInformation -> positionInformation.getPositionName()
                                .contains(positionName))
                        .collect(Collectors.toList());
            }
            // if (salary != null) {
            // positionInformations = positionInformations.stream()
            // .filter(positionInformation -> positionInformation.getStartingSalary()
            // .contains(salary))
            // .collect(Collectors.toList());
            // }
            if (workingYears != null) {
                positionInformations = positionInformations.stream()
                        .filter(positionInformation -> workingYears.contains(positionInformation.getWorkingYears()))
                        .collect(Collectors.toList());
            }
            if (educations != null) {
                positionInformations = positionInformations.stream()
                        .filter(positionInformation -> educations.contains(positionInformation.getEducation()))
                        .collect(Collectors.toList());
            }
            if (directionTags != null) {
                positionInformations = positionInformations.stream()
                        .filter(positionInformation -> directionTags.contains(positionInformation.getDirectionTags()))
                        .collect(Collectors.toList());
            }
            if (workAreas != null) {
                positionInformations = positionInformations.stream()
                        .filter(positionInformation -> workAreas.contains(positionInformation.getWorkAreaName()))
                        .collect(Collectors.toList());
            }
            if (positionTypes != null) {
                positionInformations = positionInformations.stream()
                        .filter(positionInformation -> positionTypes.contains(positionInformation.getPositionType()))
                        .collect(Collectors.toList());
            }
            // if (scales != null) {
            // positionInformations = positionInformations.stream()
            // .filter(positionInformation -> scales.contains(positionInformation.get()))
            // .collect(Collectors.toList());
            // }
            // if (financingStages != null) {
            // positionInformations = positionInformations.stream()
            // .filter(positionInformation -> financingStages
            // .contains(positionInformation.getFinancingStage()))
            // .collect(Collectors.toList());
            // }
            // if (comprehensions != null) {
            // positionInformations = positionInformations.stream()
            // .filter(positionInformation ->
            // comprehensions.contains(positionInformation.getComprehension()))
            // .collect(Collectors.toList());
            // }
            if (workingPlace != null) {
                positionInformations = positionInformations.stream()
                        .filter(positionInformation -> positionInformation.getWorkCityName()
                                .contains(workingPlace))
                        .collect(Collectors.toList());
            }
            pageResult.setTotalCount(positionInformations.size());
            pageResult.setContents(positionInformations.stream()
                    .skip(pageable.getPageSize() * pageable.getPageNumber())
                    .limit(pageable.getPageSize())
                    .collect(Collectors.toList()));
            return serviceToControllerBody.success(pageResult);
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

    @Override
    public ServiceToControllerBody<List<BigData>> getBigDataByCompanyInformationId(@NotNull UUID companyInformationId,
            Date startDate, Date endDate, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

}
