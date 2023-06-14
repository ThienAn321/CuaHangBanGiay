package com.molla.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminOrderManagement {
	
	@RequestMapping("/admin/order")
	public String orderManagement() {
		return "admin/order/order-table";
	}
	
}
