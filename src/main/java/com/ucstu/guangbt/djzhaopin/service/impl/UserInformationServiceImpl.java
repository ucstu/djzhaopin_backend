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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserInformationServiceImpl implements UserInformationService {

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Override
    public UserInformation updateUserInformationByUserInfoId(UUID userinfoid, UserInformation userInformation) {
        userInformation.setUserId(userinfoid);
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EducationExperience> getEducationExperiences(UUID userinfoid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EducationExperience getEducationExperienceByEducationExperienceId(UUID userinfoid, UUID eduexperienceid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorkExperience createWorkExperience(UUID userinfoid, WorkExperience workExperience) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorkExperience deleteWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorkExperience updateWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid,
            WorkExperience workExperience) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WorkExperience> getWorkExperiences(UUID userinfoid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WorkExperience getWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProjectExperience createProjectExperience(UUID userinfoid, ProjectExperience projectExperience) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProjectExperience deleteProjectExperienceByProjectExperienceId(UUID userinfoid, UUID projectexperienceid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProjectExperience updateProjectExperienceByProjectExperienceId(UUID userinfoid, UUID projectexperienceid,
            ProjectExperience projectExperience) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<ProjectExperience> getProjectExperiences(UUID userinfoid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProjectExperience getProjectExperienceByProjectExperienceId(UUID userinfoid, UUID projectexperienceid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeliveryRecord createDeliveryRecord(UUID userinfoid, DeliveryRecord deliveryRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeliveryRecord deleteDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeliveryRecord updateDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid,
            DeliveryRecord deliveryRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecords(UUID userinfoid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DeliveryRecord getDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AttentionRecord createAttentionRecord(UUID userinfoid, AttentionRecord attentionRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AttentionRecord deleteAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AttentionRecord updateAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid,
            AttentionRecord attentionRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<AttentionRecord> getAttentionRecords(UUID userinfoid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AttentionRecord getAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InspectionRecord createInspectionRecord(UUID userinfoid, InspectionRecord inspectionRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InspectionRecord deleteInspectionRecordByInspectionRecordId(UUID userinfoid, UUID inspectionrecordid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InspectionRecord updateInspectionRecordByInspectionRecordId(UUID userinfoid, UUID inspectionrecordid,
            InspectionRecord inspectionRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<InspectionRecord> getInspectionRecords(UUID userinfoid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public InspectionRecord getInspectionRecordByInspectionRecordId(UUID userinfoid, UUID inspectionrecordid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GarnerRecord createGarnerRecord(UUID userinfoid, GarnerRecord garnerRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GarnerRecord deleteGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GarnerRecord updateGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid,
            GarnerRecord garnerRecord) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<GarnerRecord> getGarnerRecords(UUID userinfoid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GarnerRecord getGarnerRecordByGarnerRecordId(UUID userinfoid, UUID garnerrecordid) {
        // TODO Auto-generated method stub
        return null;
    }

}
