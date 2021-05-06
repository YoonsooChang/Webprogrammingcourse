package kr.or.connect.reservation.dao.sqls;

public class CommentDaoSqls {
	public static final String SELECT_BY_DISPLAY_ID_EXCEPT_IMAGE = ""
		+ "SELECT "
		+ "		commentitem.comment 	AS comment, "
		+ "    	commentitem.id			AS comment_id, "
		+ "		commentitem.create_date AS create_date, "
		+ "    	commentitem.modify_date AS modify_date, "
		+ "		commentitem.score 		AS score, "
		+ "    	commentitem.product_id 	AS product_id, \n"

		+ "    	reservation.reservation_date	 AS reservation_date, "
		+ "    	reservation.reservation_email	 AS reservation_email, "
		+ "    	reservation.id					 AS reservation_info_id, "
		+ "    	reservation.reservation_name	 AS reservation_name, "
		+ "    	reservation.reservation_tel		 AS reservation_telephone \n"

		+ " FROM "
		+ "		reservation_user_comment AS commentitem \n"

		+ " JOIN "
		+ "		reservation_info AS reservation \n"

		+ " ON "
		+ "		commentitem.reservation_info_id = reservation.id\r\n"

		+ " WHERE "
		+ "		reservation.display_info_id = :displayInfoId \n"

		+ " ORDER BY "
		+ "		comment_id DESC;";

	public static final String SELECT_BY_DISPLAY_ID_EXCEPT_IMAGE_LIMIT = ""
		+ "SELECT "
		+ "		commentitem.comment 	AS comment, "
		+ "    	commentitem.id			AS comment_id, "
		+ "		commentitem.create_date AS create_date, "
		+ "    	commentitem.modify_date AS modify_date, "
		+ "		commentitem.score 		AS score, "
		+ "    	commentitem.product_id 	AS product_id, \n"

		+ "    	reservation.reservation_date	 AS reservation_date, "
		+ "    	reservation.reservation_email	 AS reservation_email, "
		+ "    	reservation.id					 AS reservation_info_id, "
		+ "    	reservation.reservation_name	 AS reservation_name, "
		+ "    	reservation.reservation_tel		 AS reservation_telephone \n"

		+ " FROM "
		+ "		reservation_user_comment AS commentitem \n"

		+ " JOIN "
		+ "		reservation_info AS reservation \n"

		+ " ON "
		+ "		commentitem.reservation_info_id = reservation.id\r\n"

		+ " WHERE "
		+ "		reservation.display_info_id = :displayInfoId \n"

		+ " ORDER BY "
		+ "		comment_id DESC \n"

		+ " LIMIT :limit;";

	public static final String SELECT_IMAGES_BY_COMMENT_ID = ""
		+ "SELECT "
		+ "		file.content_type	AS content_type,"
		+ "		file.create_date	AS create_date,"
		+ "		file.delete_flag	AS delete_flag,"
		+ "		file.id 			AS file_id,"
		+ "		file.file_name		AS file_name,"
		+ "		file.modify_date 	AS modify_date,"
		+ "		file.save_file_name AS save_file_name, \n"

		+ "		image.id 							AS image_id,"
		+ "		image.reservation_info_id			AS reservation_info_id,"
		+ "		image.reservation_user_comment_id	AS reservation_comment_id \n"

		+ "FROM "
		+ "		reservation_user_comment_image AS image \n"

		+ "JOIN "
		+ "		file_info AS file \n"

		+ "ON "
		+ "		file.id = image.file_id \n"

		+ "WHERE "
		+ "		image.reservation_info_id = :reservationInfoId \n"

		+ "AND "
		+ "		image.reservation_user_comment_id = :commentId;";

}
