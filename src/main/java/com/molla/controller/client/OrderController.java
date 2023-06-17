package com.molla.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.molla.model.Order;
import com.molla.model.OrderDetail;
import com.molla.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
	@Autowired
	OrderService orderService;

	@RequestMapping("/order/history")
	public String list(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("orders", orderService.findByUsername(username));
		return "client/order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		Order order = orderService.findById(id);
		model.addAttribute("order", order);
		
		List<OrderDetail> orderDetails = order.getOrderDetails();
		double sum = 0;
		for (OrderDetail orderDetail : orderDetails) {
			double sanPham = (orderDetail.getProducts().getPrice() * ((100.0 - orderDetail.getProducts().getDiscount()) /100))* orderDetail.getQuantity();
			sum += sanPham;
		}
		sum += order.getShippingCost();
		model.addAttribute("sum", sum);
		return "client/order/detail";
	}
}
