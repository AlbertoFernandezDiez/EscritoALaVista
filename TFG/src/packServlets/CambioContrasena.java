package packServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packBD.GestorBD;

/**
 * Servlet implementation class CambioContrasena
 */
@WebServlet({ "/CambioContrasena", "/CCA" })
public class CambioContrasena extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CambioContrasena() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
				

		boolean admin = false;
		try{
			admin = (boolean) session.getAttribute("admin");
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		if (admin)
		{
			request.setAttribute("admin", admin);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Gestion/cambioContrasena.jsp");
			rd.forward(request, response);
		}
		else
			response.sendRedirect("/Error/noEresAdmin.html");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		boolean admin = false;

		try {
		admin = (boolean) session.getAttribute("admin");
			if (admin)
			{
				String old = null, newC = null;

				old = request.getParameter("old");
				newC = request.getParameter("newC");

			boolean result = GestorBD.getGestorBD().changePasswordAdmin("admin",old,newC);
			
				// TODO Auto-generated method stub
				PrintWriter pw = response.getWriter();
				pw.write(String.valueOf(result));

			}
			else
				response.sendRedirect("Error/NoLogeado.html");

		}catch(NullPointerException e){
			e.printStackTrace();
			response.sendRedirect("Error/NoLogeado.html");
		}
	}

}
