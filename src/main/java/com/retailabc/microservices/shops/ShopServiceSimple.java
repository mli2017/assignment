package com.retailabc.microservices.shops;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.retailabc.microservices.exceptions.ShopNotFoundException;
import com.retailabc.microservices.models.Address;
import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.RichShop;
import com.retailabc.microservices.models.Shop;

@Component
public class ShopServiceSimple extends Observable implements ShopService {
	private static final int DEFAULT_RADIUS = 10;

	@Autowired
	private GeoService geoService;

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public Shop addShop(Shop shop) {
		Shop oldShop = shopRepository.add(shop);

		// In real scenario, you may just drop a message and let the interested
		// party deal with it asynchronously
		setChanged();
		notifyObservers(shop);

		return oldShop;
	}

	@Override
	public List<Shop> findByLocation(GeoLocation location) {
		return findByLocation(location, DEFAULT_RADIUS);
	}

	@Override
	public List<Shop> findByLocation(GeoLocation location, int radius) {
		Set<String> shopNames = geoService.getShopNamesWithInRadius(location, radius);
		List<Shop> shops = new ArrayList<>();

		if (shopNames.size() > 0) {

			for (String shopName : shopNames)
				shops.add(findByName(shopName));

		}

		return shops;

	}

	@Override
	public Shop findByName(String shopName) throws ShopNotFoundException {
		if (shopName == null || shopName.isEmpty())
			throw new ShopNotFoundException(shopName);

		Shop shop = shopRepository.getByName(shopName);

		if (shop == null)
			throw new ShopNotFoundException(shopName);

		return enRichShop(shop);

	}

	private RichShop enRichShop(Shop shop) {
		RichShop richShop = new RichShop(shop);

		GeoLocation geoLocation = geoService.getLocationByShopName(shop.getName());

		if (geoLocation != null)
			richShop.setLocation(geoLocation);

		return richShop;
	}

	public void initialize(int totalNumber) {
		for (int i = 1; i < totalNumber + 1; i++) {
			String name = "Shop" + String.format("%04d", i);
			addShop(new Shop(name, new Address(i, "AB1 2DE")));
		}
	}

	@PostConstruct
	public void initObservers() {
		this.addObserver(geoService);
	}

}
