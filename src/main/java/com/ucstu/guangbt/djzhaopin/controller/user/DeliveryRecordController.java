package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ucstu.guangbt.djzhaopin.entity.user.DeliveryRecord;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/userInfos/{userInfoId}/deliveryRecords")
public class DeliveryRecordController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<DeliveryRecord>> createDeliveryRecord(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @Valid @RequestBody DeliveryRecord deliveryRecord) {
        return ResponseBody.handle(userInformationService
                .createDeliveryRecord(userInformationId, deliveryRecord));
    }

    @DeleteMapping("/{deliveryRecordId}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> deleteDeliveryRecordByDeliveryRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("deliveryRecordId") @NotNull UUID deliveryRecordId) {
        return ResponseBody.handle(userInformationService
                .deleteDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId));
    }

    @PutMapping("/{deliveryRecordId}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> updateDeliveryRecordByDeliveryRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("deliveryRecordId") @NotNull UUID deliveryRecordId,
            @Valid @RequestBody DeliveryRecord deliveryRecord) {
        return ResponseBody.handle(userInformationService
                .updateDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId,
                        deliveryRecord));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<PageResult<DeliveryRecord>>> getDeliveryRecordsByUserInformationId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @RequestParam("status") @NotEmpty List<@Valid @Range(min = 1, max = 5) Integer> status,
            @RequestParam(value = "interviewTime", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date interviewTime,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(userInformationService
                .getDeliveryRecordsByUserInformationId(userInformationId, status, interviewTime, pageable));
    }

    @GetMapping("/{deliveryRecordId}")
    public ResponseEntity<ResponseBody<DeliveryRecord>> getDeliveryRecordByDeliveryRecordId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("deliveryRecordId") @NotNull UUID deliveryRecordId) {
        return ResponseBody.handle(userInformationService
                .getDeliveryRecordByDeliveryRecordId(userInformationId, deliveryRecordId));
    }

}
