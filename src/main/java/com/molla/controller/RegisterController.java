package com.molla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.molla.model.Account;
import com.molla.model.Role;
import com.molla.service.AccountService;
import com.molla.service.RoleService;

@Controller
public class RegisterController {
	@Autowired
	AccountService accountService;

	@Autowired
	RoleService roleService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("account", new Account()); 
		return "client/user/register";
	}

	@PostMapping("/createUser")
	public String createUser(Model model, @Validated @ModelAttribute("account") Account user, Errors errors) {
		Role userRole = roleService.getRole("USER");
		boolean check = accountService.checkUsername(user.getUsername());
		
		if(errors.hasErrors()) {
			return "client/user/register";
		}
		
		if(check) {
			model.addAttribute("message","Tài khoản này đã có người sử dụng !");
		}
		
		if (user.getPassword().equals(user.getCPassword())) {
			// mã hóa mật khẩu
			String password = passwordEncoder.encode(user.getPassword());
			String cPassword = passwordEncoder.encode(user.getCPassword());
			
			// set role mặc định là User
			user.setRole(userRole);
			
			user.setPassword(password);
			user.setCPassword(cPassword);
			
			Account account = accountService.createUser(user);
			model.addAttribute("message","Chúc mừng bạn đã đăng kí thành công !");
			return "client/user/register";
		} else {
			model.addAttribute("message","Mật khẩu và mật khẩu xác nhận không giống nhau !");
			return "client/user/register";
		}
	}
}
