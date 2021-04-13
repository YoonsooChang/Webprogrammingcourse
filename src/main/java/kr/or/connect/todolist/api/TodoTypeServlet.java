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
import kr.or.connect.todolist.dto.TodoStatus;

@WebServlet("/update")
public class TodoTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDao todoDao;

	public TodoTypeServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		todoDao = TodoDao.getInstance();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("charset=UTF-8");

		Long id = Long.parseLong(request.getParameter("id"));
		TodoStatus type = TodoStatus.valueOf(request.getParameter("type"));

		TodoDto updateTarget = TodoDto.createUpdateTodoDto(id, type);
		String updateResult = todoDao.updateTodo(updateTarget);

		PrintWriter out = response.getWriter();

		out.print(updateResult);
		out.close();

	}

}
