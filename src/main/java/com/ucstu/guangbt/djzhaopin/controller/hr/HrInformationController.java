package com.ucstu.guangbt.djzhaopin.controller.hr;

import java.util.List;
import java.util.Optional;
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
        Optional<HrInformation> hrInformationOptional = hrInformationService
                .getHrInformationByHrInformationId(hrInformationId);
        if (hrInformationOptional.isPresent()) {
            return ResponseBody.success(hrInformationOptional.get());
        }
        return ResponseBody.notFound().build();

    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<HrInformation>>> getHrInformations(@PageableDefault Pageable pageable) {
        Optional<List<HrInformation>> hrInformationsOptional = hrInformationService.getHrInformations(pageable);
        if (hrInformationsOptional.isPresent()) {
            return ResponseBody.success(hrInformationsOptional.get());
        }
        return ResponseBody.notFound().build();
    }

    @PutMapping("/{hrinfoid}")
    public ResponseEntity<ResponseBody<HrInformation>> updateHrInformationByHrInformationId(
            @PathVariable("hrinfoid") UUID hrInformationId,
            @Valid @RequestBody HrInformation hrInformation) {
        Optional<HrInformation> hrInformationOptional = hrInformationService
                .updateHrInformationByHrInformationId(hrInformationId, hrInformation);
        if (hrInformationOptional.isPresent()) {
            return ResponseBody.success(hrInformationOptional.get());
        }
        return ResponseBody.notFound().build();
    }
}
