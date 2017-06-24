package com.retailabc.microservices.shops;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.retailabc.microservices.exceptions.ShopNotFoundException;
import com.retailabc.microservices.models.Address;
import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.RichShop;
import com.retailabc.microservices.models.Shop;
import com.retailabc.microservices.shops.ShopServiceSimple.FEATURE;

@RunWith(SpringJUnit4ClassRunner.class)
// ApplicationContext will be loaded from the ShopsConfiguration class
@ContextConfiguration(classes = ShopsConfiguration.class, loader = AnnotationConfigContextLoader.class)
public class ShopServiceTests {

	static final Logger logger = LoggerFactory.getLogger(ShopServiceTests.class);

	@Autowired
	ShopService shopService;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void validateAddShop() {
		logger.info("Start validAddShop test");

		Shop shop1 = new Shop("shopA", new Address(1, "AB1 2DE"));

		Shop oldShop1 = shopService.addShop(shop1);

		Assert.assertNull(oldShop1);

		Shop shop2 = new Shop("shopA", new Address(2, "AB1 2DE"));
		Shop oldShop2 = shopService.addShop(shop2);

		Assert.assertNotNull(oldShop2);

		logger.info("End validAddShop test");
	}

	@Test
	public void validateShopNotFoundThrown() {

		logger.info("Start validShopNotFoundThrown test");
		String shopName = "XXX";

		exception.expect(ShopNotFoundException.class);

		shopService.findByName(shopName);

		logger.info("End validShopNotFoundThrown test");
	}

	@Test
	public void validateAddAndGetShop() {

		logger.info("Start validAddShop test");
		String shopName = "shopA";

		Shop shop1 = new Shop(shopName, new Address(1, "AB1 2DE"));

		shopService.addShop(shop1);

		RichShop rshop = (RichShop) shopService.findByName(shopName);

		Assert.assertNotNull(rshop);

		logger.info("shop is " + rshop);
	}

	@Test
	public void validateGetNearestShop() {

		logger.info("Start validGetNearestShop test");

		String shopName = "Shop0001";

		RichShop rshop = (RichShop) shopService.findByName(shopName);

		Assert.assertNotNull(rshop);

		String locations = rshop.getFeature(FEATURE.GEOLOCATION.name().toLowerCase());

		logger.info("location is " + locations);

		GeoLocation location = new GeoLocation(locations);

		List<Shop> shops = shopService.findByLocation(location, 10);

		for (Shop shop : shops)
			logger.info("shop is " + shop);

	}

}
