package com.ucstu.guangbt.djzhaopin.controller.company;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/companyinfos")
public class CompanyInformationCotroller {

        @Autowired
        private CompanyInformationService companyInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<CompanyInformation>> createCompanyInformation(
                        @Valid @RequestBody CompanyInformation companyInformation) {
                return ResponseBody.created(companyInformationService.createCompanyInformation(companyInformation));
        }

        @PostMapping("{companyinfoid}")
        public ResponseEntity<ResponseBody<CompanyInformation>> updateCompanyInformationByCompanyInfoId(
                        @PathVariable UUID companyinfoid,
                        @Valid @RequestBody CompanyInformation companyInformation) {
                return ResponseBody.created(
                                companyInformationService.updateCompanyInformationByCompanyInfoId(companyinfoid,
                                                companyInformation));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<CompanyInformation>>> getCompanyInformations(
                        @PageableDefault(value = 5, sort = {
                                        "companyInformationId" }, direction = Sort.Direction.DESC) Pageable pageable) {
                return ResponseBody.success(companyInformationService.getCompanyInformations(pageable));
        }

        @GetMapping("{companyinfoid}")
        public ResponseEntity<ResponseBody<CompanyInformation>> getCompanyInformationByCompanyInfoId(
                        @PathVariable UUID companyinfoid) {
                return ResponseBody.created(
                                companyInformationService.getCompanyInformationByCompanyInfoId(companyinfoid));
        }

        /**
         * 获取投递记录信息
         *
         * @param companyinfoid
         * @param state
         * @param workingYears
         * @param sex
         * @param age
         * @param jobId
         * @param deliveryDate
         * @param search
         * @param pageable
         * @return
         */
        @GetMapping("{companyinfoid}/deliveryrecords")
        public ResponseEntity<ResponseBody<List<DeliveryRecord>>> getDeliveryRecords(
                        @PathVariable UUID companyinfoid, @RequestParam Integer state,
                        @RequestParam Integer workingYears,
                        @RequestParam String sex, @RequestParam Integer age, @RequestParam UUID jobId,
                        @RequestParam Date deliveryDate, @RequestParam String search,
                        @PageableDefault(value = 5, sort = {
                                        "deliveryRecordId" }, direction = Sort.Direction.DESC) Pageable pageable) {
                return ResponseBody
                                .success(companyInformationService.getDeliveryRecords(companyinfoid, state,
                                                workingYears, sex, age,
                                                jobId, deliveryDate, search, pageable));
        }
}
