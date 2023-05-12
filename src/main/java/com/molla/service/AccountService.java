package com.molla.service;

import com.molla.model.Account;

public interface AccountService {
	Account findById(String username);
	
	Account createUser(Account account);
	
	boolean checkUsername(String username);
}
