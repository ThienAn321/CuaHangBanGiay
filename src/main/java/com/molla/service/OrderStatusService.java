package com.molla.service;

import java.util.List;

import com.molla.model.OrderStatus;

public interface OrderStatusService {
	List<OrderStatus> findAll();
	
	OrderStatus getStatus(String id);
}
