package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.ProductDao;
import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductResponse;
import kr.or.connect.reservation.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public ProductResponse getProductsByCategoryId(Integer categoryId, Integer start) {
		List<Product> list = productDao.selectByCategory(categoryId, start, LIMIT);
		Integer totalCount = productDao.selectCountByCategory(categoryId);

		ProductResponse productResponse = new ProductResponse();
		productResponse.setItems(list);
		productResponse.setTotalCount(totalCount);

		return productResponse;
	}

}
