package com.retailabc.microservices.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.RichShop;
import com.retailabc.microservices.models.Shop;
import com.retailabc.microservices.util.RandomUtils;

/**
 * 
 * Simple client send one request of each type
 * 
 * 
 * @author Min Li
 *
 */
@Component
public class ClientCommandRunner implements CommandLineRunner {
	static final Logger logger = LoggerFactory.getLogger(ClientCommandRunner.class);

	private static String serviceUrl = "http://localhost:";

	protected RestTemplate restTemplate = new RestTemplate();

	@Override
	public void run(String... args) throws Exception {

		initArguments(args);
		doWork();
	}

	private void initArguments(String[] args) {

		if (args.length == 2) {
			serviceUrl = serviceUrl + args[1];
		} else {
			serviceUrl = serviceUrl + "13002";
		}
		serviceUrl = serviceUrl + "/shops";

		logger.info("serviceurl= " + serviceUrl);
	}

	public void doWork() {
		findByName();
		findByLocation();
		addShop();

	}

	private void findByName() {

		String name = RandomUtils.getRandomName();
		logger.info("findByName() invoked: for " + name);
		RichShop shop = restTemplate.getForObject(serviceUrl + "/byname/{shopName}", RichShop.class, name);

		if (shop != null)
			logger.info("shop found =" + shop);
	}

	public void findByLocation() {
		GeoLocation location = RandomUtils.getRandomGeoLocation();

		logger.info("findByLocation() invoked: for " + location);

		String url = serviceUrl + "/bylocation/";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<GeoLocation> entity = new HttpEntity<>(location, headers);

		ResponseEntity<Object[]> response = restTemplate.postForEntity(url, entity, Object[].class);

		Object[] shops = response.getBody();

		for (Object shop : shops) {
			logger.info("shop " + shop);
		}

		logger.info("findByLocation() invoked: for " + location);

	}

	public void addShop() {
		Shop shop = RandomUtils.getRandomShop();

		logger.info("addShop() invoked: for " + shop);

		String url = serviceUrl + "/add/";

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Shop> entity = new HttpEntity<>(shop, headers);

		ResponseEntity<Shop> response = restTemplate.postForEntity(url, entity, Shop.class);

		Shop oldVersion = response.getBody();

		if (oldVersion != null)
			logger.info("oldVersion= " + oldVersion);

	}
}
