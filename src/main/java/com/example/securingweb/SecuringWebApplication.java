package com.example.securingweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecuringWebApplication {

	public static void main(String[] args) throws Throwable {
		System.setProperty("javax.net.debug", "ssl:handshake");
		SpringApplication.run(SecuringWebApplication.class, args);
	}

}
