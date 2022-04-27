package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.repository.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.DeliveryRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.PositionInformationRepository;
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

    @Resource
    private PositionInformationRepository positionInformationRepository;

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
    public Optional<List<CompanyInformation>> getCompanyInformations(Pageable pageable) {
        return Optional.ofNullable(companyInformationRepository.findAll(pageable).getContent());
    }

    @Override
    public Optional<CompanyInformation> getCompanyInformationByCompanyInformationId(UUID companyInformationId) {
        return companyInformationRepository.findById(companyInformationId);
    }

    @Override
    public Optional<List<DeliveryRecord>> getDeliveryRecordsByCompanyInformationId(UUID companyInformationId,
            Integer state,
            Integer workingYears, String sex, Integer age, UUID jobId, Date deliveryDate, String search,
            Pageable pageable) {
        return Optional.ofNullable(deliveryRecordRepository.findAll(pageable).getContent());
    }

    @Override
    public Optional<List<PositionInformation>> getPositionInfos(Pageable pageable) {
        return Optional.ofNullable(positionInformationRepository.findAll(pageable).getContent());
    }

    @Override
    public Optional<PositionInformation> createPositionInformation(UUID companyInformationId,
            PositionInformation positionInformation) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            companyInformationOptional.get().getPositionInformations().add(positionInformation);
            return Optional.ofNullable(companyInformationRepository.save(companyInformationOptional.get())
                    .getPositionInformations()
                    .get(companyInformationOptional.get().getPositionInformations().size() - 1));
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
                positionInformation1.setCeilingSalary(positionInformation.getCeilingSalary());
                positionInformation1.setCompanyInformationId(positionInformation.getCompanyInformationId());
                positionInformation1.setDepartment(positionInformation.getDepartment());
                positionInformation1.setDescription(positionInformation.getDescription());
                positionInformation1.setDirectionTags(positionInformation.getDirectionTags());
                positionInformation1.setEducation(positionInformation.getEducation());
                positionInformation1.setHighlights(positionInformation.getHighlights());
                positionInformation1.setHrInformationId(positionInformation.getHrInformationId());
                positionInformation1.setName(positionInformation.getName());
                positionInformation1.setPositionInterviewInfo(positionInformation.getPositionInterviewInfo());
                positionInformation1.setPositionType(positionInformation.getPositionType());
                positionInformation1.setPositionWorkingPlace(positionInformation.getPositionWorkingPlace());
                positionInformation1.setReleaseDate(positionInformation.getReleaseDate());
                positionInformation1.setStartingSalary(positionInformation.getStartingSalary());
                positionInformation1.setWeekendReleaseTime(positionInformation.getWeekendReleaseTime());
                positionInformation1.setWorkArea(positionInformation.getWorkArea());
                positionInformation1.setWorkTime(positionInformation.getWorkTime());
                positionInformation1.setWorkingYears(positionInformation.getWorkingYears());
                return Optional.ofNullable(companyInformationRepository.save(companyInformation)
                        .getPositionInformations()
                        .get(companyInformation.getPositionInformations().indexOf(positionInformation1)));
            }
            return Optional.empty();
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<PositionInformation>> getPositionInformationsByCompanyInformationId(UUID companyInformationId,
            Pageable pageable) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationRepository
                .findById(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            return Optional.of(companyInformationOptional.get().getPositionInformations());
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
}
