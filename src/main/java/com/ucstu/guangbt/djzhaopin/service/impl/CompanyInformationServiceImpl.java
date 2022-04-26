package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.repository.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.DeliveryRecordRepository;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class CompanyInformationServiceImpl implements CompanyInformationService {

    @Resource
    private CompanyInformationRepository companyInformationRepository;

    @Resource
    private DeliveryRecordRepository deliveryRecordRepository;

    @Override
    public Optional<CompanyInformation> createCompanyInformation(CompanyInformation companyInformation) {
        return Optional.ofNullable(companyInformationRepository.save(companyInformation));
    }

    @Override
    public Optional<CompanyInformation> updateCompanyInformationByCompanyInformationId(UUID companyInformationId,
            CompanyInformation companyInformation) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            companyInformation.setCompanyInformationId(companyInformationId);
            return Optional.ofNullable(companyInformationRepository.save(companyInformation));
        }
        return Optional.empty();
    }

    @Override
    public Stream<CompanyInformation> getCompanyInformations(Pageable pageable) {
        return companyInformationRepository.findAll(pageable).getContent().stream();
    }

    @Override
    public Optional<CompanyInformation> getCompanyInformationByCompanyInformationId(UUID companyInformationId) {
        return companyInformationRepository.findById(companyInformationId);
    }

    @Override
    public Stream<DeliveryRecord> getDeliveryRecordsByCompanyInformationId(UUID companyInformationId, Integer state,
            Integer workingYears, String sex, Integer age, UUID jobId, Date deliveryDate, String search,
            Pageable pageable) {
        // return deliveryRecordRepository
        // .searchDeliveryRecord(companyInformationId, state, workingYears, sex, age,
        // jobId, deliveryDate, search,
        // pageable)
        // .stream();
        return Stream.empty();
    }

    @Override
    public Optional<PositionInformation> createPositionInformation(UUID companyInformationId,
            PositionInformation positionInformation) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            companyInformationOptional.get().getPositionInformations().add(positionInformation);
            companyInformationRepository.save(companyInformationOptional.get());
            return Optional.ofNullable(positionInformation);
        }
        return Optional.empty();
    }

    @Override
    public Optional<PositionInformation> deletePositionInformationByPositionInformationId(UUID companyInformationId,
            UUID positionInformationId) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            CompanyInformation companyInformation = companyInformationOptional.get();
            Optional<PositionInformation> positionInformationOptional = companyInformation.getPositionInformations()
                    .stream()
                    .filter(positionInformation -> positionInformation.getPositionInformationId()
                            .equals(positionInformationId))
                    .findFirst();
            if (positionInformationOptional.isPresent()) {
                companyInformation.getPositionInformations().remove(positionInformationOptional.get());
                return Optional.of((PositionInformation) companyInformationRepository.save(companyInformation)
                        .getPositionInformations()
                        .toArray()[companyInformation.getPositionInformations().size() - 1]);
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<PositionInformation> updatePositionInformationByPositionInformationId(UUID companyInformationId,
            UUID positionInformationId,
            PositionInformation positionInformation) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            CompanyInformation companyInformation = companyInformationOptional.get();
            Optional<PositionInformation> positionInformationOptional = companyInformation.getPositionInformations()
                    .stream()
                    .filter(positionInformation1 -> positionInformation1.getPositionInformationId()
                            .equals(positionInformationId))
                    .findFirst();
            if (positionInformationOptional.isPresent()) {
                PositionInformation positionInformation1 = positionInformationOptional.get();
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<PositionInformation> getPositionInformationByPositionInfoId(UUID companyInformationId,
            UUID positioninfoid) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            CompanyInformation companyInformation = companyInformationOptional.get();
            Optional<PositionInformation> positionInformationOptional = companyInformation.getPositionInformations()
                    .stream()
                    .filter(positionInformation -> positionInformation.getPositionInformationId()
                            .equals(positioninfoid))
                    .findFirst();
            if (positionInformationOptional.isPresent()) {
                return Optional.of(positionInformationOptional.get());
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Stream<PositionInformation> getPositionInformationsByCompanyInformationId(UUID companyInformationId,
            Pageable pageable) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            CompanyInformation companyInformation = companyInformationOptional.get();
            return companyInformation.getPositionInformations().stream();
        }
        return Stream.empty();
    }

}
