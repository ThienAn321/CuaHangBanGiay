package com.molla.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminAccountManagement {
	
	@RequestMapping("/admin/user")
	public String productManagement() {
		return "admin/user/user-table";
	}
}
