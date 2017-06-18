package com.retailabc.microservices.shops;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The shops Spring configuration.
 * 
 * @author Min Li
 */
@Configuration
@ComponentScan
public class ShopsConfiguration {

	@Autowired
	private ShopServiceSimple shopServiceSimple;

	@Bean
	public ShopService shopService() {

		// preload some test data
		shopServiceSimple.initialize(500);

		return shopServiceSimple;
	}
}
