package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Optional;
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

@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Resource
    private UserInformationRepository userInformationRepository;

    @Override
    public UserInformation updateUserInformationByUserInfoId(UUID userInformationId,
            UserInformation userInformation) {
        userInformation.setUserInformationId(userInformationId);
        return userInformationRepository.save(userInformation);
    }

    @Override
    public Stream<UserInformation> getUserInformations(Pageable pageable) {
        return userInformationRepository.findAll(pageable).get();
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
    public Stream<JobExpectation> getJobExpectations(UUID userinfoid) {
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
                educationExperience1.get().setCity(educationExperience.getCity());
                educationExperience1.get().setDirectionTags(educationExperience.getDirectionTags());
                educationExperience1.get().setEducationBackground(educationExperience.getEducationBackground());
                educationExperience1.get().setEducationType(educationExperience.getEducationType());
                educationExperience1.get().setEndDate(educationExperience.getEndDate());
                educationExperience1.get().setSchoolName(educationExperience.getSchoolName());
                educationExperience1.get().setStartDate(educationExperience.getStartDate());
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
    public Stream<EducationExperience> getEducationExperiences(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getEducationExperiences();
    }

    @Override
    public Optional<EducationExperience> getEducationExperienceByEducationExperienceId(UUID userinfoid,
            UUID eduexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getEducationExperiences().stream()
                .filter(e -> e.getEducationExperienceId().equals(eduexperienceid)).findFirst().get();
    }

    @Override
    public Optional<WorkExperience> createWorkExperience(UUID userinfoid, WorkExperience workExperience) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getWorkExperiences().add(workExperience);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getWorkExperiences().get(userInformation.getWorkExperiences().size() - 1);
    }

    @Override
    public Optional<WorkExperience> deleteWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        WorkExperience workExperience = userInformation.getWorkExperiences().stream()
                .filter(w -> w.getWorkExperienceId().equals(workexperienceid)).findFirst().get();
        userInformation.getWorkExperiences().remove(workExperience);
        return workExperience;
    }

    @Override
    public Optional<WorkExperience> updateWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid,
            WorkExperience workExperience) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        WorkExperience workExperience1 = userInformation.getWorkExperiences().stream()
                .filter(w -> w.getWorkExperienceId().equals(workexperienceid)).findFirst().get();
        userInformation.getWorkExperiences().remove(workExperience1);
        userInformation.getWorkExperiences().add(workExperience);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getWorkExperiences().get(userInformation.getWorkExperiences().size() - 1);
    }

    @Override
    public Stream<WorkExperience> getWorkExperiences(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getWorkExperiences();
    }

    @Override
    public Optional<WorkExperience> getWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getWorkExperiences().stream()
                .filter(w -> w.getWorkExperienceId().equals(workexperienceid)).findFirst().get();
    }

    @Override
    public Optional<ProjectExperience> createProjectExperience(UUID userinfoid, ProjectExperience projectExperience) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getProjectExperiences().add(projectExperience);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getProjectExperiences().get(userInformation.getProjectExperiences().size() - 1);
    }

    @Override
    public Optional<ProjectExperience> deleteProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        ProjectExperience projectExperience = userInformation.getProjectExperiences().stream()
                .filter(p -> p.getProjectExperienceId().equals(projectexperienceid)).findFirst().get();
        userInformation.getProjectExperiences().remove(projectExperience);
        return projectExperience;
    }

    @Override
    public Optional<ProjectExperience> updateProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid,
            ProjectExperience projectExperience) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        ProjectExperience projectExperience1 = userInformation.getProjectExperiences().stream()
                .filter(p -> p.getProjectExperienceId().equals(projectexperienceid)).findFirst().get();
        userInformation.getProjectExperiences().remove(projectExperience1);
        userInformation.getProjectExperiences().add(projectExperience);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getProjectExperiences().get(userInformation.getProjectExperiences().size() - 1);
    }

    @Override
    public Stream<ProjectExperience> getProjectExperiences(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getProjectExperiences();
    }

    @Override
    public Optional<ProjectExperience> getProjectExperienceByProjectExperienceId(UUID userinfoid,
            UUID projectexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getProjectExperiences().stream()
                .filter(p -> p.getProjectExperienceId().equals(projectexperienceid)).findFirst().get();
    }

    @Override
    public Optional<DeliveryRecord> createDeliveryRecord(UUID userinfoid, DeliveryRecord deliveryRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getDeliveryRecords().add(deliveryRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getDeliveryRecords().get(userInformation.getDeliveryRecords().size() - 1);
    }

    @Override
    public Optional<DeliveryRecord> deleteDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        DeliveryRecord deliveryRecord = userInformation.getDeliveryRecords().stream()
                .filter(d -> d.getDeliveryRecordId().equals(deliveryrecordid)).findFirst().get();
        userInformation.getDeliveryRecords().remove(deliveryRecord);
        return deliveryRecord;
    }

    @Override
    public Optional<DeliveryRecord> updateDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid,
            DeliveryRecord deliveryRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        DeliveryRecord deliveryRecord1 = userInformation.getDeliveryRecords().stream()
                .filter(d -> d.getDeliveryRecordId().equals(deliveryrecordid)).findFirst().get();
        userInformation.getDeliveryRecords().remove(deliveryRecord1);
        userInformation.getDeliveryRecords().add(deliveryRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getDeliveryRecords().get(userInformation.getDeliveryRecords().size() - 1);
    }

    @Override
    public Stream<DeliveryRecord> getDeliveryRecords(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getDeliveryRecords();
    }

    @Override
    public Optional<DeliveryRecord> getDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getDeliveryRecords().stream()
                .filter(d -> d.getDeliveryRecordId().equals(deliveryrecordid)).findFirst().get();
    }

    @Override
    public Optional<AttentionRecord> createAttentionRecord(UUID userinfoid, AttentionRecord attentionRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getAttentionRecords().add(attentionRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getAttentionRecords().get(userInformation.getAttentionRecords().size() - 1);
    }

    @Override
    public Optional<AttentionRecord> deleteAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        AttentionRecord attentionRecord = userInformation.getAttentionRecords().stream()
                .filter(a -> a.getAttentionRecordId().equals(attentionrecordid)).findFirst().get();
        userInformation.getAttentionRecords().remove(attentionRecord);
        return attentionRecord;
    }

    @Override
    public Optional<AttentionRecord> updateAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid,
            AttentionRecord attentionRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        AttentionRecord attentionRecord1 = userInformation.getAttentionRecords().stream()
                .filter(a -> a.getAttentionRecordId().equals(attentionrecordid)).findFirst().get();
        userInformation.getAttentionRecords().remove(attentionRecord1);
        userInformation.getAttentionRecords().add(attentionRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getAttentionRecords().get(userInformation.getAttentionRecords().size() - 1);
    }

    @Override
    public Stream<AttentionRecord> getAttentionRecords(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getAttentionRecords();
    }

    @Override
    public Optional<AttentionRecord> getAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getAttentionRecords().stream()
                .filter(a -> a.getAttentionRecordId().equals(attentionrecordid)).findFirst().get();
    }

    @Override
    public Optional<InspectionRecord> createInspectionRecord(UUID userinfoid, InspectionRecord inspectionRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getInspectionRecords().add(inspectionRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getInspectionRecords().get(userInformation.getInspectionRecords().size() - 1);
    }

    @Override
    public Optional<InspectionRecord> deleteInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        InspectionRecord inspectionRecord = userInformation.getInspectionRecords().stream()
                .filter(i -> i.getInspectionRecordId().equals(inspectionrecordid)).findFirst().get();
        userInformation.getInspectionRecords().remove(inspectionRecord);
        return inspectionRecord;
    }

    @Override
    public Optional<InspectionRecord> updateInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid,
            InspectionRecord inspectionRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        InspectionRecord inspectionRecord1 = userInformation.getInspectionRecords().stream()
                .filter(i -> i.getInspectionRecordId().equals(inspectionrecordid)).findFirst().get();
        userInformation.getInspectionRecords().remove(inspectionRecord1);
        userInformation.getInspectionRecords().add(inspectionRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getInspectionRecords().get(userInformation.getInspectionRecords().size() - 1);
    }

    @Override
    public Stream<InspectionRecord> getInspectionRecords(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getInspectionRecords();
    }

    @Override
    public Optional<InspectionRecord> getInspectionRecordByInspectionRecordId(UUID userinfoid,
            UUID inspectionrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getInspectionRecords().stream()
                .filter(i -> i.getInspectionRecordId().equals(inspectionrecordid)).findFirst().get();
    }

    @Override
    public Optional<GarnerRecord> createGarnerRecord(UUID userinfoid, GarnerRecord garnerRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getGarnerRecords().add(garnerRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getGarnerRecords().get(userInformation.getGarnerRecords().size() - 1);
    }

    @Override
    public Optional<GarnerRecord> deleteGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        GarnerRecord garnerRecord = userInformation.getGarnerRecords().stream()
                .filter(g -> g.getGarnerRecordId().equals(garnerrecordid)).findFirst().get();
        userInformation.getGarnerRecords().remove(garnerRecord);
        return garnerRecord;
    }

    @Override
    public Optional<GarnerRecord> updateGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid,
            GarnerRecord garnerRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        GarnerRecord garnerRecord1 = userInformation.getGarnerRecords().stream()
                .filter(g -> g.getGarnerRecordId().equals(garnerrecordid)).findFirst().get();
        userInformation.getGarnerRecords().remove(garnerRecord1);
        userInformation.getGarnerRecords().add(garnerRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getGarnerRecords().get(userInformation.getGarnerRecords().size() - 1);
    }

    @Override
    public Stream<GarnerRecord> getGarnerRecords(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getGarnerRecords();
    }

    @Override
    public Optional<GarnerRecord> getGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getGarnerRecords().stream()
                .filter(g -> g.getGarnerRecordId().equals(garnerrecordid)).findFirst().get();
    }

}
