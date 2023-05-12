package com.molla.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.NoSuchElementException;

import com.molla.model.Account;
import com.molla.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	@Autowired
	AccountService accountService;
	
	@Autowired
	protected void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(username -> {
			try {
				Account user = accountService.findById(username);
				String password = passwordEncoder().encode(user.getPassword());
				String roles = user.getRole().getId();
				return User.withUsername(username).password(password).roles(roles).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found");
			}
		});		
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.cors().disable();
		
		http
			.authorizeHttpRequests()
			.requestMatchers("/oder/**").authenticated()
			.requestMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().permitAll();
		
		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home", true)
			.failureUrl("/error");
		
//		http.logout()
//			.logoutUrl("/security/logoff")
//			.logoutSuccessUrl("/security/logoff/success");

		http.rememberMe()
			.tokenValiditySeconds(86400);

		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
