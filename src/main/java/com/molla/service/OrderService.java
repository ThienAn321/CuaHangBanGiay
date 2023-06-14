package com.molla.service;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.molla.model.Order;

public interface OrderService {
	List<Order> findAll();
	Order create(JsonNode orderData);

	Order findById(Integer id);

	List<Order> findByUsername(String username);
	
	Order update(Order order);
}
