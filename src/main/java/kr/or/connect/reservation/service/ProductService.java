package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ProductResponse;

public interface ProductService {
	static final Integer LIMIT = 4;

	ProductResponse getProductsByCategoryId(Integer categoryId, Integer start);
}
