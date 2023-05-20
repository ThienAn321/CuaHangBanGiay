package com.molla.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.molla.model.Account;
import com.molla.model.ConfirmationToken;
import com.molla.model.Role;
import com.molla.repository.AccountRepository;
import com.molla.service.AccountService;
import com.molla.service.ConfirmationTokenService;
import com.molla.service.EmailService;
import com.molla.service.RoleService;

import jakarta.mail.MessagingException;

import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	RoleService roleService;

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	ConfirmationTokenService confirmationTokenService;
	
	@Autowired
	EmailService emailService;

	@Override
	public Account findById(String username) {
		return accountRepository.findById(username).get();
	}

	@Override
	public void createUser(Account account) {
		String password = passwordEncoder.encode(account.getPassword());
		String cPassword = passwordEncoder.encode(account.getCPassword());
		
		Role userRole = roleService.getRole("USER");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");
		LocalDateTime now = LocalDateTime.now();

		account.setPassword(password);
		account.setCPassword(cPassword);
		account.setRole(userRole);
		account.setAccountVerified(false);
		account.setCreateDate(now.format(formatter));

		accountRepository.save(account);

		// random token
		String token = UUID.randomUUID().toString();
		// set token expire 15 minutes
		LocalDateTime tokenExpireAt = now.plusMinutes(15);

		ConfirmationToken confirmationToken = new ConfirmationToken(token, now.format(formatter),
				tokenExpireAt.format(formatter), false, account);
		confirmationTokenService.save(confirmationToken);
		try {
			emailService.sendMail(
					"thienan98765123@gmail.com",
					account.getEmail(), 
					"Confirm Account", 
					emailService.buildEmail(account.getFullname(), token));
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean checkUsername(String username) {
		return accountRepository.existsById(username);
	}

	@Override
	public boolean verifyAccount(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token).get();

		if (confirmationToken == null || confirmationToken.isExpired()) {
			return false;
		}

		Account account = accountRepository.findByUsername(confirmationToken.getAccount().getUsername());

		if (account == null) {
			return false;
		}

		account.setAccountVerified(true);
		accountRepository.save(account);
		return true;
	}

}
