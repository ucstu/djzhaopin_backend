package com.ucstu.guangbt.djzhaopin;

import com.ucstu.guangbt.djzhaopin.utils.ApplicationContextUtil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@EnableJpaAuditing
@SpringBootApplication
@EnableGlobalAuthentication
public class DjzhaopinApplication {

	public static void main(String[] args) {
		ApplicationContextUtil.setApplicationContext(SpringApplication.run(DjzhaopinApplication.class, args));
	}

}
