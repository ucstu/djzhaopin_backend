package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
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
@RequestMapping("/userinfos/{userinfoid}/deliveryrecords")
public class DeliveryRecordController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<DeliveryRecord>> createDeliveryRecord(
            @PathVariable("userinfoid") UUID userInformationId,
            @Valid @RequestBody DeliveryRecord deliveryRecord) {
        Optional<DeliveryRecord> deliveryRecordOptional = userInformationService
                .createDeliveryRecord(userInformationId, deliveryRecord);
        if (deliveryRecordOptional.isPresent()) {
            return ResponseBody.created(deliveryRecordOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @DeleteMapping("/{deliveryrecordid}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> deleteDeliveryRecordByDeliveryRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("deliveryrecordid") UUID deliveryRecordId) {
        Optional<DeliveryRecord> deliveryRecordOptional = userInformationService
                .deleteDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId);
        if (deliveryRecordOptional.isPresent()) {
            return ResponseBody.success(deliveryRecordOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @PutMapping("/{deliveryrecordid}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> updateDeliveryRecordByDeliveryRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("deliveryrecordid") UUID deliveryRecordId,
            @Valid @RequestBody DeliveryRecord deliveryRecord) {
        Optional<DeliveryRecord> deliveryRecordOptional = userInformationService
                .updateDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId, deliveryRecord);
        if (deliveryRecordOptional.isPresent()) {
            return ResponseBody.success(deliveryRecordOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<DeliveryRecord>>> getDeliveryRecordsByUserInformationId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Optional<List<DeliveryRecord>> deliveryRecordsOptional = userInformationService
                .getDeliveryRecordsByUserInformationId(userInformationId, pageable);
        if (deliveryRecordsOptional.isPresent()) {
            return ResponseBody.success(deliveryRecordsOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("/{deliveryrecordid}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> getDeliveryRecordByDeliveryRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("deliveryrecordid") UUID deliveryRecordId) {
        Optional<DeliveryRecord> deliveryRecordOptional = userInformationService
                .getDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId);
        if (deliveryRecordOptional.isPresent()) {
            return ResponseBody.success(deliveryRecordOptional.get());
        }
        return ResponseBody.notFound().build();
    }
}
