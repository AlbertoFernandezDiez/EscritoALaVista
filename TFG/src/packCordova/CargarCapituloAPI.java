package packCordova;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import packBD.GestorBD;
import packBeans.Capitulo;
import packBeans.Obra;

/**
 * Servlet implementation class CargarCapituloAPI
 */
@WebServlet("/api/CargarCapituloAPI")
public class CargarCapituloAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CargarCapituloAPI() {
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
		String idCS = request.getParameter("id"), loggedId = request.getParameter("loggedid");
		int idC = 0,id = 0;
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();


	
		try{
			idC = Integer.parseInt(idCS);
			id = Integer.parseInt(loggedId);
		}catch(NumberFormatException e){}

		json.put("loggedId", id);



		if(id != 0 && idC != 0)
		{
			Capitulo capitulo = GestorBD.getGestorBD().getCapitulosBeans(idC);

			json.put("titulo",capitulo.getNombre()); 

			String img = "";

			if(capitulo.getImagen() != null){			
				File imgFile = new File(filePath,capitulo.getImagen());
				img = Encoder.getMyEncoder().encodeInBase64(imgFile);
				json.put("imagen", img);
			}

			json.put("imagen", img);

			for (String aux : capitulo.getText()){
				array.put(new JSONObject().put("par", aux));
			}

			json.put("texto",array);
			json.put("comentario", capitulo.getComentarios_autor());
		}
		else
		{
			json.put("titulo",""); 

		}



		System.out.println("pedido");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
	}

}
