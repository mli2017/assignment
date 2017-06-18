package com.retailabc.microservices.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Address DTO 
 * 
 * @author Min Li
 */
public class Address {
	private final int number;
    private final String postCode;
    
    	
    @JsonCreator
    public Address(@JsonProperty("number")int number, 
    		@JsonProperty("postCode")String postCode) {
		super();
		this.number = number;
		this.postCode = postCode;
	}

	public int getNumber() {
		return number;
	}

	public String getPostCode() {
		return postCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (number != other.number)
			return false;
		if (postCode == null) {
			if (other.postCode != null)
				return false;
		} else if (!postCode.equals(other.postCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Address [number=" + number + ", postCode=" + postCode + "]";
	}

}
