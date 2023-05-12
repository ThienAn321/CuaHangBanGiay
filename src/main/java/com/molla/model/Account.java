package com.molla.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class Account{
	@Id
	@NotBlank(message = "Không để trống username")
	private String username;
	
	@NotBlank(message = "Không để trống password")
	private String password;
	
	@NotBlank(message = "Không để trống confirm password")
	private String CPassword;
	
	@NotBlank(message = "Không để trống email")
	@Email(message = "Không đúng định dạng email")
	private String email;
	
	@NotBlank(message = "Không để trống họ và tên")
	private String fullname;
	
	private String address;
	private String phone;
	private Date createDate;
	private Boolean status;
	private String activateCode;
	private String forgotPasswordCode;
	private String avatar;
	
	@ManyToOne
	@JoinColumn(name = "Role_Id")
	private Role role;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
}
