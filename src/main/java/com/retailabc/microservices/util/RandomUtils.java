package com.retailabc.microservices.util;

import java.util.concurrent.ThreadLocalRandom;

import com.retailabc.microservices.models.Address;
import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.Shop;

public class RandomUtils {
	private static ThreadLocalRandom random = ThreadLocalRandom.current();

	public static GeoLocation getRandomGeoLocation() {
		double longitude = getRandomDouble(-180, 180);
		double latitude = getRandomDouble(-90, 90);

		return new GeoLocation(longitude, latitude);
	}

	public static Shop getRandomShop() {
		String name = getRandomName(400, 1000);
		return new Shop(name, getRandomAddress());
	}

	public static String getRandomName() {
		return getRandomName(1, 500);
	}

	public static String getRandomName(int min, int max) {
		int randomInt = getRandomInt(min, max);
		return "Shop" + String.format("%04d", randomInt);
	}

	public static Address getRandomAddress() {

		return new Address(getRandomInt(1, 200), getRandomPostCode());
	}

	private static String getRandomPostCode() {
		StringBuilder sb = new StringBuilder();

		sb.append(getRandom2Chars());
		sb.append(getRandomInt(1, 20));
		sb.append(" ");
		sb.append(getRandomInt(1, 10));
		sb.append(getRandom2Chars());

		return sb.toString();
	}

	public static double getRandomDouble(double rangeMin, double rangeMax) {

		double randomDouble = rangeMin + (rangeMax - rangeMin) * random.nextDouble();

		return randomDouble;

	}

	public static int getRandomInt(int min, int max) {

		return random.nextInt(min, max + 1);

	}

	private static String getRandom2Chars() {

		String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		int rand1 = getRandomInt(0, validChars.length() - 1);
		int rand2 = getRandomInt(0, validChars.length() - 1);

		return "" + validChars.charAt(rand1) + validChars.charAt(rand2);

	}
}
