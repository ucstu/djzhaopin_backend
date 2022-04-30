package com.ucstu.guangbt.djzhaopin.controller.company;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

        @PostMapping("/{positionInfoId}")
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
        public ResponseEntity<ResponseBody<List<PositionInformation>>> getPositionInformationsByCompanyInformationId(
                        @PathVariable("companyInfoId") @NotNull UUID companyInformationId,
                        @RequestParam(value = "name", required = false) String name,
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
                                .getPositionInformationsByCompanyInformationId(companyInformationId, name, salary,
                                                workingYears, educations, directionTags, workAreas, positionTypes,
                                                scales, financingStages, comprehensions, workingPlace, pageable));
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
