package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
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
@RequestMapping("/userinfos/{userinfoid}/deliveryrecords")
public class DeliveryRecordController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<DeliveryRecord>> createDeliveryRecord(
            @PathVariable UUID userinfoid,
            @Valid @RequestBody DeliveryRecord deliveryRecord) {
        return ResponseBody.success(userInformationService.createDeliveryRecord(userinfoid, deliveryRecord));
    }

    @DeleteMapping("/{deliveryrecordid}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> deleteDeliveryRecordByDeliveryRecordId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID deliveryrecordid) {
        return ResponseBody
                .success(userInformationService.deleteDeliveryRecordByDeliveryRecordId(userinfoid, deliveryrecordid));
    }

    @PutMapping("/{deliveryrecordid}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> updateDeliveryRecordByDeliveryRecordId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID deliveryrecordid,
            @Valid @RequestBody DeliveryRecord deliveryRecord) {
        return ResponseBody
                .success(userInformationService.updateDeliveryRecordByDeliveryRecordId(userinfoid, deliveryrecordid,
                        deliveryRecord));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<DeliveryRecord>>> getDeliveryRecords(
            @PathVariable UUID userinfoid) {
        return ResponseBody.success(userInformationService.getDeliveryRecords(userinfoid));
    }

    @GetMapping("/{deliveryrecordid}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> getDeliveryRecordByDeliveryRecordId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID deliveryrecordid) {
        return ResponseBody
                .success(userInformationService.getDeliveryRecordByDeliveryRecordId(userinfoid, deliveryrecordid));
    }
}
