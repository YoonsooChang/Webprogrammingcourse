import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/today")
public class TodayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public TodayServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter htmlContentsWriter = response.getWriter();
		String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/M/d HH:mm"));

		htmlContentsWriter.println("<html>");
		htmlContentsWriter.println("<head><title>My Homepage</title></head>");
		htmlContentsWriter.println("<body>");
		htmlContentsWriter.println("<a href='/aboutme/index.html'>메인화면</a>");
		htmlContentsWriter
			.println("<h1 style=\"text-align:center;line-height:50vh;margin:20vh 0\">현재 시간 : " + currentTime + "</h1>");
		htmlContentsWriter.println("</body>");
		htmlContentsWriter.println("</html>");

		htmlContentsWriter.close();
	}

}
