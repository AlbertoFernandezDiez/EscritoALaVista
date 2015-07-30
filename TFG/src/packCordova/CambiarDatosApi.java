package packCordova;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import packBD.GestorBD;
import packBeans.Autor;

/**
 * Servlet implementation class CambiarDatosApi
 */
@WebServlet("/api/CambiarDatosApi")
public class CambiarDatosApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CambiarDatosApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init( ){
		// Get the file location where it would be stored.
		filePath =getServletContext().getInitParameter("file-upload"); 
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedID = "";
		int id = 0;

		try{
			loggedID = request.getParameter("loggedid");
		}catch(NullPointerException e){}

		/*try{
			id = UsuariosLoggeados.getMyUsuariosLogeados().getUsuario(loggedID);
		}catch(NullPointerException e){}*/

		try{
			id = Integer.parseInt(loggedID);
		}
		catch(NumberFormatException e){
		}

		JSONArray array = new JSONArray();
		JSONObject json = new JSONObject();

		if (id != 0){
			Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);
			json.put("email", autor.getEmail());
			json.put("pais", autor.getPais());

			String img = "";

			if(autor.getImagen() != null){			
				File imgFile = new File(filePath,autor.getImagen());
				try{
				img = Encoder.getMyEncoder().encodeInBase64(imgFile);
				json.put("imagen", img);
				}catch(FileNotFoundException e){}
			}

			for (String aux : autor.getAbout()){
				array.put(new JSONObject().put("texto", aux));
			}
			json.put("about", array);
		}

		System.out.println("pedido");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
	}

}
