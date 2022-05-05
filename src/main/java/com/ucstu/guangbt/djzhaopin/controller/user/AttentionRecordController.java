package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

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
@RequestMapping("/userInfos/{userInfoId}/attentionRecords")
public class AttentionRecordController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    @PreAuthorize("hasPermission('#userInformationId', 'AttentionRecord', 'create')")
    public ResponseEntity<ResponseBody<AttentionRecord>> createAttentionRecord(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @Valid @RequestBody AttentionRecord attentionRecord) {
        return ResponseBody.handle(userInformationService
                .createAttentionRecord(userInformationId, attentionRecord));
    }

    @DeleteMapping("/{attentionRecordId}")
    @PreAuthorize("hasPermission('#userInformationId', 'AttentionRecord', 'delete')")
    public ResponseEntity<ResponseBody<AttentionRecord>> deleteAttentionRecordByAttentionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("attentionRecordId") @NotNull UUID attentionRecordId) {
        return ResponseBody.handle(userInformationService
                .deleteAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId));
    }

    @PutMapping("/{attentionRecordId}")
    @PreAuthorize("hasPermission('#userInformationId', 'AttentionRecord', 'update')")
    public ResponseEntity<ResponseBody<AttentionRecord>> updateAttentionRecordByAttentionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("attentionRecordId") @NotNull UUID attentionRecordId,
            @Valid @RequestBody AttentionRecord attentionRecord) {
        return ResponseBody.handle(userInformationService
                .updateAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId,
                        attentionRecord));
    }

    @GetMapping("")
    @PreAuthorize("hasPermission('#userInformationId', 'AttentionRecords', 'read')")
    public ResponseEntity<ResponseBody<PageResult<AttentionRecord>>> getAttentionRecordsByUserInformationId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PageableDefault(size = 10) Pageable pageable) {

        return ResponseBody.handle(userInformationService
                .getAttentionRecordsByUserInformationId(userInformationId, pageable));
    }

    @GetMapping("/{attentionRecordId}")
    @PreAuthorize("hasPermission('#userInformationId', 'AttentionRecord', 'read')")
    public ResponseEntity<ResponseBody<AttentionRecord>> getAttentionRecordByAttentionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("attentionRecordId") @NotNull UUID attentionRecordId) {
        return ResponseBody.handle(userInformationService
                .getAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId));
    }

}
