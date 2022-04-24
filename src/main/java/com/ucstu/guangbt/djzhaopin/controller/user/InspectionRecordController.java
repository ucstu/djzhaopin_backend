package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.InspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RestController
@RequestMapping("/userinfos/{userinfoid}/inspectionrecords")
public class InspectionRecordController {

        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<InspectionRecord>> createInspectionRecord(
                        @PathVariable UUID userinfoid,
                        @Valid @RequestBody InspectionRecord inspectionRecord) {
                return ResponseBody
                                .success(userInformationService.createInspectionRecord(userinfoid, inspectionRecord));
        }

        @DeleteMapping("/{inspectionrecordid}")
        public ResponseEntity<ResponseBody<InspectionRecord>> deleteInspectionRecordByInspectionRecordId(
                        @PathVariable UUID userinfoid,
                        @PathVariable UUID inspectionrecordid) {
                return ResponseBody
                                .success(userInformationService.deleteInspectionRecordByInspectionRecordId(userinfoid,
                                                inspectionrecordid));
        }

        @PutMapping("/{inspectionrecordid}")
        public ResponseEntity<ResponseBody<InspectionRecord>> updateInspectionRecordByInspectionRecordId(
                        @PathVariable UUID userinfoid,
                        @PathVariable UUID inspectionrecordid,
                        @Valid @RequestBody InspectionRecord inspectionRecord) {
                return ResponseBody
                                .success(userInformationService.updateInspectionRecordByInspectionRecordId(userinfoid,
                                                inspectionrecordid,
                                                inspectionRecord));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<InspectionRecord>>> getInspectionRecords(
                        @PathVariable UUID userinfoid) {
                return ResponseBody.success(userInformationService.getInspectionRecords(userinfoid));
        }

        @GetMapping("/{inspectionrecordid}")
        public ResponseEntity<ResponseBody<InspectionRecord>> getInspectionRecordByInspectionRecordId(
                        @PathVariable UUID userinfoid,
                        @PathVariable UUID inspectionrecordid) {
                return ResponseBody
                                .success(
                                                userInformationService.getInspectionRecordByInspectionRecordId(
                                                                userinfoid, inspectionrecordid));
        }
}
