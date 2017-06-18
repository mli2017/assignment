package com.retailabc.microservices.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static String getJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		// Object to JSON in String
		return mapper.writeValueAsString(object);
	}
}
