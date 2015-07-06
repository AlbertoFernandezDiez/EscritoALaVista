package packServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packBD.GestorBD;

/**
 * Servlet implementation class HabilitarDeshabilitarObraAutor
 */
@WebServlet({ "/HabilitarDeshabilitarObraAutor", "/HDOA" })
public class HabilitarDeshabilitarObraAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HabilitarDeshabilitarObraAutor() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		boolean admin = false;
		try{
			admin = (boolean)session.getAttribute("admin");
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}

		if (admin)
		{
			// El id en BD a deshabilitar/habilitar
			String idS = request.getParameter("id");
			//Elemento a deshabilitar Autor (0) Obra (1)
			String tipoS = request.getParameter("tipo");
			//Habilitar(1) Deshabilitar(0)
			String opcionS = request.getParameter("opcion");

			int id = 0,tipo = 0,opcion = 0;
			try{
				id = Integer.parseInt(idS);
				tipo = Integer.parseInt(tipoS);
				opcion = Integer.parseInt(opcionS);
			}
			catch (NumberFormatException e)
			{
				e.printStackTrace();
			}
			boolean resultado = false;
			switch(tipo)
			{
			case 0:
				resultado = GestorBD.getGestorBD().modificarVisibilidadAutor(id,opcion);
				break;
			case 1:
				resultado =	GestorBD.getGestorBD().modificarVisibilidadObra(id,opcion);
				break;
			}
			PrintWriter pw = response.getWriter();
			pw.write(String.valueOf(resultado));
		}
		else
			response.sendRedirect("Error/noEresAdmin.html");

	}

}
