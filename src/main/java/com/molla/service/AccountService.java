package com.molla.service;

import com.molla.model.Account;

public interface AccountService {
	Account findById(String username);
	
	public void createUser(Account account);
	
	public void forgotPassword(Account account);
	
	boolean checkUsername(String username);
	
	boolean verifyAccount (String token);
}
