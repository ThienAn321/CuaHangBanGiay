package com.molla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.molla.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
	Account findByUsername(String username);
	
	Account findByEmail(String email);
	
	@Query("SELECT u FROM Account u where u.role.id = 'USER'")
	List<Account> findByRoleUser();
}
