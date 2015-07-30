package packCordova;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import packBD.GestorBD;

/**
 * Servlet implementation class RegistrarseApi
 */
@WebServlet("/api/RegistrarseApi")
public class RegistrarseApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String filePath;

	public void init( ){
		// Get the file location where it would be stored.
		filePath =getServletContext().getInitParameter("file-upload"); 
	}
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrarseApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean resultado = false;

		String about = "", contrasena = "", email = "", foto = ""
				, nombre = "", pais = "", nacimiento = "";
		Date fecha;

		try{
			nombre = request.getParameter("nombre");
			contrasena = request.getParameter("contrasena");
			email = request.getParameter("email");
			pais = request.getParameter("pais");
			nacimiento = request.getParameter("nacimiento");
			about = request.getParameter("about");
			foto = request.getParameter("foto");
		}catch(NullPointerException e){}


		try{
			fecha = Date.valueOf(nacimiento);
		}
		catch(IllegalArgumentException e){
			e.printStackTrace();
			fecha= new Date(System.currentTimeMillis());
		}

		String path = "";
		
		if (!foto.equals(""))
		path = Encoder.getMyEncoder().decodeInBase64(foto, filePath);
		else
		path = null;

		if (GestorBD.getGestorBD().addUser(nombre,email,contrasena,pais,fecha,about,path) != 0)
			resultado = true;

		PrintWriter pw = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		pw.write(new JSONObject().put("value", resultado).toString());
	}

}
