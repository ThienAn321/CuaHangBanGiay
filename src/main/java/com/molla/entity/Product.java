package com.molla.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;


@Entity
@Data
@Table(name = "Products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private Integer size;
	private Double price;
	private String description;
	private Integer stock;
	private Integer discount;

	@ManyToOne
	@JoinColumn(name = "Category_Id")
	private Category category;

//	@ManyToOne
//	@JoinColumn(name = "Subcategory_Id")
//	private Subcategory subcategory;
//
//
	@JsonIgnore
	@OneToMany(mappedBy = "products")
	List<OrderDetail> orderDetail;	

}
