package com.molla.controller.client;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.molla.model.Product;
import com.molla.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
//
//	void page(Model model, Page<Product> list, @PathVariable("pageNumber") int currentPage) {
//		int totalPages = list.getTotalPages();
//		long totalItems = list.getTotalElements();
//		List<Product> products = list.getContent();
//
//		model.addAttribute("currentPage", currentPage);
//		model.addAttribute("totalPages", totalPages);
//		model.addAttribute("totalItems", totalItems);
//		model.addAttribute("countries", products);
//	}

	@GetMapping("/product/list")
	public String getAllPages(Model model, @RequestParam("cid") Optional<String> cid, @Param("keyword") String keyword) {
		return list(model, cid, 1, keyword);
	}

	@RequestMapping("/product/list/{pageNumber}")
	public String list(Model model, @RequestParam("cid") Optional<String> cid,
			@PathVariable("pageNumber") int currentPage, @Param("keyword") String keyword) {
		if (keyword != null) {
			Page<Product> list = productService.findByKey(keyword, currentPage);
			model.addAttribute("items", list);
			model.addAttribute("keyword", keyword);

			int totalPages = list.getTotalPages();
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalPages", totalPages);
		} else if (cid.isPresent()) {
			Page<Product> list = productService.findByCategoryId(cid.get(), currentPage);
			model.addAttribute("items", list);

			int totalPages = list.getTotalPages();
			model.addAttribute("cid", cid.get());
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalPages", totalPages);
		} else {
			Page<Product> list = productService.findAll(currentPage);
			model.addAttribute("items", list);

			int totalPages = list.getTotalPages();
			model.addAttribute("totalPages", totalPages);
			model.addAttribute("currentPage", currentPage);
		}

		return "client/product/product_list";
	}

	@RequestMapping("/product/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Product item = productService.findById(id);
		model.addAttribute("item", item);

		List<Product> list = productService.findByCategoryId(item.getCategory().getId());
		model.addAttribute("items", list);

		return "client/product/product_detail";
	}
}
