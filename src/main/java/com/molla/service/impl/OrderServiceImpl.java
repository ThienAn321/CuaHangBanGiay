package com.molla.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.molla.model.Order;
import com.molla.model.OrderDetail;
import com.molla.repository.OrderDetailRepository;
import com.molla.repository.OrderRepository;
import com.molla.repository.ProductRepository;
import com.molla.service.OrderService;
import com.molla.model.Product;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order create(JsonNode orderData) {
		ObjectMapper mapper = new ObjectMapper();

		Order order = mapper.convertValue(orderData, Order.class);
		orderRepository.save(order);

		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
		};
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
				.peek(d -> d.setOrder(order)).collect(Collectors.toList());
		orderDetailRepository.saveAll(details);
		
		for(OrderDetail orderDetail : details) {
		    int productId = orderDetail.getProducts().getId();
		    Product product = productRepository.findById(productId).get();
		    int remainStock = product.getStock() - orderDetail.getQuantity();
		    product.setStock(remainStock);
		    productRepository.save(product);
		}
		orderDetailRepository.saveAll(details);
		return order;
	}

	@Override
	public Order findById(Integer id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {
		return orderRepository.findByUsername(username);
	}

	@Override
	public Order update(Order order) {
		return orderRepository.save(order);
	}

}
