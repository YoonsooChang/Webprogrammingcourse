package kr.or.connect.todolist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.todolist.dto.TodoDto;

public class TodoDao {
	private static final String dburl = "jdbc:mysql://10.113.116.52:13306/intern06";
	private static final String dbUser = "intern06";
	private static final String dbpasswd = "intern06";

	public TodoDao() {
		super();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<TodoDto> getTodos() {
		String sql = "SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY regdate DESC";
		List<TodoDto> todos = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Long id = rs.getLong("id");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int sequence = rs.getInt("sequence");
				String type = rs.getString("type");
				String regDate = new SimpleDateFormat("yyyy-MM-dd").format(rs.getString("regdate"));

				TodoDto todo = new TodoDto(id, name, regDate, sequence, title, type);
				todos.add(todo);
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		return todos;
	}

	public String updateTodo(TodoDto todo) {
		String sql = "UPDATE todo SET type = ? WHERE id = ?";
		int updateCount = 0;
		String updateResult = null;

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {

			String todoType = todo.getType();
			ps.setString(1, (todoType.equals("TODO") ? "DOING" : "DONE"));
			ps.setLong(2, todo.getId());

			updateCount = ps.executeUpdate();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		if (updateCount == 0) {
			updateResult = "Failure";
		} else {
			updateResult = "Success";
		}

		return updateResult;
	}

	public int addTodo(TodoDto todo) {
		String sql = "INSERT INTO todo (title, name, sequence) VALUES (?, ?, ?)";
		int insertCount = 0;

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());

			insertCount = ps.executeUpdate();
			return insertCount;

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return insertCount;

	}
}
