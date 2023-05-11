package com.molla.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Categories")
public class Category{
	@Id
	private String id;
	private String name;
	private String icon;
	
//	@JsonIgnore
//	@OneToMany(mappedBy = "category")
//	private List<Subcategory> subcategories;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Product> products;
	
}
