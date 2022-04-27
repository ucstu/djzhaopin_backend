package com.ucstu.guangbt.djzhaopin.service;

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
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;

import org.springframework.data.domain.Pageable;

public interface UserInformationService {

        public ServiceToControllerBody<UserInformation> updateUserInformationByUserInfoId(UUID userinfoid,
                        UserInformation userInformation);

        public ServiceToControllerBody<List<UserInformation>> getUserInformations(Pageable pageable);

        public ServiceToControllerBody<UserInformation> getUserInformationByUserInfoId(UUID userinfoid);

        public ServiceToControllerBody<JobExpectation> createJobExpectation(UUID userinfoid,
                        JobExpectation jobExpectation);

        public ServiceToControllerBody<JobExpectation> deleteJobExpectationByJobExpectationId(UUID userinfoid,
                        UUID jobexpectationid);

        public ServiceToControllerBody<JobExpectation> updateJobExpectationByJobExpectationId(UUID userinfoid,
                        UUID jobexpectationid,
                        JobExpectation jobExpectation);

        public ServiceToControllerBody<List<JobExpectation>> getJobExpectationsByUserInformationId(UUID userinfoid,
                        Pageable pageable);

        public ServiceToControllerBody<JobExpectation> getJobExpectationByJobExpectationId(UUID userinfoid,
                        UUID jobexpectationid);

        public ServiceToControllerBody<EducationExperience> createEducationExperience(UUID userinfoid,
                        EducationExperience educationExperience);

        public ServiceToControllerBody<EducationExperience> deleteEducationExperienceByEducationExperienceId(
                        UUID userinfoid,
                        UUID eduexperienceid);

        public ServiceToControllerBody<EducationExperience> updateEducationExperienceByEducationExperienceId(
                        UUID userinfoid,
                        UUID eduexperienceid,
                        EducationExperience educationExperience);

        public ServiceToControllerBody<List<EducationExperience>> getEducationExperiencesByUserInformationId(
                        UUID userinfoid,
                        Pageable pageable);

        public ServiceToControllerBody<EducationExperience> getEducationExperienceByEducationExperienceId(
                        UUID userinfoid,
                        UUID eduexperienceid);

        public ServiceToControllerBody<WorkExperience> createWorkExperience(UUID userinfoid,
                        WorkExperience workExperience);

        public ServiceToControllerBody<WorkExperience> deleteWorkExperienceByWorkExperienceId(UUID userinfoid,
                        UUID workexperienceid);

        public ServiceToControllerBody<WorkExperience> updateWorkExperienceByWorkExperienceId(UUID userinfoid,
                        UUID workexperienceid,
                        WorkExperience workExperience);

        public ServiceToControllerBody<List<WorkExperience>> getWorkExperiencesByUserInformationId(UUID userinfoid,
                        Pageable pageable);

        public ServiceToControllerBody<WorkExperience> getWorkExperienceByWorkExperienceId(UUID userinfoid,
                        UUID workexperienceid);

        public ServiceToControllerBody<ProjectExperience> createProjectExperience(UUID userinfoid,
                        ProjectExperience projectExperience);

        public ServiceToControllerBody<ProjectExperience> deleteProjectExperienceByProjectExperienceId(UUID userinfoid,
                        UUID projectexperienceid);

        public ServiceToControllerBody<ProjectExperience> updateProjectExperienceByProjectExperienceId(UUID userinfoid,
                        UUID projectexperienceid,
                        ProjectExperience projectExperience);

        public ServiceToControllerBody<List<ProjectExperience>> getProjectExperiencesByUserInformationId(
                        UUID userinfoid,
                        Pageable pageable);

        public ServiceToControllerBody<ProjectExperience> getProjectExperienceByProjectExperienceId(UUID userinfoid,
                        UUID projectexperienceid);

        public ServiceToControllerBody<DeliveryRecord> createDeliveryRecord(UUID userinfoid,
                        DeliveryRecord deliveryRecord);

        public ServiceToControllerBody<DeliveryRecord> deleteDeliveryRecordByDeliveryRecordId(UUID userinfoid,
                        UUID deliveryrecordid);

        public ServiceToControllerBody<DeliveryRecord> updateDeliveryRecordByDeliveryRecordId(UUID userinfoid,
                        UUID deliveryrecordid,
                        DeliveryRecord deliveryRecord);

        public ServiceToControllerBody<List<DeliveryRecord>> getDeliveryRecordsByUserInformationId(UUID userinfoid,
                        Pageable pageable);

        public ServiceToControllerBody<DeliveryRecord> getDeliveryRecordByDeliveryRecordId(UUID userinfoid,
                        UUID deliveryrecordid);

        public ServiceToControllerBody<AttentionRecord> createAttentionRecord(UUID userinfoid,
                        AttentionRecord attentionRecord);

        public ServiceToControllerBody<AttentionRecord> deleteAttentionRecordByAttentionRecordId(UUID userinfoid,
                        UUID attentionrecordid);

        public ServiceToControllerBody<AttentionRecord> updateAttentionRecordByAttentionRecordId(UUID userinfoid,
                        UUID attentionrecordid,
                        AttentionRecord attentionRecord);

        public ServiceToControllerBody<List<AttentionRecord>> getAttentionRecordsByUserInformationId(UUID userinfoid);

        public ServiceToControllerBody<AttentionRecord> getAttentionRecordByAttentionRecordId(UUID userinfoid,
                        UUID attentionrecordid);

        public ServiceToControllerBody<InspectionRecord> createInspectionRecord(UUID userinfoid,
                        InspectionRecord inspectionRecord);

        public ServiceToControllerBody<InspectionRecord> deleteInspectionRecordByInspectionRecordId(UUID userinfoid,
                        UUID inspectionrecordid);

        public ServiceToControllerBody<InspectionRecord> updateInspectionRecordByInspectionRecordId(UUID userinfoid,
                        UUID inspectionrecordid,
                        InspectionRecord inspectionRecord);

        public ServiceToControllerBody<List<InspectionRecord>> getInspectionRecordsByUserInformationId(UUID userinfoid,
                        Pageable pageable);

        public ServiceToControllerBody<InspectionRecord> getInspectionRecordByInspectionRecordId(UUID userinfoid,
                        UUID inspectionrecordid);

        public ServiceToControllerBody<GarnerRecord> createGarnerRecord(UUID userinfoid, GarnerRecord garnerRecord);

        public ServiceToControllerBody<GarnerRecord> deleteGarnerRecordByGarnerRecordId(UUID userinfoid,
                        UUID garnerrecordid);

        public ServiceToControllerBody<GarnerRecord> updateGarnerRecordByGarnerRecordId(UUID userinfoid,
                        UUID garnerrecordid,
                        GarnerRecord garnerRecord);

        public ServiceToControllerBody<List<GarnerRecord>> getGarnerRecordsByUserInformationId(UUID userinfoid,
                        Pageable pageable);

        public ServiceToControllerBody<GarnerRecord> getGarnerRecordByGarnerRecordId(UUID userinfoid,
                        UUID garnerrecordid);

}
