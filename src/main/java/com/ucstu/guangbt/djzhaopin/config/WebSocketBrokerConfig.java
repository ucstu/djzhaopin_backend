package com.ucstu.guangbt.djzhaopin.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.ucstu.guangbt.djzhaopin.utils.JsonWebTokenUtil;

@Configuration
@EnableWebSocketMessageBroker // 启用WebSocket消息代理
/**
 * 配置消息代理和 stomp 端点
 */
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Resource
    private JsonWebTokenUtil jsonWebTokenUtil;

    @Override
    // 配置消息代理
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler(); // 创建一个线程池
        taskScheduler.setPoolSize(10); // 设置线程池大小
        taskScheduler.setThreadNamePrefix("task-stomp-heartbeat-"); // 设置线程名称前缀
        taskScheduler.initialize(); // 初始化线程池
        registry.enableSimpleBroker("/topic", "/queue").setHeartbeatValue(new long[] { 10000, 10000 })
                .setTaskScheduler(taskScheduler); // 配置消息代理
    }

    @Override
    // 配置stomp端点
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*");
        registry.addEndpoint("/ws").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    // 配置消息处理器
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() { // 配置消息拦截器

            @Override
            // 设置消息拦截器
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class); // 获取消息头访问器
                if (StompCommand.CONNECT.equals(accessor.getCommand())) {
                    String param = accessor.getFirstNativeHeader("Authorization");
                    if (StringUtils.hasLength(param)) { // 如果消息头中包含Authorization字段
                        String token = StringUtils.delete(param, "Bearer "); // 删除Bearer字段
                        if (jsonWebTokenUtil.validateToken(token)) { // 验证token是否有效
                            CustomUserDetails userDetails = jsonWebTokenUtil.getCustomUserDetailsFromToken(token); // 获取用户信息
                            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( // 创建认证信息
                                    userDetails, null, userDetails.getAuthorities());
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            accessor.setUser(() -> { // 设置用户
                                return userDetails.getJsonWebToken().getFullInformationId().toString();
                            });
                        }
                    }
                }
                return message;
            }

        });
    }

}
