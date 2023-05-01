package com.molla.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Orders")
public class Order{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "Username")
	private User user;
	private Date createDate;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "order_status")
	private OrderStatus orderStatus;
	
	private String address;
	private String description;
	private String phoneNumber;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
	
	private Integer isPaid;
	private Date expectedDate;
	private String orderTimeDetail;
	private Double shippingCost;

	
}
