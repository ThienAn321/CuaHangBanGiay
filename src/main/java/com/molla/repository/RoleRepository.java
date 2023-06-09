package com.molla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.molla.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
