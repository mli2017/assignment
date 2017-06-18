package com.retailabc.microservices.shops;

import com.retailabc.microservices.models.Shop;
/**
 * 
 * Repository to keep user submitted shop data.
 * 
 * @author Min Li
 *
 */
public interface ShopRepository {
	/**
	 * Add a shop, if shop of same name exists, return previous version Shop
	 * otherwise, return null
	 * 
	 * @param shop
	 * @return
	 */
	public Shop add(Shop shop);
	
	/**
	 * Find a shop by name
	 * 
	 * @param name
	 * @return
	 */
	public Shop getByName(String name);
}
