package com.retailabc.microservices.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Shop DTO - represent a shop, identified by name
 * 
 * @author Min Li
 */
@JsonRootName("Shop")
public class Shop {
	private final String name;
	private final Address address;
	
		
	@JsonCreator
	public Shop(@JsonProperty("name")String name, 
			@JsonProperty("address")Address address) {
		super();
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shop other = (Shop) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Shop [name=" + name + ", address=" + address + "]";
	}

}
