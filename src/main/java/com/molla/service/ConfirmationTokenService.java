package com.molla.service;

import java.util.Optional;

import com.molla.model.ConfirmationToken;

public interface ConfirmationTokenService {
	ConfirmationToken save(ConfirmationToken token);
	
	Optional<ConfirmationToken> findByToken(String token);
}
