package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.EducationExperience;
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
@RequestMapping("/userinfos/{userinfoid}/eduexperiences")
public class EducationExperienceController {

    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<EducationExperience>> createEducationExperience(
            @PathVariable UUID userinfoid,
            @Valid @RequestBody EducationExperience educationExperience) {
        return ResponseBody.success(userInformationService.createEducationExperience(userinfoid, educationExperience));
    }

    @DeleteMapping("/{eduexperienceid}")
    public ResponseEntity<ResponseBody<EducationExperience>> deleteEducationExperienceByEducationExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID eduexperienceid) {
        return ResponseBody
                .success(userInformationService.deleteEducationExperienceByEducationExperienceId(userinfoid,
                        eduexperienceid));
    }

    @PutMapping("/{eduexperienceid}")
    public ResponseEntity<ResponseBody<EducationExperience>> updateEducationExperienceByEducationExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID eduexperienceid,
            @Valid @RequestBody EducationExperience educationExperience) {
        return ResponseBody
                .success(userInformationService.updateEducationExperienceByEducationExperienceId(userinfoid,
                        eduexperienceid, educationExperience));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<EducationExperience>>> getEducationExperiences(
            @PathVariable UUID userinfoid) {
        return ResponseBody.success(userInformationService.getEducationExperiences(userinfoid));
    }

    @GetMapping("/{eduexperienceid}")
    public ResponseEntity<ResponseBody<EducationExperience>> getEducationExperienceByEducationExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID eduexperienceid) {
        return ResponseBody
                .success(userInformationService.getEducationExperienceByEducationExperienceId(userinfoid,
                        eduexperienceid));
    }

}
