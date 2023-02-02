package com.example.securingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories("com.example.securingweb.certificate")
//@ComponentScan(basePackages = { "com.example.securingweb.certificate" })
//@EntityScan("com.example.securingweb.certificate.*")
@SpringBootApplication
public class SecuringWebApplication {

	public static void main(String[] args) throws Throwable {
		System.setProperty("javax.net.debug", "ssl:handshake");

		SpringApplication.run(SecuringWebApplication.class, args);
	}

}
