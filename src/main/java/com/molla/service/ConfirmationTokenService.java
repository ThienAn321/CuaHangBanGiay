package com.molla.service;

import java.util.Optional;

import com.molla.model.Account;
import com.molla.model.ConfirmationToken;

public interface ConfirmationTokenService {
	public void sendConfirmationToken(Account account);
	
	public void sendForgotPasswordToken(Account account);
	
	ConfirmationToken save(ConfirmationToken token);
	
	Optional<ConfirmationToken> findByToken(String token);
	
	void delete(Integer id);
}
