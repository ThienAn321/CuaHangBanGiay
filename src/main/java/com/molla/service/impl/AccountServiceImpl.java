package com.molla.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molla.model.Account;
import com.molla.repository.AccountRepository;
import com.molla.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Account findById(String username) {
		return accountRepository.findById(username).get();
	}

	@Override
	public Account createUser(Account account) {
		return accountRepository.save(account);
	}

	@Override
	public boolean checkUsername(String username) {
		return accountRepository.existsById(username);
	}	

}
