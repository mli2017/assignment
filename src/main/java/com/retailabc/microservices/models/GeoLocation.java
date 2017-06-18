package com.retailabc.microservices.models;

import java.math.BigDecimal;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * GeoLocation DTO
 * 
 * @author Min Li
 */
public class GeoLocation implements Comparable<GeoLocation> {
	private static final String SEPARATOR = ",";
	private final double longitude;
	private final double latitude;

	

	@JsonCreator
	public GeoLocation(@JsonProperty("longitude")double longitude,
			@JsonProperty("latitude")double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public GeoLocation(String value) {
		String[] location = value.split(SEPARATOR);
		this.longitude = Double.parseDouble(location[0]);
		this.latitude = Double.parseDouble(location[1]);
	}

	public int compareTo(GeoLocation loc1) {
		int result = compareDouble(this.longitude, loc1.longitude);

		if (result == 0)
			return compareDouble(this.latitude, loc1.latitude);
		else
			return result;

	}

	private int compareDouble(double d1, double d2) {
		return BigDecimal.valueOf(d1).compareTo(BigDecimal.valueOf(d2));
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	@Override
	public int hashCode() {

		return Objects.hash(longitude, latitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GeoLocation other = (GeoLocation) obj;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return longitude + SEPARATOR + latitude;
	}

}
