package com.retailabc.microservices.services.shops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import com.retailabc.microservices.shops.ShopsConfiguration;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * 
 * @author Min Li
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(ShopsConfiguration.class)
public class ShopsServer {

	static final Logger logger = LoggerFactory.getLogger(ShopsServer.class);

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for shops-server.yml
		System.setProperty("spring.config.name", "shops-server");

		SpringApplication.run(ShopsServer.class, args);
	}
}
