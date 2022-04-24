package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.JobExpectation;
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
@RequestMapping("/userinfos/{userinfoid}/jobexpectations")
public class JobExpectationController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<JobExpectation>> createJobExpectation(
            @PathVariable UUID userinfoid,
            @Valid @RequestBody JobExpectation jobExpectation) {
        return ResponseBody.success(userInformationService.createJobExpectation(userinfoid, jobExpectation));
    }

    @DeleteMapping("/{jobexpectationid}")
    public ResponseEntity<ResponseBody<JobExpectation>> deleteJobExpectationByJobExpectationId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID jobexpectationid) {
        return ResponseBody
                .success(userInformationService.deleteJobExpectationByJobExpectationId(userinfoid, jobexpectationid));
    }

    @PutMapping("/{jobexpectationid}")
    public ResponseEntity<ResponseBody<JobExpectation>> updateJobExpectationByJobExpectationId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID jobexpectationid,
            @Valid @RequestBody JobExpectation jobExpectation) {
        return ResponseBody
                .success(userInformationService.updateJobExpectationByJobExpectationId(userinfoid, jobexpectationid,
                        jobExpectation));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<JobExpectation>>> getJobExpectations(
            @PathVariable UUID userinfoid) {
        return ResponseBody.success(userInformationService.getJobExpectations(userinfoid));
    }

    @GetMapping("/{jobexpectationid}")
    public ResponseEntity<ResponseBody<JobExpectation>> getJobExpectationByJobExpectationId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID jobexpectationid) {
        return ResponseBody
                .success(userInformationService.getJobExpectationByJobExpectationId(userinfoid, jobexpectationid));
    }
}
