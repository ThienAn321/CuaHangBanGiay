package com.molla.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminLoginController {
	
	@RequestMapping("/loginAdmin")
	public String loginAdminForm() {
		return "admin/user/login";
	}
}
