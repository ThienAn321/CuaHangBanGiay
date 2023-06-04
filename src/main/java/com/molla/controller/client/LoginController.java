package com.molla.controller.client;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	@RequestMapping("/login")
	public String loginForm() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    		return "client/user/login";
        }
 
        return "redirect:/home";
	}
	
	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("error","Sai thông tin đăng nhập !");
		return "client/user/login";
	}

}
