package kr.or.connect.reservation.dao.sqls;

public class ReservationInfoDaoSqls {
	public static final String SELECT_BY_USER_EMAIL = ""
		+ "SELECT \r\n"
		+ "		reservation.cancel_flag 		AS cancel_yn,\r\n"
		+ "		reservation.create_date 		AS reservation_create_date,\r\n"
		+ "		reservation.display_info_id 	AS display_info_id,\r\n"
		+ "		reservation.modify_date 		AS reservation_modify_date,\r\n"
		+ "		reservation.product_id 			AS product_id,\r\n"
		+ "		reservation.reservation_date 	AS reservation_date,\r\n"
		+ "		reservation.reservation_email	AS reservation_email,\r\n"
		+ "		reservation.id 					AS reservation_info_id,\r\n"
		+ "		reservation.reservation_name 	AS reservation_name,\r\n"
		+ "		reservation.reservation_tel 	AS reservation_telephone,\r\n"

		+ "    	SUM(product_price.price * reservation_price.count) AS total_price,\r\n"

		+ "		category.id 					AS category_id,\r\n"
		+ "		category.name 					AS category_name,\r\n"

		+ "		display.create_date 			AS create_date,\r\n"
		+ "    	display.email 					AS email,\r\n"
		+ "		display.homepage 				AS homepage,\r\n"
		+ "		display.modify_date 			AS modify_date,\r\n"
		+ "		display.opening_hours 			AS opening_hours,\r\n"
		+ "		display.place_lot 				AS place_lot,\r\n"
		+ "		display.place_name 				AS place_name,\r\n"
		+ "		display.place_street 			AS place_street,\r\n"
		+ "		display.tel 					AS telephone,\r\n"

		+ "		product.content 				AS product_content,\r\n"
		+ "		product.description 			AS product_description,\r\n"
		+ "		product.event 					AS product_event\r\n"

		+ "FROM \r\n"
		+ "		reservation_info AS reservation\r\n"

		+ "JOIN \r\n"
		+ "		display_info AS display\r\n"
		+ "ON \r\n"
		+ "		display.id = reservation.display_info_id\r\n"

		+ "JOIN \r\n"
		+ "		product\r\n"
		+ "ON \r\n"
		+ "		display.product_id = product.id\r\n"

		+ "JOIN \r\n"
		+ "		category \r\n"
		+ "ON \r\n"
		+ "		category.id = product.category_id\r\n"

		+ "JOIN \r\n"
		+ "		reservation_info_price AS reservation_price\r\n"
		+ "ON \r\n"
		+ "		reservation_price.reservation_info_id = reservation.id\r\n"

		+ "JOIN \r\n"
		+ "		product_price\r\n"
		+ "ON \r\n"
		+ "		reservation_price.product_price_id = product_price.id\r\n"

		+ "WHERE \r\n"
		+ "		reservation.reservation_email = :user \r\n"

		+ "GROUP BY \r\n"
		+ "		reservation.id;";

	public static final String UPDATE_CANCEL_STATE_TRUE = ""
		+ "UPDATE "
		+ "		reservation_info \r\n"
		+ "SET "
		+ "		cancel_flag = true \r\n"
		+ "WHERE "
		+ "		reservation_email = :user \r\n"
		+ "AND "
		+ "		id = :id; ";

}
