package packCordova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import packBD.GestorBD;
import packBeans.Autor;

/**
 * Servlet implementation class CambiarContrasenaAPI
 */
@WebServlet("/api/CambioContraAPI")
public class CambiarContrasenaAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CambiarContrasenaAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contraV ="",contraN = "",loggedID = "";
		int id = 0;

		try{
			contraV = request.getParameter("conV");
			contraN = request.getParameter("conN");
			loggedID = request.getParameter("loggedID");

		}catch(NullPointerException e){}

		boolean resultado = false;

		try{
			id = Integer.parseInt(loggedID);
		}catch(NumberFormatException e){}

		Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);

		if (id != 0 && autor.getActive() != 0){
			if(GestorBD.getGestorBD().checkUser(autor.getNombre(), contraV)!= 0){
				resultado = GestorBD.getGestorBD().changePassword(id, contraV, contraN);
			}
		}	
		PrintWriter pw = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		pw.write(String.valueOf(new JSONObject().put("value", resultado).put("desh", autor.getActive() == 0)));

	}

}
