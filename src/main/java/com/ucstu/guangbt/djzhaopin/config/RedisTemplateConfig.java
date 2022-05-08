package com.ucstu.guangbt.djzhaopin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, String> verificationCodeTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> verificationCodeTemplate = new RedisTemplate<>();
        verificationCodeTemplate.setKeySerializer(new StringRedisSerializer());
        verificationCodeTemplate.setValueSerializer(new StringRedisSerializer());
        verificationCodeTemplate.setConnectionFactory(redisConnectionFactory);
        return verificationCodeTemplate;
    }

    @Bean
    public RedisTemplate<String, String> onlineUserTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> onlineUserTemplate = new RedisTemplate<>();
        onlineUserTemplate.setKeySerializer(new StringRedisSerializer());
        onlineUserTemplate.setValueSerializer(new StringRedisSerializer());
        onlineUserTemplate.setConnectionFactory(redisConnectionFactory);
        return onlineUserTemplate;
    }

}
