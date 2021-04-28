package kr.or.connect.reservation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.CategoryResponse;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryApiController {
	private final CategoryService categoryService;

	public CategoryApiController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public CategoryResponse getCategoryResponse() {
		return categoryService.getCategories();
	}
}
