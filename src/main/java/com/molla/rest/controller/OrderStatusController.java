package com.molla.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.molla.model.OrderStatus;
import com.molla.service.OrderStatusService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderStatus")
public class OrderStatusController {
	@Autowired
	OrderStatusService orderStatusService;
	
	@GetMapping()
	public List<OrderStatus> findAll() {
		return orderStatusService.findAll();
	}
}
