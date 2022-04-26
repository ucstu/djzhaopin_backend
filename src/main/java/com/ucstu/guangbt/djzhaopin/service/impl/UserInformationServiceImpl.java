package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.EducationExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.InspectionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.JobExpectation;
import com.ucstu.guangbt.djzhaopin.entity.user.ProjectExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;
import com.ucstu.guangbt.djzhaopin.repository.UserInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Resource
    private UserInformationRepository userInformationRepository;

    @Override
    public Optional<UserInformation> updateUserInformationByUserInfoId(UUID userInformationId,
            UserInformation userInformation) {
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
            return Optional.of(userInformationRepository.save(userInformation2));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<UserInformation>> getUserInformations(Pageable pageable) {
        return Optional.of(userInformationRepository.findAll(pageable).getContent());
    }

    @Override
    public Optional<UserInformation> getUserInformationByUserInfoId(UUID userinfoid) {
        return userInformationRepository.findById(userinfoid);
    }

    @Override
    public Optional<JobExpectation> createJobExpectation(UUID userinfoid, JobExpectation jobExpectation) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getJobExpectations().add(jobExpectation);
            JobExpectation jobExpectation1 = (JobExpectation) userInformation.get().getJobExpectations()
                    .toArray()[userInformation.get()
                            .getJobExpectations().size() - 1];
            return Optional.of(jobExpectation1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<JobExpectation> deleteJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<JobExpectation> jobExpectation = userInformation.get().getJobExpectations()
                    .stream().filter(jobExpectation1 -> jobexpectationid.equals(jobExpectation1.getJobExpectationId()))
                    .findFirst();
            if (jobExpectation.isPresent()) {
                userInformation.get().getJobExpectations().remove(jobExpectation.get());
                return Optional.of(jobExpectation.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<JobExpectation> updateJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid,
            JobExpectation jobExpectation) {
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
                return Optional.of(jobExpectation1.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Stream<JobExpectation> getJobExpectationsByUserInformationId(UUID userinfoid, Pageable pageable) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return userInformation.get().getJobExpectations().stream();
        } else {
            return Stream.empty();
        }
    }

    @Override
    public Optional<JobExpectation> getJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<JobExpectation> jobExpectation = userInformation.get().getJobExpectations()
                    .stream().filter(jobExpectation1 -> jobexpectationid.equals(jobExpectation1.getJobExpectationId()))
                    .findFirst();
            if (jobExpectation.isPresent()) {
                return Optional.of(jobExpectation.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<EducationExperience> createEducationExperience(UUID userinfoid,
            EducationExperience educationExperience) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getEducationExperiences().add(educationExperience);
            EducationExperience educationExperience1 = (EducationExperience) userInformation.get()
                    .getEducationExperiences().toArray()[userInformation.get()
                            .getEducationExperiences().size() - 1];
            return Optional.of(educationExperience1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<EducationExperience> deleteEducationExperienceByEducationExperienceId(UUID userinfoid,
            UUID eduexperienceid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<EducationExperience> educationExperience = userInformation.get().getEducationExperiences()
                    .stream()
                    .filter(educationExperience1 -> eduexperienceid
                            .equals(educationExperience1.getEducationExperienceId()))
                    .findFirst();
            if (educationExperience.isPresent()) {
                userInformation.get().getEducationExperiences().remove(educationExperience.get());
                return Optional.of(educationExperience.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<EducationExperience> updateEducationExperienceByEducationExperienceId(UUID userinfoid,
            UUID eduexperienceid,
            EducationExperience educationExperience) {
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
                return Optional.of(educationExperience1.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Stream<EducationExperience> getEducationExperiencesByUserInformationId(UUID userinfoid, Pageable pageable) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return userInformation.get().getEducationExperiences().stream();
        } else {
            return Stream.empty();
        }
    }

    @Override
    public Optional<EducationExperience> getEducationExperienceByEducationExperienceId(UUID userinfoid,
            UUID eduexperienceid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<EducationExperience> educationExperience = userInformation.get().getEducationExperiences()
                    .stream()
                    .filter(educationExperience1 -> eduexperienceid
                            .equals(educationExperience1.getEducationExperienceId()))
                    .findFirst();
            if (educationExperience.isPresent()) {
                return Optional.of(educationExperience.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<WorkExperience> createWorkExperience(UUID userinfoid, WorkExperience workExperience) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getWorkExperiences().add(workExperience);
            WorkExperience workExperience1 = (WorkExperience) userInformation.get()
                    .getWorkExperiences().toArray()[userInformation.get()
                            .getWorkExperiences().size() - 1];
            return Optional.of(workExperience1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<WorkExperience> deleteWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<WorkExperience> workExperience = userInformation.get().getWorkExperiences()
                    .stream()
                    .filter(workExperience1 -> workexperienceid
                            .equals(workExperience1.getWorkExperienceId()))
                    .findFirst();
            if (workExperience.isPresent()) {
                userInformation.get().getWorkExperiences().remove(workExperience.get());
                return Optional.of(workExperience.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<WorkExperience> updateWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid,
            WorkExperience workExperience) {
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
                return Optional.of(workExperience1.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Stream<WorkExperience> getWorkExperiencesByUserInformationId(UUID userinfoid, Pageable pageable) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return userInformation.get().getWorkExperiences().stream();
        } else {
            return Stream.empty();
        }
    }

    @Override
    public Optional<WorkExperience> getWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<WorkExperience> workExperience = userInformation.get().getWorkExperiences()
                    .stream()
                    .filter(workExperience1 -> workexperienceid
                            .equals(workExperience1.getWorkExperienceId()))
                    .findFirst();
            if (workExperience.isPresent()) {
                return Optional.of(workExperience.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProjectExperience> createProjectExperience(UUID userinfoid, ProjectExperience projectExperience) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getProjectExperiences().add(projectExperience);
            ProjectExperience projectExperience1 = (ProjectExperience) userInformation.get()
                    .getProjectExperiences().toArray()[userInformation.get()
                            .getProjectExperiences().size() - 1];
            return Optional.of(projectExperience1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProjectExperience> deleteProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<ProjectExperience> projectExperience = userInformation.get().getProjectExperiences()
                    .stream()
                    .filter(projectExperience1 -> projectexperienceid
                            .equals(projectExperience1.getProjectExperienceId()))
                    .findFirst();
            if (projectExperience.isPresent()) {
                userInformation.get().getProjectExperiences().remove(projectExperience.get());
                return Optional.of(projectExperience.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<ProjectExperience> updateProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid,
            ProjectExperience projectExperience) {
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
                return Optional.of(projectExperience1.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Stream<ProjectExperience> getProjectExperiencesByUserInformationId(UUID userinfoid, Pageable pageable) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return userInformation.get().getProjectExperiences().stream();
        } else {
            return Stream.empty();
        }
    }

    @Override
    public Optional<ProjectExperience> getProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<ProjectExperience> projectExperience = userInformation.get().getProjectExperiences()
                    .stream()
                    .filter(projectExperience1 -> projectexperienceid
                            .equals(projectExperience1.getProjectExperienceId()))
                    .findFirst();
            if (projectExperience.isPresent()) {
                return Optional.of(projectExperience.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeliveryRecord> createDeliveryRecord(UUID userinfoid, DeliveryRecord deliveryRecord) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getDeliveryRecords().add(deliveryRecord);
            DeliveryRecord deliveryRecord1 = (DeliveryRecord) userInformation.get()
                    .getDeliveryRecords().toArray()[userInformation.get()
                            .getDeliveryRecords().size() - 1];
            return Optional.of(deliveryRecord1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeliveryRecord> deleteDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<DeliveryRecord> deliveryRecord = userInformation.get().getDeliveryRecords()
                    .stream()
                    .filter(deliveryRecord1 -> deliveryrecordid
                            .equals(deliveryRecord1.getDeliveryRecordId()))
                    .findFirst();
            if (deliveryRecord.isPresent()) {
                userInformation.get().getDeliveryRecords().remove(deliveryRecord.get());
                return Optional.of(deliveryRecord.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeliveryRecord> updateDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid,
            DeliveryRecord deliveryRecord) {
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
                return Optional.of(deliveryRecord1.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Set<DeliveryRecord>> getDeliveryRecordsByUserInformationId(UUID userinfoid, Pageable pageable) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return Optional.of(userInformation.get().getDeliveryRecords());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<DeliveryRecord> deliveryRecord = userInformation.get().getDeliveryRecords()
                    .stream()
                    .filter(deliveryRecord1 -> deliveryrecordid
                            .equals(deliveryRecord1.getDeliveryRecordId()))
                    .findFirst();
            if (deliveryRecord.isPresent()) {
                return Optional.of(deliveryRecord.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AttentionRecord> createAttentionRecord(UUID userinfoid, AttentionRecord attentionRecord) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getAttentionRecords().add(attentionRecord);
            AttentionRecord attentionRecord1 = (AttentionRecord) userInformation.get()
                    .getAttentionRecords().toArray()[userInformation.get()
                            .getAttentionRecords().size() - 1];
            return Optional.of(attentionRecord1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AttentionRecord> deleteAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<AttentionRecord> attentionRecord = userInformation.get().getAttentionRecords()
                    .stream()
                    .filter(attentionRecord1 -> attentionrecordid
                            .equals(attentionRecord1.getAttentionRecordId()))
                    .findFirst();
            if (attentionRecord.isPresent()) {
                userInformation.get().getAttentionRecords().remove(attentionRecord.get());
                return Optional.of(attentionRecord.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AttentionRecord> updateAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid,
            AttentionRecord attentionRecord) {
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
                return Optional.of(attentionRecord1.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Set<AttentionRecord>> getAttentionRecordsByUserInformationId(UUID userinfoid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return Optional.of(userInformation.get().getAttentionRecords());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AttentionRecord> getAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<AttentionRecord> attentionRecord = userInformation.get().getAttentionRecords()
                    .stream()
                    .filter(attentionRecord1 -> attentionrecordid
                            .equals(attentionRecord1.getAttentionRecordId()))
                    .findFirst();
            if (attentionRecord.isPresent()) {
                return Optional.of(attentionRecord.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<InspectionRecord> createInspectionRecord(UUID userinfoid, InspectionRecord inspectionRecord) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getInspectionRecords().add(inspectionRecord);
            InspectionRecord inspectionRecord1 = (InspectionRecord) userInformation.get()
                    .getInspectionRecords().toArray()[userInformation.get()
                            .getInspectionRecords().size() - 1];
            return Optional.of(inspectionRecord1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<InspectionRecord> deleteInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<InspectionRecord> inspectionRecord = userInformation.get().getInspectionRecords()
                    .stream()
                    .filter(inspectionRecord1 -> inspectionrecordid
                            .equals(inspectionRecord1.getInspectionRecordId()))
                    .findFirst();
            if (inspectionRecord.isPresent()) {
                userInformation.get().getInspectionRecords().remove(inspectionRecord.get());
                return Optional.of(inspectionRecord.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<InspectionRecord> updateInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid,
            InspectionRecord inspectionRecord) {
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
                return Optional.of(inspectionRecord1.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Stream<InspectionRecord> getInspectionRecordsByUserInformationId(UUID userinfoid, Pageable pageable) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return userInformation.get().getInspectionRecords().stream();
        } else {
            return Stream.empty();
        }
    }

    @Override
    public Optional<InspectionRecord> getInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<InspectionRecord> inspectionRecord = userInformation.get().getInspectionRecords()
                    .stream()
                    .filter(inspectionRecord1 -> inspectionrecordid
                            .equals(inspectionRecord1.getInspectionRecordId()))
                    .findFirst();
            if (inspectionRecord.isPresent()) {
                return Optional.of(inspectionRecord.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GarnerRecord> createGarnerRecord(UUID userinfoid, GarnerRecord garnerRecord) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            userInformation.get().getGarnerRecords().add(garnerRecord);
            GarnerRecord garnerRecord1 = (GarnerRecord) userInformation.get()
                    .getGarnerRecords().toArray()[userInformation.get()
                            .getGarnerRecords().size() - 1];
            return Optional.of(garnerRecord1);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GarnerRecord> deleteGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<GarnerRecord> garnerRecord = userInformation.get().getGarnerRecords()
                    .stream()
                    .filter(garnerRecord1 -> garnerrecordid
                            .equals(garnerRecord1.getGarnerRecordId()))
                    .findFirst();
            if (garnerRecord.isPresent()) {
                userInformation.get().getGarnerRecords().remove(garnerRecord.get());
                return Optional.of(garnerRecord.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<GarnerRecord> updateGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid,
            GarnerRecord garnerRecord) {
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
                return Optional.of(garnerRecord1.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Stream<GarnerRecord> getGarnerRecordsByUserInformationId(UUID userinfoid, Pageable pageable) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            return userInformation.get().getGarnerRecords().stream();
        } else {
            return Stream.empty();
        }
    }

    @Override
    public Optional<GarnerRecord> getGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        Optional<UserInformation> userInformation = userInformationRepository.findById(userinfoid);
        if (userInformation.isPresent()) {
            Optional<GarnerRecord> garnerRecord = userInformation.get().getGarnerRecords()
                    .stream()
                    .filter(garnerRecord1 -> garnerrecordid
                            .equals(garnerRecord1.getGarnerRecordId()))
                    .findFirst();
            if (garnerRecord.isPresent()) {
                return Optional.of(garnerRecord.get());
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

}
