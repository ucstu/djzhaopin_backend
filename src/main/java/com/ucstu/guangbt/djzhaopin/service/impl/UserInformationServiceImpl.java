package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.EducationExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.JobExpectation;
import com.ucstu.guangbt.djzhaopin.entity.user.ProjectExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.repository.company.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.company.position.PositionInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.AttentionRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.DeliveryRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.EducationExperienceRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.GarnerRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.JobExpectationRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.ProjectExperienceRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.UserInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.UserInspectionRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.WorkExperienceRepository;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Resource
    private UserInformationRepository userInformationRepository;

    @Resource
    private AttentionRecordRepository attentionRecordRepository;

    @Resource
    private DeliveryRecordRepository deliveryRecordRepository;

    @Resource
    private EducationExperienceRepository educationExperienceRepository;

    @Resource
    private GarnerRecordRepository garnerRecordRepository;

    @Resource
    private JobExpectationRepository jobExpectationRepository;

    @Resource
    private ProjectExperienceRepository projectExperienceRepository;

    @Resource
    private WorkExperienceRepository workExperienceRepository;

    @Resource
    private UserInspectionRecordRepository userInspectionRecordRepository;

    @Resource
    private CompanyInformationRepository companyInformationRepository;

    @Resource
    private PositionInformationRepository positionInformationRepository;

    @Override
    @Transactional
    public ServiceToControllerBody<UserInformation> createUserInformation(UserInformation userInformation) {
        ServiceToControllerBody<UserInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        return serviceToControllerBody.created(userInformationRepository.save(userInformation));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<UserInformation> deleteUserInformationByUserInformationId(UUID userInformationId) {
        ServiceToControllerBody<UserInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        userInformationRepository.delete(userInformationOptional.get());
        return serviceToControllerBody.success(userInformationOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<UserInformation> updateUserInformationByUserInformationId(UUID userInformationId,
            UserInformation userInformation) {
        ServiceToControllerBody<UserInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        userInformation.setUserInformationId(userInformationId);
        return serviceToControllerBody.success(userInformationRepository.save(userInformation));
    }

    @Override
    public ServiceToControllerBody<PageResult<UserInformation>> getUserInformations(Pageable pageable) {
        ServiceToControllerBody<PageResult<UserInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<UserInformation> userInformations = userInformationRepository.findAll(pageable);
        PageResult<UserInformation> pageResult = new PageResult<>();
        if (!userInformations.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(userInformations.getTotalElements());
        pageResult.setContents(userInformations.getContent());
        pageResult.setContentsName("userInformations");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<UserInformation> getUserInformationByUserInformationId(UUID userInformationId) {
        ServiceToControllerBody<UserInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        return serviceToControllerBody.success(userInformationOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<JobExpectation> createJobExpectation(UUID userInformationId,
            JobExpectation jobExpectation) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        jobExpectation.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.created(jobExpectationRepository.save(jobExpectation));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<JobExpectation> deleteJobExpectationByJobExpectationId(UUID userInformationId,
            UUID jobExpectationId) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<JobExpectation> jobExpectationOptional = userInformationOptional.get().getJobExpectations().stream()
                .filter(jobExpectation -> jobExpectation.getJobExpectationId().equals(jobExpectationId))
                .findAny();
        if (!jobExpectationOptional.isPresent()) {
            return serviceToControllerBody.error("jobExpectationId", "职位期望不存在", jobExpectationId);
        }
        jobExpectationRepository.delete(jobExpectationOptional.get());
        return serviceToControllerBody.success(jobExpectationOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<JobExpectation> updateJobExpectationByJobExpectationId(UUID userInformationId,
            UUID jobExpectationId, JobExpectation jobExpectation) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<JobExpectation> jobExpectationOptional = userInformationOptional.get().getJobExpectations().stream()
                .filter(jobExpectation1 -> jobExpectation1.getJobExpectationId().equals(jobExpectationId))
                .findAny();
        if (!jobExpectationOptional.isPresent()) {
            return serviceToControllerBody.error("jobExpectationId", "职位期望不存在", jobExpectationId);
        }
        jobExpectation.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.success(jobExpectationRepository.save(jobExpectation));
    }

    @Override
    public ServiceToControllerBody<PageResult<JobExpectation>> getJobExpectationsByUserInformationId(
            UUID userInformationId, Pageable pageable) {
        ServiceToControllerBody<PageResult<JobExpectation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Page<JobExpectation> jobExpectations = jobExpectationRepository.findByUserInformation(
                userInformationOptional.get(), pageable);
        PageResult<JobExpectation> pageResult = new PageResult<>();
        if (!jobExpectations.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(jobExpectations.getTotalElements());
        pageResult.setContents(jobExpectations.getContent());
        pageResult.setContentsName("jobExpectations");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<JobExpectation> getJobExpectationByJobExpectationId(UUID userInformationId,
            UUID jobExpectationId) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<JobExpectation> jobExpectationOptional = userInformationOptional.get().getJobExpectations().stream()
                .filter(jobExpectation -> jobExpectation.getJobExpectationId().equals(jobExpectationId))
                .findAny();
        if (!jobExpectationOptional.isPresent()) {
            return serviceToControllerBody.error("jobExpectationId", "职位期望不存在", jobExpectationId);
        }
        return serviceToControllerBody.success(jobExpectationOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<EducationExperience> createEducationExperience(UUID userInformationId,
            EducationExperience educationExperience) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        educationExperience.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.created(educationExperienceRepository.save(educationExperience));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<EducationExperience> deleteEducationExperienceByEducationExperienceId(
            UUID userInformationId, UUID eduExperienceId) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<EducationExperience> educationExperienceOptional = userInformationOptional.get()
                .getEducationExperiences().stream()
                .filter(educationExperience -> educationExperience.getEducationExperienceId().equals(eduExperienceId))
                .findAny();
        if (!educationExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("eduExperienceId", "教育经历不存在", eduExperienceId);
        }
        educationExperienceRepository.delete(educationExperienceOptional.get());
        return serviceToControllerBody.success(educationExperienceOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<EducationExperience> updateEducationExperienceByEducationExperienceId(
            UUID userInformationId, UUID eduExperienceId, EducationExperience educationExperience) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<EducationExperience> educationExperienceOptional = userInformationOptional.get()
                .getEducationExperiences().stream()
                .filter(educationExperience1 -> educationExperience1.getEducationExperienceId().equals(eduExperienceId))
                .findAny();
        if (!educationExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("eduExperienceId", "教育经历不存在", eduExperienceId);
        }
        educationExperience.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.success(educationExperienceRepository.save(educationExperience));
    }

    @Override
    public ServiceToControllerBody<PageResult<EducationExperience>> getEducationExperiencesByUserInformationId(
            UUID userInformationId, Pageable pageable) {
        ServiceToControllerBody<PageResult<EducationExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Page<EducationExperience> educationExperiences = educationExperienceRepository.findByUserInformation(
                userInformationOptional.get(), pageable);
        PageResult<EducationExperience> pageResult = new PageResult<>();
        if (!educationExperiences.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(educationExperiences.getTotalElements());
        pageResult.setContents(educationExperiences.getContent());
        pageResult.setContentsName("educationExperiences");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<EducationExperience> getEducationExperienceByEducationExperienceId(
            UUID userInformationId, UUID eduExperienceId) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<EducationExperience> educationExperienceOptional = userInformationOptional.get()
                .getEducationExperiences().stream()
                .filter(educationExperience -> educationExperience.getEducationExperienceId().equals(eduExperienceId))
                .findAny();
        if (!educationExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("eduExperienceId", "教育经历不存在", eduExperienceId);
        }
        return serviceToControllerBody.success(educationExperienceOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<WorkExperience> createWorkExperience(UUID userInformationId,
            WorkExperience workExperience) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        workExperience.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.created(workExperienceRepository.save(workExperience));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<WorkExperience> deleteWorkExperienceByWorkExperienceId(UUID userInformationId,
            UUID workExperienceId) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<WorkExperience> workExperienceOptional = userInformationOptional.get()
                .getWorkExperiences().stream()
                .filter(workExperience -> workExperience.getWorkExperienceId().equals(workExperienceId))
                .findAny();
        if (!workExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("workExperienceId", "工作经历不存在", workExperienceId);
        }
        workExperienceRepository.delete(workExperienceOptional.get());
        return serviceToControllerBody.success(workExperienceOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<WorkExperience> updateWorkExperienceByWorkExperienceId(UUID userInformationId,
            UUID workExperienceId, WorkExperience workExperience) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<WorkExperience> workExperienceOptional = userInformationOptional.get()
                .getWorkExperiences().stream()
                .filter(workExperience1 -> workExperience1.getWorkExperienceId().equals(workExperienceId))
                .findAny();
        if (!workExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("workExperienceId", "工作经历不存在", workExperienceId);
        }
        workExperience.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.success(workExperienceRepository.save(workExperience));
    }

    @Override
    public ServiceToControllerBody<PageResult<WorkExperience>> getWorkExperiencesByUserInformationId(
            UUID userInformationId, Pageable pageable) {
        ServiceToControllerBody<PageResult<WorkExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Page<WorkExperience> workExperiences = workExperienceRepository.findByUserInformation(
                userInformationOptional.get(), pageable);
        PageResult<WorkExperience> pageResult = new PageResult<>();
        if (!workExperiences.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(workExperiences.getTotalElements());
        pageResult.setContents(workExperiences.getContent());
        pageResult.setContentsName("workExperiences");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<WorkExperience> getWorkExperienceByWorkExperienceId(UUID userInformationId,
            UUID workExperienceId) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<WorkExperience> workExperienceOptional = userInformationOptional.get()
                .getWorkExperiences().stream()
                .filter(workExperience -> workExperience.getWorkExperienceId().equals(workExperienceId))
                .findAny();
        if (!workExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("workExperienceId", "工作经历不存在", workExperienceId);
        }
        return serviceToControllerBody.success(workExperienceOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<ProjectExperience> createProjectExperience(UUID userInformationId,
            ProjectExperience projectExperience) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        projectExperience.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.created(projectExperienceRepository.save(projectExperience));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<ProjectExperience> deleteProjectExperienceByProjectExperienceId(
            UUID userInformationId, UUID projectExperienceId) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<ProjectExperience> projectExperienceOptional = userInformationOptional.get()
                .getProjectExperiences().stream()
                .filter(projectExperience -> projectExperience.getProjectExperienceId().equals(projectExperienceId))
                .findAny();
        if (!projectExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("projectExperienceId", "项目经历不存在", projectExperienceId);
        }
        projectExperienceRepository.delete(projectExperienceOptional.get());
        return serviceToControllerBody.success(projectExperienceOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<ProjectExperience> updateProjectExperienceByProjectExperienceId(
            UUID userInformationId, UUID projectExperienceId, ProjectExperience projectExperience) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<ProjectExperience> projectExperienceOptional = userInformationOptional.get()
                .getProjectExperiences().stream()
                .filter(projectExperience1 -> projectExperience1.getProjectExperienceId().equals(projectExperienceId))
                .findAny();
        if (!projectExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("projectExperienceId", "项目经历不存在", projectExperienceId);
        }
        projectExperience.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.success(projectExperienceRepository.save(projectExperience));
    }

    @Override
    public ServiceToControllerBody<PageResult<ProjectExperience>> getProjectExperiencesByUserInformationId(
            UUID userInformationId, Pageable pageable) {
        ServiceToControllerBody<PageResult<ProjectExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Page<ProjectExperience> projectExperiences = projectExperienceRepository.findByUserInformation(
                userInformationOptional.get(), pageable);
        PageResult<ProjectExperience> pageResult = new PageResult<>();
        if (!projectExperiences.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(projectExperiences.getTotalElements());
        pageResult.setContents(projectExperiences.getContent());
        pageResult.setContentsName("projectExperiences");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> getProjectExperienceByProjectExperienceId(UUID userInformationId,
            UUID projectExperienceId) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<ProjectExperience> projectExperienceOptional = userInformationOptional.get()
                .getProjectExperiences().stream()
                .filter(projectExperience -> projectExperience.getProjectExperienceId().equals(projectExperienceId))
                .findAny();
        if (!projectExperienceOptional.isPresent()) {
            return serviceToControllerBody.error("projectExperienceId", "项目经历不存在", projectExperienceId);
        }
        return serviceToControllerBody.success(projectExperienceOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<DeliveryRecord> createDeliveryRecord(UUID userInformationId,
            DeliveryRecord deliveryRecord) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository.findById(deliveryRecord
                .getCompanyInformationId());
        Optional<PositionInformation> positionInformationOptional = positionInformationRepository
                .findById(deliveryRecord.getPositionInformationId());
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在",
                    deliveryRecord.getCompanyInformationId());
        }
        if (!positionInformationOptional.isPresent()) {
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在",
                    deliveryRecord.getPositionInformationId());
        }
        return serviceToControllerBody.created(deliveryRecordRepository.save(deliveryRecord));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<DeliveryRecord> deleteDeliveryRecordByDeliveryRecordId(UUID userInformationId,
            UUID deliveryRecordId) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<DeliveryRecord> deliveryRecordOptional = userInformationOptional.get()
                .getDeliveryRecords().stream()
                .filter(deliveryRecord -> deliveryRecord.getDeliveryRecordId().equals(deliveryRecordId))
                .findAny();
        if (!deliveryRecordOptional.isPresent()) {
            return serviceToControllerBody.error("deliveryRecordId", "投递记录不存在", deliveryRecordId);
        }
        deliveryRecordRepository.delete(deliveryRecordOptional.get());
        return serviceToControllerBody.success(deliveryRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<DeliveryRecord> updateDeliveryRecordByDeliveryRecordId(UUID userInformationId,
            UUID deliveryRecordId, DeliveryRecord deliveryRecord) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository.findById(deliveryRecord
                .getCompanyInformationId());
        Optional<PositionInformation> positionInformationOptional = positionInformationRepository
                .findById(deliveryRecord.getPositionInformationId());
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在",
                    deliveryRecord.getCompanyInformationId());
        }
        if (!positionInformationOptional.isPresent()) {
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在",
                    deliveryRecord.getPositionInformationId());
        }
        Optional<DeliveryRecord> deliveryRecordOptional = userInformationOptional.get()
                .getDeliveryRecords().stream()
                .filter(deliveryRecord1 -> deliveryRecord1.getDeliveryRecordId().equals(deliveryRecordId))
                .findAny();
        if (!deliveryRecordOptional.isPresent()) {
            return serviceToControllerBody.error("deliveryRecordId", "投递记录不存在", deliveryRecordId);
        }
        return serviceToControllerBody.success(deliveryRecordRepository.save(deliveryRecord));
    }

    @Override
    public ServiceToControllerBody<PageResult<DeliveryRecord>> getDeliveryRecordsByUserInformationId(
            UUID userInformationId, Integer status, Pageable pageable) {
        ServiceToControllerBody<PageResult<DeliveryRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (!userInformation.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Example<DeliveryRecord> deliveryRecordExample = Example
                .of(new DeliveryRecord().setStatus(status).setUserInformation(userInformation.get()));
        Page<DeliveryRecord> deliveryRecords = deliveryRecordRepository.findAll(deliveryRecordExample, pageable);
        PageResult<DeliveryRecord> pageResult = new PageResult<>();
        if (!deliveryRecords.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(deliveryRecords.getTotalElements());
        pageResult.setContents(deliveryRecords.getContent());
        pageResult.setContentsName("deliveryRecords");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> getDeliveryRecordByDeliveryRecordId(UUID userInformationId,
            UUID deliveryRecordId) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<DeliveryRecord> deliveryRecordOptional = userInformationOptional.get()
                .getDeliveryRecords().stream()
                .filter(deliveryRecord -> deliveryRecord.getDeliveryRecordId().equals(deliveryRecordId))
                .findAny();
        if (!deliveryRecordOptional.isPresent()) {
            return serviceToControllerBody.error("deliveryRecordId", "投递记录不存在", deliveryRecordId);
        }
        return serviceToControllerBody.success(deliveryRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<AttentionRecord> createAttentionRecord(UUID userInformationId,
            AttentionRecord attentionRecord) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(attentionRecord.getCompanyInformationId());
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在",
                    attentionRecord.getCompanyInformationId());
        }
        return serviceToControllerBody.success(attentionRecordRepository.save(attentionRecord));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<AttentionRecord> deleteAttentionRecordByAttentionRecordId(UUID userInformationId,
            UUID attentionRecordId) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<AttentionRecord> attentionRecordOptional = userInformationOptional.get()
                .getAttentionRecords().stream()
                .filter(attentionRecord -> attentionRecord.getAttentionRecordId().equals(attentionRecordId))
                .findAny();
        if (!attentionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("attentionRecordId", "关注记录不存在", attentionRecordId);
        }
        attentionRecordRepository.delete(attentionRecordOptional.get());
        return serviceToControllerBody.success(attentionRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<AttentionRecord> updateAttentionRecordByAttentionRecordId(UUID userInformationId,
            UUID attentionRecordId,
            AttentionRecord attentionRecord) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(attentionRecord.getCompanyInformationId());
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在",
                    attentionRecord.getCompanyInformationId());
        }
        Optional<AttentionRecord> attentionRecordOptional = userInformationOptional.get()
                .getAttentionRecords().stream()
                .filter(attentionRecord1 -> attentionRecord1.getAttentionRecordId().equals(attentionRecordId))
                .findAny();
        if (!attentionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("attentionRecordId", "关注记录不存在", attentionRecordId);
        }
        attentionRecord.setAttentionRecordId(attentionRecordId);
        return serviceToControllerBody.success(attentionRecordRepository.save(attentionRecord));
    }

    @Override
    public ServiceToControllerBody<PageResult<AttentionRecord>> getAttentionRecordsByUserInformationId(
            UUID userInformationId, Pageable pageable) {
        ServiceToControllerBody<PageResult<AttentionRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Page<AttentionRecord> attentionRecords = attentionRecordRepository
                .findByUserInformation(userInformationOptional.get(), pageable);
        PageResult<AttentionRecord> pageResult = new PageResult<>();
        if (!attentionRecords.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(attentionRecords.getTotalElements());
        pageResult.setContents(attentionRecords.getContent());
        pageResult.setContentsName("attentionRecords");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> getAttentionRecordByAttentionRecordId(UUID userInformationId,
            UUID attentionRecordId) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<AttentionRecord> attentionRecordOptional = userInformationOptional.get()
                .getAttentionRecords().stream()
                .filter(attentionRecord -> attentionRecord.getAttentionRecordId().equals(attentionRecordId))
                .findAny();
        if (!attentionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("attentionRecordId", "关注记录不存在", attentionRecordId);
        }
        return serviceToControllerBody.success(attentionRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<UserInspectionRecord> createUserInspectionRecord(UUID userInformationId,
            UserInspectionRecord inspectionRecord) {
        ServiceToControllerBody<UserInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        inspectionRecord.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.success(userInspectionRecordRepository.save(inspectionRecord));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<UserInspectionRecord> deleteUserInspectionRecordByUserInspectionRecordId(
            UUID userInformationId, UUID inspectionRecordId) {
        ServiceToControllerBody<UserInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<UserInspectionRecord> userInspectionRecordOptional = userInformationOptional.get()
                .getUserInspectionRecords().stream()
                .filter(userInspectionRecord -> userInspectionRecord.getUserInspectionRecordId()
                        .equals(inspectionRecordId))
                .findAny();
        if (!userInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("inspectionRecordId", "查看记录不存在", inspectionRecordId);
        }
        userInspectionRecordRepository.delete(userInspectionRecordOptional.get());
        return serviceToControllerBody.success(userInspectionRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<UserInspectionRecord> updateUserInspectionRecordByUserInspectionRecordId(
            UUID userInformationId, UUID inspectionRecordId, UserInspectionRecord inspectionRecord) {
        ServiceToControllerBody<UserInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<UserInspectionRecord> userInspectionRecordOptional = userInformationOptional.get()
                .getUserInspectionRecords().stream()
                .filter(userInspectionRecord -> userInspectionRecord.getUserInspectionRecordId()
                        .equals(inspectionRecordId))
                .findAny();
        if (!userInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("inspectionRecordId", "查看记录不存在", inspectionRecordId);
        }
        inspectionRecord.setUserInformation(userInformationOptional.get());
        return serviceToControllerBody.success(userInspectionRecordRepository.save(inspectionRecord));
    }

    @Override
    public ServiceToControllerBody<PageResult<UserInspectionRecord>> getUserInspectionRecordsByUserInformationId(
            UUID userInformationId, Pageable pageable) {
        ServiceToControllerBody<PageResult<UserInspectionRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Page<UserInspectionRecord> userInspectionRecords = userInspectionRecordRepository.findByUserInformation(
                userInformationOptional.get(), pageable);
        PageResult<UserInspectionRecord> pageResult = new PageResult<>();
        if (!userInspectionRecords.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(userInspectionRecords.getTotalElements());
        pageResult.setContents(userInspectionRecords.getContent());
        pageResult.setContentsName("userInspectionRecords");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<UserInspectionRecord> getUserInspectionRecordByUserInspectionRecordId(
            UUID userInformationId, UUID inspectionRecordId) {
        ServiceToControllerBody<UserInspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<UserInspectionRecord> userInspectionRecordOptional = userInformationOptional.get()
                .getUserInspectionRecords().stream()
                .filter(userInspectionRecord -> userInspectionRecord.getUserInspectionRecordId()
                        .equals(inspectionRecordId))
                .findAny();
        if (!userInspectionRecordOptional.isPresent()) {
            return serviceToControllerBody.error("inspectionRecordId", "查看记录不存在", inspectionRecordId);
        }
        return serviceToControllerBody.success(userInspectionRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<GarnerRecord> createGarnerRecord(UUID userInformationId, GarnerRecord garnerRecord) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(garnerRecord.getCompanyInformationId());
        Optional<PositionInformation> positionInformationOptional = positionInformationRepository
                .findById(garnerRecord.getPositionInformationId());
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在",
                    garnerRecord.getCompanyInformationId());
        }
        if (!positionInformationOptional.isPresent()) {
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在",
                    garnerRecord.getPositionInformationId());
        }
        return serviceToControllerBody.success(garnerRecordRepository.save(garnerRecord));
    }

    @Override
    @Transactional
    public ServiceToControllerBody<GarnerRecord> deleteGarnerRecordByGarnerRecordId(UUID userInformationId,
            UUID garnerRecordId) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<GarnerRecord> garnerRecordOptional = userInformationOptional.get()
                .getGarnerRecords().stream()
                .filter(garnerRecord -> garnerRecord.getGarnerRecordId()
                        .equals(garnerRecordId))
                .findAny();
        if (!garnerRecordOptional.isPresent()) {
            return serviceToControllerBody.error("garnerRecordId", "收藏记录不存在", garnerRecordId);
        }
        garnerRecordRepository.delete(garnerRecordOptional.get());
        return serviceToControllerBody.success(garnerRecordOptional.get());
    }

    @Override
    @Transactional
    public ServiceToControllerBody<GarnerRecord> updateGarnerRecordByGarnerRecordId(UUID userInformationId,
            UUID garnerRecordId, GarnerRecord garnerRecord) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(garnerRecord.getCompanyInformationId());
        Optional<PositionInformation> positionInformationOptional = positionInformationRepository
                .findById(garnerRecord.getPositionInformationId());
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        if (!companyInformationOptional.isPresent()) {
            return serviceToControllerBody.error("companyInformationId", "公司信息不存在",
                    garnerRecord.getCompanyInformationId());
        }
        if (!positionInformationOptional.isPresent()) {
            return serviceToControllerBody.error("positionInformationId", "职位信息不存在",
                    garnerRecord.getPositionInformationId());
        }
        Optional<GarnerRecord> garnerRecordOptional = userInformationOptional.get()
                .getGarnerRecords().stream()
                .filter(garnerRecord1 -> garnerRecord1.getGarnerRecordId()
                        .equals(garnerRecordId))
                .findAny();
        if (!garnerRecordOptional.isPresent()) {
            return serviceToControllerBody.error("garnerRecordId", "收藏记录不存在", garnerRecordId);
        }
        garnerRecord.setGarnerRecordId(garnerRecordId);
        return serviceToControllerBody.success(garnerRecordRepository.save(garnerRecord));
    }

    @Override
    public ServiceToControllerBody<PageResult<GarnerRecord>> getGarnerRecordsByUserInformationId(UUID userInformationId,
            Pageable pageable) {
        ServiceToControllerBody<PageResult<GarnerRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Page<GarnerRecord> garnerRecords = garnerRecordRepository.findByUserInformation(userInformationOptional.get(),
                pageable);
        PageResult<GarnerRecord> pageResult = new PageResult<>();
        if (!garnerRecords.hasContent()) {
            pageResult.setTotalCount(0);
            pageResult.setContents(new ArrayList<>());
            return serviceToControllerBody.success(pageResult);
        }
        pageResult.setTotalCount(garnerRecords.getTotalElements());
        pageResult.setContents(garnerRecords.getContent());
        pageResult.setContentsName("garnerRecords");
        return serviceToControllerBody.success(pageResult);
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> getGarnerRecordByGarnerRecordId(UUID userInformationId,
            UUID garnerRecordId) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (!userInformationOptional.isPresent()) {
            return serviceToControllerBody.error("userInformationId", "用户信息不存在", userInformationId);
        }
        Optional<GarnerRecord> garnerRecordOptional = userInformationOptional.get()
                .getGarnerRecords().stream()
                .filter(garnerRecord -> garnerRecord.getGarnerRecordId()
                        .equals(garnerRecordId))
                .findAny();
        if (!garnerRecordOptional.isPresent()) {
            return serviceToControllerBody.error("garnerRecordId", "收藏记录不存在", garnerRecordId);
        }
        return serviceToControllerBody.success(garnerRecordOptional.get());
    }

}
