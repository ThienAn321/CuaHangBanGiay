package com.molla.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.molla.model.OrderStatus;
import com.molla.repository.OrderStatusRepository;
import com.molla.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{
	@Autowired
	OrderStatusRepository orderStatusRepository;

	@Override
	public List<OrderStatus> findAll() {
		return orderStatusRepository.findAll();
	}

	@Override
	public OrderStatus getStatus(String id) {
		return orderStatusRepository.findById(id).get();
	}

}
