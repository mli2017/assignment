package com.retailabc.microservices.shops;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.Shop;

/**
 * A RESTFul controller for shop service.
 * 
 * @author Min Li
 */
@RestController
public class ShopsController {

	static final Logger logger = LoggerFactory.getLogger(ShopsController.class);

	@Autowired
	protected ShopService shopService;

	/**
	 * 
	 * Fetch shop by name
	 * 
	 * @param shopName
	 * @return
	 */
	@RequestMapping("/shops/byname/{shopName}")
	public Shop byName(@PathVariable("shopName") String shopName) {

		logger.info("shops-service byName() invoked: " + shopName);

		Shop shop = shopService.findByName(shopName);

		logger.info("shops-service byName() found: " + shop);

		return shop;

	}

	/**
	 * 
	 * Fetch shops by location
	 * 
	 * @param shopName
	 * @return
	 */
	@RequestMapping(value = "/shops/bylocation/", method = RequestMethod.POST)
	public List<Shop> byLocation(@RequestBody GeoLocation location) {

		logger.info("shops-service byLocation() invoked: " + location);

		List<Shop> shops = shopService.findByLocation(location);

		logger.info("shops-service byLocation() found: " + shops.size());

		return shops;

	}

	/**
	 * Add or update a shop
	 * 
	 * @param shop
	 * @return
	 */
	@RequestMapping(value = "/shops/add/", method = RequestMethod.POST)
	public Shop addShop(@RequestBody Shop shop) {

		logger.info("shops-service addShop() invoked: " + shop);

		Shop oldVersion = shopService.addShop(shop);

		if (oldVersion != null)
			logger.info("shops-service addShop(), old version exists: " + oldVersion);

		return oldVersion;

	}

}
