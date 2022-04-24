package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
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
@RequestMapping("/userinfos/{userinfoid}/attentionrecords")
public class AttentionRecordController {
        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<AttentionRecord>> createAttentionRecord(
                        @PathVariable UUID userinfoid,
                        @Valid @RequestBody AttentionRecord attentionRecord) {
                return ResponseBody.success(userInformationService.createAttentionRecord(userinfoid, attentionRecord));
        }

        @DeleteMapping("/{attentionrecordid}")
        public ResponseEntity<ResponseBody<AttentionRecord>> deleteAttentionRecordByAttentionRecordId(
                        @PathVariable UUID userinfoid,
                        @PathVariable UUID attentionrecordid) {
                return ResponseBody
                                .success(userInformationService.deleteAttentionRecordByAttentionRecordId(userinfoid,
                                                attentionrecordid));
        }

        @PutMapping("/{attentionrecordid}")
        public ResponseEntity<ResponseBody<AttentionRecord>> updateAttentionRecordByAttentionRecordId(
                        @PathVariable UUID userinfoid,
                        @PathVariable UUID attentionrecordid,
                        @Valid @RequestBody AttentionRecord attentionRecord) {
                return ResponseBody
                                .success(userInformationService.updateAttentionRecordByAttentionRecordId(userinfoid,
                                                attentionrecordid, attentionRecord));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<AttentionRecord>>> getAttentionRecords(
                        @PathVariable UUID userinfoid) {
                return ResponseBody.success(userInformationService.getAttentionRecords(userinfoid));
        }

        @GetMapping("/{attentionrecordid}")
        public ResponseEntity<ResponseBody<AttentionRecord>> getAttentionRecordByAttentionRecordId(
                        @PathVariable UUID userinfoid,
                        @PathVariable UUID attentionrecordid) {
                return ResponseBody
                                .success(userInformationService.getAttentionRecordByAttentionRecordId(userinfoid,
                                                attentionrecordid));
        }
}
