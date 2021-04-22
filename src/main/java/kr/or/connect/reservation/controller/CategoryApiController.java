package kr.or.connect.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservation.dto.CategoryResponse;
import kr.or.connect.reservation.service.CategoryService;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryApiController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public CategoryResponse list() {
		CategoryResponse categoryResponse = categoryService.getCategories();
		return categoryResponse;
	}
}
