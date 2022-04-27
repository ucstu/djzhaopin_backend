package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.JobExpectation;
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
@RequestMapping("/userinfos/{userinfoid}/jobexpectations")
public class JobExpectationController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<JobExpectation>> createJobExpectation(
            @PathVariable("userinfoid") UUID userInformationId,
            @Valid @RequestBody JobExpectation jobExpectation) {
        return ResponseBody.handle(userInformationService
                .createJobExpectation(userInformationId, jobExpectation));
    }

    @DeleteMapping("/{jobexpectationid}")
    public ResponseEntity<ResponseBody<JobExpectation>> deleteJobExpectationByJobExpectationId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("jobexpectationid") UUID jobExpectationId) {
        return ResponseBody.handle(userInformationService
                .deleteJobExpectationByJobExpectationId(userInformationId, jobExpectationId));
    }

    @PutMapping("/{jobexpectationid}")
    public ResponseEntity<ResponseBody<JobExpectation>> updateJobExpectationByJobExpectationId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("jobexpectationid") UUID jobExpectationId,
            @Valid @RequestBody JobExpectation jobExpectation) {
        return ResponseBody.handle(userInformationService
                .updateJobExpectationByJobExpectationId(userInformationId, jobExpectationId, jobExpectation));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<JobExpectation>>> getJobExpectationsByUserInformationId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseBody.handle(userInformationService
                .getJobExpectationsByUserInformationId(userInformationId, pageable));
    }

    @GetMapping("/{jobexpectationid}")
    public ResponseEntity<ResponseBody<JobExpectation>> getJobExpectationByJobExpectationId(
            @PathVariable("userinfoid") UUID userInformationId,
            @PathVariable("jobexpectationid") UUID jobExpectationId) {
        return ResponseBody.handle(userInformationService
                .getJobExpectationByJobExpectationId(userInformationId, jobExpectationId));
    }
}
