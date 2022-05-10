package com.ucstu.guangbt.djzhaopin.config;

import java.util.List;
import java.util.UUID;

import com.ucstu.guangbt.djzhaopin.entity.util.MessageRecord;
import com.ucstu.guangbt.djzhaopin.model.ResponseBody;
import com.ucstu.guangbt.djzhaopin.repository.MessageRecordRepository;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import jakarta.annotation.Resource;

@Component
public class STOMPConnectEventListener implements ApplicationListener {

    @Resource
    private RedisTemplate<String, String> onlineUserTemplate;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @Resource
    private MessageRecordRepository messageRecordRepository;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof SessionConnectEvent) {
            SessionConnectEvent connectEvent = (SessionConnectEvent) event;
            StompHeaderAccessor accessor = StompHeaderAccessor.wrap(connectEvent.getMessage());
            if (accessor.getUser() != null) {
                onlineUserTemplate.opsForSet().add("onlineUser", accessor.getUser().getName());
            }
        } else if (event instanceof SessionSubscribeEvent) {
            SessionSubscribeEvent subscribeEvent = (SessionSubscribeEvent) event;
            StompHeaderAccessor accessor = StompHeaderAccessor.wrap(subscribeEvent.getMessage());
            if (accessor.getDestination().equals("/user/queue/message") && accessor.getUser() != null) {
                List<MessageRecord> messageRecords = messageRecordRepository.findByServiceId(UUID.fromString(
                        accessor.getUser().getName()));
                simpMessagingTemplate.convertAndSendToUser(accessor.getUser().getName(), "/queue/message",
                        new ResponseBody<>().setStatus(HttpStatus.OK.value()).setMessage(
                                HttpStatus.OK.getReasonPhrase()).setBody(messageRecords));
                messageRecordRepository.deleteAll(messageRecords);
            }
        } else if (event instanceof SessionDisconnectEvent) {
            SessionDisconnectEvent disconnectEvent = (SessionDisconnectEvent) event;
            StompHeaderAccessor accessor = StompHeaderAccessor.wrap(disconnectEvent.getMessage());
            if (accessor.getUser() != null) {
                onlineUserTemplate.opsForSet().remove("onlineUser", accessor.getUser().getName());
            }
        }
    }

}
