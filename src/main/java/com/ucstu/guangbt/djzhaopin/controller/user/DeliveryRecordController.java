package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
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
@RequestMapping("/userinfos/{userinfoid}/deliveryrecords")
public class DeliveryRecordController {

        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<DeliveryRecord>> createDeliveryRecord(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @Valid @RequestBody DeliveryRecord deliveryRecord) {
                return ResponseBody.handle(userInformationService
                                .createDeliveryRecord(userInformationId, deliveryRecord));
        }

        @DeleteMapping("/{deliveryrecordid}")
        public ResponseEntity<ResponseBody<DeliveryRecord>> deleteDeliveryRecordByDeliveryRecordId(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @PathVariable("deliveryrecordid") @NotNull UUID deliveryRecordId) {
                return ResponseBody.handle(userInformationService
                                .deleteDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId));
        }

        @PutMapping("/{deliveryrecordid}")
        public ResponseEntity<ResponseBody<DeliveryRecord>> updateDeliveryRecordByDeliveryRecordId(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @PathVariable("deliveryrecordid") @NotNull UUID deliveryRecordId,
                        @Valid @RequestBody DeliveryRecord deliveryRecord) {
                return ResponseBody.handle(userInformationService
                                .updateDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId,
                                                deliveryRecord));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<DeliveryRecord>>> getDeliveryRecordsByUserInformationId(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(userInformationService
                                .getDeliveryRecordsByUserInformationId(userInformationId, pageable));
        }

        @GetMapping("/{deliveryrecordid}")
        public ResponseEntity<ResponseBody<DeliveryRecord>> getDeliveryRecordByDeliveryRecordId(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @PathVariable("deliveryrecordid") @NotNull UUID deliveryRecordId) {
                return ResponseBody.handle(userInformationService
                                .getDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId));
        }
}
