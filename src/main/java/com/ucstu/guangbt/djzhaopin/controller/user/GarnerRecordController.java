package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.GarnerRecord;
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
@RequestMapping("/userinfos/{userinfoid}/garnerrecords")
public class GarnerRecordController {
    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<GarnerRecord>> createGarnerRecord(
            @PathVariable UUID userinfoid,
            @Valid @RequestBody GarnerRecord garnerRecord) {
        return ResponseBody.success(userInformationService.createGarnerRecord(userinfoid, garnerRecord));
    }

    @DeleteMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> deleteGarnerRecordByGarnerRecordId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID garnerrecordid) {
        return ResponseBody
                .success(userInformationService.deleteGarnerRecordByGarnerRecordId(userinfoid, garnerrecordid));
    }

    @PutMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> updateGarnerRecordByGarnerRecordId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID garnerrecordid,
            @Valid @RequestBody GarnerRecord garnerRecord) {
        return ResponseBody
                .success(userInformationService.updateGarnerRecordByGarnerRecordId(userinfoid, garnerrecordid,
                        garnerRecord));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<GarnerRecord>>> getGarnerRecords(
            @PathVariable UUID userinfoid) {
        return ResponseBody.success(userInformationService.getGarnerRecords(userinfoid));
    }

    @GetMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> getGarnerRecordByGarnerRecordId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID garnerrecordid) {
        return ResponseBody
                .success(userInformationService.getGarnerRecordByGarnerRecordId(userinfoid, garnerrecordid));
    }
}
