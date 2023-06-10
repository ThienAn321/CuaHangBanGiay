package com.molla.service;

import java.util.List;

import com.molla.model.Account;

public interface AccountService {
	Account findById(String username);
	
	Account findByEmail(String email);
	
	List<Account> findByRoleUser();
	
	public void createUser(Account account);
	
	boolean checkUsername(String username);
	
	boolean verifyRegisterAccount (String token);
	
	boolean verifyToken(String token);
	
	public void changePassword(Account account, String password);
	
}
