package com.ucstu.guangbt.djzhaopin.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;
import com.ucstu.guangbt.djzhaopin.model.company.BigData;

import org.springframework.data.domain.Pageable;

public interface CompanyInformationService {

    public ServiceToControllerBody<CompanyInformation> createCompanyInformation(
            CompanyInformation companyInformation);

    public ServiceToControllerBody<CompanyInformation> deleteCompanyInformationByCompanyInfoId(
            UUID companyInformationId);

    public ServiceToControllerBody<CompanyInformation> updateCompanyInformationByCompanyInformationId(
            UUID companyInformationId,
            CompanyInformation companyInformation);

    public ServiceToControllerBody<PageResult<CompanyInformation>> getCompanyInformationsByCompanyName(
            String companyName,
            Pageable pageable);

    public ServiceToControllerBody<CompanyInformation> getCompanyInformationByCompanyInformationId(
            UUID companyInformationId);

    public ServiceToControllerBody<PageResult<DeliveryRecord>> getDeliveryRecordsByCompanyInformationId(
            UUID companyInformationId, Date createdAt, Date updatedAt, List<Integer> status,
            Date interviewTime, List<Integer> workingYears, List<String> sexs, List<Integer> ages,
            List<UUID> positionInformationIds, List<Date> deliveryDates, String userName,
            Pageable pageable);

    public ServiceToControllerBody<PageResult<PositionInformation>> getPositionInfos(String positionName,
            String salary,
            List<Integer> workingYears, List<Integer> educations, List<String> directionTags,
            List<String> workAreas, List<Integer> positionTypes, List<Integer> scales,
            List<Integer> financingStages, List<String> comprehensions, String workingPlace,
            Pageable pageable);

    public ServiceToControllerBody<PositionInformation> createPositionInformation(UUID companyInformationId,
            PositionInformation positionInformation);

    public ServiceToControllerBody<PositionInformation> deletePositionInformationByPositionInformationId(
            UUID companyInformationId,
            UUID positionInformationId);

    public ServiceToControllerBody<PositionInformation> updatePositionInformationByPositionInformationId(
            UUID companyInformationId,
            UUID positionInformationId,
            PositionInformation positionInformation);

    public ServiceToControllerBody<PageResult<PositionInformation>> getPositionInformationsByCompanyInformationId(
            UUID companyInformationId, String positionName, String salary, List<Integer> workingYears,
            List<Integer> educations, List<String> directionTags, List<String> workAreas,
            List<Integer> positionTypes,
            List<Integer> scales, List<Integer> financingStages, List<String> comprehensions,
            String workingPlace,
            Pageable pageable);

    public ServiceToControllerBody<PositionInformation> getPositionInformationByPositionInformationId(
            UUID companyInformationId, UUID positionInformationId);

    public ServiceToControllerBody<PageResult<UserInspectionRecord>> getSawMeRecordsByCompanyInformationId(
            UUID companyInformationId, Date startDate, Date endDate, Pageable pageable);

    public ServiceToControllerBody<List<BigData>> getBigDataByCompanyInformationId(
            UUID companyInformationId, UUID hrInformationId, Date startDate, Date endDate,
            Pageable pageable);

}
