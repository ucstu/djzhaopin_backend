package com.ucstu.guangbt.djzhaopin.controller.company;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/companyinfos")
public class CompanyInformationCotroller {

    @Resource
    private CompanyInformationService companyInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<CompanyInformation>> createCompanyInformation(
            @Valid @RequestBody CompanyInformation companyInformation) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationService
                .createCompanyInformation(companyInformation);
        if (companyInformationOptional.isPresent()) {
            return ResponseBody.created(companyInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @PostMapping("{companyinfoid}")
    public ResponseEntity<ResponseBody<CompanyInformation>> updateCompanyInformationByCompanyInformationId(
            @PathVariable("companyinfoid") UUID companyInformationId,
            @Valid @RequestBody CompanyInformation companyInformation) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationService
                .updateCompanyInformationByCompanyInformationId(companyInformationId,
                        companyInformation);
        if (companyInformationOptional.isPresent()) {
            return ResponseBody.success(companyInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<CompanyInformation>>> getCompanyInformations(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Optional<List<CompanyInformation>> companyInformationsOptional = companyInformationService
                .getCompanyInformations(pageable);
        if (companyInformationsOptional.isPresent()) {
            return ResponseBody.success(companyInformationsOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("{companyinfoid}")
    public ResponseEntity<ResponseBody<CompanyInformation>> getCompanyInformationByCompanyInformationId(
            @PathVariable("companyinfoid") UUID companyInformationId) {
        Optional<CompanyInformation> companyInformationOptional = companyInformationService
                .getCompanyInformationByCompanyInformationId(companyInformationId);
        if (companyInformationOptional.isPresent()) {
            return ResponseBody.success(companyInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("{companyinfoid}/deliveryrecords")
    public ResponseEntity<ResponseBody<List<DeliveryRecord>>> getDeliveryRecordsByCompanyInformationId(
            @PathVariable("companyinfoid") UUID companyInformationId,
            @RequestParam Integer state,
            @RequestParam Integer workingYears,
            @RequestParam String sex, @RequestParam Integer age, @RequestParam UUID jobId,
            @RequestParam Date deliveryDate, @RequestParam String search,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Optional<List<DeliveryRecord>> deliveryRecordsOptional = companyInformationService
                .getDeliveryRecordsByCompanyInformationId(companyInformationId, state, workingYears,
                        sex, age, jobId, deliveryDate, search, pageable);
        if (deliveryRecordsOptional.isPresent()) {
            return ResponseBody.success(deliveryRecordsOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("/positioninfos")
    public ResponseEntity<ResponseBody<List<PositionInformation>>> getPositionInfos(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Optional<List<PositionInformation>> companyInformationsOptional = companyInformationService
                .getPositionInfos(pageable);
        if (companyInformationsOptional.isPresent()) {
            return ResponseBody.success(companyInformationsOptional.get());
        }
        return ResponseBody.notFound().build();
    }
}
