package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInspectionRecord;
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
public class UserInspectionRecordController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<UserInspectionRecord>> createUserInspectionRecord(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @Valid @RequestBody UserInspectionRecord userInspectionRecord) {
        return ResponseBody.handle(userInformationService
                .createUserInspectionRecord(userInformationId, userInspectionRecord));
    }

    @DeleteMapping("/{inspectionRecordId}")
    public ResponseEntity<ResponseBody<UserInspectionRecord>> deleteUserInspectionRecordByUserInspectionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("inspectionRecordId") @NotNull UUID userInspectionRecordId) {
        return ResponseBody.handle(userInformationService
                .deleteUserInspectionRecordByUserInspectionRecordId(userInformationId,
                        userInspectionRecordId));
    }

    @PutMapping("/{inspectionRecordId}")
    public ResponseEntity<ResponseBody<UserInspectionRecord>> updateUserInspectionRecordByUserInspectionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("inspectionRecordId") @NotNull UUID userInspectionRecordId,
            @Valid @RequestBody UserInspectionRecord userInspectionRecord) {
        return ResponseBody.handle(userInformationService
                .updateUserInspectionRecordByUserInspectionRecordId(userInformationId,
                        userInspectionRecordId,
                        userInspectionRecord));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<UserInspectionRecord>>> getUserInspectionRecordsByUserInformationId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(userInformationService
                .getUserInspectionRecordsByUserInformationId(userInformationId, pageable));
    }

    @GetMapping("/{inspectionRecordId}")
    public ResponseEntity<ResponseBody<UserInspectionRecord>> getUserInspectionRecordByUserInspectionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("inspectionRecordId") @NotNull UUID userInspectionRecordId) {
        return ResponseBody.handle(userInformationService
                .getUserInspectionRecordByUserInspectionRecordId(userInformationId,
                        userInspectionRecordId));
    }

}
