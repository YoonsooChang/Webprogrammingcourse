package kr.or.connect.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.ProductResponse;
import kr.or.connect.reservation.service.ProductService;

@RestController
@RequestMapping(path = "/api/product")
public class ProductApiController {
	private final ProductService productService;

	public ProductApiController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ProductResponse getProductResponse(@RequestParam(name = "categoryId", required = false, defaultValue = "0")
	int categoryId,
		@RequestParam(name = "start", required = false, defaultValue = "0")
		int start) {
		return productService.getProductsByCategoryId(categoryId, start);
	}
}
