package com.retailabc.microservices.shops;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.Shop;

/**
 * 
 * This class is a implementation of GeoService
 * 
 * It keeps a sorted map of locations to speed up filtering out the locations
 * within a radius range. The algorithm is hugely simplified as in reality you
 * would rely on a specialised lib to do the job.
 * 
 * The name to location map is also kept to provide fast search location by name
 * service
 * 
 * 
 * @author Min Li
 *
 */

@Service
public class GeoServiceSimple implements GeoService {
	static final Logger logger = LoggerFactory.getLogger(GeoServiceSimple.class);

	@Autowired
	private GoogleMapService googleMapService;

	private NavigableMap<GeoLocation, String> locationToName = new ConcurrentSkipListMap<>();
	private Map<String, GeoLocation> nameToLocation = new ConcurrentHashMap<>();

	@Override
	public Set<String> getShopNamesWithInRadius(GeoLocation location, int radius) {
		GeoLocation higherKey = getHigherKey(location, radius);
		GeoLocation lowerKey = getLowerKey(location, radius);

		Map<GeoLocation, String> filteredLocations = locationToName.subMap(lowerKey, higherKey);

		return Collections.unmodifiableSet(new HashSet<String>(filteredLocations.values()));
	}

	@Override
	public GeoLocation getLocationByShopName(String name) {
		return nameToLocation.get(name);
	}

	@Override
	public void update(Observable o, Object arg) {
		Shop shop = (Shop) arg;

		GeoLocation geoLocation = googleMapService.getLocationByAddress(shop.getAddress());

		locationToName.put(geoLocation, shop.getName());

		nameToLocation.put(shop.getName(), geoLocation);

		// logger.info(" Location added for: " + shop.getName() + "
		// geoLocation=" + geoLocation);

	}

	private GeoLocation getHigherKey(GeoLocation location, int radius) {
		return new GeoLocation(location.getLongitude() + radius, location.getLatitude() + radius);
	}

	private GeoLocation getLowerKey(GeoLocation location, int radius) {
		return new GeoLocation(location.getLongitude() - radius, location.getLatitude() - radius);
	}

}
