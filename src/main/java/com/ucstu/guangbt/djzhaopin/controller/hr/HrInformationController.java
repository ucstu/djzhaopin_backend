package com.ucstu.guangbt.djzhaopin.controller.hr;

import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.HrInformationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/hrinfos")
public class HrInformationController {

    @Autowired
    private HrInformationService hrInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<HrInformation>> addHrInformation(
            @RequestParam String accountInformationId,
            @Valid @RequestBody HrInformation hrInformation) {
        return ResponseBody.created(hrInformationService.addHrInformation(accountInformationId, hrInformation));
    }

    @GetMapping("/{hrinfoid}")
    public ResponseEntity<ResponseBody<HrInformation>> queryHrInformationByHrInfoId(@PathVariable UUID hrinfoid) {
        return ResponseBody.success(hrInformationService.queryHrInformationByHrInfoId(hrinfoid));
    }

    @PutMapping("/{hrinfoid}")
    public ResponseEntity<ResponseBody<HrInformation>> updateHrInformationByHrInfoId(@PathVariable UUID hrinfoid,
            @Valid @RequestBody HrInformation hrInformation) {
        return ResponseBody.success(hrInformationService.updateHrInformationByHrInfoId(hrinfoid, hrInformation));
    }
}
