package com.ucstu.guangbt.djzhaopin.controller.company;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.model.company.BigData;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

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

        @DeleteMapping("/{companyInfoId}")
        public ResponseEntity<ResponseBody<CompanyInformation>> deleteCompanyInformationByCompanyInfoId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId) {
                return ResponseBody.handle(companyInformationService
                                .deleteCompanyInformationByCompanyInfoId(companyInformationId));
        }

        @PutMapping("{companyInfoId}")
        public ResponseEntity<ResponseBody<CompanyInformation>> updateCompanyInformationByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
                        @Valid @RequestBody CompanyInformation companyInformation) {
                return ResponseBody.handle(companyInformationService
                                .updateCompanyInformationByCompanyInformationId(companyInformationId,
                                                companyInformation));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<PageResult<CompanyInformation>>> getCompanyInformations(
                        @RequestParam(value = "companyName", required = false) String companyName,
                        @RequestParam(value = "scales", required = false) List<@Valid @Range(min = 1, max = 6) Integer> scales,
                        @RequestParam(value = "financingStages", required = false) List<@Valid @Range(min = 1, max = 8) Integer> financingStages,
                        @RequestParam(value = "comprehensions", required = false) List<String> comprehensions,
                        @RequestParam(value = "location", required = false) String location,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getCompanyInformations(companyName, scales, financingStages, comprehensions, location,
                                                pageable));
        }

        @GetMapping("{companyInfoId}")
        public ResponseEntity<ResponseBody<CompanyInformation>> getCompanyInformationByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId) {
                return ResponseBody.handle(companyInformationService
                                .getCompanyInformationByCompanyInformationId(companyInformationId));
        }

        @GetMapping("{companyInfoId}/deliveryRecords")
        public ResponseEntity<ResponseBody<PageResult<DeliveryRecord>>> getDeliveryRecordsByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
                        @RequestParam(value = "createdAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt,
                        @RequestParam(value = "updatedAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date updatedAt,
                        @RequestParam("status") @NotEmpty List<@Valid @Range(min = 1, max = 5) Integer> status,
                        @RequestParam(value = "interviewTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date interviewTime,
                        @RequestParam(value = "workingYears", required = false) List<@Valid @Range(min = 1, max = 6) Integer> workingYears,
                        @RequestParam(value = "sexs", required = false) List<String> sexs,
                        @RequestParam(value = "ages", required = false) List<Integer> ages,
                        @RequestParam(value = "positionInfoIds", required = false) List<UUID> positionInformationIds,
                        @RequestParam(value = "deliveryDates", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") List<Date> deliveryDates,
                        @RequestParam(value = "userName", required = false) String userName,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getDeliveryRecordsByCompanyInformationId(companyInformationId, createdAt, updatedAt,
                                                status, interviewTime, workingYears, sexs, ages, positionInformationIds,
                                                deliveryDates, userName, pageable));
        }

        @GetMapping("/positionInfos")
        public ResponseEntity<ResponseBody<PageResult<PositionInformation>>> getPositionInfos(
                        @RequestParam(value = "positionName", required = false) String positionName,
                        @RequestParam(value = "positionType", required = false) String positionType,
                        @RequestParam(value = "salary", required = false) String salary,
                        @RequestParam(value = "workingYears", required = false) List<@Valid @Range(min = 1, max = 6) Integer> workingYears,
                        @RequestParam(value = "educations", required = false) List<@Valid @Range(min = 1, max = 5) Integer> educations,
                        @RequestParam(value = "directionTags", required = false) List<String> directionTags,
                        @RequestParam(value = "workProvinceName", required = false) String workProvinceName,
                        @RequestParam(value = "workCityName", required = false) String workCityName,
                        @RequestParam(value = "workAreaNames", required = false) List<String> workAreaNames,
                        @RequestParam(value = "workTypes", required = false) List<@Valid @Range(min = 1, max = 3) Integer> workTypes,
                        @RequestParam(value = "scales", required = false) List<@Valid @Range(min = 1, max = 6) Integer> scales,
                        @RequestParam(value = "financingStages", required = false) List<@Valid @Range(min = 1, max = 8) Integer> financingStages,
                        @RequestParam(value = "comprehensions", required = false) List<String> comprehensions,
                        @RequestParam(value = "workingPlace", required = false) String workingPlace,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getPositionInfos(positionName, positionType, salary, workingYears, educations,
                                                directionTags,
                                                workProvinceName, workCityName, workAreaNames, workTypes, scales,
                                                financingStages,
                                                comprehensions, workingPlace, pageable));
        }

        @GetMapping("{companyInfoId}/bigData")
        public ResponseEntity<ResponseBody<List<BigData>>> getBigDataByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
                        @RequestParam("hrInformationId") @NotNull UUID hrInformationId,
                        @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                        @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getBigDataByCompanyInformationId(companyInformationId, hrInformationId, startDate,
                                                endDate, pageable));
        }

        @GetMapping("{companyInfoId}/sawMeRecords")
        public ResponseEntity<ResponseBody<PageResult<UserInspectionRecord>>> getSawMeRecordsByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
                        @RequestParam(value = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                        @RequestParam(value = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getSawMeRecordsByCompanyInformationId(companyInformationId, startDate, endDate,
                                                pageable));
        }

}
