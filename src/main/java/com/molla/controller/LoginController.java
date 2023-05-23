package com.molla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.molla.model.Account;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String loginForm() {
		return "client/user/login";
	}

	@GetMapping("/forgot")
	public String forgotForm() {
		return "client/user/forgotForm";
	}

	@RequestMapping("/confirmPassword")
	public String confirmPassword() {
		return "client/user/confirmPassword";
	}
}
