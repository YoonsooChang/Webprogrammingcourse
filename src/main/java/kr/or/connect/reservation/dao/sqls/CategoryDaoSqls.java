package kr.or.connect.reservation.dao.sqls;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT id, name, count FROM category \n"
		+ "JOIN  (SELECT category_id, COUNT(*) AS count FROM product GROUP BY category_id) AS category_count \n"
		+ "ON category.id = category_count.category_id;";
}
