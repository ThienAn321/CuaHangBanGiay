package com.molla.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molla.model.Role;
import com.molla.repository.RoleRepository;
import com.molla.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role getRole(String id) {
		return roleRepository.findById(id).get();
	}

}
