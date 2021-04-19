package kr.or.connect.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.ProductResponse;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;

	@GetMapping
	public ProductResponse list(@RequestParam(name = "categoryId", required = false)
	Integer categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0")
		Integer start) {
		ProductResponse productList = productService.getProductsByCategoryId(categoryId, start);
		return productList;
	}
}
