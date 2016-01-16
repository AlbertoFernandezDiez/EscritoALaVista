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
import javax.xml.ws.http.HTTPBinding;

import packBD.GestorBD;

/**
 * Servlet implementation class LogIn
 */
@WebServlet("/LogIn")
@javax.servlet.annotation.MultipartConfig
public class LogIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


		//Recuperamos los parametros
		String username = request.getParameter("usuario");
		String password = request.getParameter("contrasena");

		//Comprobamos en BD si el usuario y la contraseña 
		//son correctos, recuperamos el indice si son
		//correctos, sino un 0
		int id = GestorBD.getGestorBD().checkUser(username,password);
		
		if (id != 0){

			if(GestorBD.getGestorBD().checkAutorDeshabilitado(id)){
				response.sendRedirect("Error/UsuarioDeshabilitado.html");
			}
			else{
				//Los datos son correctos, creamos 
				//una sesion con el indice y el 
				//nombre de usuario
				HttpSession session = request.getSession();
				session.setAttribute("username",username);
				session.setAttribute("id", id);
			//	session.setMaxInactiveInterval(600);

				//Redireccionamos a la 
				//pagina principal
				response.sendRedirect("Index");
			}

		}
		else
			response.sendRedirect("Error/LoginFallido.html");		
	}

}
