package kr.or.connect.reservation.service.impl;

import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.ProductResponse;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;

	public ProductServiceImpl(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public ProductResponse getProductsByCategoryId(int categoryId, int start) {
		if (categoryId == ProductService.CATEGORY_ALL) {
			return new ProductResponse(productDao.selectAll(start, ProductService.LIMIT),
				productDao.selectCountAll());
		} else {
			return new ProductResponse(productDao.selectByCategory(categoryId, start, ProductService.LIMIT),
				productDao.selectCountByCategory(categoryId));
		}

	}

}
