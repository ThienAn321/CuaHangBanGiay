package com.molla.entity;

import java.io.Serializable;
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
@Table(name = "Subcategories")
public class Subcategory{
	@Id
	private String id;
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "Category_Id")
	private Category category;
	
	private String icon;
	
	@JsonIgnore
	@OneToMany(mappedBy = "subcategory")
	private List<Product> products;

}
