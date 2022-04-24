package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.List;
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
import com.ucstu.guangbt.djzhaopin.repository.UserInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Resource
    private UserInformationRepository userInformationRepository;

    @Override
    public UserInformation updateUserInformationByUserInfoId(UUID userInformationId, UserInformation userInformation) {
        userInformation.setUserInformationId(userInformationId);
        return userInformationRepository.save(userInformation);
    }

    @Override
    public List<UserInformation> getUserInformations(Pageable pageable) {
        return userInformationRepository.findAll(pageable).getContent();
    }

    @Override
    public UserInformation getUserInformationByUserInfoId(UUID userinfoid) {
        return userInformationRepository.findById(userinfoid).get();
    }

    @Override
    public JobExpectation createJobExpectation(UUID userinfoid, @Valid JobExpectation jobExpectation) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getJobExpectations().add(jobExpectation);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getJobExpectations().get(userInformation.getJobExpectations().size() - 1);
    }

    @Override
    public JobExpectation deleteJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        JobExpectation jobExpectation = userInformation.getJobExpectations().stream()
                .filter(j -> j.getJobExpectationId().equals(jobexpectationid)).findFirst().get();
        userInformation.getJobExpectations().remove(jobExpectation);
        return jobExpectation;
    }

    @Override
    public JobExpectation updateJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid,
            JobExpectation jobExpectation) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        JobExpectation jobExpectation1 = userInformation.getJobExpectations().stream()
                .filter(j -> j.getJobExpectationId().equals(jobexpectationid)).findFirst().get();
        userInformation.getJobExpectations().remove(jobExpectation1);
        userInformation.getJobExpectations().add(jobExpectation);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getJobExpectations().get(userInformation.getJobExpectations().size() - 1);
    }

    @Override
    public List<JobExpectation> getJobExpectations(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getJobExpectations();
    }

    @Override
    public JobExpectation getJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getJobExpectations().stream()
                .filter(j -> j.getJobExpectationId().equals(jobexpectationid)).findFirst().get();
    }

    @Override
    public EducationExperience createEducationExperience(UUID userinfoid, EducationExperience educationExperience) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getEducationExperiences().add(educationExperience);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getEducationExperiences().get(userInformation.getEducationExperiences().size() - 1);
    }

    @Override
    public EducationExperience deleteEducationExperienceByEducationExperienceId(UUID userinfoid, UUID eduexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        EducationExperience educationExperience = userInformation.getEducationExperiences().stream()
                .filter(e -> e.getEducationExperienceId().equals(eduexperienceid)).findFirst().get();
        userInformation.getEducationExperiences().remove(educationExperience);
        return educationExperience;
    }

    @Override
    public EducationExperience updateEducationExperienceByEducationExperienceId(UUID userinfoid, UUID eduexperienceid,
            EducationExperience educationExperience) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        EducationExperience educationExperience1 = userInformation.getEducationExperiences().stream()
                .filter(e -> e.getEducationExperienceId().equals(eduexperienceid)).findFirst().get();
        userInformation.getEducationExperiences().remove(educationExperience1);
        userInformation.getEducationExperiences().add(educationExperience);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getEducationExperiences().get(userInformation.getEducationExperiences().size() - 1);
    }

    @Override
    public List<EducationExperience> getEducationExperiences(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getEducationExperiences();
    }

    @Override
    public EducationExperience getEducationExperienceByEducationExperienceId(UUID userinfoid, UUID eduexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getEducationExperiences().stream()
                .filter(e -> e.getEducationExperienceId().equals(eduexperienceid)).findFirst().get();
    }

    @Override
    public WorkExperience createWorkExperience(UUID userinfoid, WorkExperience workExperience) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getWorkExperiences().add(workExperience);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getWorkExperiences().get(userInformation.getWorkExperiences().size() - 1);
    }

    @Override
    public WorkExperience deleteWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        WorkExperience workExperience = userInformation.getWorkExperiences().stream()
                .filter(w -> w.getWorkExperienceId().equals(workexperienceid)).findFirst().get();
        userInformation.getWorkExperiences().remove(workExperience);
        return workExperience;
    }

    @Override
    public WorkExperience updateWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid,
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
    public List<WorkExperience> getWorkExperiences(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getWorkExperiences();
    }

    @Override
    public WorkExperience getWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getWorkExperiences().stream()
                .filter(w -> w.getWorkExperienceId().equals(workexperienceid)).findFirst().get();
    }

    @Override
    public ProjectExperience createProjectExperience(UUID userinfoid, ProjectExperience projectExperience) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getProjectExperiences().add(projectExperience);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getProjectExperiences().get(userInformation.getProjectExperiences().size() - 1);
    }

    @Override
    public ProjectExperience deleteProjectExperienceByProjectExperienceId(UUID userinfoid, UUID projectexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        ProjectExperience projectExperience = userInformation.getProjectExperiences().stream()
                .filter(p -> p.getProjectExperienceId().equals(projectexperienceid)).findFirst().get();
        userInformation.getProjectExperiences().remove(projectExperience);
        return projectExperience;
    }

    @Override
    public ProjectExperience updateProjectExperienceByProjectExperienceId(UUID userinfoid, UUID projectexperienceid,
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
    public List<ProjectExperience> getProjectExperiences(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getProjectExperiences();
    }

    @Override
    public ProjectExperience getProjectExperienceByProjectExperienceId(UUID userinfoid, UUID projectexperienceid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getProjectExperiences().stream()
                .filter(p -> p.getProjectExperienceId().equals(projectexperienceid)).findFirst().get();
    }

    @Override
    public DeliveryRecord createDeliveryRecord(UUID userinfoid, DeliveryRecord deliveryRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getDeliveryRecords().add(deliveryRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getDeliveryRecords().get(userInformation.getDeliveryRecords().size() - 1);
    }

    @Override
    public DeliveryRecord deleteDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        DeliveryRecord deliveryRecord = userInformation.getDeliveryRecords().stream()
                .filter(d -> d.getDeliveryRecordId().equals(deliveryrecordid)).findFirst().get();
        userInformation.getDeliveryRecords().remove(deliveryRecord);
        return deliveryRecord;
    }

    @Override
    public DeliveryRecord updateDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid,
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
    public List<DeliveryRecord> getDeliveryRecords(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getDeliveryRecords();
    }

    @Override
    public DeliveryRecord getDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getDeliveryRecords().stream()
                .filter(d -> d.getDeliveryRecordId().equals(deliveryrecordid)).findFirst().get();
    }

    @Override
    public AttentionRecord createAttentionRecord(UUID userinfoid, AttentionRecord attentionRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getAttentionRecords().add(attentionRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getAttentionRecords().get(userInformation.getAttentionRecords().size() - 1);
    }

    @Override
    public AttentionRecord deleteAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        AttentionRecord attentionRecord = userInformation.getAttentionRecords().stream()
                .filter(a -> a.getAttentionRecordId().equals(attentionrecordid)).findFirst().get();
        userInformation.getAttentionRecords().remove(attentionRecord);
        return attentionRecord;
    }

    @Override
    public AttentionRecord updateAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid,
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
    public List<AttentionRecord> getAttentionRecords(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getAttentionRecords();
    }

    @Override
    public AttentionRecord getAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getAttentionRecords().stream()
                .filter(a -> a.getAttentionRecordId().equals(attentionrecordid)).findFirst().get();
    }

    @Override
    public InspectionRecord createInspectionRecord(UUID userinfoid, InspectionRecord inspectionRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getInspectionRecords().add(inspectionRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getInspectionRecords().get(userInformation.getInspectionRecords().size() - 1);
    }

    @Override
    public InspectionRecord deleteInspectionRecordByInspectionRecordId(UUID userinfoid, UUID inspectionrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        InspectionRecord inspectionRecord = userInformation.getInspectionRecords().stream()
                .filter(i -> i.getInspectionRecordId().equals(inspectionrecordid)).findFirst().get();
        userInformation.getInspectionRecords().remove(inspectionRecord);
        return inspectionRecord;
    }

    @Override
    public InspectionRecord updateInspectionRecordByInspectionRecordId(UUID userinfoid, UUID inspectionrecordid,
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
    public List<InspectionRecord> getInspectionRecords(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getInspectionRecords();
    }

    @Override
    public InspectionRecord getInspectionRecordByInspectionRecordId(UUID userinfoid, UUID inspectionrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getInspectionRecords().stream()
                .filter(i -> i.getInspectionRecordId().equals(inspectionrecordid)).findFirst().get();
    }

    @Override
    public GarnerRecord createGarnerRecord(UUID userinfoid, GarnerRecord garnerRecord) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        userInformation.getGarnerRecords().add(garnerRecord);
        userInformation = userInformationRepository.save(userInformation);
        return userInformation.getGarnerRecords().get(userInformation.getGarnerRecords().size() - 1);
    }

    @Override
    public GarnerRecord deleteGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        GarnerRecord garnerRecord = userInformation.getGarnerRecords().stream()
                .filter(g -> g.getGarnerRecordId().equals(garnerrecordid)).findFirst().get();
        userInformation.getGarnerRecords().remove(garnerRecord);
        return garnerRecord;
    }

    @Override
    public GarnerRecord updateGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid,
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
    public List<GarnerRecord> getGarnerRecords(UUID userinfoid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getGarnerRecords();
    }

    @Override
    public GarnerRecord getGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        UserInformation userInformation = userInformationRepository.findById(userinfoid).get();
        return userInformation.getGarnerRecords().stream()
                .filter(g -> g.getGarnerRecordId().equals(garnerrecordid)).findFirst().get();
    }

}
