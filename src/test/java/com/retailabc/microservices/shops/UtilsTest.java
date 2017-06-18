package com.retailabc.microservices.shops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.Shop;
import com.retailabc.microservices.util.JsonUtils;
import com.retailabc.microservices.util.RandomUtils;

public class UtilsTest {
	static final Logger logger = LoggerFactory.getLogger(UtilsTest.class);

	@Ignore
	@Test
	public void generateRandomDouble() {
		logger.info("Start double test");

		for (int i = 0; i < 100; i++) {
			logger.info(new Double(RandomUtils.getRandomDouble(0, 90.0)).toString());
		}

	}

	@Test
	public void getShopJsonString() throws JsonProcessingException {
		logger.info("Start json test");

		Shop shop = RandomUtils.getRandomShop();

		String jsonShop = JsonUtils.getJsonString(shop);

		logger.info(jsonShop);

		GeoLocation location = RandomUtils.getRandomGeoLocation();

		String jsonLocation = JsonUtils.getJsonString(location);

		logger.info(jsonLocation);

	}

}
