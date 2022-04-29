package com.ucstu.guangbt.djzhaopin.controller;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.model.util.CityInformation;
import com.ucstu.guangbt.djzhaopin.model.util.DirectionTag;
import com.ucstu.guangbt.djzhaopin.model.util.FilterInformation;
import com.ucstu.guangbt.djzhaopin.model.util.areainformation.AreaInformation;
import com.ucstu.guangbt.djzhaopin.model.util.positiontype.PositionType;
import com.ucstu.guangbt.djzhaopin.service.UtilService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotBlank;

@Validated
@CrossOrigin
@RestController
public class UtilController {

    @Resource
    private UtilService utilService;

    @GetMapping("/areaInformations")
    public ResponseEntity<ResponseBody<List<AreaInformation>>> getAreaInformations(
            @RequestParam @NotBlank String city) {
        return ResponseBody.handle(utilService.getAreaInformations(city));
    }

    @GetMapping("/filterInformation")
    public ResponseEntity<ResponseBody<FilterInformation>> getFilterInformation() {
        return ResponseBody.handle(utilService.getFilterInformation());
    }

    @GetMapping("/positionTypes")
    public ResponseEntity<ResponseBody<List<PositionType>>> getPositionTypes() {
        return ResponseBody.handle(utilService.getPositionTypes());
    }

    @GetMapping("/directionTags")
    public ResponseEntity<ResponseBody<List<DirectionTag>>> getDirectionTags(
            @RequestParam @NotBlank String positionName) {
        return ResponseBody.handle(utilService.getDirectionTags(positionName));
    }

    @GetMapping("/cityInformations")
    public ResponseEntity<ResponseBody<List<CityInformation>>> getCityInformations() {
        return ResponseBody.handle(utilService.getCityInformations());
    }

    @GetMapping("/recommendations")
    public ResponseEntity<ResponseBody<List<UUID>>> getRecommendations(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(utilService.getRecommendations(pageable));
    }

    @GetMapping("/messageRecords")
    public ResponseEntity<ResponseBody<List<MessageRecord>>> getMessageRecords(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseBody.handle(utilService.getMessageRecords(pageable));
    }

    @GetMapping("/verificationCode")
    public ResponseEntity<ResponseBody<String>> getVerificationCode(
            @RequestParam @NotBlank String phoneNumber) {
        return ResponseBody.handle(utilService.getVerificationCode(phoneNumber));
    }

    @GetMapping("/newVersion")
    public ResponseEntity<ResponseBody<String>> getNewVersion() {
        return ResponseBody.handle(utilService.getNewVersion());
    }

    @PostMapping("/files")
    public ResponseEntity<ResponseBody<String>> uploadFiles(
            @RequestBody MultipartFile file) {
        return ResponseBody.handle(utilService.uploadFile(file));
    }

    @PostMapping("/avatars")
    public ResponseEntity<ResponseBody<String>> uploadAvatars(
            @RequestBody MultipartFile avatar) {
        return ResponseBody.handle(utilService.uploadAvatar(avatar));
    }
}
