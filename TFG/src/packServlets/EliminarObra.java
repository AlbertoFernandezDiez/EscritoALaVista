package packServlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packBD.GestorBD;
import packBeans.Obra;

/**
 * Servlet implementation class EliminarObra
 */
@WebServlet({ "/EliminarObra", "/EO" })
public class EliminarObra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarObra() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

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
			ArrayList<Obra> lista = GestorBD.getGestorBD().getObrasBeans(0, 0, 0);
			HashMap<Integer, String> autores = GestorBD.getGestorBD().getHasMapAutores();

			request.setAttribute("obras", lista);
			request.setAttribute("autores", autores);

			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Gestion/eliminarObra.jsp");
			rd.forward(request, response);
		}
		else
		{
			response.sendRedirect("/Error/noEresAdmin.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

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
			int id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
		}
		else
		{}
	}

}


