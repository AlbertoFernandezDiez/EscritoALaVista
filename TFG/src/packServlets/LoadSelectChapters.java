package packServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import packBD.GestorBD;
import packClases.Capitulo;
import packClases.ListaCapitulos;
import packClases.Obra;

/**
 * Servlet implementation class LoadSelectChapters
 */
@WebServlet("/LoadSelectChapters")
public class LoadSelectChapters extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadSelectChapters() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String obraId = request.getParameter("obra");
		String capId = request.getParameter("capitulo");
		int id = 0;
		int idC = 0;
		JSONObject json = new JSONObject(); 

		if (obraId != null)
			id = Integer.parseInt(obraId);

		if (capId != null)
			idC = Integer.parseInt(capId);

		if(idC == 0)
		{
			if(id != 0){

				Obra obra = GestorBD.getGestorBD().getObra(id);

				JSONObject jsonObra = new JSONObject(); 
				jsonObra.put("titulo", obra.getTitulo());
				jsonObra.put("resumen", obra.getResumen());


				json.put("campos", jsonObra);
				ListaCapitulos listCap = GestorBD.getGestorBD().getCapitulos(id);
				Iterator<Capitulo> itC = listCap.getIterator();
Capitulo cap = null;

JSONArray array = new JSONArray();
				while (itC.hasNext()){
					jsonObra = new JSONObject();
cap = itC.next();
				jsonObra.put("idC", cap.getId());
				jsonObra.put("nombre", cap.getNombre());
				array.put(jsonObra);
				}
				
				json.put("capitulos", array);
			}
		}
		
		if(idC != 0){

			Capitulo cap = GestorBD.getGestorBD().getCapitulo(idC);

			JSONObject jsonCapitulo = new JSONObject(); 
			jsonCapitulo.put("titulo", cap.getNombre());
			jsonCapitulo.put("capitulo", cap.getTextoC());
			jsonCapitulo.put("comentarioA", cap.getComentariosAutor());

			json.put("camposC", jsonCapitulo);
			ListaCapitulos listCap = GestorBD.getGestorBD().getCapitulos(id);
			Iterator<Capitulo> itC = listCap.getIterator();

			/*	while (itC.hasNext()){

			}*/

		}
		response.setContentType("application/x-json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(json);
		out.flush();
		out.close();
	}

}
