package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;
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
import jakarta.validation.constraints.NotNull;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/userinfos/{userinfoid}/workexperiences")
public class WorkExperienceController {

        @Resource
        private UserInformationService userInformationService;

        @PostMapping("")
        public ResponseEntity<ResponseBody<WorkExperience>> createWorkExperience(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @Valid @RequestBody WorkExperience workExperience) {
                return ResponseBody.handle(userInformationService
                                .createWorkExperience(userInformationId, workExperience));
        }

        @DeleteMapping("/{workexperienceid}")
        public ResponseEntity<ResponseBody<WorkExperience>> deleteWorkExperienceByWorkExperienceId(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @PathVariable("workexperienceid") @NotNull UUID workExperienceId) {
                return ResponseBody.handle(userInformationService
                                .deleteWorkExperienceByWorkExperienceId(userInformationId, workExperienceId));
        }

        @PutMapping("/{workexperienceid}")
        public ResponseEntity<ResponseBody<WorkExperience>> updateWorkExperienceByWorkExperienceId(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @PathVariable("workexperienceid") @NotNull UUID workExperienceId,
                        @Valid @RequestBody WorkExperience workExperience) {
                return ResponseBody.handle(userInformationService
                                .updateWorkExperienceByWorkExperienceId(userInformationId, workExperienceId,
                                                workExperience));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<WorkExperience>>> getWorkExperiencesByUserInformationId(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @PageableDefault(size = 10) Pageable pageable) {
                return ResponseBody.handle(userInformationService
                                .getWorkExperiencesByUserInformationId(userInformationId, pageable));
        }

        @GetMapping("/{workexperienceid}")
        public ResponseEntity<ResponseBody<WorkExperience>> getWorkExperienceByWorkExperienceId(
                        @PathVariable("userinfoid") @NotNull UUID userInformationId,
                        @PathVariable("workexperienceid") @NotNull UUID workExperienceId) {
                return ResponseBody.handle(userInformationService
                                .getWorkExperienceByWorkExperienceId(userInformationId, workExperienceId));
        }
}
