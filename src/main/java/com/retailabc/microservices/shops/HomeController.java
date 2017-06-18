package com.retailabc.microservices.shops;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller.
 * 
 * @author Min Li
 */
@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}

}
