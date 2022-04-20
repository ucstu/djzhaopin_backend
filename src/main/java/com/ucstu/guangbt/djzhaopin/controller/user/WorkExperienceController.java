package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.WorkExperience;
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
@RequestMapping("/userinfos/{userinfoid}/workexperiences")
public class WorkExperienceController {

    @Autowired
    private UserInformationService userInformationService;

    @PostMapping("")
    public ResponseEntity<ResponseBody<WorkExperience>> createWorkExperience(
            @PathVariable UUID userinfoid,
            @Valid @RequestBody WorkExperience workExperience) {
        return ResponseBody.success(userInformationService.createWorkExperience(userinfoid, workExperience));
    }

    @DeleteMapping("/{workexperienceid}")
    public ResponseEntity<ResponseBody<WorkExperience>> deleteWorkExperienceByWorkExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID workexperienceid) {
        return ResponseBody
                .success(userInformationService.deleteWorkExperienceByWorkExperienceId(userinfoid,
                        workexperienceid));
    }

    @PutMapping("/{workexperienceid}")
    public ResponseEntity<ResponseBody<WorkExperience>> updateWorkExperienceByWorkExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID workexperienceid,
            @Valid @RequestBody WorkExperience workExperience) {
        return ResponseBody
                .success(userInformationService.updateWorkExperienceByWorkExperienceId(userinfoid,
                        workexperienceid, workExperience));
    }

    @GetMapping("")
    public ResponseEntity<ResponseBody<List<WorkExperience>>> getWorkExperiences(
            @PathVariable UUID userinfoid) {
        return ResponseBody.success(userInformationService.getWorkExperiences(userinfoid));
    }

    @GetMapping("/{workexperienceid}")
    public ResponseEntity<ResponseBody<WorkExperience>> getWorkExperienceByWorkExperienceId(
            @PathVariable UUID userinfoid,
            @PathVariable UUID workexperienceid) {
        return ResponseBody
                .success(userInformationService.getWorkExperienceByWorkExperienceId(userinfoid,
                        workexperienceid));
    }
}
