package com.ucstu.guangbt.djzhaopin.controller.company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.company.position.PositionInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.CompanyInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RestController
@RequestMapping("/companyinfos/{companyinfoid}/positioninfos")
public class PositionInformationController {

    @Resource
    private CompanyInformationService companyInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<PositionInformation>> createPositionInformation(
            @PathVariable("companyinfoid") UUID companyInformationId,
            @Valid @RequestBody PositionInformation positionInformation) {
        Optional<PositionInformation> positionInformationOptional = companyInformationService
                .createPositionInformation(companyInformationId, positionInformation);
        if (positionInformationOptional.isPresent()) {
            return ResponseBody.success(positionInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @DeleteMapping("/{positioninfoid}")
    public ResponseEntity<ResponseBody<PositionInformation>> deletePositionInformationByPositionInformationId(
            @PathVariable("companyinfoid") UUID companyInformationId,
            @PathVariable("positioninfoid") UUID positionInformationId) {
        Optional<PositionInformation> positionInformationOptional = companyInformationService
                .deletePositionInformationByPositionInformationId(companyInformationId,
                        positionInformationId);
        if (positionInformationOptional.isPresent()) {
            return ResponseBody.success(positionInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @PostMapping("/{positioninfoid}")
    public ResponseEntity<ResponseBody<PositionInformation>> updatePositionInformationByPositionInformationId(
            @PathVariable("companyinfoid") UUID companyInformationId,
            @PathVariable("positioninfoid") UUID positionInformationId,
            @Valid @RequestBody PositionInformation positionInformation) {
        Optional<PositionInformation> positionInformationOptional = companyInformationService
                .updatePositionInformationByPositionInformationId(companyInformationId,
                        positionInformationId,
                        positionInformation);
        if (positionInformationOptional.isPresent()) {
            return ResponseBody.success(positionInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<PositionInformation>>> getPositionInformationsByCompanyInformationId(
            @PathVariable("companyinfoid") UUID companyInformationId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Optional<List<PositionInformation>> positionInformationsOptional = companyInformationService
                .getPositionInformationsByCompanyInformationId(companyInformationId, pageable);
        if (positionInformationsOptional.isPresent()) {
            return ResponseBody.success(positionInformationsOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("/{positioninfoid}")
    public ResponseEntity<ResponseBody<PositionInformation>> getPositionInformationByPositionInfoId(
            @PathVariable("companyinfoid") UUID companyInformationId,
            @PathVariable("positioninfoid") UUID positionInformationId) {
        Optional<PositionInformation> positionInformationOptional = companyInformationService
                .getPositionInformationByPositionInfoId(companyInformationId, positionInformationId);
        if (positionInformationOptional.isPresent()) {
            return ResponseBody.success(positionInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }

}
