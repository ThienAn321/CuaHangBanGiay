package com.molla.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CartController {
	@RequestMapping("/cart")
	public String cart() {
		return "client/cart/cart";
	}
	
	@RequestMapping("/checkout")
	public String checkout() {
		return "client/cart/checkout";
	}
}
