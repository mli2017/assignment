package com.retailabc.microservices.client;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ClientApplication.class).web(false).run(args);
	}

}
