package kr.or.connect.todolist.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	private TodoDao todoDao;

	public MainServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		todoDao = TodoDao.getInstance();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		List<TodoDto> todoItems = todoDao.getTodos();

		Map<String, List<TodoDto>> todoLists = todoItems.stream()
			.collect(Collectors.groupingBy(todo -> todo.getType().toString().toLowerCase()));

		request.setAttribute("todolists", todoLists);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/main.jsp");
		requestDispatcher.forward(request, response);

	}

}
