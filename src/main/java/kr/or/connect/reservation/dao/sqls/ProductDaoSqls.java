package kr.or.connect.reservation.dao.sqls;

public class ProductDaoSqls {
	public static final String SELECT_BY_CATEGORY = "SELECT content AS product_content, \n"
		+ "description AS product_description, \n"
		+ "P.id AS product_id, \n"
		+ "D.id AS display_info_id, \n"
		+ "place_name, \n"
		+ "F.save_file_name AS product_image_url \n"
		+ "FROM product P \n"
		+ "JOIN display_info D ON P.id = D.product_id \n"
		+ "JOIN product_image I ON P.id = I.product_id \n"
		+ "JOIN file_info F ON I.file_id = F.id \n"
		+ "WHERE ( (:categoryid IS NULL) OR (:categoryid IS NOT NULL AND P.category_id = :categoryid) ) \n"
		+ "AND I.type =\"th\" \n"
		+ "ORDER BY display_info_id DESC \n"
		+ "limit :start, :limit";

	public static final String SELECT_COUNT_BY_CATEGORY = "SELECT COUNT(*) FROM product \n"
		+ "JOIN display_info \n"
		+ "ON (product.id = display_info.product_id) \n"
		+ "WHERE ( (:categoryid IS NULL) OR (:categoryid IS NOT NULL AND category_id = :categoryid) )";
}