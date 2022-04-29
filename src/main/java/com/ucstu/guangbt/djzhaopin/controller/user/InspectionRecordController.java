package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.InspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

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
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/userInfos/{userInfoId}/inspectionRecords")
public class InspectionRecordController {

        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<InspectionRecord>> createInspectionRecord(
                        @PathVariable("userInfoId") @NotNull UUID userInformationId,
                        @Valid @RequestBody InspectionRecord inspectionRecord) {
                return ResponseBody.handle(userInformationService
                                .createInspectionRecord(userInformationId, inspectionRecord));
        }

        @DeleteMapping("/{inspectionRecordId}")
        public ResponseEntity<ResponseBody<InspectionRecord>> deleteInspectionRecordByInspectionRecordId(
                        @PathVariable("userInfoId") @NotNull UUID userInformationId,
                        @PathVariable("inspectionRecordId") @NotNull UUID inspectionRecordId) {
                return ResponseBody.handle(userInformationService
                                .deleteInspectionRecordByInspectionRecordId(userInformationId, inspectionRecordId));
        }

        @PutMapping("/{inspectionRecordId}")
        public ResponseEntity<ResponseBody<InspectionRecord>> updateInspectionRecordByInspectionRecordId(
                        @PathVariable("userInfoId") @NotNull UUID userInformationId,
                        @PathVariable("inspectionRecordId") @NotNull UUID inspectionRecordId,
                        @Valid @RequestBody InspectionRecord inspectionRecord) {
                return ResponseBody.handle(userInformationService
                                .updateInspectionRecordByInspectionRecordId(userInformationId, inspectionRecordId,
                                                inspectionRecord));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<InspectionRecord>>> getInspectionRecordsByUserInformationId(
                        @PathVariable("userInfoId") @NotNull UUID userInformationId,
                        @PageableDefault(page = 5, size = 10) Pageable pageable) {
                return ResponseBody.handle(userInformationService
                                .getInspectionRecordsByUserInformationId(userInformationId, pageable));
        }

        @GetMapping("/{inspectionRecordId}")
        public ResponseEntity<ResponseBody<InspectionRecord>> getInspectionRecordByInspectionRecordId(
                        @PathVariable("userInfoId") @NotNull UUID userInformationId,
                        @PathVariable("inspectionRecordId") @NotNull UUID inspectionRecordId) {
                return ResponseBody.handle(userInformationService
                                .getInspectionRecordByInspectionRecordId(userInformationId, inspectionRecordId));
        }
}
