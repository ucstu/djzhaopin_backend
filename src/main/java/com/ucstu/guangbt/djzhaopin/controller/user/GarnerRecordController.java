package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.Optional;
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
        Optional<GarnerRecord> garnerRecordOptional = userInformationService.createGarnerRecord(userInformationId,
                garnerRecord);
        if (garnerRecordOptional.isPresent()) {
            return ResponseBody.created(garnerRecordOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @DeleteMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> deleteGarnerRecordByGarnerRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("garnerrecordid") UUID garnerRecordId) {
        Optional<GarnerRecord> garnerRecordOptional = userInformationService.deleteGarnerRecordByGarnerRecordId(
                userInformationId, garnerRecordId);
        if (garnerRecordOptional.isPresent()) {
            return ResponseBody.success(garnerRecordOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @PutMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> updateGarnerRecordByGarnerRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("garnerrecordid") UUID garnerRecordId,
            @Valid @RequestBody GarnerRecord garnerRecord) {
        Optional<GarnerRecord> garnerRecordOptional = userInformationService.updateGarnerRecordByGarnerRecordId(
                userInformationId, garnerRecordId, garnerRecord);
        if (garnerRecordOptional.isPresent()) {
            return ResponseBody.success(garnerRecordOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<GarnerRecord>>> getGarnerRecordsByUserInformationId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Optional<List<GarnerRecord>> garnerRecords = userInformationService
                .getGarnerRecordsByUserInformationId(userInformationId, pageable);
        if (garnerRecords.isPresent()) {
            return ResponseBody.success(garnerRecords.get());
        }
        return ResponseBody.notFound().build();
    }

    @GetMapping("/{garnerrecordid}")
    public ResponseEntity<ResponseBody<GarnerRecord>> getGarnerRecordByGarnerRecordId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("garnerrecordid") UUID garnerRecordId) {
        Optional<GarnerRecord> garnerRecordOptional = userInformationService.getGarnerRecordByGarnerRecordId(
                userInformationId, garnerRecordId);
        if (garnerRecordOptional.isPresent()) {
            return ResponseBody.success(garnerRecordOptional.get());
        }
        return ResponseBody.notFound().build();
    }
}
