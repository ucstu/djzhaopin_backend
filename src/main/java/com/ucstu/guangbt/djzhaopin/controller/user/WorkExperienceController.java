package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/userInfos/{userInfoId}/workExperiences")
public class WorkExperienceController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<WorkExperience>> createWorkExperience(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @Valid @RequestBody WorkExperience workExperience) {
        return ResponseBody.handle(userInformationService
                .createWorkExperience(userInformationId, workExperience));
    }

    @DeleteMapping("/{workExperienceId}")
    public ResponseEntity<ResponseBody<WorkExperience>> deleteWorkExperienceByWorkExperienceId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("workExperienceId") @NotNull UUID workExperienceId) {
        return ResponseBody.handle(userInformationService
                .deleteWorkExperienceByWorkExperienceId(userInformationId, workExperienceId));
    }

    @PutMapping("/{workExperienceId}")
    public ResponseEntity<ResponseBody<WorkExperience>> updateWorkExperienceByWorkExperienceId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("workExperienceId") @NotNull UUID workExperienceId,
            @Valid @RequestBody WorkExperience workExperience) {
        return ResponseBody.handle(userInformationService
                .updateWorkExperienceByWorkExperienceId(userInformationId, workExperienceId,
                        workExperience));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<PageResult<WorkExperience>>> getWorkExperiencesByUserInformationId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(userInformationService
                .getWorkExperiencesByUserInformationId(userInformationId, pageable));
    }

    @GetMapping("/{workExperienceId}")
    public ResponseEntity<ResponseBody<WorkExperience>> getWorkExperienceByWorkExperienceId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("workExperienceId") @NotNull UUID workExperienceId) {
        return ResponseBody.handle(userInformationService
                .getWorkExperienceByWorkExperienceId(userInformationId, workExperienceId));
    }

}
