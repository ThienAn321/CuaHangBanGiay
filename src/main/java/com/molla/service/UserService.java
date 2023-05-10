package com.molla.service;

import com.molla.entity.User;

public interface UserService {
	User findById(String username);
}
