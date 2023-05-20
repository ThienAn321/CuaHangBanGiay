package com.molla.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molla.model.ConfirmationToken;
import com.molla.repository.ConfirmationTokenRepository;
import com.molla.service.ConfirmationTokenService;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService{
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	@Override
	public ConfirmationToken save(ConfirmationToken token) {
		return confirmationTokenRepository.save(token);
	}

	@Override
	public Optional<ConfirmationToken> findByToken(String token) {
		return confirmationTokenRepository.findByToken(token);
	}
	
	
}
