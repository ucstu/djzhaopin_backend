package com.ucstu.guangbt.djzhaopin.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.model.ErrorContent;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.repository.MessageRecordRepository;
import com.ucstu.guangbt.djzhaopin.service.MessageRecordService;

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
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private MessageRecordRepository messageRecordRepository;

    @Override
    public void sendUserMessage(Principal principal, MessageRecord messageRecord) {
        Set<ConstraintViolation<Object>> validate = Validation.buildDefaultValidatorFactory().getValidator()
                .validate(messageRecord);
        if (validate != null) {
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
            simpMessagingTemplate.convertAndSendToUser(messageRecord.getServiceId().toString(), "/queue/message",
                    new ResponseBody<>().setStatus(HttpStatus.BAD_REQUEST.value()).setMessage(
                            HttpStatus.BAD_REQUEST.getReasonPhrase()).setBody(new ArrayList<>() {
                                {
                                    add(messageRecord);
                                }
                            }));
        }
    }

}
