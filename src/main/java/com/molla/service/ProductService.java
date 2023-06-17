package com.molla.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.molla.model.Product;

public interface ProductService {
	
	List<Product> findAll();
	
	Page<Product> findAll(int pageNumber);

	Product findById(Integer id);
	
	Page<Product> findByKey(String keyword, int pageNumber);
	
	List<Product> findByCategoryId(String cid);
	
	Page<Product> findByCategoryId(String cid, int pageNumber);
	
	Product create(Product product);
	
	Product update(Product product);
	
	void delete(Integer id);
	
}
