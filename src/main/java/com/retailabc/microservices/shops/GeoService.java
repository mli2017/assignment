package com.retailabc.microservices.shops;

import java.util.Observer;
import java.util.Set;

import com.retailabc.microservices.models.GeoLocation;

/**
 * 
 * Geo location service, an observer to shop service and is notified when a new
 * shop added.
 * 
 * @author Min Li
 *
 */
public interface GeoService extends Observer {
	/**
	 * Return name of shops within proximity of given location
	 * 
	 * @param location
	 * @param radius
	 * @return
	 */
	Set<String> getShopNamesWithInRadius(GeoLocation location, int radius);

	/**
	 * 
	 * Get GeoLocation for a shop by name
	 * 
	 * @param name
	 * @return
	 */
	GeoLocation getLocationByShopName(String name);
}
