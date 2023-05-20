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
import org.springframework.web.bind.annotation.RequestParam;

import com.molla.model.Account;
import com.molla.model.Role;
import com.molla.service.AccountService;
import com.molla.service.RoleService;

@Controller
public class RegisterController {
	@Autowired
	AccountService accountService;

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		return "client/user/register";
	}

	@PostMapping("/createUser")
	public String createUser(Model model, @Validated @ModelAttribute("account") Account user, Errors errors) {
		boolean check = accountService.checkUsername(user.getUsername());

		if (errors.hasErrors()) {
			return "client/user/register";
		}

		if (check) {
			model.addAttribute("message", "Tài khoản này đã có người sử dụng !");
		}

		if (user.getPassword().equals(user.getCPassword())) {
			accountService.createUser(user);
			model.addAttribute("message", "Chúc mừng bạn đã đăng kí thành công !");
			model.addAttribute("active", "Vui lòng check email của bạn để kích hoạt tài khoản !");
			return "client/user/verify";
		} else {
			model.addAttribute("message", "Mật khẩu và mật khẩu xác nhận không giống nhau !");
			return "client/user/register";
		}
	}

	@GetMapping("/confirm")
	public String confirm(Model model, @RequestParam("token") String token) {
		if (accountService.verifyAccount(token)) {
			model.addAttribute("message", "Chúc mừng bạn đã kích hoạt tài khoản thành công !");
			return "client/user/confirm";
		} else {
			model.addAttribute("message", "Đường link này đã hết hạn !");
			return "client/user/confirm";
		}

	}

}