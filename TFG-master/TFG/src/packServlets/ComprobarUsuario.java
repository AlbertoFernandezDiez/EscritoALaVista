package packServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;

/**
 * Servlet implementation class ComprobarUsuario
 */
@WebServlet({ "/ComprobarUsuario", "/CU" })
public class ComprobarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprobarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		boolean resultado = false;
		
		if(nombre != null)
		resultado = GestorBD.getGestorBD().comprobarNombre(nombre);

		PrintWriter pw = response.getWriter();
		pw.write(String.valueOf(resultado));
	}

}
