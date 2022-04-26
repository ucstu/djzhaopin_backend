package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;
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
@RequestMapping("/userinfos/{userinfoid}/workexperiences")
public class WorkExperienceController {

        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<WorkExperience>> createWorkExperience(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @Valid @RequestBody WorkExperience workExperience) {
                Optional<WorkExperience> workExperienceOptional = userInformationService
                                .createWorkExperience(userInformationId, workExperience);
                if (workExperienceOptional.isPresent()) {
                        return ResponseBody.created(workExperienceOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @DeleteMapping("/{workexperienceid}")
        public ResponseEntity<ResponseBody<WorkExperience>> deleteWorkExperienceByWorkExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("workexperienceid") UUID workExperienceId) {
                Optional<WorkExperience> workExperienceOptional = userInformationService
                                .deleteWorkExperienceByWorkExperienceId(userInformationId, workExperienceId);
                if (workExperienceOptional.isPresent()) {
                        return ResponseBody.success(workExperienceOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @PutMapping("/{workexperienceid}")
        public ResponseEntity<ResponseBody<WorkExperience>> updateWorkExperienceByWorkExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("workexperienceid") UUID workExperienceId,
                        @Valid @RequestBody WorkExperience workExperience) {
                Optional<WorkExperience> workExperienceOptional = userInformationService
                                .updateWorkExperienceByWorkExperienceId(userInformationId, workExperienceId,
                                                workExperience);
                if (workExperienceOptional.isPresent()) {
                        return ResponseBody.success(workExperienceOptional.get());
                }
                return ResponseBody.notFound().build();
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<WorkExperience>>> getWorkExperiencesByUserInformationId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PageableDefault(page = 0, size = 10) Pageable pageable) {
                Optional<List<WorkExperience>> workExperiences = userInformationService
                                .getWorkExperiencesByUserInformationId(userInformationId, pageable);
                if (workExperiences.isPresent()) {
                        return ResponseBody.success(workExperiences.get());
                } else {
                        return ResponseBody.notFound().build();
                }
        }

        @GetMapping("/{workexperienceid}")
        public ResponseEntity<ResponseBody<WorkExperience>> getWorkExperienceByWorkExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("workexperienceid") UUID workExperienceId) {
                Optional<WorkExperience> workExperienceOptional = userInformationService
                                .getWorkExperienceByWorkExperienceId(userInformationId, workExperienceId);
                if (workExperienceOptional.isPresent()) {
                        return ResponseBody.success(workExperienceOptional.get());
                }
                return ResponseBody.notFound().build();
        }
}
