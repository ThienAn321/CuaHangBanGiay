package com.molla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.molla.entity.Product;
import com.molla.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping("/product/list")
	public String list(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "client/product/list";
	}
}
