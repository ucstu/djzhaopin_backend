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
import org.springframework.format.annotation.DateTimeFormat;
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
@RequestMapping("/companyInfos")
public class CompanyInformationController {

        @Resource
        private CompanyInformationService companyInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<CompanyInformation>> createCompanyInformation(
                        @Valid @RequestBody CompanyInformation companyInformation) {
                return ResponseBody.handle(companyInformationService
                                .createCompanyInformation(companyInformation));
        }

        @PostMapping("{companyInfoId}")
        public ResponseEntity<ResponseBody<CompanyInformation>> updateCompanyInformationByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
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

        @GetMapping("{companyInfoId}")
        public ResponseEntity<ResponseBody<CompanyInformation>> getCompanyInformationByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId) {
                return ResponseBody.handle(companyInformationService
                                .getCompanyInformationByCompanyInformationId(companyInformationId));
        }

        @GetMapping("{companyInfoId}/deliveryRecords")
        public ResponseEntity<ResponseBody<List<DeliveryRecord>>> getDeliveryRecordsByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
                        @RequestParam("status") @NotNull List<Integer> status,
                        @RequestParam("workingYears") List<Integer> workingYears,
                        @RequestParam("sexs") List<String> sexs,
                        @RequestParam("ages") List<Integer> ages,
                        @RequestParam("positionInfoIds") List<UUID> positionInformationIds,
                        @RequestParam("deliveryDates") @DateTimeFormat(pattern = "yyyy-MM-dd") List<Date> deliveryDates,
                        @RequestParam("search") String search,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getDeliveryRecordsByCompanyInformationId(companyInformationId, status, workingYears,
                                                sexs, ages, positionInformationIds, deliveryDates, search, pageable));
        }

        @GetMapping("/positionInfos")
        public ResponseEntity<ResponseBody<List<PositionInformation>>> getPositionInfos(
                        @RequestParam("name") String name,
                        @RequestParam("salary") String salary,
                        @RequestParam("workingYears") List<Integer> workingYears,
                        @RequestParam("educations") List<Integer> educations,
                        @RequestParam("directionTags") List<String> directionTags,
                        @RequestParam("workAreas") List<String> workAreas,
                        @RequestParam("positionTypes") List<Integer> positionTypes,
                        @RequestParam("scales") List<Integer> scales,
                        @RequestParam("financingStages") List<Integer> financingStages,
                        @RequestParam("comprehensions") List<String> comprehensions,
                        @RequestParam("workingPlace") String workingPlace,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getPositionInfos(name, salary, workingYears, educations, directionTags, workAreas,
                                                positionTypes, scales, financingStages, comprehensions, workingPlace,
                                                pageable));
        }
}
