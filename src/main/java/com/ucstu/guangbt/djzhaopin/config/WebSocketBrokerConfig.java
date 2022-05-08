package com.ucstu.guangbt.djzhaopin.config;

import com.ucstu.guangbt.djzhaopin.utils.JsonWebTokenUtil;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Resource
    private JsonWebTokenUtil jsonWebTokenUtil;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/queue");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {

            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String param = accessor.getFirstNativeHeader("Authorization");
                    if (StringUtils.hasLength(param)) {
                        String token = StringUtils.delete(param, "Bearer ");
                        if (jsonWebTokenUtil.validateToken(token)) {
                            CustomUserDetails userDetails = jsonWebTokenUtil.getCustomUserDetailsFromToken(token);
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            accessor.setUser(() -> {
                                return userDetails.getJsonWebToken().getFullInformationId().toString();
                            });
                        }
                    }
                }
                return message;
            }

        });
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.addDecoratorFactory((webSocketHandler) -> new WebSocketHandlerDecorator(webSocketHandler) {

            @Override
            public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                // 输出进行 websocket 连接的用户信息
                super.afterConnectionEstablished(session);
                if (session.getPrincipal() != null) {
                    String username = session.getPrincipal().getName();
                    log.info("用户 {} 已连接", username);
                }
            }

            @Override
            public void afterConnectionClosed(final WebSocketSession session, CloseStatus closeStatus)
                    throws Exception {
                // 输出关闭 websocket 连接的用户信息
                super.afterConnectionClosed(session, closeStatus);
                if (session.getPrincipal() != null) {
                    String username = session.getPrincipal().getName();
                    log.info("用户 {} 已断开", username);
                }
            }
        });
    }

}
