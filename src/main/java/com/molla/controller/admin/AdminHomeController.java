package com.molla.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminHomeController {
	
	@RequestMapping("/admin/home")
	public String home() {
		return "admin/layout/main";
	}
	
	@RequestMapping("/admin")
	public String home2() {
		return "redirect:/admin/home";
	}
}
