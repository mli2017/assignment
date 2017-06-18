package com.retailabc.microservices.shops;

import java.util.List;

import com.retailabc.microservices.exceptions.ShopNotFoundException;
import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.Shop;

/**
 * 
 * Act a a Facade to ShopRepository and GeoService, provide simple interface to
 * end user.
 * 
 * @author Min Li
 *
 */
public interface ShopService {
	/**
	 * Add a shop to shop service, if shop of the same name exists, old version
	 * shop is returned, otherwise null return
	 * 
	 * @param shop
	 * @return
	 */
	Shop addShop(Shop shop);

	/**
	 * 
	 * Find shops within default radius miles of given location
	 * 
	 * @param location
	 * @param radius
	 * @return
	 */
	List<Shop> findByLocation(GeoLocation location);

	/**
	 * 
	 * Find shops within radius miles of given location
	 * 
	 * @param location
	 * @param radius
	 * @return
	 */
	List<Shop> findByLocation(GeoLocation location, int radius);

	/**
	 * 
	 * Find a shop by name
	 * 
	 * @param shopName
	 * @return
	 * @throws ShopNotFoundException
	 */
	Shop findByName(String shopName) throws ShopNotFoundException;

}
