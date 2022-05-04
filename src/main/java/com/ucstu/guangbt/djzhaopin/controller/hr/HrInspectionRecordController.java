package com.ucstu.guangbt.djzhaopin.controller.hr;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInspectionRecord;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.HrInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/hrInfos/{hrInfoId}/inspectionRecords")
public class HrInspectionRecordController {

    @Resource
    private HrInformationService hrInformationService;

    @PostMapping("")
    @PreAuthorize("hasPermission(#hrInformationId, 'HrInspectionRecord', 'create')")
    public ResponseEntity<ResponseBody<HrInspectionRecord>> createHrInspectionRecord(
            @PathVariable("hrInfoId") @NotNull UUID hrInformationId,
            @Valid @RequestBody HrInspectionRecord hrInspectionRecord) {
        return ResponseBody.handle(hrInformationService.createHrInspectionRecord(hrInformationId, hrInspectionRecord));
    }

    @DeleteMapping("/{inspectionRecordId}")
    @PreAuthorize("hasPermission(#hrInformationId, 'HrInspectionRecord', 'delete')")
    public ResponseEntity<ResponseBody<HrInspectionRecord>> deleteHrInspectionRecordByInspectionRecordId(
            @PathVariable("hrInfoId") @NotNull UUID hrInformationId,
            @PathVariable("inspectionRecordId") @NotNull UUID inspectionRecordId) {
        return ResponseBody
                .handle(hrInformationService.deleteHrInspectionRecordByInspectionRecordId(inspectionRecordId));
    }

    @PutMapping("/{inspectionRecordId}")
    @PreAuthorize("hasPermission(#hrInformationId, 'HrInspectionRecord', 'update')")
    public ResponseEntity<ResponseBody<HrInspectionRecord>> updateHrInspectionRecordByInspectionRecordId(
            @PathVariable("hrInfoId") @NotNull UUID hrInformationId,
            @PathVariable("inspectionRecordId") @NotNull UUID inspectionRecordId,
            @Valid @RequestBody HrInspectionRecord hrInspectionRecord) {
        return ResponseBody
                .handle(hrInformationService.updateHrInspectionRecordByInspectionRecordId(inspectionRecordId,
                        hrInspectionRecord));
    }

    @GetMapping("")
    @PreAuthorize("hasPermission(#hrInformationId, 'HrInspectionRecords', 'read')")
    public ResponseEntity<ResponseBody<List<HrInspectionRecord>>> getHrInspectionRecordByHrInformationId(
            @PathVariable("hrInfoId") @NotNull UUID hrInformationId, @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody
                .handle(hrInformationService.getHrInspectionRecordByHrInformationId(hrInformationId, pageable));
    }

    @GetMapping("/{inspectionRecordId}")
    @PreAuthorize("hasPermission(#hrInformationId, 'HrInspectionRecord', 'read')")
    public ResponseEntity<ResponseBody<HrInspectionRecord>> getHrInspectionRecordByInspectionRecordId(
            @PathVariable("hrInfoId") @NotNull UUID hrInformationId,
            @PathVariable("inspectionRecordId") @NotNull UUID inspectionRecordId) {
        return ResponseBody
                .handle(hrInformationService.getHrInspectionRecordByInspectionRecordId(inspectionRecordId));
    }

}
