package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.EducationExperience;
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
@RequestMapping("/userinfos/{userinfoid}/eduexperiences")
public class EducationExperienceController {

        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<EducationExperience>> createEducationExperience(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @Valid @RequestBody EducationExperience educationExperience) {
                return ResponseBody.handle(userInformationService
                                .createEducationExperience(userInformationId, educationExperience));
        }

        @DeleteMapping("/{eduexperienceid}")
        public ResponseEntity<ResponseBody<EducationExperience>> deleteEducationExperienceByEducationExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("eduexperienceid") UUID eduExperienceId) {
                return ResponseBody.handle(userInformationService
                                .deleteEducationExperienceByEducationExperienceId(userInformationId, eduExperienceId));
        }

        @PutMapping("/{eduexperienceid}")
        public ResponseEntity<ResponseBody<EducationExperience>> updateEducationExperienceByEducationExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("eduexperienceid") UUID eduExperienceId,
                        @Valid @RequestBody EducationExperience educationExperience) {
                return ResponseBody.handle(userInformationService
                                .updateEducationExperienceByEducationExperienceId(userInformationId, eduExperienceId,
                                                educationExperience));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<EducationExperience>>> getEducationExperiencesByUserInformationId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(userInformationService
                                .getEducationExperiencesByUserInformationId(userInformationId, pageable));
        }

        @GetMapping("/{eduexperienceid}")
        public ResponseEntity<ResponseBody<EducationExperience>> getEducationExperienceByEducationExperienceId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @PathVariable("eduexperienceid") UUID eduExperienceId) {
                return ResponseBody.handle(userInformationService
                                .getEducationExperienceByEducationExperienceId(userInformationId, eduExperienceId));
        }

}
