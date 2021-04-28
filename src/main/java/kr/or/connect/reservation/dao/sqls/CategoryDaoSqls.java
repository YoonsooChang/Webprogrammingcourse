package kr.or.connect.reservation.dao.sqls;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = ""
		+ "SELECT "
		+ "		category.id as id, category.name as name, COUNT(*) as count\n"
		+ "FROM "
		+ "		category \n"
		+ "LEFT OUTER JOIN "
		+ "		product \n"
		+ "ON "
		+ "		category.id = product.category_id \n"
		+ "LEFT OUTER JOIN "
		+ "		display_info \n"
		+ "ON"
		+ " 	product.id = display_info.product_id \n"
		+ "GROUP BY "
		+ "		category_id";
}