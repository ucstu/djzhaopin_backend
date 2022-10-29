package com.ucstu.guangbt.djzhaopin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

import com.ucstu.guangbt.djzhaopin.utils.ApplicationContextUtil;
import com.ucstu.guangbt.djzhaopin.utils.ApplicationInitUtil;

@EnableCaching // 启用缓存
@EnableJpaAuditing // 启用jpa审计功能
@SpringBootApplication // springboot应用
@EnableGlobalAuthentication // 启用全局认证
@EnableMongoRepositories(basePackages = "com.ucstu.guangbt.djzhaopin.entity")
public class DjzhaopinApplication {

	public static void main(String[] args) {
		ApplicationContextUtil.setApplicationContext(SpringApplication.run(DjzhaopinApplication.class, args));
		ApplicationContextUtil.getBean(ApplicationInitUtil.class).init();
	}

}
