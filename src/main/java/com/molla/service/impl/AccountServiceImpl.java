package com.molla.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

		Role userRole = roleService.getRole("USER");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");
		LocalDateTime now = LocalDateTime.now();

		account.setPassword(password);
		account.setRole(userRole);
		account.setAccountVerified(false);
		account.setCreateDate(now.format(formatter));

		accountRepository.save(account);
		confirmationTokenService.sendConfirmationToken(account);
	}

	@Override
	public boolean checkUsername(String username) {
		return accountRepository.existsById(username);
	}

	@Override
	public boolean verifyRegisterAccount(String token) {
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
		confirmationTokenService.delete(confirmationToken.getId());
		return true;
	}

	@Override
	public Account findByEmail(String email) {
		return accountRepository.findByEmail(email);
	}

	@Override
	public boolean verifyToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService.findByToken(token).get();

		if (confirmationToken == null || confirmationToken.isExpired()) {
			return false;
		}

		return true;
	}

	@Override
	public void changePassword(Account account, String password) {
		String encodePassword = passwordEncoder.encode(password);
		account.setPassword(encodePassword);
		accountRepository.save(account);

	}

	@Override
	public List<Account> findByRoleUser() {
		return accountRepository.findByRoleUser();
	}

}
