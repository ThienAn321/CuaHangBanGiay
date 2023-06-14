package com.molla.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminCategoryManagement {
	
	@RequestMapping("/admin/category")
	public String categoryManagement() {
		return "admin/category/category-table";
	}
	
	@RequestMapping("/admin/category/add")
	public String categoryAddManagement() {
		return "admin/category/category-add";
	}
}
