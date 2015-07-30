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
 * Servlet implementation class ListarCapitulosAPI
 */
@WebServlet("/api/ListarCapitulosAPI")
public class ListarCapitulosAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarCapitulosAPI() {
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

		String idOS = request.getParameter("id"), loggedId = request.getParameter("loggedid");
		int idO = 0,id = 0;
		JSONObject json, json1 = new JSONObject();
		JSONArray array = new JSONArray();

		/*try{
			id = UsuariosLoggeados.getMyUsuariosLogeados().getUsuario(loggedId);
		}catch(NullPointerException e){}*/

		try{
			idO = Integer.parseInt(idOS);
			id= Integer.parseInt(loggedId);
		}catch(NumberFormatException e){}

		json1.put("loggedId", loggedId);

		Obra obra = GestorBD.getGestorBD().getObraBeans(idO);

		if (obra != null){
			json1.put("titulo",obra.getTitulo()); 
		json1.put("resumen",obra.getResumen());

		String img = "";

		if(obra.getPortada() != null){			
			File imgFile = new File(filePath,obra.getPortada());
			img = Encoder.getMyEncoder().encodeInBase64(imgFile);
			json1.put("imagen", img);
		}
		}
		else{
			json1.put("titulo",""); 
			json1.put("resumen","");
		}





		if(id != 0 && idO != 0)
		{
			ArrayList<Capitulo> listaArrayList = GestorBD.getGestorBD().getCapituloBeans(idO);

			json1.put("loggedId", loggedId);


			for (Capitulo capitulo : listaArrayList)
			{
				json = new JSONObject();
				json.put("id", capitulo.getId());
				json.put("nombre", capitulo.getNombre());
				array.put(json);
			}

			json1.put("lista", array);
		}


		System.out.println("pedido");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(json1.toString());

	}

}
