package com.retailabc.microservices.shops;

import com.retailabc.microservices.models.Address;
import com.retailabc.microservices.models.GeoLocation;

/**
 * 
 * Very simple interface to simulate Google Map API service
 * 
 * @author Min Li
 *
 */
public interface GoogleMapService {
	/**
	 * Get the Geo Location for a given address
	 * 
	 * @param address
	 * @return
	 */
	GeoLocation getLocationByAddress(Address address);
}
