package com.molla.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molla.entity.User;
import com.molla.repository.UserRepository;
import com.molla.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User findById(String username) {
		return userRepository.findById(username).get();
	}

}
