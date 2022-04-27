package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
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
@RequestMapping("/userinfos/{userinfoid}/attentionrecords")
public class AttentionRecordController {
    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<AttentionRecord>> createAttentionRecord(
            @PathVariable("userinfoid") UUID userInformationId,
            @Valid @RequestBody AttentionRecord attentionRecord) {
        return ResponseBody.handle(userInformationService
                .createAttentionRecord(userInformationId, attentionRecord));
    }

    @DeleteMapping("/{attentionrecordid}")
    public ResponseEntity<ResponseBody<AttentionRecord>> deleteAttentionRecordByAttentionRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("attentionrecordid") UUID attentionRecordId) {
        return ResponseBody.handle(userInformationService
                .deleteAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId));
    }

    @PutMapping("/{attentionrecordid}")
    public ResponseEntity<ResponseBody<AttentionRecord>> updateAttentionRecordByAttentionRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("attentionrecordid") UUID attentionRecordId,
            @Valid @RequestBody AttentionRecord attentionRecord) {
        return ResponseBody.handle(userInformationService
                .updateAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId,
                        attentionRecord));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<AttentionRecord>>> getAttentionRecordsByUserInformationId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        return ResponseBody.handle(userInformationService
                .getAttentionRecordsByUserInformationId(userInformationId));
    }

    @GetMapping("/{attentionrecordid}")
    public ResponseEntity<ResponseBody<AttentionRecord>> getAttentionRecordByAttentionRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("attentionrecordid") UUID attentionRecordId) {
        return ResponseBody.handle(userInformationService
                .getAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId));
    }
}
