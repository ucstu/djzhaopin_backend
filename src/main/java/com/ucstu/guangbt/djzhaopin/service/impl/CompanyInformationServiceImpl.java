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

public class CompanyInformationServiceImpl implements CompanyInformationService {

    @Autowired
    private CompanyInformationRepository companyInformationRepository;

    @Override
    public CompanyInformation createCompanyInformation(CompanyInformation companyInformation) {
        return companyInformationRepository.save(companyInformation);
    }

    @Override
    public CompanyInformation updateCompanyInformationByCompanyInfoId(UUID companyinfoid,
            CompanyInformation companyInformation) {
        CompanyInformation companyInformation2 = companyInformationRepository.findById(companyinfoid).get();
        if (companyInformation2 != null)
            return null;
        else {
            companyInformation.setCompanyId(companyinfoid);
            return companyInformationRepository.saveAndFlush(companyInformation);
        }
    }

    @Override
    public List<CompanyInformation> queryCompanyInformations(Pageable pageable) {
        return companyInformationRepository.findAll(pageable).getContent();
    }

    @Override
    public CompanyInformation queryCompanyInformationByCompanyInfoId(UUID companyinfoid) {
        return companyInformationRepository.findById(companyinfoid).get();
    }

    @Override
    public List<DeliveryRecord> queryDeliveryRecords(UUID companyinfoid, Integer state, Integer workingYears,
            String sex, Integer age, UUID jobId, Date deliveryDate, String search, Pageable pageable) {
        return null;
    }

}
