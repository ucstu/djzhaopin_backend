package com.ucstu.guangbt.djzhaopin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    // 创建一个RestTemplate实例。
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        return new RestTemplate(factory);
    }

    @Bean
    // 设置请求超时时间
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory(); // 创建一个普通的http请求工厂
        factory.setConnectTimeout(15000); // 设置连接超时
        factory.setReadTimeout(5000); // 设置读取超时
        return factory;
    }

}
