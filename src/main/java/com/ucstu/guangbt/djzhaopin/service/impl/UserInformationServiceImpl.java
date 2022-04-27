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
    public ServiceToControllerBody<UserInformation> updateUserInformationByUserInfoId(UUID userInformationId,
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
    public ServiceToControllerBody<UserInformation> getUserInformationByUserInfoId(UUID userinfoid) {
        ServiceToControllerBody<UserInformation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userinfoid);
        if (userInformationOptional.isPresent()) {
            return serviceToControllerBody.setContent(userInformationOptional.get());
        }
        return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
    }

    @Override
    public ServiceToControllerBody<JobExpectation> createJobExpectation(UUID userinfoid,
            JobExpectation jobExpectation) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformationOptional = userInformationRepository.findById(userinfoid);
        if (userInformationOptional.isPresent()) {
            userInformationOptional.get().getJobExpectations().add(jobExpectation);
            return serviceToControllerBody.setContent(jobExpectation);
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<JobExpectation> deleteJobExpectationByJobExpectationId(UUID userinfoid,
            UUID jobexpectationid) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<JobExpectation> jobExpectation = userInformation.get().getJobExpectations()
                    .stream().filter(jobExpectation1 -> jobexpectationid.equals(jobExpectation1.getJobExpectationId()))
                    .findFirst();
            if (jobExpectation.isPresent()) {
                userInformation.get().getJobExpectations().remove(jobExpectation.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(jobExpectation.get());
            } else {
                return serviceToControllerBody.error("jobexpectationid", "没有找到该职位期望", jobexpectationid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<JobExpectation> updateJobExpectationByJobExpectationId(UUID userinfoid,
            UUID jobexpectationid,
            JobExpectation jobExpectation) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<JobExpectation> jobExpectation1 = userInformation.get().getJobExpectations()
                    .stream().filter(jobExpectation2 -> jobexpectationid.equals(jobExpectation2.getJobExpectationId()))
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
                return serviceToControllerBody.error("jobexpectationid", "没有找到该职位期望", jobexpectationid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<List<JobExpectation>> getJobExpectationsByUserInformationId(UUID userinfoid,
            Pageable pageable) {
        ServiceToControllerBody<List<JobExpectation>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getJobExpectations());
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<JobExpectation> getJobExpectationByJobExpectationId(UUID userinfoid,
            UUID jobexpectationid) {
        ServiceToControllerBody<JobExpectation> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<JobExpectation> jobExpectation = userInformation.get().getJobExpectations()
                    .stream().filter(jobExpectation1 -> jobexpectationid.equals(jobExpectation1.getJobExpectationId()))
                    .findFirst();
            if (jobExpectation.isPresent()) {
                return serviceToControllerBody.setContent(jobExpectation.get());
            } else {
                return serviceToControllerBody.error("jobexpectationid", "没有找到该职位期望", jobexpectationid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<EducationExperience> createEducationExperience(UUID userinfoid,
            EducationExperience educationExperience) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getEducationExperiences().add(educationExperience);
            return serviceToControllerBody.setContent(educationExperience);
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<EducationExperience> deleteEducationExperienceByEducationExperienceId(
            UUID userinfoid,
            UUID eduexperienceid) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<EducationExperience> educationExperience = userInformation.get().getEducationExperiences()
                    .stream()
                    .filter(educationExperience1 -> eduexperienceid
                            .equals(educationExperience1.getEducationExperienceId()))
                    .findFirst();
            if (educationExperience.isPresent()) {
                userInformation.get().getEducationExperiences().remove(educationExperience.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(educationExperience.get());
            } else {
                return serviceToControllerBody.error("eduexperienceid", "没有找到该教育经历", eduexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<EducationExperience> updateEducationExperienceByEducationExperienceId(
            UUID userinfoid,
            UUID eduexperienceid,
            EducationExperience educationExperience) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<EducationExperience> educationExperience1 = userInformation.get().getEducationExperiences()
                    .stream()
                    .filter(educationExperience2 -> eduexperienceid
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
                return serviceToControllerBody.error("eduexperienceid", "没有找到该教育经历", eduexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<List<EducationExperience>> getEducationExperiencesByUserInformationId(
            UUID userinfoid,
            Pageable pageable) {
        ServiceToControllerBody<List<EducationExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getEducationExperiences());
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<EducationExperience> getEducationExperienceByEducationExperienceId(UUID userinfoid,
            UUID eduexperienceid) {
        ServiceToControllerBody<EducationExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<EducationExperience> educationExperience = userInformation.get().getEducationExperiences()
                    .stream()
                    .filter(educationExperience1 -> eduexperienceid
                            .equals(educationExperience1.getEducationExperienceId()))
                    .findFirst();
            if (educationExperience.isPresent()) {
                return serviceToControllerBody.setContent(educationExperience.get());
            } else {
                return serviceToControllerBody.error("eduexperienceid", "没有找到该教育经历", eduexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<WorkExperience> createWorkExperience(UUID userinfoid,
            WorkExperience workExperience) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getWorkExperiences().add(workExperience);
            return serviceToControllerBody.setContent(workExperience);
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<WorkExperience> deleteWorkExperienceByWorkExperienceId(UUID userinfoid,
            UUID workexperienceid) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<WorkExperience> workExperience = userInformation.get().getWorkExperiences()
                    .stream()
                    .filter(workExperience1 -> workexperienceid
                            .equals(workExperience1.getWorkExperienceId()))
                    .findFirst();
            if (workExperience.isPresent()) {
                userInformation.get().getWorkExperiences().remove(workExperience.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(workExperience.get());
            } else {
                return serviceToControllerBody.error("workexperienceid", "没有找到该工作经历", workexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<WorkExperience> updateWorkExperienceByWorkExperienceId(UUID userinfoid,
            UUID workexperienceid,
            WorkExperience workExperience) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<WorkExperience> workExperience1 = userInformation.get().getWorkExperiences()
                    .stream()
                    .filter(workExperience2 -> workexperienceid
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
                return serviceToControllerBody.error("workexperienceid", "没有找到该工作经历", workexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<List<WorkExperience>> getWorkExperiencesByUserInformationId(UUID userinfoid,
            Pageable pageable) {
        ServiceToControllerBody<List<WorkExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getWorkExperiences());
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<WorkExperience> getWorkExperienceByWorkExperienceId(UUID userinfoid,
            UUID workexperienceid) {
        ServiceToControllerBody<WorkExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<WorkExperience> workExperience = userInformation.get().getWorkExperiences()
                    .stream()
                    .filter(workExperience1 -> workexperienceid
                            .equals(workExperience1.getWorkExperienceId()))
                    .findFirst();
            if (workExperience.isPresent()) {
                return serviceToControllerBody.setContent(workExperience.get());
            } else {
                return serviceToControllerBody.error("workexperienceid", "没有找到该工作经历", workexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> createProjectExperience(UUID userinfoid,
            ProjectExperience projectExperience) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getProjectExperiences().add(projectExperience);
            return serviceToControllerBody.setContent(projectExperience);
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> deleteProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<ProjectExperience> projectExperience = userInformation.get().getProjectExperiences()
                    .stream()
                    .filter(projectExperience1 -> projectexperienceid
                            .equals(projectExperience1.getProjectExperienceId()))
                    .findFirst();
            if (projectExperience.isPresent()) {
                userInformation.get().getProjectExperiences().remove(projectExperience.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(projectExperience.get());
            } else {
                return serviceToControllerBody.error("projectexperienceid", "没有找到该项目经历", projectexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> updateProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid,
            ProjectExperience projectExperience) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<ProjectExperience> projectExperience1 = userInformation.get().getProjectExperiences()
                    .stream()
                    .filter(projectExperience2 -> projectexperienceid
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
                return serviceToControllerBody.error("projectexperienceid", "没有找到该项目经历", projectexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<List<ProjectExperience>> getProjectExperiencesByUserInformationId(UUID userinfoid,
            Pageable pageable) {
        ServiceToControllerBody<List<ProjectExperience>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getProjectExperiences());
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<ProjectExperience> getProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid) {
        ServiceToControllerBody<ProjectExperience> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<ProjectExperience> projectExperience = userInformation.get().getProjectExperiences()
                    .stream()
                    .filter(projectExperience1 -> projectexperienceid
                            .equals(projectExperience1.getProjectExperienceId()))
                    .findFirst();
            if (projectExperience.isPresent()) {
                return serviceToControllerBody.setContent(projectExperience.get());
            } else {
                return serviceToControllerBody.error("projectexperienceid", "没有找到该项目经历", projectexperienceid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> createDeliveryRecord(UUID userinfoid,
            DeliveryRecord deliveryRecord) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getDeliveryRecords().add(deliveryRecord);
            return serviceToControllerBody.setContent(deliveryRecord);
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> deleteDeliveryRecordByDeliveryRecordId(UUID userinfoid,
            UUID deliveryrecordid) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<DeliveryRecord> deliveryRecord = userInformation.get().getDeliveryRecords()
                    .stream()
                    .filter(deliveryRecord1 -> deliveryrecordid
                            .equals(deliveryRecord1.getDeliveryRecordId()))
                    .findFirst();
            if (deliveryRecord.isPresent()) {
                userInformation.get().getDeliveryRecords().remove(deliveryRecord.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(deliveryRecord.get());
            } else {
                return serviceToControllerBody.error("deliveryrecordid", "没有找到该项目经历", deliveryrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> updateDeliveryRecordByDeliveryRecordId(UUID userinfoid,
            UUID deliveryrecordid,
            DeliveryRecord deliveryRecord) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<DeliveryRecord> deliveryRecord1 = userInformation.get().getDeliveryRecords()
                    .stream()
                    .filter(deliveryRecord2 -> deliveryrecordid
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
                return serviceToControllerBody.error("deliveryrecordid", "没有找到该项目经历", deliveryrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<List<DeliveryRecord>> getDeliveryRecordsByUserInformationId(UUID userinfoid,
            Pageable pageable) {
        ServiceToControllerBody<List<DeliveryRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getDeliveryRecords());
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<DeliveryRecord> getDeliveryRecordByDeliveryRecordId(UUID userinfoid,
            UUID deliveryrecordid) {
        ServiceToControllerBody<DeliveryRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<DeliveryRecord> deliveryRecord = userInformation.get().getDeliveryRecords()
                    .stream()
                    .filter(deliveryRecord1 -> deliveryrecordid
                            .equals(deliveryRecord1.getDeliveryRecordId()))
                    .findFirst();
            if (deliveryRecord.isPresent()) {
                return serviceToControllerBody.setContent(deliveryRecord.get());
            } else {
                return serviceToControllerBody.error("deliveryrecordid", "没有找到该项目经历", deliveryrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> createAttentionRecord(UUID userinfoid,
            AttentionRecord attentionRecord) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getAttentionRecords().add(attentionRecord);
            return serviceToControllerBody.setContent(attentionRecord);
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> deleteAttentionRecordByAttentionRecordId(UUID userinfoid,
            UUID attentionrecordid) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<AttentionRecord> attentionRecord = userInformation.get().getAttentionRecords()
                    .stream()
                    .filter(attentionRecord1 -> attentionrecordid
                            .equals(attentionRecord1.getAttentionRecordId()))
                    .findFirst();
            if (attentionRecord.isPresent()) {
                userInformation.get().getAttentionRecords().remove(attentionRecord.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(attentionRecord.get());
            } else {
                return serviceToControllerBody.error("attentionrecordid", "没有找到该关注记录", attentionrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> updateAttentionRecordByAttentionRecordId(UUID userinfoid,
            UUID attentionrecordid,
            AttentionRecord attentionRecord) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<AttentionRecord> attentionRecord1 = userInformation.get().getAttentionRecords()
                    .stream()
                    .filter(attentionRecord2 -> attentionrecordid
                            .equals(attentionRecord2.getAttentionRecordId()))
                    .findFirst();
            if (attentionRecord1.isPresent()) {
                attentionRecord1.get().setCompanyInformationId(attentionRecord.getCompanyInformationId());
                attentionRecord1.get().setUserInformationId(attentionRecord.getUserInformationId());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(attentionRecord1.get());
            } else {
                return serviceToControllerBody.error("attentionrecordid", "没有找到该关注记录", attentionrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<List<AttentionRecord>> getAttentionRecordsByUserInformationId(UUID userinfoid) {
        ServiceToControllerBody<List<AttentionRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getAttentionRecords());
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<AttentionRecord> getAttentionRecordByAttentionRecordId(UUID userinfoid,
            UUID attentionrecordid) {
        ServiceToControllerBody<AttentionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<AttentionRecord> attentionRecord = userInformation.get().getAttentionRecords()
                    .stream()
                    .filter(attentionRecord1 -> attentionrecordid
                            .equals(attentionRecord1.getAttentionRecordId()))
                    .findFirst();
            if (attentionRecord.isPresent()) {
                return serviceToControllerBody.setContent(attentionRecord.get());
            } else {
                return serviceToControllerBody.error("attentionrecordid", "没有找到该关注记录", attentionrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<InspectionRecord> createInspectionRecord(UUID userinfoid,
            InspectionRecord inspectionRecord) {
        ServiceToControllerBody<InspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getInspectionRecords().add(inspectionRecord);
            return serviceToControllerBody.setContent(inspectionRecord);
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<InspectionRecord> deleteInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid) {
        ServiceToControllerBody<InspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<InspectionRecord> inspectionRecord = userInformation.get().getInspectionRecords()
                    .stream()
                    .filter(inspectionRecord1 -> inspectionrecordid
                            .equals(inspectionRecord1.getInspectionRecordId()))
                    .findFirst();
            if (inspectionRecord.isPresent()) {
                userInformation.get().getInspectionRecords().remove(inspectionRecord.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(inspectionRecord.get());
            } else {
                return serviceToControllerBody.error("inspectionrecordid", "没有找到该检验记录", inspectionrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<InspectionRecord> updateInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid,
            InspectionRecord inspectionRecord) {
        ServiceToControllerBody<InspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<InspectionRecord> inspectionRecord1 = userInformation.get().getInspectionRecords()
                    .stream()
                    .filter(inspectionRecord2 -> inspectionrecordid
                            .equals(inspectionRecord2.getInspectionRecordId()))
                    .findFirst();
            if (inspectionRecord1.isPresent()) {
                inspectionRecord1.get().setFromId(inspectionRecord.getFromId());
                inspectionRecord1.get().setToId(inspectionRecord.getToId());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(inspectionRecord1.get());
            } else {
                return serviceToControllerBody.error("inspectionrecordid", "没有找到该检验记录", inspectionrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<List<InspectionRecord>> getInspectionRecordsByUserInformationId(UUID userinfoid,
            Pageable pageable) {
        ServiceToControllerBody<List<InspectionRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            // TUDO 只返回了InspectionRecord，未返回pageable
            return serviceToControllerBody.setContent(userInformation.get().getInspectionRecords());
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<InspectionRecord> getInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid) {
        ServiceToControllerBody<InspectionRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<InspectionRecord> inspectionRecord = userInformation.get().getInspectionRecords()
                    .stream()
                    .filter(inspectionRecord1 -> inspectionrecordid
                            .equals(inspectionRecord1.getInspectionRecordId()))
                    .findFirst();
            if (inspectionRecord.isPresent()) {
                return serviceToControllerBody.setContent(inspectionRecord.get());
            } else {
                return serviceToControllerBody.error("inspectionrecordid", "没有找到该检验记录", inspectionrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> createGarnerRecord(UUID userinfoid, GarnerRecord garnerRecord) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getGarnerRecords().add(garnerRecord);
            return serviceToControllerBody.setContent(garnerRecord);
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> deleteGarnerRecordByGarnerRecordId(UUID userinfoid,
            UUID garnerrecordid) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<GarnerRecord> garnerRecord = userInformation.get().getGarnerRecords()
                    .stream()
                    .filter(garnerRecord1 -> garnerrecordid
                            .equals(garnerRecord1.getGarnerRecordId()))
                    .findFirst();
            if (garnerRecord.isPresent()) {
                userInformation.get().getGarnerRecords().remove(garnerRecord.get());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(garnerRecord.get());
            } else {
                return serviceToControllerBody.error("garnerrecordid", "没有找到该收货记录", garnerrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> updateGarnerRecordByGarnerRecordId(UUID userinfoid,
            UUID garnerrecordid,
            GarnerRecord garnerRecord) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<GarnerRecord> garnerRecord1 = userInformation.get().getGarnerRecords()
                    .stream()
                    .filter(garnerRecord2 -> garnerrecordid
                            .equals(garnerRecord2.getGarnerRecordId()))
                    .findFirst();
            if (garnerRecord1.isPresent()) {
                garnerRecord1.get().setJobInformationId(garnerRecord.getJobInformationId());
                garnerRecord1.get().setUserInformationId(garnerRecord.getUserInformationId());
                userInformationRepository.save(userInformation.get());
                return serviceToControllerBody.setContent(garnerRecord1.get());
            } else {
                return serviceToControllerBody.error("garnerrecordid", "没有找到该收货记录", garnerrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<List<GarnerRecord>> getGarnerRecordsByUserInformationId(UUID userinfoid,
            Pageable pageable) {
        ServiceToControllerBody<List<GarnerRecord>> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return serviceToControllerBody.setContent(userInformation.get().getGarnerRecords());
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

    @Override
    public ServiceToControllerBody<GarnerRecord> getGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        ServiceToControllerBody<GarnerRecord> serviceToControllerBody = new ServiceToControllerBody<>();
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<GarnerRecord> garnerRecord = userInformation.get().getGarnerRecords()
                    .stream()
                    .filter(garnerRecord1 -> garnerrecordid
                            .equals(garnerRecord1.getGarnerRecordId()))
                    .findFirst();
            if (garnerRecord.isPresent()) {
                return serviceToControllerBody.setContent(garnerRecord.get());
            } else {
                return serviceToControllerBody.error("garnerrecordid", "没有找到该收货记录", garnerrecordid);
            }
        } else {
            return serviceToControllerBody.error("userinfoid", "没有找到该用户", userinfoid);
        }
    }

}
