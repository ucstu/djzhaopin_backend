package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.config.CustomUserDetails;
import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;
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
import com.ucstu.guangbt.djzhaopin.repository.user.UserInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.UserInspectionRecordRepository;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;
import com.ucstu.guangbt.djzhaopin.utils.EmailMessageUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import jakarta.annotation.Resource;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.ListJoin;
import jakarta.persistence.criteria.Predicate;

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
    private UserInformationRepository userInformationRepository;

    @Resource
    private MessageRecordRepository messageRecordRepository;

    @Resource
    private EmailMessageUtil emailMessageUtil;

    @Override
    @Transactional
    public ServiceToControllerBody<CompanyInformation> createCompanyInformation(CompanyInformation companyInformation) {
        ServiceToControllerBody<CompanyInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        if (companyInformation.getLogoUrl() == null || !StringUtils.hasLength(companyInformation.getLogoUrl())) {
            companyInformation.setLogoUrl("/image/heard3.webp");
        }
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
        for (AttentionRecord attentionRecord : companyInformationOptional.get().getAttentionRecords()) {
            emailMessageUtil.sendEmail(attentionRecord.getUserInformation().getEmail(),
                    "东江招聘-关注公司通知",
                    "您关注的公司《" + companyInformationOptional.get().getCompanyName() + "》已被删除，请登录系统查看！");
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
        for (AttentionRecord attentionRecord : companyInformationOptional.get().getAttentionRecords()) {
            emailMessageUtil.sendEmail(attentionRecord.getUserInformation().getEmail(),
                    "东江招聘-关注公司通知",
                    "您关注的公司《" + companyInformationOptional.get().getCompanyName() + "》已被更新，请登录系统查看！");
        }
        companyInformation.setCompanyInformationId(companyInformationId);
        companyInformation.setCreatedAt(companyInformationOptional.get().getCreatedAt());
        companyInformation.setPositionInformations(companyInformationOptional.get().getPositionInformations());
        companyInformation.setAttentionRecords(companyInformationOptional.get().getAttentionRecords());
        companyInformation.setHrInformations(companyInformationOptional.get().getHrInformations());
        return serviceToControllerBody.success(companyInformationRepository.save(companyInformation));
    }

    @Override
    public ServiceToControllerBody<PageResult<CompanyInformation>> getCompanyInformations(
            String companyName, List<Integer> scales, List<Integer> financingStages, List<String> comprehensions,
            String location, Pageable pageable) {
        ServiceToControllerBody<PageResult<CompanyInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Specification<CompanyInformation> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (companyName != null && !companyName.isEmpty()) {
                predicates.add(cb.or(cb.like(root.get("companyName"), "%" + companyName + "%"),
                        cb.like(root.get("fullName"), "%" + companyName + "%")));
            }
            if (scales != null && !scales.isEmpty()) {
                predicates.add(cb.in(root.get("scale")).value(scales));
            }
            if (financingStages != null && !financingStages.isEmpty()) {
                predicates.add(cb.in(root.get("financingStage")).value(financingStages));
            }
            if (comprehensions != null && !comprehensions.isEmpty()) {
                predicates.add(cb.in(root.get("comprehensionName")).value(comprehensions));
            }
            if (location != null && !location.isEmpty()) {
                Float longitude = Float.valueOf(location.split(",")[0]);
                Float latitude = Float.valueOf(location.split(",")[1]);
                Expression<Double> expression = cb.sqrt(cb.diff(
                        cb.prod(cb.diff(root.get("longitude"), longitude),
                                cb.diff(root.get("longitude"), longitude)),
                        cb.prod(cb.diff(root.get("latitude"), latitude),
                                cb.diff(root.get("latitude"), latitude))));
                query.orderBy(cb.asc(expression));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        Page<CompanyInformation> companyInformations = companyInformationRepository.findAll(specification, pageable);
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
        Specification<DeliveryRecord> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Calendar calendar = Calendar.getInstance();
            predicates.add(cb.equal(root.get("companyInformation"), companyInformationOptional.get()));
            if (createdAt != null) {
                calendar.setTime(createdAt);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                predicates.add(cb.between(root.get("createdAt"), createdAt, calendar.getTime()));
            }
            if (updatedAt != null) {
                calendar.setTime(updatedAt);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                predicates.add(cb.between(root.get("updatedAt"), updatedAt, calendar.getTime()));
            }
            if (status != null && !status.isEmpty()) {
                predicates.add(root.get("status").in(status));
            }
            if (interviewTime != null) {
                calendar.setTime(interviewTime);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                predicates.add(cb.between(root.get("interviewTime"), interviewTime, calendar.getTime()));
            }
            Join<DeliveryRecord, UserInformation> userInformationJoin = root.join("userInformation");
            if (workingYears != null && !workingYears.isEmpty()) {
                predicates.add(userInformationJoin.get("workingYears").in(workingYears));
            }
            if (sexs != null && !sexs.isEmpty()) {
                predicates.add(userInformationJoin.get("sex").in(sexs));
            }
            if (ages != null && !ages.isEmpty()) {
                In<Integer> ageIn = cb.in(
                        cb.function("ROUND", Integer.class,
                                cb.quot(cb.function(
                                        "DATEDIFF",
                                        Integer.class,
                                        cb.function("CURDATE", Date.class),
                                        userInformationJoin.get("dateOfBirth")), 365)));
                for (Integer age1 : ages) {
                    ageIn.value(age1);
                }
                predicates.add(ageIn);
            }
            Join<DeliveryRecord, PositionInformation> positionInformationJoin = root.join("positionInformation");
            if (positionInformationIds != null && !positionInformationIds.isEmpty()) {
                predicates.add(positionInformationJoin.get("positionInformationId").in(positionInformationIds));
            }
            if (deliveryDates != null && !deliveryDates.isEmpty()) {
                Date firstDeliveryDate = deliveryDates.get(0);
                calendar.setTime(firstDeliveryDate);
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                Predicate predicate = cb.between(root.get("createdAt"), firstDeliveryDate, calendar.getTime());
                for (int i = 1; i < deliveryDates.size(); i++) {
                    Date deliveryDate = deliveryDates.get(i);
                    calendar.setTime(deliveryDate);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    predicate = cb.or(predicate, cb.between(root.get("createdAt"), deliveryDate, calendar.getTime()));
                }
                predicates.add(predicate);
            }
            if (userName != null) {
                predicates.add(
                        cb.like(cb.concat(userInformationJoin.get("firstName"), userInformationJoin.get("lastName")),
                                "%" + userName + "%"));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
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
    public ServiceToControllerBody<PageResult<PositionInformation>> getPositionInfos(String positionName,
            String positionType, String salary, List<Integer> workingYears, List<Integer> educations,
            List<String> directionTags, String workProvinceName, String workCityName, List<String> workAreaNames,
            List<Integer> workTypes, List<Integer> scales, List<Integer> financingStages,
            List<String> comprehensions, String workingPlace, Pageable pageable) {
        ServiceToControllerBody<PageResult<PositionInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Specification<PositionInformation> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (positionName != null) {
                predicates.add(cb.like(root.get("positionName"), "%" + positionName + "%"));
            }
            if (positionType != null) {
                predicates.add(cb.like(root.get("positionType"), "%" + positionType + "%"));
            }
            if (salary != null && StringUtils.hasLength(salary)) {
                String startingSalary = salary.split(",")[0];
                String ceilingSalary = salary.split(",")[1];
                predicates.add(cb.greaterThan(root.get("startingSalary"), startingSalary));
                predicates.add(cb.lessThan(root.get("ceilingSalary"), ceilingSalary));
            }
            if (workingYears != null && !workingYears.isEmpty()) {
                predicates.add(root.get("workingYears").in(workingYears));
            }
            if (educations != null && !educations.isEmpty()) {
                predicates.add(root.get("education").in(educations));
            }
            if (directionTags != null && !directionTags.isEmpty()) {
                predicates.add(root.join("directionTags").in(directionTags));
            }
            if (workProvinceName != null) {
                predicates.add(cb.like(root.get("workProvinceName"), "%" + workProvinceName + "%"));
            }
            if (workCityName != null) {
                predicates.add(cb.like(root.get("workCityName"), "%" + workCityName + "%"));
            }
            if (workAreaNames != null && !workAreaNames.isEmpty()) {
                predicates.add(root.get("workAreaName").in(workAreaNames));
            }
            if (workTypes != null && !workTypes.isEmpty()) {
                predicates.add(root.get("positionType").in(workTypes));
            }
            Join<PositionInformation, CompanyInformation> companyInformationJoin = root.join("companyInformation");
            if (scales != null && !scales.isEmpty()) {
                predicates.add(companyInformationJoin.get("scale").in(scales));
            }
            if (financingStages != null && !financingStages.isEmpty()) {
                predicates.add(companyInformationJoin.get("financingStage").in(financingStages));
            }
            if (comprehensions != null && !comprehensions.isEmpty()) {
                predicates.add(companyInformationJoin.get("comprehensionName").in(comprehensions));
            }
            if (workingPlace != null) {
                Float longitude = Float.valueOf(workingPlace.split(",")[0]);
                Float latitude = Float.valueOf(workingPlace.split(",")[1]);
                Expression<Double> expression = cb.sqrt(cb.diff(
                        cb.prod(cb.diff(root.get("longitude"), longitude),
                                cb.diff(root.get("longitude"), longitude)),
                        cb.prod(cb.diff(root.get("latitude"), latitude),
                                cb.diff(root.get("latitude"), latitude))));
                query.orderBy(cb.asc(expression));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        Page<PositionInformation> positionInformations = positionInformationRepository.findAll(specification, pageable);
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
        for (AttentionRecord attentionRecord : companyInformationOptional.get().getAttentionRecords()) {
            emailMessageUtil.sendEmail(attentionRecord.getUserInformation().getEmail(),
                    "东江人才网-关注公司通知",
                    "您关注的公司《" + companyInformationOptional.get().getCompanyName() + "》发布了新职位，请登录系统查看！");
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
        for (GarnerRecord garnerRecord : positionInformationOptional.get().getGarnerRecords()) {
            emailMessageUtil.sendEmail(garnerRecord.getUserInformation().getEmail(),
                    "东江人才网-收藏职位通知",
                    "您收藏的职位《" + positionInformationOptional.get().getPositionName() + "》已被删除，请登录系统查看！");
        }
        companyInformationOptional.get().getPositionInformations().remove(positionInformationOptional.get());
        companyInformationRepository.save(companyInformationOptional.get());
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
        for (GarnerRecord garnerRecord : positionInformationOptional.get().getGarnerRecords()) {
            emailMessageUtil.sendEmail(garnerRecord.getUserInformation().getEmail(),
                    "东江人才网-收藏职位通知",
                    "您收藏的职位《" + positionInformationOptional.get().getPositionName() + "》已被更新，请登录系统查看！");
        }
        positionInformation.setHrInformation(hrInformationOptional.get());
        positionInformation.setPositionInformationId(positionInformationId);
        positionInformation.setCompanyInformation(companyInformationOptional.get());
        positionInformation.setCreatedAt(positionInformationOptional.get().getCreatedAt());
        positionInformation.setDeliveryRecords(positionInformationOptional.get().getDeliveryRecords());
        positionInformation.setGarnerRecords(positionInformationOptional.get().getGarnerRecords());
        positionInformation.setUserInspectionRecords(positionInformationOptional.get().getUserInspectionRecords());
        return serviceToControllerBody.success(positionInformationRepository.save(positionInformation));
    }

    @Override
    public ServiceToControllerBody<PageResult<PositionInformation>> getPositionInformationsByCompanyInformationId(
            UUID companyInformationId, String positionName, String positionType, String salary,
            List<Integer> workingYears, List<Integer> educations, List<String> directionTags, String workProvinceName,
            String workCityName, List<String> workAreaNames, List<Integer> workTypes, List<Integer> scales,
            List<Integer> financingStages, List<String> comprehensions, String workingPlace, Pageable pageable) {
        ServiceToControllerBody<PageResult<PositionInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在", companyInformationId);
        }
        Specification<PositionInformation> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("companyInformation"), companyInformationOptional.get()));
            if (positionName != null) {
                predicates.add(cb.like(root.get("positionName"), "%" + positionName + "%"));
            }
            if (positionType != null) {
                predicates.add(cb.like(root.get("positionType"), "%" + positionType + "%"));
            }
            if (salary != null) {
                String startingSalary = salary.split(",")[0];
                String ceilingSalary = salary.split(",")[1];
                predicates.add(cb.greaterThan(root.get("startingSalary"), startingSalary));
                predicates.add(cb.lessThan(root.get("ceilingSalary"), ceilingSalary));
            }
            if (workingYears != null && !workingYears.isEmpty()) {
                predicates.add(root.get("workingYears").in(workingYears));
            }
            if (educations != null && !educations.isEmpty()) {
                predicates.add(root.get("education").in(educations));
            }
            if (directionTags != null && !directionTags.isEmpty()) {
                predicates.add(root.join("directionTags").in(directionTags));
            }
            if (workProvinceName != null) {
                predicates.add(cb.like(root.get("workProvinceName"), "%" + workProvinceName + "%"));
            }
            if (workCityName != null) {
                predicates.add(cb.like(root.get("workCityName"), "%" + workCityName + "%"));
            }
            if (workAreaNames != null && !workAreaNames.isEmpty()) {
                ListJoin<PositionInformation, String> workAreaNamesJoin = root.joinList("workAreaNames");
                predicates.add(workAreaNamesJoin.in(workAreaNames));
            }
            if (workTypes != null && !workTypes.isEmpty()) {
                predicates.add(root.get("positionType").in(workTypes));
            }
            Join<PositionInformation, CompanyInformation> companyInformationJoin = root.join("companyInformation");
            if (scales != null && !scales.isEmpty()) {
                predicates.add(companyInformationJoin.get("scale").in(scales));
            }
            if (financingStages != null && !financingStages.isEmpty()) {
                predicates.add(companyInformationJoin.get("financingStage").in(financingStages));
            }
            if (comprehensions != null && !comprehensions.isEmpty()) {
                predicates.add(companyInformationJoin.get("comprehensionName").in(comprehensions));
            }
            if (workingPlace != null) {
                Float longitude = Float.valueOf(workingPlace.split(",")[0]);
                Float latitude = Float.valueOf(workingPlace.split(",")[1]);
                Expression<Double> expression = cb.sqrt(cb.diff(
                        cb.prod(cb.diff(root.get("longitude"), longitude),
                                cb.diff(root.get("longitude"), longitude)),
                        cb.prod(cb.diff(root.get("latitude"), latitude),
                                cb.diff(root.get("latitude"), latitude))));
                query.orderBy(cb.asc(expression));
            }
            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
        };
        Page<PositionInformation> positionInformations = positionInformationRepository.findAll(specification, pageable);
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
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        if (userDetails.getJsonWebToken().getAccountType() == 1) {
            Optional<UserInformation> userInformationOptional = userInformationRepository
                    .findById(userDetails.getJsonWebToken().getFullInformationId());
            userInspectionRecordRepository
                    .save(new UserInspectionRecord().setUserInformation(userInformationOptional.get())
                            .setCompanyInformation(companyInformationOptional.get())
                            .setPositionInformation(positionInformationOptional.get()));
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
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        endDate = calendar.getTime();
        while (startDate.before(endDate)) {
            BigData bigData = new BigData();
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            bigData.setDate(startDate);
            bigData.setInspectionRecordCount(userInspectionRecordRepository
                    .countByCompanyInformationAndCreatedAtBetween(companyInformationOptional.get(),
                            startDate, calendar.getTime()));
            bigData.setDeliveryRecordCount(deliveryRecordRepository
                    .countByCompanyInformationAndCreatedAtBetween(companyInformationOptional.get(),
                            startDate,
                            calendar.getTime()));
            bigData.setOnlineCommunicateCount(0);
            bigDatas.add(bigData);
            startDate = calendar.getTime();
        }
        return serviceToControllerBody.success(bigDatas);
    }

}
