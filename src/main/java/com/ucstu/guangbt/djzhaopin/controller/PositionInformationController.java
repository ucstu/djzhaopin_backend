package com.ucstu.guangbt.djzhaopin.controller;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.model.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/companyinfos/{companyinfoid}/positioninfos")
public class PositionInformationController {

        @Autowired
        private PositionInformationService positionInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<PositionInformation>> createPositionInformation(
                        @PathVariable UUID companyinfoid,
                        @Valid PositionInformation positionInformation) {
                return ResponseBody
                                .success(positionInformationService.createPositionInformation(companyinfoid,
                                                positionInformation));
        }

        @DeleteMapping("/{positioninfoid}")
        public ResponseEntity<ResponseBody<PositionInformation>> deletePositionInformationByPositionInfoId(
                        @PathVariable UUID companyinfoid,
                        @PathVariable UUID positioninfoid) {
                return ResponseBody
                                .success(positionInformationService.deletePositionInformation(positioninfoid));
        }

        @PostMapping("/{positioninfoid}")
        public ResponseEntity<ResponseBody<PositionInformation>> updatePositionInformationByPositionInfoId(
                        @PathVariable UUID companyinfoid,
                        @PathVariable UUID positioninfoid,
                        @Valid PositionInformation positionInformation) {
                return ResponseBody
                                .success(positionInformationService.updatePositionInformation(positioninfoid,
                                                positionInformation));
        }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<PositionInformation>> getPositionInformations() {
        return ResponseBody
                .success(positionInformationService.getPositionInformations());
    }

        @GetMapping("/{positioninfoid}")
        public ResponseEntity<ResponseBody<PositionInformation>> getPositionInformationByPositionInfoId(
                        @PathVariable UUID positioninfoid) {
                return ResponseBody
                                .success(positionInformationService
                                                .getPositionInformationByPositionInfoId(positioninfoid));
        }

}
