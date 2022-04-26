package com.ucstu.guangbt.djzhaopin.controller;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;

@Validated
@CrossOrigin
@RestController
public class UtilController {

    @Resource
    private UtilService utilService;

    @GetMapping("/areaInformations")
    public ResponseEntity<ResponseBody<List<AreaInformation>>> getAreaInformations(
            @RequestParam String city) {
        Optional<List<AreaInformation>> areaInformationsOptional = utilService.getAreaInformations(city);
        if (areaInformationsOptional.isPresent()) {
            return ResponseBody.success(areaInformationsOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @GetMapping("/filterinformation")
    public ResponseEntity<ResponseBody<FilterInformation>> getFilterInformation() {
        Optional<FilterInformation> areaInformationsOptional = utilService.getFilterInformation();
        if (areaInformationsOptional.isPresent()) {
            return ResponseBody.success(areaInformationsOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @GetMapping("/positiontypes")
    public ResponseEntity<ResponseBody<List<PositionType>>> getPositionTypes() {
        Optional<List<PositionType>> positionTypesOptional = utilService.getPositionTypes();
        if (positionTypesOptional.isPresent()) {
            return ResponseBody.success(positionTypesOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @GetMapping("/directiontags")
    public ResponseEntity<ResponseBody<List<DirectionTag>>> getDirectionTags(
            @RequestParam String positionName) {
        Optional<List<DirectionTag>> directionTagsOptional = utilService.getDirectionTags(positionName);
        if (directionTagsOptional.isPresent()) {
            return ResponseBody.success(directionTagsOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @GetMapping("/cityinformations")
    public ResponseEntity<ResponseBody<List<CityInformation>>> getCityInformations() {
        Optional<List<CityInformation>> areaInformationsOptional = utilService.getCityInformations();
        if (areaInformationsOptional.isPresent()) {
            return ResponseBody.success(areaInformationsOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @GetMapping("/recommendations")
    public ResponseEntity<ResponseBody<List<UUID>>> getRecommendations(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Optional<List<UUID>> areaInformationsOptional = utilService.getRecommendations(pageable);
        if (areaInformationsOptional.isPresent()) {
            return ResponseBody.success(areaInformationsOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @GetMapping("/messagerecords")
    public ResponseEntity<ResponseBody<List<MessageRecord>>> getMessageRecords(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Optional<List<MessageRecord>> areaInformationsOptional = utilService.getMessageRecords(pageable);
        if (areaInformationsOptional.isPresent()) {
            return ResponseBody.success(areaInformationsOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @GetMapping("/verificationCode")
    public ResponseEntity<ResponseBody<String>> getVerificationCode(
            @RequestParam String phoneNumber) {
        Optional<String> verificationCodeOptional = utilService.getVerificationCode(phoneNumber);
        if (verificationCodeOptional.isPresent()) {
            return ResponseBody.success(verificationCodeOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @GetMapping("/newversion")
    public ResponseEntity<ResponseBody<String>> getNewVersion() {
        Optional<String> newVersionOptional = utilService.getNewVersion();
        if (newVersionOptional.isPresent()) {
            return ResponseBody.success(newVersionOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @PostMapping("/files")
    public ResponseEntity<ResponseBody<String>> uploadFiles(
            MultipartFile file) {
        Optional<String> fileUrlOptional = utilService.uploadFile(file);
        if (fileUrlOptional.isPresent()) {
            return ResponseBody.created(fileUrlOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }

    @PostMapping("/avatars")
    public ResponseEntity<ResponseBody<String>> uploadAvatars(
            MultipartFile avatar) {
        Optional<String> avatarUrlOptional = utilService.uploadAvatar(avatar);
        if (avatarUrlOptional.isPresent()) {
            return ResponseBody.created(avatarUrlOptional.get());
        } else {
            return ResponseBody.notFound().build();
        }
    }
}
