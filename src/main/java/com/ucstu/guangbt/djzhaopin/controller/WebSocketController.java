package com.ucstu.guangbt.djzhaopin.controller;

import java.security.Principal;

import javax.annotation.Resource;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.service.MessageRecordService;

@Controller
public class WebSocketController {

    @Resource
    private MessageRecordService messageRecordService;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    public void sendUserMessage(Principal principal, MessageRecord messageRecord) {
        messageRecordService.sendUserMessage(principal, messageRecord);
    }

}
