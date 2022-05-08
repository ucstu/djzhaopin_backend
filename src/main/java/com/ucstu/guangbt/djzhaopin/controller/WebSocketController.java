package com.ucstu.guangbt.djzhaopin.controller;

import java.security.Principal;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    /**
     * 点对点发送消息，将消息发送到指定用户
     */
    @MessageMapping("/message")
    @SendToUser("/queue/message")
    public MessageRecord sendUserMessage(Principal principal, @Payload MessageRecord messageRecord) {
        return messageRecord;
    }

}
