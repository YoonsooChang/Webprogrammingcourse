package kr.or.connect.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todolist.dto.TodoDto;

public class TodoDao {
	private static String dburl = "jdbc:mysql://10.113.116.52:13306/intern06";
	private static String dbUser = "intern06";
	private static String dbpasswd = "intern06";

	public List<TodoDto> getTodos() {
		List<TodoDto> todos = new ArrayList<>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "SELECT id, title, name, sequence, type, DATE_FORMAT(regdate, '%Y-%m-%d') FROM todo ORDER BY regdate DESC";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Long id = rs.getLong(1);
					String title = rs.getString(2);
					String name = rs.getString(3);
					int sequence = rs.getInt(4);
					String type = rs.getString(5);
					String regDate = rs.getString(6);

					TodoDto todo = new TodoDto(id, name, regDate, sequence, title, type);
					todos.add(todo);
				}
			} catch (Exception qEx) {
				qEx.printStackTrace();
			}

		} catch (Exception connPsEx) {
			connPsEx.printStackTrace();
		}

		return todos;
	}

	public int updateTodo(TodoDto todo) {
		int updateCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String todoType = todo.getType();
		String sql = "UPDATE todo SET type = ? WHERE id = ?";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, (todoType.equals("TODO") ? "DOING" : "DONE"));
			ps.setLong(2, todo.getId());

			try {
				updateCount = ps.executeUpdate();
			} catch (Exception uEx) {
				uEx.printStackTrace();
			}

		} catch (Exception connPsEx) {
			connPsEx.printStackTrace();
		}

		return updateCount;
	}

	public int addTodo(TodoDto todo) {
		int insertCount = 0;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String sql = "INSERT INTO todo (title, name, sequence) VALUES (?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());

			try {
				insertCount = ps.executeUpdate();
			} catch (Exception uEx) {
				uEx.printStackTrace();
			}

		} catch (Exception connPsEx) {
			connPsEx.printStackTrace();
		}
		return insertCount;

	}
}
