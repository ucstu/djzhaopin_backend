package com.ucstu.guangbt.djzhaopin.controller.company;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.CompanyInformation;
import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.model.company.BigData;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasPermission('CompanyInformation', 'create')")
    public ResponseEntity<ResponseBody<CompanyInformation>> createCompanyInformation(
            @Valid @RequestBody CompanyInformation companyInformation) {
        return ResponseBody.handle(companyInformationService
                .createCompanyInformation(companyInformation));
    }

    @DeleteMapping("/{companyInfoId}")
    @PreAuthorize("hasPermission('#companyInformationId', 'CompanyInformation', 'delete')")
    public ResponseEntity<ResponseBody<CompanyInformation>> deleteCompanyInformationByCompanyInfoId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId) {
        return ResponseBody.handle(companyInformationService
                .deleteCompanyInformationByCompanyInfoId(companyInformationId));
    }

    @PutMapping("{companyInfoId}")
    @PreAuthorize("hasPermission('#companyInformationId', 'CompanyInformation', 'update')")
    public ResponseEntity<ResponseBody<CompanyInformation>> updateCompanyInformationByCompanyInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
            @Valid @RequestBody CompanyInformation companyInformation) {
        return ResponseBody.handle(companyInformationService
                .updateCompanyInformationByCompanyInformationId(companyInformationId,
                        companyInformation));
    }

    @GetMapping("")
    @PreAuthorize("hasPermission('CompanyInformations', 'read')")
    public ResponseEntity<ResponseBody<PageResult<CompanyInformation>>> getCompanyInformationsByCompanyName(
            @RequestParam(value = "companyName", required = false) String companyName,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(companyInformationService
                .getCompanyInformationsByCompanyName(companyName, pageable));
    }

    @GetMapping("{companyInfoId}")
    @PreAuthorize("hasPermission('#companyInformationId', 'CompanyInformation', 'read')")
    public ResponseEntity<ResponseBody<CompanyInformation>> getCompanyInformationByCompanyInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId) {
        return ResponseBody.handle(companyInformationService
                .getCompanyInformationByCompanyInformationId(companyInformationId));
    }

    @GetMapping("{companyInfoId}/deliveryRecords")
    @PreAuthorize("hasPermission('#companyInformationId', 'deliveryRecords', 'read')")
    public ResponseEntity<ResponseBody<PageResult<DeliveryRecord>>> getDeliveryRecordsByCompanyInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
            @RequestParam(value = "createdAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdAt,
            @RequestParam(value = "updatedAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date updatedAt,
            @RequestParam("status") @NotNull List<Integer> status,
            @RequestParam(value = "workingYears", required = false) List<Integer> workingYears,
            @RequestParam(value = "sexs", required = false) List<String> sexs,
            @RequestParam(value = "ages", required = false) List<Integer> ages,
            @RequestParam(value = "positionInfoIds", required = false) List<UUID> positionInformationIds,
            @RequestParam(value = "deliveryDates", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") List<Date> deliveryDates,
            @RequestParam(value = "search", required = false) String search,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(companyInformationService
                .getDeliveryRecordsByCompanyInformationId(companyInformationId, createdAt, updatedAt,
                        status,
                        workingYears, sexs, ages, positionInformationIds, deliveryDates, search,
                        pageable));
    }

    @GetMapping("/positionInfos")
    @PreAuthorize("hasPermission('CompanyInformations', 'read')")
    public ResponseEntity<ResponseBody<PageResult<PositionInformation>>> getPositionInfos(
            @RequestParam(value = "positionName", required = false) String positionName,
            @RequestParam(value = "salary", required = false) String salary,
            @RequestParam(value = "workingYears", required = false) List<Integer> workingYears,
            @RequestParam(value = "educations", required = false) List<Integer> educations,
            @RequestParam(value = "directionTags", required = false) List<String> directionTags,
            @RequestParam(value = "workAreas", required = false) List<String> workAreas,
            @RequestParam(value = "positionTypes", required = false) List<Integer> positionTypes,
            @RequestParam(value = "scales", required = false) List<Integer> scales,
            @RequestParam(value = "financingStages", required = false) List<Integer> financingStages,
            @RequestParam(value = "comprehensions", required = false) List<String> comprehensions,
            @RequestParam(value = "workingPlace", required = false) String workingPlace,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(companyInformationService
                .getPositionInfos(positionName, salary, workingYears, educations, directionTags,
                        workAreas,
                        positionTypes, scales, financingStages, comprehensions, workingPlace,
                        pageable));
    }

    @GetMapping("{companyInfoId}/bigData")
    @PreAuthorize("hasPermission('#companyInformationId', 'bigData', 'read')")
    public ResponseEntity<ResponseBody<List<BigData>>> getBigDataByCompanyInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
            @RequestParam("hrInformationId") @NotNull UUID hrInformationId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(companyInformationService
                .getBigDataByCompanyInformationId(companyInformationId, hrInformationId, startDate, endDate, pageable));
    }

    @GetMapping("{companyInfoId}/sawMeRecords")
    @PreAuthorize("hasPermission('#companyInformationId', 'sawMeRecords', 'read')")
    public ResponseEntity<ResponseBody<PageResult<UserInspectionRecord>>> getSawMeRecordsByCompanyInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(companyInformationService
                .getSawMeRecordsByCompanyInformationId(companyInformationId, startDate, endDate, pageable));
    }

}
