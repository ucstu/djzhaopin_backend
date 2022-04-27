package com.ucstu.guangbt.djzhaopin.controller.hr;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.HrInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/hrinfos")
public class HrInformationController {

    @Resource
    private HrInformationService hrInformationService;

    @GetMapping("/{hrinfoid}")
    public ResponseEntity<ResponseBody<HrInformation>> getHrInformationByHrInformationId(
            @PathVariable("hrinfoid") UUID hrInformationId) {
        return ResponseBody.handle(hrInformationService.getHrInformationByHrInformationId(hrInformationId));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<HrInformation>>> getHrInformations(@PageableDefault Pageable pageable) {
        return ResponseBody.handle(hrInformationService.getHrInformations(pageable));
    }

    @PutMapping("/{hrinfoid}")
    public ResponseEntity<ResponseBody<HrInformation>> updateHrInformationByHrInformationId(
            @PathVariable("hrinfoid") UUID hrInformationId,
            @Valid @RequestBody HrInformation hrInformation) {
        return ResponseBody.handle(hrInformationService
                .updateHrInformationByHrInformationId(hrInformationId, hrInformation));
    }
}
