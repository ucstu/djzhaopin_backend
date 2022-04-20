package com.ucstu.guangbt.djzhaopin.service;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.EducationExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.JobExpectation;
import com.ucstu.guangbt.djzhaopin.entity.user.ProjectExperience;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;

import org.springframework.data.domain.Pageable;

public interface UserInformationService {

        public UserInformation updateUserInformationByUserInfoId(UUID userinfoid, UserInformation userInformation);

        public List<UserInformation> getUserInformations(Pageable pageable);

        public UserInformation getUserInformationByUserInfoId(UUID userinfoid);

        public JobExpectation createJobExpectation(UUID userinfoid, JobExpectation jobExpectation);

        public JobExpectation deleteJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid);

        public JobExpectation updateJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid,
                        JobExpectation jobExpectation);

        public List<JobExpectation> getJobExpectations(UUID userinfoid);

        public JobExpectation getJobExpectationByJobExpectationId(UUID userinfoid, UUID jobexpectationid);

        public EducationExperience createEducationExperience(UUID userinfoid,
                        EducationExperience educationExperience);

        public EducationExperience deleteEducationExperienceByEducationExperienceId(UUID userinfoid,
                        UUID eduexperienceid);

        public EducationExperience updateEducationExperienceByEducationExperienceId(UUID userinfoid,
                        UUID eduexperienceid,
                        EducationExperience educationExperience);

        public List<EducationExperience> getEducationExperiences(UUID userinfoid);

        public EducationExperience getEducationExperienceByEducationExperienceId(UUID userinfoid, UUID eduexperienceid);

        public WorkExperience createWorkExperience(UUID userinfoid, WorkExperience workExperience);

        public WorkExperience deleteWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid);

        public WorkExperience updateWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid,
                        WorkExperience workExperience);

        public List<WorkExperience> getWorkExperiences(UUID userinfoid);

        public WorkExperience getWorkExperienceByWorkExperienceId(UUID userinfoid, UUID workexperienceid);

        public ProjectExperience createProjectExperience(UUID userinfoid, ProjectExperience projectExperience);

        public ProjectExperience deleteProjectExperienceByProjectExperienceId(UUID userinfoid,
                        UUID projectexperienceid);

        public ProjectExperience updateProjectExperienceByProjectExperienceId(UUID userinfoid, UUID projectexperienceid,
                        ProjectExperience projectExperience);

        public List<ProjectExperience> getProjectExperiences(UUID userinfoid);

        public ProjectExperience getProjectExperienceByProjectExperienceId(UUID userinfoid, UUID projectexperienceid);

        public DeliveryRecord createDeliveryRecord(UUID userinfoid, DeliveryRecord deliveryRecord);

        public DeliveryRecord deleteDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid);

        public DeliveryRecord updateDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid,
                        DeliveryRecord deliveryRecord);

        public List<DeliveryRecord> getDeliveryRecords(UUID userinfoid);

        public DeliveryRecord getDeliveryRecordByDeliveryRecordId(UUID userinfoid, UUID deliveryrecordid);

        public AttentionRecord createAttentionRecord(UUID userinfoid, AttentionRecord attentionRecord);

        public AttentionRecord deleteAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid);

        public AttentionRecord updateAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid,
                        AttentionRecord attentionRecord);

        public List<AttentionRecord> getAttentionRecords(UUID userinfoid);

        public AttentionRecord getAttentionRecordByAttentionRecordId(UUID userinfoid, UUID attentionrecordid);

}
