package com.ucstu.guangbt.djzhaopin.service;

import java.util.UUID;

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

import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Pageable;

public interface UserInformationService {
        public ServiceToControllerBody<UserInformation> createUserInformation(UserInformation userInformation);

        public ServiceToControllerBody<UserInformation> deleteUserInformationByUserInformationId(
                        UUID userInformationId);

        public ServiceToControllerBody<UserInformation> updateUserInformationByUserInformationId(UUID userInformationId,
                        UserInformation userInformation);

        public ServiceToControllerBody<PageResult<UserInformation>> getUserInformations(Pageable pageable);

        public ServiceToControllerBody<UserInformation> getUserInformationByUserInformationId(UUID userInformationId);

        public ServiceToControllerBody<JobExpectation> createJobExpectation(UUID userInformationId,
                        JobExpectation jobExpectation);

        public ServiceToControllerBody<JobExpectation> deleteJobExpectationByJobExpectationId(UUID userInformationId,
                        UUID jobExpectationId);

        public ServiceToControllerBody<JobExpectation> updateJobExpectationByJobExpectationId(UUID userInformationId,
                        UUID jobExpectationId,
                        JobExpectation jobExpectation);

        public ServiceToControllerBody<PageResult<JobExpectation>> getJobExpectationsByUserInformationId(
                        UUID userInformationId,
                        Pageable pageable);

        public ServiceToControllerBody<JobExpectation> getJobExpectationByJobExpectationId(UUID userInformationId,
                        UUID jobExpectationId);

        public ServiceToControllerBody<EducationExperience> createEducationExperience(UUID userInformationId,
                        EducationExperience educationExperience);

        public ServiceToControllerBody<EducationExperience> deleteEducationExperienceByEducationExperienceId(
                        UUID userInformationId,
                        UUID eduExperienceId);

        public ServiceToControllerBody<EducationExperience> updateEducationExperienceByEducationExperienceId(
                        UUID userInformationId,
                        UUID eduExperienceId,
                        EducationExperience educationExperience);

        public ServiceToControllerBody<PageResult<EducationExperience>> getEducationExperiencesByUserInformationId(
                        UUID userInformationId,
                        Pageable pageable);

        public ServiceToControllerBody<EducationExperience> getEducationExperienceByEducationExperienceId(
                        UUID userInformationId,
                        UUID eduExperienceId);

        public ServiceToControllerBody<WorkExperience> createWorkExperience(UUID userInformationId,
                        WorkExperience workExperience);

        public ServiceToControllerBody<WorkExperience> deleteWorkExperienceByWorkExperienceId(UUID userInformationId,
                        UUID workExperienceId);

        public ServiceToControllerBody<WorkExperience> updateWorkExperienceByWorkExperienceId(UUID userInformationId,
                        UUID workExperienceId,
                        WorkExperience workExperience);

        public ServiceToControllerBody<PageResult<WorkExperience>> getWorkExperiencesByUserInformationId(
                        UUID userInformationId,
                        Pageable pageable);

        public ServiceToControllerBody<WorkExperience> getWorkExperienceByWorkExperienceId(UUID userInformationId,
                        UUID workExperienceId);

        public ServiceToControllerBody<ProjectExperience> createProjectExperience(UUID userInformationId,
                        ProjectExperience projectExperience);

        public ServiceToControllerBody<ProjectExperience> deleteProjectExperienceByProjectExperienceId(
                        UUID userInformationId,
                        UUID projectExperienceId);

        public ServiceToControllerBody<ProjectExperience> updateProjectExperienceByProjectExperienceId(
                        UUID userInformationId,
                        UUID projectExperienceId,
                        ProjectExperience projectExperience);

        public ServiceToControllerBody<PageResult<ProjectExperience>> getProjectExperiencesByUserInformationId(
                        UUID userInformationId,
                        Pageable pageable);

        public ServiceToControllerBody<ProjectExperience> getProjectExperienceByProjectExperienceId(
                        UUID userInformationId,
                        UUID projectExperienceId);

        public ServiceToControllerBody<DeliveryRecord> createDeliveryRecord(UUID userInformationId,
                        DeliveryRecord deliveryRecord);

        public ServiceToControllerBody<DeliveryRecord> deleteDeliveryRecordByDeliveryRecordId(UUID userInformationId,
                        UUID deliveryRecordId);

        public ServiceToControllerBody<DeliveryRecord> updateDeliveryRecordByDeliveryRecordId(UUID userInformationId,
                        UUID deliveryRecordId,
                        DeliveryRecord deliveryRecord);

        public ServiceToControllerBody<PageResult<DeliveryRecord>> getDeliveryRecordsByUserInformationId(
                        UUID userInformationId,
                        @Range(min = 1, max = 5) Integer status, Pageable pageable);

        public ServiceToControllerBody<DeliveryRecord> getDeliveryRecordByDeliveryRecordId(UUID userInformationId,
                        UUID deliveryRecordId);

        public ServiceToControllerBody<AttentionRecord> createAttentionRecord(UUID userInformationId,
                        AttentionRecord attentionRecord);

        public ServiceToControllerBody<AttentionRecord> deleteAttentionRecordByAttentionRecordId(UUID userInformationId,
                        UUID attentionRecordId);

        public ServiceToControllerBody<AttentionRecord> updateAttentionRecordByAttentionRecordId(UUID userInformationId,
                        UUID attentionRecordId,
                        AttentionRecord attentionRecord);

        public ServiceToControllerBody<PageResult<AttentionRecord>> getAttentionRecordsByUserInformationId(
                        UUID userInformationId);

        public ServiceToControllerBody<AttentionRecord> getAttentionRecordByAttentionRecordId(UUID userInformationId,
                        UUID attentionRecordId);

        public ServiceToControllerBody<UserInspectionRecord> createUserInspectionRecord(UUID userInformationId,
                        UserInspectionRecord userUserInspectionRecord);

        public ServiceToControllerBody<UserInspectionRecord> deleteUserInspectionRecordByUserInspectionRecordId(
                        UUID userInformationId,
                        UUID userUserInspectionRecordId);

        public ServiceToControllerBody<UserInspectionRecord> updateUserInspectionRecordByUserInspectionRecordId(
                        UUID userInformationId,
                        UUID userUserInspectionRecordId,
                        UserInspectionRecord userUserInspectionRecord);

        public ServiceToControllerBody<PageResult<UserInspectionRecord>> getUserInspectionRecordsByUserInformationId(
                        UUID userInformationId,
                        Pageable pageable);

        public ServiceToControllerBody<UserInspectionRecord> getUserInspectionRecordByUserInspectionRecordId(
                        UUID userInformationId,
                        UUID userUserInspectionRecordId);

        public ServiceToControllerBody<GarnerRecord> createGarnerRecord(UUID userInformationId,
                        GarnerRecord garnerRecord);

        public ServiceToControllerBody<GarnerRecord> deleteGarnerRecordByGarnerRecordId(UUID userInformationId,
                        UUID garnerRecordId);

        public ServiceToControllerBody<GarnerRecord> updateGarnerRecordByGarnerRecordId(UUID userInformationId,
                        UUID garnerRecordId,
                        GarnerRecord garnerRecord);

        public ServiceToControllerBody<PageResult<GarnerRecord>> getGarnerRecordsByUserInformationId(
                        UUID userInformationId,
                        Pageable pageable);

        public ServiceToControllerBody<GarnerRecord> getGarnerRecordByGarnerRecordId(UUID userInformationId,
                        UUID garnerRecordId);
}
