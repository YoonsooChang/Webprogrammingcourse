package kr.or.connect.reservation.dao.sqls;

public class ProductDaoSqls {
	public static final String SELECT_BY_CATEGORY = "SELECT product_info.*, display_info.id AS display_info_id, place_name \n"
		+ "FROM (SELECT \n"
		+ "			description AS product_description, \n"
		+ "			content AS product_content,\n"
		+ "    		id AS product_id,\n"
		+ "    		product_image_url \n"
		+ "		FROM product \n"
		+ "		JOIN \n"
		+ "			(SELECT product_id, save_file_name AS product_image_url \n"
		+ "			 FROM product_image JOIN file_info \n"
		+ "			 ON product_image.file_id = file_info.id \n"
		+ "			 WHERE product_image.type = \"th\") "
		+ "		AS image_info \n"
		+ "		ON product.id = image_info.product_id \n"
		+ "		WHERE ( (:categoryid IS NULL) OR (:categoryid IS NOT NULL AND category_id = :categoryid) ) \n"
		+ ") AS product_info \n"
		+ "JOIN display_info \n"
		+ "USING (product_id) \n"
		+ "ORDER BY product_id DESC \n"
		+ "limit :start, :limit;";

	public static final String SELECT_COUNT_BY_CATEGORY = "SELECT COUNT(*) FROM product \n"
		+ "JOIN display_info \n"
		+ "ON (product.id = display_info.product_id) \n"
		+ "WHERE ( (:categoryid IS NULL) OR (:categoryid IS NOT NULL AND category_id = :categoryid) )";
}