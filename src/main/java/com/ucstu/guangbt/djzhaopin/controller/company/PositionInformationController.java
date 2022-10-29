package com.ucstu.guangbt.djzhaopin.controller.company;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/companyInfos/{companyInfoId}/positionInfos")
public class PositionInformationController {

    @Resource
    private CompanyInformationService companyInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<PositionInformation>> createPositionInformation(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
            @Valid @RequestBody PositionInformation positionInformation) {
        return ResponseBody.handle(companyInformationService
                .createPositionInformation(companyInformationId, positionInformation));
    }

    @DeleteMapping("/{positionInfoId}")
    public ResponseEntity<ResponseBody<PositionInformation>> deletePositionInformationByPositionInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
            @PathVariable("positionInfoId") @NotNull UUID positionInformationId) {
        return ResponseBody.handle(companyInformationService
                .deletePositionInformationByPositionInformationId(companyInformationId,
                        positionInformationId));
    }

    @PutMapping("/{positionInfoId}")
    public ResponseEntity<ResponseBody<PositionInformation>> updatePositionInformationByPositionInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
            @PathVariable("positionInfoId") @NotNull UUID positionInformationId,
            @Valid @RequestBody PositionInformation positionInformation) {
        return ResponseBody.handle(companyInformationService
                .updatePositionInformationByPositionInformationId(companyInformationId,
                        positionInformationId,
                        positionInformation));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<PageResult<PositionInformation>>> getPositionInformationsByCompanyInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
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
                .getPositionInformationsByCompanyInformationId(companyInformationId, positionName, positionType,
                        salary, workingYears, educations, directionTags, workProvinceName,
                        workCityName, workAreaNames, workTypes, scales, financingStages,
                        comprehensions, workingPlace, pageable));
    }

    @GetMapping("/{positionInfoId}")
    public ResponseEntity<ResponseBody<PositionInformation>> getPositionInformationByPositionInformationId(
            @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
            @PathVariable("positionInfoId") @NotNull UUID positionInformationId) {
        return ResponseBody.handle(companyInformationService
                .getPositionInformationByPositionInformationId(companyInformationId,
                        positionInformationId));
    }

}
