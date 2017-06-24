package com.retailabc.microservices.shops;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import com.retailabc.microservices.exceptions.ShopNotFoundException;
import com.retailabc.microservices.models.Address;
import com.retailabc.microservices.models.GeoLocation;
import com.retailabc.microservices.models.RichShop;
import com.retailabc.microservices.models.RichShop;
import com.retailabc.microservices.models.Shop;
import com.retailabc.microservices.services.shops.ShopsServer;
import com.retailabc.microservices.util.RandomUtils;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringJUnit4ClassRunner.class)
// @SpringBootTest(classes = ShopsServer.class)
@WebAppConfiguration
@ContextConfiguration(classes = ShopsConfiguration.class, loader =  AnnotationConfigWebContextLoader.class)
@Ignore
public class ShopServiceControllerTests {

	static final Logger logger = LoggerFactory.getLogger(ShopServiceControllerTests.class);

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	ShopService shopService;

	/*@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}*/

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getShops() throws Exception {
		GeoLocation location=RandomUtils.getRandomGeoLocation();
		String request=getLocationSearchUrl(location);
		ResultActions resultActions =mockMvc.perform(get(request));
		resultActions.andDo(MockMvcResultHandlers.print());
	}
	
	private String getLocationSearchUrl(GeoLocation location) {
		StringBuilder sb = new StringBuilder();

		sb.append("/shops/search?");
		sb.append("longitude=" + location.getLongitude());
		sb.append("&");
		sb.append("latitude=" + location.getLatitude());

		return sb.toString();

	}

}
