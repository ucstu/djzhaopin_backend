package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.model.company.BigData;
import com.ucstu.guangbt.djzhaopin.repository.MessageRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.company.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.company.position.PositionInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.hr.HrInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.DeliveryRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.UserInspectionRecordRepository;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class CompanyInformationServiceImpl implements CompanyInformationService {

    @Resource
    private CompanyInformationRepository companyInformationRepository;

    @Resource
    private PositionInformationRepository positionInformationRepository;

    @Resource
    private HrInformationRepository hrInformationRepository;

    @Resource
    private DeliveryRecordRepository deliveryRecordRepository;

    @Resource
    private UserInspectionRecordRepository userInspectionRecordRepository;

    @Resource
    private MessageRecordRepository messageRecordRepository;

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
        ServiceToControllerBody<CompanyInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        companyInformationRepository.delete(companyInformationOptional.get());
        return serviceToControllerBody.success(companyInformationOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<CompanyInformation> updateCompanyInformationByCompanyInformationId(
            UUID companyInformationId, CompanyInformation companyInformation) {
        ServiceToControllerBody<CompanyInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        companyInformation.setCompanyInformationId(companyInformationId);
        companyInformation.setCreatedAt(companyInformationOptional.get().getCreatedAt());
        return serviceToControllerBody.success(companyInformationRepository.save(companyInformation));
    }

    @Override
    public ServiceToControllerBody<PageResult<CompanyInformation>> getCompanyInformationsByCompanyName(
            String companyName,
            Pageable pageable) {
        ServiceToControllerBody<PageResult<CompanyInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<CompanyInformation> companyInformations = companyInformationRepository.findAll(pageable);
        PageResult<CompanyInformation> pageResult = new PageResult<>();
        if (!companyInformations.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            pageResult.setContentsName("companyInformations");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(companyInformations.getTotalElements());
        pageResult.setContents(companyInformations.getContent());
        pageResult.setContentsName("companyInformations");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<CompanyInformation> getCompanyInformationByCompanyInformationId(
            UUID companyInformationId) {
        ServiceToControllerBody<CompanyInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        return serviceToControllerBody.success(companyInformationOptional.get());
    }

    @Override
    public ServiceToControllerBody<PageResult<DeliveryRecord>> getDeliveryRecordsByCompanyInformationId(
            UUID companyInformationId, Date createdAt, Date updatedAt, List<Integer> status, Date interviewTime,
            List<Integer> workingYears, List<String> sexs, List<Integer> ages,
            List<UUID> positionInformationIds, List<Date> deliveryDates, String userName, Pageable pageable) {
        ServiceToControllerBody<PageResult<DeliveryRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        Specification<DeliveryRecord> specification = new Specification<DeliveryRecord>() {
            @Override
            public Predicate toPredicate(Root<DeliveryRecord> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (createdAt != null) {
                    predicates.add(cb.equal(root.get("createdAt"), createdAt));
                }
                if (updatedAt != null) {
                    predicates.add(cb.equal(root.get("updatedAt"), updatedAt));
                }
                if (status != null && !status.isEmpty()) {
                    predicates.add(root.get("status").in(status));
                }
                if (interviewTime != null) {
                    predicates.add(cb.equal(root.get("interviewTime"), interviewTime));
                }
                Join<DeliveryRecord, UserInformation> userInformationJoin = root.join("userInformation");
                if (workingYears != null && !workingYears.isEmpty()) {
                    predicates.add(userInformationJoin.get("workingYears").in(workingYears));
                }
                if (sexs != null && !sexs.isEmpty()) {
                    predicates.add(userInformationJoin.get("sex").in(sexs));
                }
                if (ages != null && !ages.isEmpty()) {
                    predicates.add(userInformationJoin.get("age").in(ages));
                }
                Join<DeliveryRecord, PositionInformation> positionInformationJoin = root.join("positionInformation");
                if (positionInformationIds != null && !positionInformationIds.isEmpty()) {
                    predicates.add(positionInformationJoin.get("positionInformationId").in(positionInformationIds));
                }
                if (deliveryDates != null && !deliveryDates.isEmpty()) {
                    predicates.add(root.get("createdAt").in(deliveryDates));
                }
                if (userName != null) {
                    predicates.add(cb.like(userInformationJoin.get("userName"), "%" + userName + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Page<DeliveryRecord> deliveryRecords = deliveryRecordRepository.findAll(specification, pageable);
        PageResult<DeliveryRecord> pageResult = new PageResult<>();
        if (!deliveryRecords.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            pageResult.setContentsName("deliveryRecords");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(deliveryRecords.getTotalElements());
        pageResult.setContents(deliveryRecords.getContent());
        pageResult.setContentsName("deliveryRecords");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<PageResult<PositionInformation>> getPositionInfos(String positionName, String salary,
            List<Integer> workingYears, List<Integer> educations, List<String> directionTags,
            List<String> workAreas, List<Integer> positionTypes, List<Integer> scales,
            List<Integer> financingStages, List<String> comprehensions, String workingPlace,
            Pageable pageable) {
        // TODO 完善搜索功能
        ServiceToControllerBody<PageResult<PositionInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<PositionInformation> positionInformations = positionInformationRepository.findAll(pageable);
        PageResult<PositionInformation> pageResult = new PageResult<>();
        if (!positionInformations.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            pageResult.setContentsName("positionInformations");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(positionInformations.getTotalElements());
        pageResult.setContents(positionInformations.getContent());
        pageResult.setContentsName("positionInformations");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    @Transactional
    public ServiceToControllerBody<PositionInformation> createPositionInformation(UUID companyInformationId,
            PositionInformation positionInformation) {
        ServiceToControllerBody<PositionInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        Optional<HrInformation> hrInformationOptional = hrInformationRepository
                .findById(positionInformation.getHrInformationId());
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在",
                    positionInformation.getHrInformationId());
        }
        positionInformation.setCompanyInformation(companyInformationOptional.get());
        positionInformation.setHrInformation(hrInformationOptional.get());
        return serviceToControllerBody.created(positionInformationRepository.save(positionInformation));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<PositionInformation> deletePositionInformationByPositionInformationId(
            UUID companyInformationId,
            UUID positionInformationId) {
        ServiceToControllerBody<PositionInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        Optional<PositionInformation> positionInformationOptional = companyInformationOptional.get()
                .getPositionInformations().stream().filter(positionInformation -> positionInformation
                        .getPositionInformationId().equals(positionInformationId))
                .findFirst();
        if (!positionInformationOptional.isPresent()) {
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在", positionInformationId);
        }
        positionInformationRepository.delete(positionInformationOptional.get());
        return serviceToControllerBody.success(positionInformationOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<PositionInformation> updatePositionInformationByPositionInformationId(
            UUID companyInformationId, UUID positionInformationId, PositionInformation positionInformation) {
        ServiceToControllerBody<PositionInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        Optional<HrInformation> hrInformationOptional = hrInformationRepository
                .findById(positionInformation.getHrInformationId());
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        if (!hrInformationOptional.isPresent()) {
            return serviceToControllerBody.error("hrInformationId", "HR信息不存在",
                    positionInformation.getHrInformationId());
        }
        Optional<PositionInformation> positionInformationOptional = companyInformationOptional.get()
                .getPositionInformations()
                .stream().filter(positionInformation1 -> positionInformation1.getPositionInformationId()
                        .equals(positionInformationId))
                .findFirst();
        if (!positionInformationOptional.isPresent()) {
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在", positionInformationId);
        }
        positionInformation.setPositionInformationId(positionInformationId);
        positionInformation.setCompanyInformation(companyInformationOptional.get());
        positionInformation.setCreatedAt(positionInformationOptional.get().getCreatedAt());
        return serviceToControllerBody.success(positionInformationRepository.save(positionInformation));
    }

    @Override
    public ServiceToControllerBody<PageResult<PositionInformation>> getPositionInformationsByCompanyInformationId(
            UUID companyInformationId, String positionName, String salary, List<Integer> workingYears,
            List<Integer> educations, List<String> directionTags, List<String> workAreas, List<Integer> positionTypes,
            List<Integer> scales, List<Integer> financingStages, List<String> comprehensions, String workingPlace,
            Pageable pageable) {
        // TODO 完善搜索功能
        ServiceToControllerBody<PageResult<PositionInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        PageResult<PositionInformation> pageResult = new PageResult<>();
        Page<PositionInformation> positionInformations = positionInformationRepository.findAll(pageable);
        if (!positionInformations.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            pageResult.setContentsName("positionInformations");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(positionInformations.getTotalElements());
        pageResult.setContents(positionInformations.getContent());
        pageResult.setContentsName("positionInformations");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<PositionInformation> getPositionInformationByPositionInformationId(
            UUID companyInformationId,
            UUID positionInformationId) {
        ServiceToControllerBody<PositionInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        Optional<PositionInformation> positionInformationOptional = companyInformationOptional.get()
                .getPositionInformations()
                .stream().filter(positionInformation -> positionInformation.getPositionInformationId()
                        .equals(positionInformationId))
                .findFirst();
        if (!positionInformationOptional.isPresent()) {
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在", positionInformationId);
        }
        return serviceToControllerBody.success(positionInformationOptional.get());
    }

    @Override
    public ServiceToControllerBody<PageResult<UserInspectionRecord>> getSawMeRecordsByCompanyInformationId(
            UUID companyInformationId, Date startDate, Date endDate, Pageable pageable) {
        ServiceToControllerBody<PageResult<UserInspectionRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        Page<UserInspectionRecord> userInspectionRecords = userInspectionRecordRepository
                .findByCompanyInformationAndCreatedAtBetween(companyInformationOptional.get(), startDate, endDate,
                        pageable);
        PageResult<UserInspectionRecord> pageResult = new PageResult<>();
        if (!userInspectionRecords.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            pageResult.setContentsName("userInspectionRecords");
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(userInspectionRecords.getTotalElements());
        pageResult.setContents(userInspectionRecords.getContent());
        pageResult.setContentsName("userInspectionRecords");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<List<BigData>> getBigDataByCompanyInformationId(UUID companyInformationId,
            UUID hrInformationId, Date startDate, Date endDate, Pageable pageable) {
        ServiceToControllerBody<List<BigData>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        List<BigData> bigDatas = new ArrayList<>();
        while (startDate.before(endDate)) {
            BigData bigData = new BigData();
            bigData.setDate(startDate);
            bigData.setInspectionRecordCount(userInspectionRecordRepository
                    .countByCompanyInformationAndCreatedAt(companyInformationOptional.get(), startDate));
            bigData.setDeliveryRecordCount(deliveryRecordRepository
                    .countByCompanyInformationAndCreatedAt(companyInformationOptional.get(), startDate));
            bigData.setOnlineCommunicateCount(
                    messageRecordRepository.countByServiceIdAndCreatedAt(hrInformationId,
                            startDate));
            bigDatas.add(bigData);
        }
        return serviceToControllerBody.success(bigDatas);
    }

}
