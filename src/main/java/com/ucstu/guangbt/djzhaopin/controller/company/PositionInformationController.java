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
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/companyinfos/{companyinfoid}/positioninfos")
public class PositionInformationController {

        @Resource
        private CompanyInformationService companyInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<PositionInformation>> createPositionInformation(
                        @PathVariable("companyinfoid") UUID companyInformationId,
                        @Valid @RequestBody PositionInformation positionInformation) {
                return ResponseBody.handle(companyInformationService
                                .createPositionInformation(companyInformationId, positionInformation));
        }

        @DeleteMapping("/{positioninfoid}")
        public ResponseEntity<ResponseBody<PositionInformation>> deletePositionInformationByPositionInformationId(
                        @PathVariable("companyinfoid") UUID companyInformationId,
                        @PathVariable("positioninfoid") UUID positionInformationId) {
                return ResponseBody.handle(companyInformationService
                                .deletePositionInformationByPositionInformationId(companyInformationId,
                                                positionInformationId));
        }

        @PostMapping("/{positioninfoid}")
        public ResponseEntity<ResponseBody<PositionInformation>> updatePositionInformationByPositionInformationId(
                        @PathVariable("companyinfoid") UUID companyInformationId,
                        @PathVariable("positioninfoid") UUID positionInformationId,
                        @Valid @RequestBody PositionInformation positionInformation) {
                return ResponseBody.handle(companyInformationService
                                .updatePositionInformationByPositionInformationId(companyInformationId,
                                                positionInformationId,
                                                positionInformation));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<PositionInformation>>> getPositionInformationsByCompanyInformationId(
                        @PathVariable("companyinfoid") UUID companyInformationId,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(companyInformationService
                                .getPositionInformationsByCompanyInformationId(companyInformationId, pageable));
        }

        @GetMapping("/{positioninfoid}")
        public ResponseEntity<ResponseBody<PositionInformation>> getPositionInformationByPositionInformationId(
                        @PathVariable("companyinfoid") UUID companyInformationId,
                        @PathVariable("positioninfoid") UUID positionInformationId) {
                return ResponseBody.handle(companyInformationService
                                .getPositionInformationByPositionInformationId(companyInformationId,
                                                positionInformationId));
        }

}
