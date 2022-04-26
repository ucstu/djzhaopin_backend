package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.ProjectExperience;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
@RequestMapping("/userinfos/{userinfoid}/projectexperiences")
public class ProjectExperienceController {

        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<ProjectExperience>> createProjectExperience(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @Valid @RequestBody ProjectExperience projectExperience) {
                Optional<ProjectExperience> projectExperienceOptional = userInformationService
                                .createProjectExperience(userInformationId, projectExperience);
                if (projectExperienceOptional.isPresent()) {
                        return ResponseBody.success(projectExperienceOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @DeleteMapping("/{projectexperienceid}")
        public ResponseEntity<ResponseBody<ProjectExperience>> deleteProjectExperienceByProjectExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("projectexperienceid") UUID projectExperienceId) {
                Optional<ProjectExperience> projectExperienceOptional = userInformationService
                                .deleteProjectExperienceByProjectExperienceId(userInformationId, projectExperienceId);
                if (projectExperienceOptional.isPresent()) {
                        return ResponseBody.success(projectExperienceOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @PutMapping("/{projectexperienceid}")
        public ResponseEntity<ResponseBody<ProjectExperience>> updateProjectExperienceByProjectExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("projectexperienceid") UUID projectExperienceId,
                        @Valid @RequestBody ProjectExperience projectExperience) {
                Optional<ProjectExperience> projectExperienceOptional = userInformationService
                                .updateProjectExperienceByProjectExperienceId(userInformationId, projectExperienceId,
                                                projectExperience);
                if (projectExperienceOptional.isPresent()) {
                        return ResponseBody.success(projectExperienceOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<Set<ProjectExperience>>> getProjectExperiencesByUserInformationId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PageableDefault(page = 0, size = 10) Pageable pageable) {
                Optional<Set<ProjectExperience>> projectExperienceStream = userInformationService
                                .getProjectExperiencesByUserInformationId(userInformationId, pageable);
                if (projectExperienceStream.isPresent()) {
                        return ResponseBody.success(projectExperienceStream.get());
                } else {
                        return ResponseBody.notFound().build();
                }
        }

        @GetMapping("/{projectexperienceid}")
        public ResponseEntity<ResponseBody<ProjectExperience>> getProjectExperienceByProjectExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("projectexperienceid") UUID projectExperienceId) {
                Optional<ProjectExperience> projectExperienceOptional = userInformationService
                                .getProjectExperienceByProjectExperienceId(userInformationId, projectExperienceId);
                if (projectExperienceOptional.isPresent()) {
                        return ResponseBody.success(projectExperienceOptional.get());
                }
                return ResponseBody.notFound().build();
        }

}
