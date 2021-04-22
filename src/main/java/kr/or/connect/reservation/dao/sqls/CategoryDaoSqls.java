package kr.or.connect.reservation.dao.sqls;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT category.*, COUNT(*) \n"
		+ "FROM category \n"
		+ "LEFT OUTER JOIN product \n"
		+ "ON category.id = product.category_id \n"
		+ "GROUP BY category_id";
}