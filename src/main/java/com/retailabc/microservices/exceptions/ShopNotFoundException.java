package com.retailabc.microservices.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Shop not found exception
 * 
 * @author Min Li
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShopNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShopNotFoundException(String shopName) {
		super("This shop does not exist: " + shopName);
	}
}
