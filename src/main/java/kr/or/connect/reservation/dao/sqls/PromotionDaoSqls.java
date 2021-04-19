package kr.or.connect.reservation.dao.sqls;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = "SELECT id, product_id, product_image_url \n"
		+ "FROM promotion \n"
		+ "JOIN (SELECT product_id, save_file_name as product_image_url \n"
		+ "		FROM product_image JOIN file_info \n"
		+ "		ON product_image.file_id = file_info.id \n"
		+ "		WHERE product_image.type = \"th\") \n"
		+ "AS thumbnail \n"
		+ "USING (product_id)";
}