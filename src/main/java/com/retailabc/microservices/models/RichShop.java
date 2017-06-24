package com.retailabc.microservices.models;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RichShop DTO - shop with extended feature
 * 
 * @author Min Li
 */
public class RichShop extends Shop {

	private Map<String, String> features;

	@JsonCreator
	public RichShop(@JsonProperty("name") String name, @JsonProperty("address") Address address) {
		super(name, address);
		features = new HashMap<>();
	}

	public RichShop(Shop shop) {
		super(shop.getName(), shop.getAddress());
		features = new HashMap<>();
	}

	@JsonAnyGetter
	public Map<String, String> getFeatures() {
		return features;
	}

	public void addFeature(String name, String value) {
		features.put(name, value);
	}

	public String getFeature(String name) {
		return features.get(name);
	}

	@Override
	public String toString() {
		return "RichShop2 " + super.toString() + "[features=" + features + "]";
	}

}
