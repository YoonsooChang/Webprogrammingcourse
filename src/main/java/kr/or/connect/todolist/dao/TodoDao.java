package kr.or.connect.todolist.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import kr.or.connect.todolist.dto.TodoDto;
import kr.or.connect.todolist.dto.TodoStatus;

public class TodoDao {
	private static TodoDao instance;
	private String dburl;
	private String dbUser;
	private String dbpasswd;

	private TodoDao() {
		super();
		Properties props = new Properties();
		Reader reader;

		try {

			reader = new FileReader(getClass().getResource("jdbc.properties").getPath());
			props.load(reader);
			Class.forName(props.getProperty("driver"));
			dburl = props.getProperty("url");
			dbUser = props.getProperty("user");
			dbpasswd = props.getProperty("password");

		} catch (ClassNotFoundException classEx) {
			classEx.printStackTrace();
		} catch (FileNotFoundException fileEx) {
			fileEx.printStackTrace();
		} catch (IOException ioEx) {
			ioEx.printStackTrace();
		}

	}

	public static TodoDao getInstance() {
		return Optional.ofNullable(instance).orElse(instance = new TodoDao());
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
				TodoStatus type = TodoStatus.valueOf(rs.getString("type"));
				LocalDate regDate = rs.getDate("regdate").toLocalDate();

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

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, todo.getType().getNextStatus().toUpperCase());
			ps.setLong(2, todo.getId());

			updateCount = ps.executeUpdate();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		String updateResult = null;

		if (updateCount == 0) {
			updateResult = "Failure";
		} else {
			updateResult = "Success";
		}

		return updateResult;
	}

	public String addTodo(TodoDto todo) {
		String sql = "INSERT INTO todo (title, name, sequence) VALUES (?, ?, ?)";
		int addCount = 0;

		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, todo.getTitle());
			ps.setString(2, todo.getName());
			ps.setInt(3, todo.getSequence());

			addCount = ps.executeUpdate();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		String addResult = null;

		if (addCount == 0) {
			addResult = "Failure";
		} else {
			addResult = "Success";
		}

		return addResult;
	}
}
