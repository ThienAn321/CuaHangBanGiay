package com.molla.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molla.entity.Product;
import com.molla.repository.ProductRepository;
import com.molla.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
	
	
}
