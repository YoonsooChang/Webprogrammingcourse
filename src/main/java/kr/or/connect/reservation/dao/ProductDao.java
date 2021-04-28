package kr.or.connect.reservation.dao;

import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_ALL;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_BY_CATEGORY;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_COUNT_ALL;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_COUNT_BY_CATEGORY;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_IMAGES_BY_ID;
import static kr.or.connect.reservation.dao.sqls.ProductDaoSqls.SELECT_PRICES_BY_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.Product;
import kr.or.connect.reservation.dto.ProductImage;
import kr.or.connect.reservation.dto.ProductPrice;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> itemMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> imageMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
	private RowMapper<ProductPrice> priceMapper = BeanPropertyRowMapper.newInstance(ProductPrice.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		params.put("categoryid", categoryId);

		List<Product> productList = jdbc.query(SELECT_BY_CATEGORY, params, itemMapper);
		return productList;
	}

	public List<Product> selectAll(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);

		List<Product> productList = jdbc.query(SELECT_ALL, params, itemMapper);
		return productList;
	}

	public int selectCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryid", categoryId);

		return jdbc.queryForObject(SELECT_COUNT_BY_CATEGORY, params, Integer.class);
	}

	public int selectCountAll() {
		return jdbc.queryForObject(SELECT_COUNT_ALL, Collections.emptyMap(), Integer.class);
	}

	public List<ProductImage> selectProductImagesById(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", productId);

		return jdbc.query(SELECT_IMAGES_BY_ID, params, imageMapper);
	}

	public List<ProductPrice> selectProductPricesById(int productId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("id", productId);

		return jdbc.query(SELECT_PRICES_BY_ID, params, priceMapper);
	}
}
