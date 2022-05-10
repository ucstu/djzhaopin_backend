package com.ucstu.guangbt.djzhaopin.controller;

import java.security.Principal;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.service.MessageRecordService;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import jakarta.annotation.Resource;

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

    @Scheduled(fixedDelay = 20000L)
    @SendTo("/topic/pingpong")
    public void sendPong() {
        simpMessagingTemplate.convertAndSend("/topic/pingpong", "pong (periodic)");
    }

    @MessageMapping("/ping")
    @SendTo("/topic/pingpong")
    public String sendPingResponse() {
        return "pong (response)";
    }

}
