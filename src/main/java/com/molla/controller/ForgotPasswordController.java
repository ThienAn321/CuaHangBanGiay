package com.molla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.molla.model.Account;
import com.molla.service.AccountService;
import com.molla.service.ConfirmationTokenService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ForgotPasswordController {
	@Autowired
	AccountService accountService;

	@Autowired
	ConfirmationTokenService confirmationTokenService;

	private String email;

	@GetMapping("/forgot")
	public String forgotForm() {
		return "client/user/forgotForm";
	}

	@PostMapping("/forgot")
	public String checkEmail(HttpServletRequest request, Model model) {
		String email = request.getParameter("email");
		this.email = email;
		Account account = accountService.findByEmail(email);
		if (account == null) {
			model.addAttribute("error", "Email không tồn tại !");
			return "client/user/forgotForm";
		}
		confirmationTokenService.sendForgotPasswordToken(account);
		model.addAttribute("active", "Vui lòng check email của bạn để tiến hành reset mật khẩu !");
		return "client/user/verify";
	}

	@GetMapping("/reset_password")
	public String confirm(Model model, @RequestParam("token") String token) {
		if (accountService.verifyToken(token)) {
			return "client/user/confirmPassword";
		} else {
			model.addAttribute("message", "Đường link này đã hết hạn !");
			return "client/user/confirm";
		}
	}

	@GetMapping("/confirm_password")
	public String confirmPasswordForm(Model model) {
		return "client/user/confirmPassword";
	}

	@PostMapping("/confirm_password")
	public String confirmPassword(HttpServletRequest request, Model model) {
		Account account = accountService.findByEmail(this.email);
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");

		if (password.equals(confirmPassword)) {
			accountService.changePassword(account, password);
			model.addAttribute("message", "Chúc mừng bạn đã đặt lại mật khẩu thành công !");
			return "client/user/confirm";
		} else {
			model.addAttribute("message", "Mật khẩu và mật khẩu xác nhận không giống nhau !");
			return "client/user/confirmPassword";
		}

	}
}
