package com.molla.service;

import java.util.List;

import com.molla.model.Product;

public interface ProductService {

	List<Product> findAll();

	Product findById(Integer id);
	
	Product create(Product product);
	
	Product update(Product product);
	
	void delete(Integer id);
	
}
