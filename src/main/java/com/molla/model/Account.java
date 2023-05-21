package com.molla.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Data
@Table(name = "Accounts")
public class Account{
	@Id
	@NotBlank(message = "Không để trống username")
	private String username;
	
	@NotBlank(message = "Không để trống password")
	private String password;
	
	@NotBlank(message = "Không để trống email")
	@Email(message = "Không đúng định dạng email")
	private String email;
	
	@NotBlank(message = "Không để trống họ và tên")
	private String fullname;
	
	private String address;
	private String phone;
	private String createDate;
	
	@Column(name = "accountverified")
	private Boolean accountVerified;
	
	private String activateCode;
	private String forgotPasswordCode;
	private String avatar;
	
	@ManyToOne
	@JoinColumn(name = "Role_Id")
	private Role role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
    private Set<ConfirmationToken> tokens;
	
}
