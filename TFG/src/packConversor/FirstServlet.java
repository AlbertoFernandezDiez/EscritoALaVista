package packConversor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	@Override

	 protected void doGet(HttpServletRequest req, HttpServletResponse resp)

	                  throws ServletException, IOException {

	           

	            PrintWriter out = resp.getWriter();

	            out.println("<html>");

	            out.println("<body>");

	            out.println("hoy es " + new Date() + "<br>");
	            out.println(GestorBD.getGestorBD().getCapitulos());

	            out.println("</body>");

	            out.println("</html>");

	 }

}
