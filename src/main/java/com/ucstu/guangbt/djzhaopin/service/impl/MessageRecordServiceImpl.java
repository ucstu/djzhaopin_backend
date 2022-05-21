package com.ucstu.guangbt.djzhaopin.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucstu.guangbt.djzhaopin.entity.hr.HrInformation;
import com.ucstu.guangbt.djzhaopin.entity.user.UserInformation;
import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.model.ErrorContent;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.repository.MessageRecordRepository;
import com.ucstu.guangbt.djzhaopin.repository.hr.HrInformationRepository;
import com.ucstu.guangbt.djzhaopin.repository.user.UserInformationRepository;
import com.ucstu.guangbt.djzhaopin.service.MessageRecordService;
import com.ucstu.guangbt.djzhaopin.utils.EmailMessageUtil;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

@Service
public class MessageRecordServiceImpl implements MessageRecordService {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private EmailMessageUtil emailMessageUtil;

    @Resource
    private RedisTemplate<String, String> onlineUserTemplate;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private MessageRecordRepository messageRecordRepository;

    @Resource
    private UserInformationRepository userInformationRepository;

    @Resource
    private HrInformationRepository hrInformationRepository;

    @Override
    public void sendUserMessage(Principal principal, MessageRecord messageRecord) {
        Set<ConstraintViolation<Object>> validate = Validation.buildDefaultValidatorFactory().getValidator()
                .validate(messageRecord);
        if (!validate.isEmpty()) {
            List<ErrorContent> errorContents = new ArrayList<>();
            for (ConstraintViolation<Object> constraintViolation : validate) {
                errorContents.add(new ErrorContent().setField(constraintViolation.getPropertyPath().toString())
                        .setDefaultMessage(constraintViolation.getMessage())
                        .setRejectedValue(constraintViolation.getInvalidValue()));
            }
            simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/error",
                    new ResponseBody<>().setStatus(HttpStatus.BAD_REQUEST.value()).setMessage(
                            HttpStatus.BAD_REQUEST.getReasonPhrase()).setErrors(errorContents));
        } else {
            if (onlineUserTemplate.opsForSet().isMember("onlineUser", messageRecord.getServiceId().toString())) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(System.currentTimeMillis()));
                calendar.add(Calendar.HOUR_OF_DAY, 8);
                simpMessagingTemplate.convertAndSendToUser(messageRecord.getServiceId().toString(), "/queue/message",
                        new ResponseBody<>().setStatus(HttpStatus.BAD_REQUEST.value()).setMessage(
                                HttpStatus.BAD_REQUEST.getReasonPhrase()).setBody(new ArrayList<>() {
                                    {
                                        add(messageRecord.setMessageRecordId(UUID.randomUUID())
                                                .setCreatedAt(calendar.getTime())
                                                .setUpdatedAt(calendar.getTime()));
                                    }
                                }));
            } else {
                if (messageRecord.getServiceType() == 1) {
                    Optional<UserInformation> userInformation = userInformationRepository
                            .findById(messageRecord.getServiceId());
                    if (userInformation.isPresent()) {
                        emailMessageUtil.sendEmail(userInformation.get().getEmail(), "东江招聘-消息提示", "您有一条新消息，请登录系统查收！");
                    } else {
                        List<ErrorContent> errorContents = new ArrayList<>();
                        errorContents.add(new ErrorContent().setField("serviceId").setDefaultMessage("用户不存在")
                                .setRejectedValue(messageRecord.getServiceId()));
                        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/error",
                                new ResponseBody<>().setStatus(HttpStatus.BAD_REQUEST.value()).setMessage(
                                        HttpStatus.BAD_REQUEST.getReasonPhrase()).setErrors(errorContents));
                    }
                } else if (messageRecord.getServiceType() == 2) {
                    Optional<HrInformation> hrInformation = hrInformationRepository
                            .findById(messageRecord.getServiceId());
                    if (hrInformation.isPresent()) {
                        emailMessageUtil.sendEmail(hrInformation.get().getAcceptEmail(), "东江招聘-消息提示",
                                "您有一条新消息，请登录系统查收！");
                    } else {
                        List<ErrorContent> errorContents = new ArrayList<>();
                        errorContents.add(new ErrorContent().setField("serviceId").setDefaultMessage("用户不存在")
                                .setRejectedValue(messageRecord.getServiceId()));
                        simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/error",
                                new ResponseBody<>().setStatus(HttpStatus.BAD_REQUEST.value()).setMessage(
                                        HttpStatus.BAD_REQUEST.getReasonPhrase()).setErrors(errorContents));
                    }
                }
                messageRecordRepository.save(messageRecord);
            }
        }
    }

}
