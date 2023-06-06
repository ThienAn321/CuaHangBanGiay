package com.molla.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminProductManagement {
	
	@RequestMapping("/admin/product")
	public String productManagement() {
		return "admin/product/product-table";
	}
	
	@RequestMapping("/admin/product/add")
	public String productAddManagement() {
		return "admin/product/product-add";
	}
}
