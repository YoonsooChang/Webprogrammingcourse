package kr.or.connect.reservation.dao.sqls;

public class CommentDaoSqls {

	public static final String SELECT_BY_DISPLAY_ID = ""
		+ "SELECT \n"
		+ "		commentitem.comment 	AS comment,"
		+ "		commentitem.id			AS comment_id,"
		+ "		commentitem.create_date AS create_date,"
		+ "		commentitem.modify_date AS modify_date,"
		+ "		commentitem.score 		AS score,"
		+ "		commentitem.product_id 	AS product_id, \n"

		+ "		reservation.reservation_date	 AS reservation_date,"
		+ "		reservation.reservation_email	 AS reservation_email,"
		+ "		reservation.id					 AS reservation_info_id,"
		+ "		reservation.reservation_name	 AS reservation_name,"
		+ "		reservation.reservation_tel		 AS reservation_telephone, \n"

		+ "    	file.content_type	AS content_type,"
		+ "		file.create_date	AS file_create_date,"
		+ "		file.delete_flag	AS delete_flag,"
		+ "		file.id 			AS file_id,"
		+ "		file.file_name		AS file_name,"
		+ "		file.modify_date 	AS file_modify_date,"
		+ "		file.save_file_name AS save_file_name, \n"

		+ "		image.id 							AS image_id,"
		+ "		image.reservation_info_id			AS image_reservation_info_id,"
		+ "		image.reservation_user_comment_id	AS reservation_comment_id \n"

		+ "FROM \n"
		+ "		reservation_user_comment AS commentitem \n"

		+ "JOIN \n"
		+ "		reservation_info AS reservation  \n"

		+ "ON \n"
		+ "		commentitem.reservation_info_id = reservation.id \n"

		+ "LEFT OUTER JOIN \n"
		+ "		reservation_user_comment_image AS image \n"

		+ "ON \n"
		+ "		image.reservation_user_comment_id = commentitem.id \n"

		+ "AND \n"
		+ "		image.reservation_info_id = reservation.id \n"

		+ "LEFT OUTER JOIN \n"
		+ "		file_info AS file \n"

		+ "ON \n"
		+ "		file.id = image.file_id \n"

		+ "WHERE \n"
		+ "		reservation.display_info_id = :displayInfoId \n"

		+ "GROUP BY "
		+ "		comment_id \n"

		+ "ORDER BY \n"
		+ "		comment_id DESC;";

}
