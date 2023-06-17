package com.molla.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.molla.model.Account;
import com.molla.service.AccountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/account")
public class AccountRestController {
	@Autowired
	AccountService accountService;

	@GetMapping("/user")
	public List<Account> findByRoleUser() {
		return accountService.findByRoleUser();
	}
	
	@PutMapping("active/{username}")
	public Account activeUser(@PathVariable("username") String username) {
		Account accountInDB = accountService.findById(username);
		accountInDB.setAccountVerified(true);
		return accountService.update(accountInDB);
	}

	@PutMapping("disable/{username}")
	public Account cancelUser(@PathVariable("username") String username) {
		Account accountInDB = accountService.findById(username);
		accountInDB.setAccountVerified(false);
		return accountService.update(accountInDB);
	}

}
