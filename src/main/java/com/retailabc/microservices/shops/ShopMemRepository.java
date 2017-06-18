package com.retailabc.microservices.shops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Repository;

import com.retailabc.microservices.models.Shop;

/**
 * Repository for Shop data - data only kept in memory for simplicity.
 * 
 * @author Min Li
 */
@Repository
public class ShopMemRepository implements ShopRepository {
	static final Logger logger = LoggerFactory.getLogger(ShopMemRepository.class);
	private Map<String, Shop> shops = new ConcurrentHashMap<>();

	public Shop add(Shop shop) {
		logger.info(" Add " + shop);
		return shops.put(shop.getName(), shop);
	}

	public Shop getByName(String name) {
		return shops.get(name);
	}

	public List<Shop> getAllShops() {
		return Collections.unmodifiableList(new ArrayList<Shop>(shops.values()));
	}

}
