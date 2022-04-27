package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.EducationExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.InspectionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.JobExpectation;
import com.ucstu.guangbt.djzhaopin.entity.user.ProjectExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.repository.UserInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Resource
    private UserInformationRepository userInformationRepository;

    @Override
    public ServiceToControllerBody<UserInformation> updateUserInformationByUserInformationId(UUID userInformationId,
            UserInformation userInformation) {
        ServiceToControllerBody<UserInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (userInformationOptional.isPresent()) {
            UserInformation userInformation2 = userInformationOptional.get();
            userInformation2.setAge(userInformation.getAge());
            userInformation2.setAvatar(userInformation.getAvatar());
            userInformation2.setCity(userInformation.getCity());
            userInformation2.setDateOfBirth(userInformation.getDateOfBirth());
            userInformation2.setEducation(userInformation.getEducation());
            userInformation2.setEmail(userInformation.getEmail());
            userInformation2.setFirstName(userInformation.getFirstName());
            userInformation2.setJobStatus(userInformation.getJobStatus());
            userInformation2.setLastName(userInformation.getLastName());
            userInformation2.setPersonalAdvantage(userInformation.getPersonalAdvantage());
            userInformation2.setPhoneNumber(userInformation.getPhoneNumber());
            userInformation2.setPictureWorks(userInformation.getPictureWorks());
            userInformation2.setPrivacySettings(userInformation.getPrivacySettings());
            userInformation2.setSex(userInformation.getSex());
            userInformation2.setSocialHomepage(userInformation.getSocialHomepage());
            userInformation2.setWorkingYears(userInformation.getWorkingYears());
            return serviceToControllerBody.setContent(userInformationRepository.save(userInformation2));
        }
        return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
    }

    @Override
    public ServiceToControllerBody<List<UserInformation>> getUserInformations(Pageable pageable) {
        ServiceToControllerBody<List<UserInformation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Page<UserInformation> userInformations = userInformationRepository.findAll(pageable);
        return serviceToControllerBody.setContent(userInformations.getContent());
    }

    @Override
    public ServiceToControllerBody<UserInformation> getUserInformationByUserInformationId(UUID userInformationId) {
        ServiceToControllerBody<UserInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (userInformationOptional.isPresent()) {
            return serviceToControllerBody.setContent(userInformationOptional.get());
        }
        return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
    }

    @Override
    public ServiceToControllerBody<JobExpectation> createJobExpectation(UUID userInformationId,
            JobExpectation jobExpectation) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userInformationId);
        if (userInformationOptional.isPresent()) {
            userInformationOptional.get().getJobExpectations().add(jobExpectation);
            return serviceToControllerBody.setContent(jobExpectation);
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<JobExpectation> deleteJobExpectationByJobExpectationId(UUID userInformationId,
            UUID jobExpectationId) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<JobExpectation> jobExpectation = userInformation.get().getJobExpectations()
                    .stream().filter(jobExpectation1 -> jobExpectationId.equals(jobExpectation1.getJobExpectationId()))
                    .findFirst();
            if (jobExpectation.isPresent()) {
                userInformation.get().getJobExpectations().remove(jobExpectation.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(jobExpectation.get());
            } else {
                return serviceToControllerBody.error("jobExpectationId", "没有找到该职位期望", jobExpectationId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<JobExpectation> updateJobExpectationByJobExpectationId(UUID userInformationId,
            UUID jobExpectationId,
            JobExpectation jobExpectation) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<JobExpectation> jobExpectation1 = userInformation.get().getJobExpectations()
                    .stream().filter(jobExpectation2 -> jobExpectationId.equals(jobExpectation2.getJobExpectationId()))
                    .findFirst();
            if (jobExpectation1.isPresent()) {
                jobExpectation1.get().setCeilingSalary(jobExpectation.getCeilingSalary());
                jobExpectation1.get().setCity(jobExpectation.getCity());
                jobExpectation1.get().setDirectionTags(jobExpectation.getDirectionTags());
                jobExpectation1.get().setPositionName(jobExpectation.getPositionName());
                jobExpectation1.get().setPositionType(jobExpectation.getPositionType());
                jobExpectation1.get().setStartingSalary(jobExpectation.getStartingSalary());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(jobExpectation1.get());
            } else {
                return serviceToControllerBody.error("jobExpectationId", "没有找到该职位期望", jobExpectationId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<List<JobExpectation>> getJobExpectationsByUserInformationId(UUID userInformationId,
            Pageable pageable) {
        ServiceToControllerBody<List<JobExpectation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getJobExpectations());
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<JobExpectation> getJobExpectationByJobExpectationId(UUID userInformationId,
            UUID jobExpectationId) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<JobExpectation> jobExpectation = userInformation.get().getJobExpectations()
                    .stream().filter(jobExpectation1 -> jobExpectationId.equals(jobExpectation1.getJobExpectationId()))
                    .findFirst();
            if (jobExpectation.isPresent()) {
                return serviceToControllerBody.setContent(jobExpectation.get());
            } else {
                return serviceToControllerBody.error("jobExpectationId", "没有找到该职位期望", jobExpectationId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<EducationExperience> createEducationExperience(UUID userInformationId,
            EducationExperience educationExperience) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            userInformation.get().getEducationExperiences().add(educationExperience);
            return serviceToControllerBody.setContent(educationExperience);
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<EducationExperience> deleteEducationExperienceByEducationExperienceId(
            UUID userInformationId,
            UUID eduExperienceId) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<EducationExperience> educationExperience = userInformation.get().getEducationExperiences()
                    .stream()
                    .filter(educationExperience1 -> eduExperienceId
                            .equals(educationExperience1.getEducationExperienceId()))
                    .findFirst();
            if (educationExperience.isPresent()) {
                userInformation.get().getEducationExperiences().remove(educationExperience.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(educationExperience.get());
            } else {
                return serviceToControllerBody.error("eduExperienceId", "没有找到该教育经历", eduExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<EducationExperience> updateEducationExperienceByEducationExperienceId(
            UUID userInformationId,
            UUID eduExperienceId,
            EducationExperience educationExperience) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<EducationExperience> educationExperience1 = userInformation.get().getEducationExperiences()
                    .stream()
                    .filter(educationExperience2 -> eduExperienceId
                            .equals(educationExperience2.getEducationExperienceId()))
                    .findFirst();
            if (educationExperience1.isPresent()) {
                educationExperience1.get().setAdmissionTime(educationExperience.getAdmissionTime());
                educationExperience1.get().setEducation(educationExperience.getEducation());
                educationExperience1.get().setGraduationTime(educationExperience.getGraduationTime());
                educationExperience1.get().setMajor(educationExperience.getMajor());
                educationExperience1.get().setSchoolName(educationExperience.getSchoolName());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(educationExperience1.get());
            } else {
                return serviceToControllerBody.error("eduExperienceId", "没有找到该教育经历", eduExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<List<EducationExperience>> getEducationExperiencesByUserInformationId(
            UUID userInformationId,
            Pageable pageable) {
        ServiceToControllerBody<List<EducationExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getEducationExperiences());
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<EducationExperience> getEducationExperienceByEducationExperienceId(
            UUID userInformationId,
            UUID eduExperienceId) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<EducationExperience> educationExperience = userInformation.get().getEducationExperiences()
                    .stream()
                    .filter(educationExperience1 -> eduExperienceId
                            .equals(educationExperience1.getEducationExperienceId()))
                    .findFirst();
            if (educationExperience.isPresent()) {
                return serviceToControllerBody.setContent(educationExperience.get());
            } else {
                return serviceToControllerBody.error("eduExperienceId", "没有找到该教育经历", eduExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<WorkExperience> createWorkExperience(UUID userInformationId,
            WorkExperience workExperience) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            userInformation.get().getWorkExperiences().add(workExperience);
            return serviceToControllerBody.setContent(workExperience);
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<WorkExperience> deleteWorkExperienceByWorkExperienceId(UUID userInformationId,
            UUID workExperienceId) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<WorkExperience> workExperience = userInformation.get().getWorkExperiences()
                    .stream()
                    .filter(workExperience1 -> workExperienceId
                            .equals(workExperience1.getWorkExperienceId()))
                    .findFirst();
            if (workExperience.isPresent()) {
                userInformation.get().getWorkExperiences().remove(workExperience.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(workExperience.get());
            } else {
                return serviceToControllerBody.error("workExperienceId", "没有找到该工作经历", workExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<WorkExperience> updateWorkExperienceByWorkExperienceId(UUID userInformationId,
            UUID workExperienceId,
            WorkExperience workExperience) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<WorkExperience> workExperience1 = userInformation.get().getWorkExperiences()
                    .stream()
                    .filter(workExperience2 -> workExperienceId
                            .equals(workExperience2.getWorkExperienceId()))
                    .findFirst();
            if (workExperience1.isPresent()) {
                workExperience1.get().setCompanyIndustry(workExperience.getCompanyIndustry());
                workExperience1.get().setCorporateName(workExperience.getCorporateName());
                workExperience1.get().setDepartment(workExperience.getDepartment());
                workExperience1.get().setEndTime(workExperience.getEndTime());
                workExperience1.get().setJobContent(workExperience.getJobContent());
                workExperience1.get().setPositionName(workExperience.getPositionName());
                workExperience1.get().setPositionType(workExperience.getPositionType());
                workExperience1.get().setStartTime(workExperience.getStartTime());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(workExperience1.get());
            } else {
                return serviceToControllerBody.error("workExperienceId", "没有找到该工作经历", workExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<List<WorkExperience>> getWorkExperiencesByUserInformationId(UUID userInformationId,
            Pageable pageable) {
        ServiceToControllerBody<List<WorkExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getWorkExperiences());
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<WorkExperience> getWorkExperienceByWorkExperienceId(UUID userInformationId,
            UUID workExperienceId) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<WorkExperience> workExperience = userInformation.get().getWorkExperiences()
                    .stream()
                    .filter(workExperience1 -> workExperienceId
                            .equals(workExperience1.getWorkExperienceId()))
                    .findFirst();
            if (workExperience.isPresent()) {
                return serviceToControllerBody.setContent(workExperience.get());
            } else {
                return serviceToControllerBody.error("workExperienceId", "没有找到该工作经历", workExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> createProjectExperience(UUID userInformationId,
            ProjectExperience projectExperience) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            userInformation.get().getProjectExperiences().add(projectExperience);
            return serviceToControllerBody.setContent(projectExperience);
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> deleteProjectExperienceByProjectExperienceId(
            UUID userInformationId,
            UUID projectExperienceId) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<ProjectExperience> projectExperience = userInformation.get().getProjectExperiences()
                    .stream()
                    .filter(projectExperience1 -> projectExperienceId
                            .equals(projectExperience1.getProjectExperienceId()))
                    .findFirst();
            if (projectExperience.isPresent()) {
                userInformation.get().getProjectExperiences().remove(projectExperience.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(projectExperience.get());
            } else {
                return serviceToControllerBody.error("projectExperienceId", "没有找到该项目经历", projectExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> updateProjectExperienceByProjectExperienceId(
            UUID userInformationId,
            UUID projectExperienceId,
            ProjectExperience projectExperience) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<ProjectExperience> projectExperience1 = userInformation.get().getProjectExperiences()
                    .stream()
                    .filter(projectExperience2 -> projectExperienceId
                            .equals(projectExperience2.getProjectExperienceId()))
                    .findFirst();
            if (projectExperience1.isPresent()) {
                projectExperience1.get().setAchievement(projectExperience.getAchievement());
                projectExperience1.get().setEndTime(projectExperience.getEndTime());
                projectExperience1.get().setProjectDescription(projectExperience.getProjectDescription());
                projectExperience1.get().setProjectLink(projectExperience.getProjectLink());
                projectExperience1.get().setProjectName(projectExperience.getProjectName());
                projectExperience1.get().setStartTime(projectExperience.getStartTime());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(projectExperience1.get());
            } else {
                return serviceToControllerBody.error("projectExperienceId", "没有找到该项目经历", projectExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<List<ProjectExperience>> getProjectExperiencesByUserInformationId(
            UUID userInformationId,
            Pageable pageable) {
        ServiceToControllerBody<List<ProjectExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getProjectExperiences());
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> getProjectExperienceByProjectExperienceId(UUID userInformationId,
            UUID projectExperienceId) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<ProjectExperience> projectExperience = userInformation.get().getProjectExperiences()
                    .stream()
                    .filter(projectExperience1 -> projectExperienceId
                            .equals(projectExperience1.getProjectExperienceId()))
                    .findFirst();
            if (projectExperience.isPresent()) {
                return serviceToControllerBody.setContent(projectExperience.get());
            } else {
                return serviceToControllerBody.error("projectExperienceId", "没有找到该项目经历", projectExperienceId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> createDeliveryRecord(UUID userInformationId,
            DeliveryRecord deliveryRecord) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            userInformation.get().getDeliveryRecords().add(deliveryRecord);
            return serviceToControllerBody.setContent(deliveryRecord);
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> deleteDeliveryRecordByDeliveryRecordId(UUID userInformationId,
            UUID deliveryRecordId) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<DeliveryRecord> deliveryRecord = userInformation.get().getDeliveryRecords()
                    .stream()
                    .filter(deliveryRecord1 -> deliveryRecordId
                            .equals(deliveryRecord1.getDeliveryRecordId()))
                    .findFirst();
            if (deliveryRecord.isPresent()) {
                userInformation.get().getDeliveryRecords().remove(deliveryRecord.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(deliveryRecord.get());
            } else {
                return serviceToControllerBody.error("deliveryRecordId", "没有找到该项目经历", deliveryRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> updateDeliveryRecordByDeliveryRecordId(UUID userInformationId,
            UUID deliveryRecordId,
            DeliveryRecord deliveryRecord) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<DeliveryRecord> deliveryRecord1 = userInformation.get().getDeliveryRecords()
                    .stream()
                    .filter(deliveryRecord2 -> deliveryRecordId
                            .equals(deliveryRecord2.getDeliveryRecordId()))
                    .findFirst();
            if (deliveryRecord1.isPresent()) {
                deliveryRecord1.get().setInterviewTime(deliveryRecord.getInterviewTime());
                deliveryRecord1.get().setJobInformationId(deliveryRecord.getJobInformationId());
                deliveryRecord1.get().setState(deliveryRecord.getState());
                deliveryRecord1.get().setUserInformationId(deliveryRecord.getUserInformationId());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(deliveryRecord1.get());
            } else {
                return serviceToControllerBody.error("deliveryRecordId", "没有找到该项目经历", deliveryRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<List<DeliveryRecord>> getDeliveryRecordsByUserInformationId(UUID userInformationId,
            Pageable pageable) {
        ServiceToControllerBody<List<DeliveryRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getDeliveryRecords());
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> getDeliveryRecordByDeliveryRecordId(UUID userInformationId,
            UUID deliveryRecordId) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<DeliveryRecord> deliveryRecord = userInformation.get().getDeliveryRecords()
                    .stream()
                    .filter(deliveryRecord1 -> deliveryRecordId
                            .equals(deliveryRecord1.getDeliveryRecordId()))
                    .findFirst();
            if (deliveryRecord.isPresent()) {
                return serviceToControllerBody.setContent(deliveryRecord.get());
            } else {
                return serviceToControllerBody.error("deliveryRecordId", "没有找到该项目经历", deliveryRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> createAttentionRecord(UUID userInformationId,
            AttentionRecord attentionRecord) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            userInformation.get().getAttentionRecords().add(attentionRecord);
            return serviceToControllerBody.setContent(attentionRecord);
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> deleteAttentionRecordByAttentionRecordId(UUID userInformationId,
            UUID attentionRecordId) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<AttentionRecord> attentionRecord = userInformation.get().getAttentionRecords()
                    .stream()
                    .filter(attentionRecord1 -> attentionRecordId
                            .equals(attentionRecord1.getAttentionRecordId()))
                    .findFirst();
            if (attentionRecord.isPresent()) {
                userInformation.get().getAttentionRecords().remove(attentionRecord.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(attentionRecord.get());
            } else {
                return serviceToControllerBody.error("attentionRecordId", "没有找到该关注记录", attentionRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> updateAttentionRecordByAttentionRecordId(UUID userInformationId,
            UUID attentionRecordId,
            AttentionRecord attentionRecord) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<AttentionRecord> attentionRecord1 = userInformation.get().getAttentionRecords()
                    .stream()
                    .filter(attentionRecord2 -> attentionRecordId
                            .equals(attentionRecord2.getAttentionRecordId()))
                    .findFirst();
            if (attentionRecord1.isPresent()) {
                attentionRecord1.get().setCompanyInformationId(attentionRecord.getCompanyInformationId());
                attentionRecord1.get().setUserInformationId(attentionRecord.getUserInformationId());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(attentionRecord1.get());
            } else {
                return serviceToControllerBody.error("attentionRecordId", "没有找到该关注记录", attentionRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<List<AttentionRecord>> getAttentionRecordsByUserInformationId(
            UUID userInformationId) {
        ServiceToControllerBody<List<AttentionRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getAttentionRecords());
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> getAttentionRecordByAttentionRecordId(UUID userInformationId,
            UUID attentionRecordId) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<AttentionRecord> attentionRecord = userInformation.get().getAttentionRecords()
                    .stream()
                    .filter(attentionRecord1 -> attentionRecordId
                            .equals(attentionRecord1.getAttentionRecordId()))
                    .findFirst();
            if (attentionRecord.isPresent()) {
                return serviceToControllerBody.setContent(attentionRecord.get());
            } else {
                return serviceToControllerBody.error("attentionRecordId", "没有找到该关注记录", attentionRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<InspectionRecord> createInspectionRecord(UUID userInformationId,
            InspectionRecord inspectionRecord) {
        ServiceToControllerBody<InspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            userInformation.get().getInspectionRecords().add(inspectionRecord);
            return serviceToControllerBody.setContent(inspectionRecord);
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<InspectionRecord> deleteInspectionRecordByInspectionRecordId(UUID userInformationId,
            UUID inspectionRecordId) {
        ServiceToControllerBody<InspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<InspectionRecord> inspectionRecord = userInformation.get().getInspectionRecords()
                    .stream()
                    .filter(inspectionRecord1 -> inspectionRecordId
                            .equals(inspectionRecord1.getInspectionRecordId()))
                    .findFirst();
            if (inspectionRecord.isPresent()) {
                userInformation.get().getInspectionRecords().remove(inspectionRecord.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(inspectionRecord.get());
            } else {
                return serviceToControllerBody.error("inspectionRecordId", "没有找到该检验记录", inspectionRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<InspectionRecord> updateInspectionRecordByInspectionRecordId(UUID userInformationId,
            UUID inspectionRecordId,
            InspectionRecord inspectionRecord) {
        ServiceToControllerBody<InspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<InspectionRecord> inspectionRecord1 = userInformation.get().getInspectionRecords()
                    .stream()
                    .filter(inspectionRecord2 -> inspectionRecordId
                            .equals(inspectionRecord2.getInspectionRecordId()))
                    .findFirst();
            if (inspectionRecord1.isPresent()) {
                inspectionRecord1.get().setFromId(inspectionRecord.getFromId());
                inspectionRecord1.get().setToId(inspectionRecord.getToId());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(inspectionRecord1.get());
            } else {
                return serviceToControllerBody.error("inspectionRecordId", "没有找到该检验记录", inspectionRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<List<InspectionRecord>> getInspectionRecordsByUserInformationId(
            UUID userInformationId,
            Pageable pageable) {
        ServiceToControllerBody<List<InspectionRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            // TODO 只返回了InspectionRecord，未返回pageable
            return serviceToControllerBody.setContent(userInformation.get().getInspectionRecords());
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<InspectionRecord> getInspectionRecordByInspectionRecordId(UUID userInformationId,
            UUID inspectionRecordId) {
        ServiceToControllerBody<InspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<InspectionRecord> inspectionRecord = userInformation.get().getInspectionRecords()
                    .stream()
                    .filter(inspectionRecord1 -> inspectionRecordId
                            .equals(inspectionRecord1.getInspectionRecordId()))
                    .findFirst();
            if (inspectionRecord.isPresent()) {
                return serviceToControllerBody.setContent(inspectionRecord.get());
            } else {
                return serviceToControllerBody.error("inspectionRecordId", "没有找到该检验记录", inspectionRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> createGarnerRecord(UUID userInformationId, GarnerRecord garnerRecord) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            userInformation.get().getGarnerRecords().add(garnerRecord);
            return serviceToControllerBody.setContent(garnerRecord);
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> deleteGarnerRecordByGarnerRecordId(UUID userInformationId,
            UUID garnerRecordId) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<GarnerRecord> garnerRecord = userInformation.get().getGarnerRecords()
                    .stream()
                    .filter(garnerRecord1 -> garnerRecordId
                            .equals(garnerRecord1.getGarnerRecordId()))
                    .findFirst();
            if (garnerRecord.isPresent()) {
                userInformation.get().getGarnerRecords().remove(garnerRecord.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(garnerRecord.get());
            } else {
                return serviceToControllerBody.error("garnerRecordId", "没有找到该收货记录", garnerRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> updateGarnerRecordByGarnerRecordId(UUID userInformationId,
            UUID garnerRecordId,
            GarnerRecord garnerRecord) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<GarnerRecord> garnerRecord1 = userInformation.get().getGarnerRecords()
                    .stream()
                    .filter(garnerRecord2 -> garnerRecordId
                            .equals(garnerRecord2.getGarnerRecordId()))
                    .findFirst();
            if (garnerRecord1.isPresent()) {
                garnerRecord1.get().setJobInformationId(garnerRecord.getJobInformationId());
                garnerRecord1.get().setUserInformationId(garnerRecord.getUserInformationId());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(garnerRecord1.get());
            } else {
                return serviceToControllerBody.error("garnerRecordId", "没有找到该收货记录", garnerRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<List<GarnerRecord>> getGarnerRecordsByUserInformationId(UUID userInformationId,
            Pageable pageable) {
        ServiceToControllerBody<List<GarnerRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getGarnerRecords());
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> getGarnerRecordByGarnerRecordId(UUID userInformationId,
            UUID garnerRecordId) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userInformationId);
        if (userInformation.isPresent()) {
            Optional<GarnerRecord> garnerRecord = userInformation.get().getGarnerRecords()
                    .stream()
                    .filter(garnerRecord1 -> garnerRecordId
                            .equals(garnerRecord1.getGarnerRecordId()))
                    .findFirst();
            if (garnerRecord.isPresent()) {
                return serviceToControllerBody.setContent(garnerRecord.get());
            } else {
                return serviceToControllerBody.error("garnerRecordId", "没有找到该收货记录", garnerRecordId);
            }
        } else {
            return serviceToControllerBody.error("userInformationId", "没有找到该用户", userInformationId);
        }
    }

}
