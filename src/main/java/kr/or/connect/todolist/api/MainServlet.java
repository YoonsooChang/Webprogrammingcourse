package kr.or.connect.todolist.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todolist.dao.TodoDao;
import kr.or.connect.todolist.dto.TodoDto;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		TodoDao todoDao = new TodoDao();
		List<TodoDto> todoItems = todoDao.getTodos();

		List<TodoDto> todos = new ArrayList<>();
		List<TodoDto> doings = new ArrayList<>();
		List<TodoDto> dones = new ArrayList<>();

		for (TodoDto todo : todoItems) {
			String todoType = todo.getType();
			if (todoType.equals("TODO"))
				todos.add(todo);
			else if (todoType.equals("DOING"))
				doings.add(todo);
			else
				dones.add(todo);
		}

		request.setAttribute("todos", todos);
		request.setAttribute("doings", doings);
		request.setAttribute("dones", dones);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}

}
