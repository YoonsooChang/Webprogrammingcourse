package kr.or.connect.todolist.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.connect.todolist.dao.TodoDao;
import kr.or.connect.todolist.dto.TodoDto;

@WebServlet("/type")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodoTypeServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("charset=UTF-8");

		Long id = Long.parseLong(request.getParameter("id"));
		String type = request.getParameter("type");

		TodoDao todoDao = new TodoDao();
		TodoDto updateTarget = new TodoDto(id, "", "", -1, "", type);

		int updateResult = todoDao.updateTodo(updateTarget);

		PrintWriter out = response.getWriter();
		out.print(updateResult == 1 ? "Success" : "Failure");
		out.close();

	}

}
