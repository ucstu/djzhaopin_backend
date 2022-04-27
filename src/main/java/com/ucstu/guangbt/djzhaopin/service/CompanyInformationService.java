package com.ucstu.guangbt.djzhaopin.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.model.ServiceToControllerBody;

import org.springframework.data.domain.Pageable;

public interface CompanyInformationService {

        public ServiceToControllerBody<CompanyInformation> createCompanyInformation(
                        CompanyInformation companyInformation);

        public ServiceToControllerBody<CompanyInformation> updateCompanyInformationByCompanyInformationId(
                        UUID companyInformationId,
                        CompanyInformation companyInformation);

        public ServiceToControllerBody<List<CompanyInformation>> getCompanyInformations(Pageable pageable);

        public ServiceToControllerBody<CompanyInformation> getCompanyInformationByCompanyInformationId(
                        UUID companyInformationId);

        public ServiceToControllerBody<List<DeliveryRecord>> getDeliveryRecordsByCompanyInformationId(
                        UUID companyInformationId,
                        Integer state,
                        Integer workingYears,
                        String sex, Integer age, UUID jobId, Date deliveryDate, String search, Pageable pageable);

        public ServiceToControllerBody<List<PositionInformation>> getPositionInfos(Pageable pageable);

        public ServiceToControllerBody<PositionInformation> createPositionInformation(UUID companyInformationId,
                        PositionInformation positionInformation);

        public ServiceToControllerBody<PositionInformation> deletePositionInformationByPositionInformationId(
                        UUID companyInformationId,
                        UUID positionInformationId);

        public ServiceToControllerBody<PositionInformation> updatePositionInformationByPositionInformationId(
                        UUID companyInformationId,
                        UUID positionInformationId,
                        PositionInformation positionInformation);

        public ServiceToControllerBody<List<PositionInformation>> getPositionInformationsByCompanyInformationId(
                        UUID companyInformationId,
                        Pageable pageable);

        public ServiceToControllerBody<PositionInformation> getPositionInformationByPositionInformationId(
                        UUID companyInformationId,
                        UUID positionInformationId);

}
