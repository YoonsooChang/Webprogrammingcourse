package kr.or.connect.reservation.dao.sqls;

public class PromotionDaoSqls {
	public static final String SELECT_ALL_WITH_IMAGE_URL = ""
		+ "SELECT "
		+ "		promotion.id 			AS id,"
		+ "		promotion.product_id 	AS product_id, "

		+ "		file.save_file_name 	AS product_image_url \n"
		+ "FROM "
		+ "		promotion \n"
		+ "LEFT OUTER JOIN "
		+ "		product_image AS image \n"
		+ "USING "
		+ "		(product_id) \n"
		+ "LEFT OUTER JOIN "
		+ "		file_info AS file \n"
		+ "ON "
		+ "		image.file_id = file.id \n"
		+ "WHERE "
		+ "		image.type = \"th\"";
}
