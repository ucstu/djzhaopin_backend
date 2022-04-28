package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;
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
@RequestMapping("/userinfos/{userinfoid}/garnerrecords")
public class GarnerRecordController {
    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<GarnerRecord>> createGarnerRecord(
            @PathVariable("userinfoid") UUID userInformationId,
            @Valid @RequestBody GarnerRecord garnerRecord) {
        return ResponseBody.handle(userInformationService.createGarnerRecord(userInformationId,
                garnerRecord));
    }

    @DeleteMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> deleteGarnerRecordByGarnerRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("garnerrecordid") UUID garnerRecordId) {
        return ResponseBody.handle(userInformationService.deleteGarnerRecordByGarnerRecordId(
                userInformationId, garnerRecordId));
    }

    @PutMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> updateGarnerRecordByGarnerRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("garnerrecordid") UUID garnerRecordId,
            @Valid @RequestBody GarnerRecord garnerRecord) {
        return ResponseBody.handle(userInformationService.updateGarnerRecordByGarnerRecordId(
                userInformationId, garnerRecordId, garnerRecord));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<GarnerRecord>>> getGarnerRecordsByUserInformationId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(userInformationService
                .getGarnerRecordsByUserInformationId(userInformationId, pageable));
    }

    @GetMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> getGarnerRecordByGarnerRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("garnerrecordid") UUID garnerRecordId) {
        return ResponseBody.handle(userInformationService.getGarnerRecordByGarnerRecordId(
                userInformationId, garnerRecordId));
    }
}
