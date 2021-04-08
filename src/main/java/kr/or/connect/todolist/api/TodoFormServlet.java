package kr.or.connect.todolist.api;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todolist.dao.TodoDao;
import kr.or.connect.todolist.dto.TodoDto;

@WebServlet("/register")
public class TodoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoFormServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/todoForm.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String addTitle = request.getParameter("title");
		String addName = request.getParameter("name");
		int addSequence = Integer.parseInt(request.getParameter("sequence"));

		TodoDao todoDao = new TodoDao();
		TodoDto addTarget = new TodoDto(1L, addName, "", addSequence, addTitle, "");

		todoDao.addTodo(addTarget);
		response.sendRedirect("http://localhost:8080/todoapp/main");
	}

}
