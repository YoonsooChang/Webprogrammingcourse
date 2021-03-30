import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter htmlContents = response.getWriter();
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		htmlContents.println("<html>");
		htmlContents.println("<head><title>My Homepage</title></head>");
		htmlContents.println("<body>");
		htmlContents.println("<a href='/aboutme/index.html'>메인화면</a>");
		htmlContents.println("<h1 style=\"text-align:center;line-height:50vh;margin:20vh 0\">현재 시간 : " + currentDateTime + "</h1>");
		htmlContents.println("</body>");
		htmlContents.println("</html>");
		
		htmlContents.close();
	}

}
