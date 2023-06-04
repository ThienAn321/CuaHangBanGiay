package com.molla.controller.client;

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
	public String createUser(Model model, String confirmPassword, @Validated @ModelAttribute("account") Account user, Errors errors) {
		
		boolean check = accountService.checkUsername(user.getUsername());

		if (errors.hasErrors()) {
			return "client/user/register";
		}

		if (check) {
			model.addAttribute("error", "Tài khoản này đã có người sử dụng !");
		}

		if (user.getPassword().equals(confirmPassword)) {
			accountService.createUser(user);
			model.addAttribute("message", "Chúc mừng bạn đã đăng kí thành công !");
			model.addAttribute("active", "Vui lòng check email của bạn để kích hoạt tài khoản !");
			return "client/user/message";
		} else {
			model.addAttribute("error", "Mật khẩu và mật khẩu xác nhận không giống nhau !");
			return "client/user/register";
		}
	}

	@GetMapping("/confirm")
	public String confirm(Model model, @RequestParam("token") String token) {
		if (accountService.verifyRegisterAccount(token)) {
			model.addAttribute("message", "Chúc mừng bạn đã kích hoạt tài khoản thành công !");
			return "client/user/message";
		} else {
			model.addAttribute("error", "Đường link này đã hết hạn !");
			return "client/user/message";
		}
	}

}
