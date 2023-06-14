package com.molla.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molla.model.Product;
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
	
	@Override
	public List<Product> findByCategoryId(String cid) {
		return productRepository.findByCategoryId(cid);
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}
	
	@Override
	public Product create(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public Product update(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Integer id) {	
		productRepository.deleteById(id);
	}
	
	
}
