package packServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packBD.GestorBD;

/**
 * Servlet implementation class LogInAdmin
 */
@WebServlet("/LogInAdmin")
@javax.servlet.annotation.MultipartConfig
public class LogInAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(300);
		boolean admin = false;

		try{
			admin =	(boolean)session.getAttribute("admin");
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		
		if (admin)
		{
			 request.setAttribute("admin", true);
			 RequestDispatcher rd = getServletContext().getRequestDispatcher("/Gestion/index.jsp");
		        rd.forward(request, response);
		}
		else
		{
			 response.sendRedirect("Error/noEresAdmin.html");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String password = request.getParameter("contrasena");
		
		if (GestorBD.getGestorBD().checkAdmin("admin",password)){
			 session.setAttribute("admin", true);
			 request.setAttribute("admin", true);
			 RequestDispatcher rd = getServletContext().getRequestDispatcher("/Gestion/index.jsp");
		        rd.forward(request, response);

		}
		else
		{
			 response.sendRedirect("Error/noEresAdmin.html");
		}
		
	}

}
