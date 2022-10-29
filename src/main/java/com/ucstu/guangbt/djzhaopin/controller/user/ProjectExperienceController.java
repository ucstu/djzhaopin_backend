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

import com.ucstu.guangbt.djzhaopin.entity.user.ProjectExperience;
import com.ucstu.guangbt.djzhaopin.model.PageResult;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/userInfos/{userInfoId}/projectExperiences")
public class ProjectExperienceController {

    @Resource
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<ProjectExperience>> createProjectExperience(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @Valid @RequestBody ProjectExperience projectExperience) {
        return ResponseBody.handle(userInformationService
                .createProjectExperience(userInformationId, projectExperience));
    }

    @DeleteMapping("/{projectExperienceId}")
    public ResponseEntity<ResponseBody<ProjectExperience>> deleteProjectExperienceByProjectExperienceId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("projectExperienceId") @NotNull UUID projectExperienceId) {
        return ResponseBody.handle(userInformationService
                .deleteProjectExperienceByProjectExperienceId(userInformationId, projectExperienceId));
    }

    @PutMapping("/{projectExperienceId}")
    public ResponseEntity<ResponseBody<ProjectExperience>> updateProjectExperienceByProjectExperienceId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("projectExperienceId") @NotNull UUID projectExperienceId,
            @Valid @RequestBody ProjectExperience projectExperience) {
        return ResponseBody.handle(userInformationService
                .updateProjectExperienceByProjectExperienceId(userInformationId, projectExperienceId,
                        projectExperience));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<PageResult<ProjectExperience>>> getProjectExperiencesByUserInformationId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(userInformationService
                .getProjectExperiencesByUserInformationId(userInformationId, pageable));
    }

    @GetMapping("/{projectExperienceId}")
    public ResponseEntity<ResponseBody<ProjectExperience>> getProjectExperienceByProjectExperienceId(
            @PathVariable("userInfoId") @NotNull UUID userInformationId,
            @PathVariable("projectExperienceId") @NotNull UUID projectExperienceId) {
        return ResponseBody.handle(userInformationService
                .getProjectExperienceByProjectExperienceId(userInformationId, projectExperienceId));
    }

}
