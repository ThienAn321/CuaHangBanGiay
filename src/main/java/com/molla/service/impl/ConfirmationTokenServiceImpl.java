package com.molla.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molla.model.Account;
import com.molla.model.ConfirmationToken;
import com.molla.repository.ConfirmationTokenRepository;
import com.molla.service.ConfirmationTokenService;
import com.molla.service.EmailService;

import jakarta.mail.MessagingException;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	EmailService emailService;

	@Override
	public void sendConfirmationToken(Account account) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");
		LocalDateTime now = LocalDateTime.now();
		String token = UUID.randomUUID().toString();
		// set token expire 15 minutes
		LocalDateTime tokenExpireAt = now.plusMinutes(15);

		ConfirmationToken confirmationToken = new ConfirmationToken(token, now.format(formatter),
				tokenExpireAt.format(formatter), false, account);
		save(confirmationToken);
		try {
			emailService.sendMail("thienan98765123@gmail.com", account.getEmail(), "Confirm Account",
					emailService.buildEmail(account.getFullname(), token));
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void sendForgotPasswordToken(Account account) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");
		LocalDateTime now = LocalDateTime.now();
		String token = UUID.randomUUID().toString();
		// set token expire 15 minutes
		LocalDateTime tokenExpireAt = now.plusMinutes(15);

		ConfirmationToken confirmationToken = new ConfirmationToken(token, now.format(formatter),
				tokenExpireAt.format(formatter), false, account);
		save(confirmationToken);
		try {
			emailService.sendMail("thienan98765123@gmail.com", account.getEmail(), "Reset your password",
					emailService.buildEmailPasswordForgot(account.getFullname(), token));
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public ConfirmationToken save(ConfirmationToken token) {
		return confirmationTokenRepository.save(token);
	}

	@Override
	public Optional<ConfirmationToken> findByToken(String token) {
		return confirmationTokenRepository.findByToken(token);
	}

	@Override
	public void delete(Integer id) {
		confirmationTokenRepository.deleteById(id);
	}

}
