package kr.or.connect.reservation.dao.sqls;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = ""
		+ "SELECT "
		+ "		id, "
		+ "		product_id, "
		+ "		product_image_id \n"
		+ "FROM "
		+ "		promotion \n"
		+ "JOIN (SELECT product_id, id as product_image_id \n"
		+ "		FROM product_image \n"
		+ "		WHERE product_image.type = \"th\") \n"
		+ "AS thumbnail \n"
		+ "USING (product_id)";

	public static final String SELECT_ALL_WITH_IMAGE_URL = ""
		+ "SELECT "
		+ "		PROMO.id AS id,"
		+ "		product_id, "
		+ "		FILE.save_file_name AS product_image_url \n"
		+ "FROM "
		+ "		promotion PROMO \n"
		+ "LEFT OUTER JOIN "
		+ "		product_image IMAGE \n"
		+ "USING "
		+ "		(product_id) \n"
		+ "LEFT OUTER JOIN "
		+ "		file_info FILE \n"
		+ "ON "
		+ "		IMAGE.file_id = FILE.id \n"
		+ "WHERE "
		+ "		IMAGE.type = \"th\"";
}
