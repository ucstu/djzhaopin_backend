package com.ucstu.guangbt.djzhaopin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@EnableJpaAuditing
@SpringBootApplication
@EnableGlobalAuthentication
public class DjzhaopinApplication {

	public static void main(String[] args) {
		SpringApplication.run(DjzhaopinApplication.class, args);
	}

}
