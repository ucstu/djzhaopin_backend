package com.ucstu.guangbt.djzhaopin.service;

import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;

import org.springframework.data.domain.Pageable;

public interface CompanyInformationService {
        public Optional<CompanyInformation> createCompanyInformation(CompanyInformation companyInformation);

        public Optional<CompanyInformation> updateCompanyInformationByCompanyInformationId(UUID companyInformationId,
                        CompanyInformation companyInformation);

        public Optional<Set<CompanyInformation>> getCompanyInformations(Pageable pageable);

        public Optional<CompanyInformation> getCompanyInformationByCompanyInformationId(UUID companyInformationId);

        public Optional<Set<DeliveryRecord>> getDeliveryRecordsByCompanyInformationId(UUID companyInformationId,
                        Integer state,
                        Integer workingYears,
                        String sex, Integer age, UUID jobId, Date deliveryDate, String search, Pageable pageable);

        public Optional<PositionInformation> createPositionInformation(UUID companyInformationId,
                        PositionInformation positionInformation);

        public Optional<PositionInformation> deletePositionInformationByPositionInformationId(UUID companyInformationId,
                        UUID positionInformationId);

        public Optional<PositionInformation> updatePositionInformationByPositionInformationId(UUID companyInformationId,
                        UUID positionInformationId,
                        PositionInformation positionInformation);

        public Optional<PositionInformation> getPositionInformationByPositionInfoId(UUID companyInformationId,
                        UUID positionInformationId);

        public Optional<Set<PositionInformation>> getPositionInformationsByCompanyInformationId(
                        UUID companyInformationId,
                        Pageable pageable);
}
