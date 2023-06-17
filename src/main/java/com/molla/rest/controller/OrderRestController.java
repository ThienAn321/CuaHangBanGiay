package com.molla.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.molla.model.Order;
import com.molla.model.OrderDetail;
import com.molla.model.OrderStatus;
import com.molla.model.Product;
import com.molla.service.OrderService;
import com.molla.service.OrderStatusService;
import com.molla.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
	@Autowired
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderStatusService orderStatusService;
	
	@GetMapping()
	public List<Order> findAll() {
		return orderService.findAll();
	}
	
	@PostMapping()
	public Order create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
	
	@PutMapping("{id}")
	public Order update(@PathVariable("id") Integer id, @RequestBody Order order) {	
		return orderService.update(order);
	}
	
	@PutMapping("cancelOrder/{id}")
	public Order cancelOrder(@PathVariable("id") Integer id, @RequestBody Order order) {
		OrderStatus orderCancel = orderStatusService.getStatus("HuyBo");
		Order orderInDB = orderService.findById(id);
		orderInDB.setOrderStatus(orderCancel);
		
		List<OrderDetail> orderDetails = orderInDB.getOrderDetails();
		for (OrderDetail orderDetail : orderDetails) {
			int productId = orderDetail.getProducts().getId();
			Product product = productService.findById(productId);
			int remainStock = product.getStock() + orderDetail.getQuantity();
            product.setStock(remainStock);
            productService.update(product);
		}
		return orderService.update(order);
	}
	
	@PutMapping("cancelOrderClient/{id}")
	public Order cancelOrderClient(@PathVariable("id") Integer id) {
		OrderStatus orderCancel = orderStatusService.getStatus("HuyBo");
		Order orderInDB = orderService.findById(id);
		orderInDB.setOrderStatus(orderCancel);
		
		List<OrderDetail> orderDetails = orderInDB.getOrderDetails();
		for (OrderDetail orderDetail : orderDetails) {
			int productId = orderDetail.getProducts().getId();
			Product product = productService.findById(productId);
			int remainStock = product.getStock() + orderDetail.getQuantity();
            product.setStock(remainStock);
            productService.update(product);
		}
		return orderService.update(orderInDB);
	}
	
}
