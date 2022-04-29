package com.ucstu.guangbt.djzhaopin.controller.company;

import java.util.Date;
import java.util.List;
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
import jakarta.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/companyinfos")
public class CompanyInformationController {

        @Resource
        private CompanyInformationService companyInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<CompanyInformation>> createCompanyInformation(
                        @Valid @RequestBody CompanyInformation companyInformation) {
                return ResponseBody.handle(companyInformationService
                                .createCompanyInformation(companyInformation));
        }

        @PostMapping("{companyinfoid}")
        public ResponseEntity<ResponseBody<CompanyInformation>> updateCompanyInformationByCompanyInformationId(
                        @PathVariable("companyinfoid") @NotNull UUID companyInformationId,
                        @Valid @RequestBody CompanyInformation companyInformation) {
                return ResponseBody.handle(companyInformationService
                                .updateCompanyInformationByCompanyInformationId(companyInformationId,
                                                companyInformation));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<CompanyInformation>>> getCompanyInformations(
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getCompanyInformations(pageable));
        }

        @GetMapping("{companyinfoid}")
        public ResponseEntity<ResponseBody<CompanyInformation>> getCompanyInformationByCompanyInformationId(
                        @PathVariable("companyinfoid") @NotNull UUID companyInformationId) {
                return ResponseBody.handle(companyInformationService
                                .getCompanyInformationByCompanyInformationId(companyInformationId));
        }

        @GetMapping("{companyinfoid}/deliveryrecords")
        public ResponseEntity<ResponseBody<List<DeliveryRecord>>> getDeliveryRecordsByCompanyInformationId(
                        @PathVariable("companyinfoid") @NotNull UUID companyInformationId,
                        @RequestParam @NotNull Integer status,
                        @RequestParam Integer workingYears,
                        @RequestParam String sex, @RequestParam Integer age,
                        @RequestParam UUID jobId,
                        @RequestParam Date deliveryDate, @RequestParam String search,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getDeliveryRecordsByCompanyInformationId(companyInformationId, status, workingYears,
                                                sex, age, jobId, deliveryDate, search, pageable));
        }

        @GetMapping("/positioninfos")
        public ResponseEntity<ResponseBody<List<PositionInformation>>> getPositionInfos(
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getPositionInfos(pageable));
        }
}
