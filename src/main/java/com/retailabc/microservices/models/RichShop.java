package com.retailabc.microservices.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RichShop DTO - shop with extended feature
 * 
 * @author Min Li
 */
public class RichShop extends Shop {

	private GeoLocation location;

	
	@JsonCreator
	public RichShop(@JsonProperty("name")String name, 
			@JsonProperty("address")Address address) {
		super(name, address);
	}

	public RichShop(Shop shop) {
		super(shop.getName(), shop.getAddress());
	}

	public GeoLocation getLocation() {
		return location;
	}

	public void setLocation(GeoLocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return super.toString() + " [geolocation=" + location + "]";
	}

}
