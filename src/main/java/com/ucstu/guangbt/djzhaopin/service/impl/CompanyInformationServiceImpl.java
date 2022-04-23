package com.ucstu.guangbt.djzhaopin.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.repository.CompanyInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CompanyInformationServiceImpl implements CompanyInformationService {

    @Autowired
    private CompanyInformationRepository companyInformationRepository;

    @Override
    public CompanyInformation createCompanyInformation(CompanyInformation companyInformation) {
        return companyInformationRepository.save(companyInformation);
    }

    @Override
    public CompanyInformation updateCompanyInformationByCompanyInfoId(UUID companyInformationId,
            CompanyInformation companyInformation) {
        CompanyInformation companyInformation2 = companyInformationRepository.findById(companyInformationId).get();
        if (companyInformation2 != null)
            return null;
        else {
            companyInformation.setCompanyInformationId(companyInformationId);
            return companyInformationRepository.saveAndFlush(companyInformation);
        }
    }

    @Override
    public List<CompanyInformation> getCompanyInformations(Pageable pageable) {
        return companyInformationRepository.findAll(pageable).getContent();
    }

    @Override
    public CompanyInformation getCompanyInformationByCompanyInfoId(UUID companyinfoid) {
        return companyInformationRepository.findById(companyinfoid).get();
    }

    @Override
    public List<DeliveryRecord> getDeliveryRecords(UUID companyinfoid, Integer state, Integer workingYears,
            String sex, Integer age, UUID jobId, Date deliveryDate, String search, Pageable pageable) {
        if (companyInformationRepository.findById(companyinfoid) != null) {

        }
        return null;
    }

}
