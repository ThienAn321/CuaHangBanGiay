package com.molla.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.NoSuchElementException;
import static org.springframework.security.config.Customizer.withDefaults;

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
				boolean enabled = user.getAccountVerified();			
				String password = user.getPassword();
				String roles = user.getRole().getId();
				return User.withUsername(username).password(password).roles(roles).disabled(!enabled).build();
			} catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "not found");
			}
		});		
	}
	
	@Bean
	@Order(2)
	public SecurityFilterChain securityFilterChainClient(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.cors().disable();
		
//		http.securityMatcher("/order/**", "/checkout")
//			.authorizeHttpRequests()
//			.anyRequest().authenticated();
////			.anyRequest().permitAll();
		
		http
			.authorizeHttpRequests()
			.requestMatchers("/order/**", "/checkout").authenticated()
			.anyRequest().permitAll(); // truy cập thoải mái
		
		http.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home", false)
			.failureUrl("/login/error")
	        .usernameParameter("username")
	        .passwordParameter("password");
		
//		http.logout()
//			.logoutUrl("/logoff")
//			.logoutSuccessUrl("/home");

		http.rememberMe()
			.tokenValiditySeconds(86400);

		return http.build();
	}
	
	@Bean
	@Order(1)
	public SecurityFilterChain securityFilterChainAdmin(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.cors().disable();
		
//		http.securityMatcher(AntPathRequestMatcher.antMatcher("/admin/**"))
//			.authorizeHttpRequests()
//			.requestMatchers(AntPathRequestMatcher.antMatcher("/admin/**")).hasRole("ADMIN");
		
		http.securityMatcher("/admin/**")
			.authorizeHttpRequests()
			.requestMatchers("/admin/**").hasAnyRole("ADMIN", "DIRECTOR"); // truy cập thoải mái;
		
		http .formLogin()
	         .loginPage("/loginAdmin")
	         .loginProcessingUrl("/login")
	         .failureUrl("/loginAdmin?error=loginError")
	         .defaultSuccessUrl("/admin", false)
	         .permitAll();
		
		
//		http.logout()
//			.logoutUrl("/logoff")
//			.logoutSuccessUrl("/home");

		http.rememberMe()
			.tokenValiditySeconds(86400);

		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
