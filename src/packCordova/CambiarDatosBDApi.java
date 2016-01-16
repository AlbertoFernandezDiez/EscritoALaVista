package packCordova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.mysql.fabric.Response;

import packBD.GestorBD;
import packBeans.Autor;

/**
 * Servlet implementation class CambiarDatosBDApi
 */
@WebServlet("/api/CambiarDatosBDApi")
public class CambiarDatosBDApi extends HttpServlet {
	private String filePath;

	public void init( ){
		// Get the file location where it would be stored.
		filePath =getServletContext().getInitParameter("file-upload"); 
	}

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CambiarDatosBDApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email ="",about = "",pais = "", imagenS = "",loggedID = "";
		int id = 0;

		try{
			email = request.getParameter("email");
			about = request.getParameter("about");
			pais = request.getParameter("pais");
			loggedID = request.getParameter("loggedid");
			imagenS = request.getParameter("foto");
		}catch(NullPointerException e){}

		boolean resultado = false;

		/*		try{
			id = UsuariosLoggeados.getMyUsuariosLogeados().getUsuario(loggedID);
		}catch(NullPointerException e){}*/

		System.out.println(email + "\n" + pais + "\n" + about + "\n" + imagenS);

		try{
			id = Integer.parseInt(loggedID);
		}catch(NumberFormatException e){}

		Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);

		if (id != 0 && autor.getActive() != 0){
			String path;
			
			if (!imagenS.equals(""))
				path = Encoder.getMyEncoder().decodeInBase64(imagenS, filePath);
			else
				path = autor.getImagen();

			resultado = GestorBD.getGestorBD().updateAutor(id, email, pais, about, path);
		}	
		PrintWriter pw = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		pw.write(String.valueOf(new JSONObject().put("value", resultado).put("desh", autor.getActive() == 0)));
	}

}
