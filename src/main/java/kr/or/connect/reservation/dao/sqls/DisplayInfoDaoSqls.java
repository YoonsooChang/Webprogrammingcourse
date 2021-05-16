package kr.or.connect.reservation.dao.sqls;

public class DisplayInfoDaoSqls {
	public static final String SELECT_BY_ID = ""
		+ "SELECT "
		+ "		category.id 			AS category_id, "
		+ "		category.name			AS category_name, "

		+ "     display.create_date 	AS create_date, "
		+ "     display.id 				AS display_info_id, "
		+ "		display.email 			AS email, "
		+ "     display.homepage 		AS homepage, "
		+ "     display.modify_date 	AS modify_date, "
		+ "     display.opening_hours 	AS opening_hours, "
		+ "     display.place_lot 		AS place_lot, "
		+ "     display.place_name 		AS place_name, "
		+ "     display.place_street 	AS place_street, "
		+ "     display.tel 			AS telephone, "

		+ "     product.content 		AS product_content, "
		+ "     product.description 	AS product_description, "
		+ "     product.event 			AS product_event, "
		+ "		product.id 				AS product_id \n"
		+ "FROM "
		+ "		display_info AS display \n"
		+ "JOIN "
		+ "		product \n"
		+ "ON "
		+ "		display.product_id = product.id \n"
		+ "JOIN "
		+ "		category \n"
		+ "ON "
		+ "		category.id = product.category_id \n"
		+ "WHERE "
		+ "		display.id = :id";

	public static final String SELECT_IMAGE_BY_ID = ""
		+ "SELECT "
		+ "		file.content_type		AS content_type, "
		+ "     file.create_date		AS create_date, "
		+ "     file.delete_flag		AS delete_flag, "
		+ "     file.file_name			AS file_name, "
		+ "     file.modify_date		AS modify_date, "
		+ "     file.save_file_name 	AS save_file_name, \n"

		+ "		image.display_info_id	AS display_info_id, "
		+ "     image.id 				AS display_info_image_id, "
		+ "     image.file_id			AS file_id \n"
		+ "FROM "
		+ "		display_info_image AS image \n"
		+ "JOIN "
		+ "		file_info AS file \n"
		+ "ON "
		+ "		image.file_id = file.id \n"
		+ "WHERE "
		+ "		display_info_id = :id;";

}
