package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/userInfos/{userInfoId}/attentionRecords")
public class AttentionRecordController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<AttentionRecord>> createAttentionRecord(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @Valid @RequestBody AttentionRecord attentionRecord) {
        return ResponseBody.handle(userInformationService
                .createAttentionRecord(userInformationId, attentionRecord));
    }

    @DeleteMapping("/{attentionRecordId}")
    public ResponseEntity<ResponseBody<AttentionRecord>> deleteAttentionRecordByAttentionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("attentionRecordId") @NotNull UUID attentionRecordId) {
        return ResponseBody.handle(userInformationService
                .deleteAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId));
    }

    @PutMapping("/{attentionRecordId}")
    public ResponseEntity<ResponseBody<AttentionRecord>> updateAttentionRecordByAttentionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("attentionRecordId") @NotNull UUID attentionRecordId,
            @Valid @RequestBody AttentionRecord attentionRecord) {
        return ResponseBody.handle(userInformationService
                .updateAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId,
                        attentionRecord));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<PageResult<AttentionRecord>>> getAttentionRecordsByUserInformationId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PageableDefault(size = 10) Pageable pageable) {

        return ResponseBody.handle(userInformationService
                .getAttentionRecordsByUserInformationId(userInformationId, pageable));
    }

    @GetMapping("/{attentionRecordId}")
    public ResponseEntity<ResponseBody<AttentionRecord>> getAttentionRecordByAttentionRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("attentionRecordId") @NotNull UUID attentionRecordId) {
        return ResponseBody.handle(userInformationService
                .getAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId));
    }

}
