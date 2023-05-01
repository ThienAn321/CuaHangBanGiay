package com.molla.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User{
	@Id
	private String username;
	private String password;
	private String email;
	private String fullname;
	private String address;
	private String phone;
	private String introduce;
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
