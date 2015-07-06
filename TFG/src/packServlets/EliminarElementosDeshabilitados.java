package packServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packBD.GestorBD;

/**
 * Servlet implementation class EliminarElementosDeshabilitados
 */
@WebServlet({ "/EliminarElementosDeshabilitados", "/EED" })
public class EliminarElementosDeshabilitados extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarElementosDeshabilitados() {
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
admin = (boolean)session.getAttribute("admin");
}
catch(NullPointerException e)
{
	e.printStackTrace();
}
		if (admin)
		{
ArrayList<Autor> listaAutor = GestorBD.getGestorBD().getAutoresBeansDeshabilitados();
ArrayList<Obra> listaObra = GestorBD.getGestorBD().getObrasBeansDeshabilitadas();
			
		}
		else
		{
			System.out.println("No eres Admin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
