package com.retailabc.microservices.services;

import com.retailabc.microservices.client.ClientApplication;
import com.retailabc.microservices.services.registration.RegistrationServer;
import com.retailabc.microservices.services.shops.ShopsServer;

/**
 * Allow the servers to be invoked from the command-line.
 * 
 * @author Min Li
 */
public class Main {

	public static void main(String[] args) {

		String serverName = "";

		switch (args.length) {
		case 1:
		case 2:
			serverName = args[0].toLowerCase();
			break;

		default:
			usage();
			return;
		}

		if (serverName.equals("registration")) {
			RegistrationServer.main(args);
		} else if (serverName.equals("shops")) {
			ShopsServer.main(args);
		} else if (serverName.equals("client")) {
			ClientApplication.main(args);
		} else {
			System.out.println("Unknown server type: " + serverName);
			usage();
		}
	}

	protected static void usage() {
		System.out.println("Usage: java -jar ... <server-name> <port>");
		System.out.println("  where server-name is 'registration', " + "'shops' or 'client' and port (for client only) ");
	}
}
