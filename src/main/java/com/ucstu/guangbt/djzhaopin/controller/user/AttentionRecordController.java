package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ucstu.guangbt.djzhaopin.entity.user.AttentionRecord;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
                        @PathVariable("userinfoid") UUID userInformationId,
                        @Valid @RequestBody AttentionRecord attentionRecord) {
                Optional<AttentionRecord> attentionRecordOptional = userInformationService
                                .createAttentionRecord(userInformationId, attentionRecord);
                if (attentionRecordOptional.isPresent()) {
                        return ResponseBody.success(attentionRecordOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @DeleteMapping("/{attentionrecordid}")
        public ResponseEntity<ResponseBody<AttentionRecord>> deleteAttentionRecordByAttentionRecordId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("attentionrecordid") UUID attentionRecordId) {
                Optional<AttentionRecord> attentionRecordOptional = userInformationService
                                .deleteAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId);
                if (attentionRecordOptional.isPresent()) {
                        return ResponseBody.success(attentionRecordOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @PutMapping("/{attentionrecordid}")
        public ResponseEntity<ResponseBody<AttentionRecord>> updateAttentionRecordByAttentionRecordId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("attentionrecordid") UUID attentionRecordId,
                        @Valid @RequestBody AttentionRecord attentionRecord) {
                Optional<AttentionRecord> attentionRecordOptional = userInformationService
                                .updateAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId,
                                                attentionRecord);
                if (attentionRecordOptional.isPresent()) {
                        return ResponseBody.success(attentionRecordOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<AttentionRecord>>> getAttentionRecordsByUserInformationId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PageableDefault(page = 0, size = 10) Pageable pageable) {

                Stream<AttentionRecord> attentionRecords = userInformationService
                                .getAttentionRecordsByUserInformationId(userInformationId);
                if (attentionRecords.count() > 0) {
                        return ResponseBody.success(attentionRecords.collect(Collectors.toList()));
                }
                return ResponseBody.notFound().build();
        }

        @GetMapping("/{attentionrecordid}")
        public ResponseEntity<ResponseBody<AttentionRecord>> getAttentionRecordByAttentionRecordId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("attentionrecordid") UUID attentionRecordId) {
                Optional<AttentionRecord> attentionRecordOptional = userInformationService
                                .getAttentionRecordByAttentionRecordId(userInformationId, attentionRecordId);
                if (attentionRecordOptional.isPresent()) {
                        return ResponseBody.success(attentionRecordOptional.get());
                }
                return ResponseBody.notFound().build();
        }
}
