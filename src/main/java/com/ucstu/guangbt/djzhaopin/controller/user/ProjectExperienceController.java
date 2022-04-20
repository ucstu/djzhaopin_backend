package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.ProjectExperience;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.beans.factory.annotation.Autowired;
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

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/userinfos/{userinfoid}/projectexperiences")
public class ProjectExperienceController {

    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<ProjectExperience>> createProjectExperience(
            @PathVariable UUID userinfoid,
            @Valid @RequestBody ProjectExperience projectExperience) {
        return ResponseBody.success(userInformationService.createProjectExperience(userinfoid, projectExperience));
    }

    @DeleteMapping("/{projectexperienceid}")
    public ResponseEntity<ResponseBody<ProjectExperience>> deleteProjectExperienceByProjectExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID projectexperienceid) {
        return ResponseBody
                .success(userInformationService.deleteProjectExperienceByProjectExperienceId(userinfoid,
                        projectexperienceid));
    }

    @PutMapping("/{projectexperienceid}")
    public ResponseEntity<ResponseBody<ProjectExperience>> updateProjectExperienceByProjectExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID projectexperienceid,
            @Valid @RequestBody ProjectExperience projectExperience) {
        return ResponseBody
                .success(userInformationService.updateProjectExperienceByProjectExperienceId(userinfoid,
                        projectexperienceid, projectExperience));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<ProjectExperience>>> getProjectExperiences(
            @PathVariable UUID userinfoid) {
        return ResponseBody.success(userInformationService.getProjectExperiences(userinfoid));
    }

    @GetMapping("/{projectexperienceid}")
    public ResponseEntity<ResponseBody<ProjectExperience>> getProjectExperienceByProjectExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID projectexperienceid) {
        return ResponseBody
                .success(userInformationService.getProjectExperienceByProjectExperienceId(userinfoid,
                        projectexperienceid));
    }

}
