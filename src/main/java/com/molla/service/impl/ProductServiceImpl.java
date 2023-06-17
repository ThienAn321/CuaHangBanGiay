package com.molla.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	public Page<Product> findByCategoryId(String cid, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1,5);
		return productRepository.findByCategoryId(cid, pageable);
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

	@Override
	public Page<Product> findByKey(String keyword, int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1,5);
		return productRepository.findByKey(keyword, pageable);
	}

	@Override
	public Page<Product> findAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1,5);
		return productRepository.findAll(pageable);
	}
	
}
