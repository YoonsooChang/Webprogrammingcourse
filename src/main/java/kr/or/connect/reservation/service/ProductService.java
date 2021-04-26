package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ProductResponse;

public interface ProductService {
	static final int LIMIT = 4;

	ProductResponse getProductsByCategoryId(int categoryId, int start);
}
