package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.Optional;
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

@Validated
@CrossOrigin
@RestController
@RequestMapping("/userinfos/{userinfoid}/inspectionrecords")
public class InspectionRecordController {

        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<InspectionRecord>> createInspectionRecord(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @Valid @RequestBody InspectionRecord inspectionRecord) {
                Optional<InspectionRecord> inspectionRecordOptional = userInformationService
                                .createInspectionRecord(userInformationId, inspectionRecord);
                if (inspectionRecordOptional.isPresent()) {
                        return ResponseBody.created(inspectionRecordOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @DeleteMapping("/{inspectionrecordid}")
        public ResponseEntity<ResponseBody<InspectionRecord>> deleteInspectionRecordByInspectionRecordId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("inspectionrecordid") UUID inspectionRecordId) {
                Optional<InspectionRecord> inspectionRecordOptional = userInformationService
                                .deleteInspectionRecordByInspectionRecordId(userInformationId, inspectionRecordId);
                if (inspectionRecordOptional.isPresent()) {
                        return ResponseBody.success(inspectionRecordOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @PutMapping("/{inspectionrecordid}")
        public ResponseEntity<ResponseBody<InspectionRecord>> updateInspectionRecordByInspectionRecordId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("inspectionrecordid") UUID inspectionRecordId,
                        @Valid @RequestBody InspectionRecord inspectionRecord) {
                Optional<InspectionRecord> inspectionRecordOptional = userInformationService
                                .updateInspectionRecordByInspectionRecordId(userInformationId, inspectionRecordId,
                                                inspectionRecord);
                if (inspectionRecordOptional.isPresent()) {
                        return ResponseBody.success(inspectionRecordOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<InspectionRecord>>> getInspectionRecordsByUserInformationId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PageableDefault(page = 5, size = 10) Pageable pageable) {
                Optional<List<InspectionRecord>> inspectionRecordsOptional = userInformationService
                                .getInspectionRecordsByUserInformationId(userInformationId, pageable);
                if (inspectionRecordsOptional.isPresent()) {
                        return ResponseBody.success(inspectionRecordsOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @GetMapping("/{inspectionrecordid}")
        public ResponseEntity<ResponseBody<InspectionRecord>> getInspectionRecordByInspectionRecordId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("inspectionrecordid") UUID inspectionRecordId) {
                Optional<InspectionRecord> inspectionRecordOptional = userInformationService
                                .getInspectionRecordByInspectionRecordId(userInformationId, inspectionRecordId);
                if (inspectionRecordOptional.isPresent()) {
                        return ResponseBody.success(inspectionRecordOptional.get());
                }
                return ResponseBody.notFound().build();
        }
}
