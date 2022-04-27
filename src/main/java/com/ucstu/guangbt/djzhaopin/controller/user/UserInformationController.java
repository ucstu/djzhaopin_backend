package com.ucstu.guangbt.djzhaopin.controller.user;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.service.UserInformationService;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/userinfos")
public class UserInformationController {

        @Resource
        private UserInformationService userInformationService;

        @PutMapping("/{userinfoid}")
        public ResponseEntity<ResponseBody<UserInformation>> updateUserInformationByUserInformationId(
                        @PathVariable("userinfoid") UUID userInformationId,
                        @Valid @RequestBody UserInformation userInformation) {
                return ResponseBody.handle(userInformationService
                                .updateUserInformationByUserInformationId(userInformationId, userInformation));
        }

        @GetMapping("")
        public ResponseEntity<ResponseBody<List<UserInformation>>> getUserInformations(
                        @PageableDefault(page = 0, size = 10) Pageable pageable) {
                return ResponseBody.handle(userInformationService
                                .getUserInformations(pageable));
        }

        @GetMapping("/{userinfoid}")
        public ResponseEntity<ResponseBody<UserInformation>> getUserInformationByUserInformationId(
                        @PathVariable("userinfoid") UUID userInformationId) {
                return ResponseBody.handle(userInformationService
                                .getUserInformationByUserInformationId(userInformationId));
        }
}
