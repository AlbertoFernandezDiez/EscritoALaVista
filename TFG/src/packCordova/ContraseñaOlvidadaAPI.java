package packCordova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.math.RandomUtils;
import org.json.JSONObject;

import packBD.GestorBD;
import packBeans.Autor;
import packServlets.MailServer;

/**
 * Servlet implementation class ContraseñaOlvidadaAPI
 */
@WebServlet("/api/ContrasenaOlvidadaAPI")
public class ContraseñaOlvidadaAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String username, password;

	public void init( ){
		username =getServletContext().getInitParameter("username"); 
		password = getServletContext().getInitParameter("password"); 
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContraseñaOlvidadaAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("email");
		String contrasena = String.valueOf(RandomUtils.nextInt());
		Autor autor = GestorBD.getGestorBD().getAutorBeansByEmail(mail);
		boolean resultado = false;

		if (autor != null)
		{
			resultado = GestorBD.getGestorBD().recuperarContrasena(autor.getId(),contrasena);
		}
		if (resultado == true)
		{
			//Mandar email
			try {
				MailServer.getMyMailServer(username, password).mandarEmailRecuperarContrasena(autor.getId(), contrasena);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Nombre Usuario: " + autor.getNombre());
			System.out.println("\nNueva contaseña: " + contrasena);
		}

		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(new JSONObject().put("value", resultado).toString());
	}

}
