package com.retailabc.microservices.shops;

import org.springframework.stereotype.Service;

import com.retailabc.microservices.models.Address;
import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.util.RandomUtils;

/**
 * 
 * Simulate Google Map service, although address is passed in, it is not used. A
 * random value of longitude and latitude is returned.
 * 
 * @author Min Li
 *
 */
@Service
public class GoogleMapServiceSimple implements GoogleMapService {

	@Override
	public GeoLocation getLocationByAddress(Address address) {
		return RandomUtils.getRandomGeoLocation();
	}

}
