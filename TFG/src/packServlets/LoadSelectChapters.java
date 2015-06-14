package packServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import packBD.GestorBD;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("username");
		int idU = 0;
		
		try {
		idU = (int) session.getValue("id");
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
		if (idU != 0)
		{
		request.setAttribute("userId",idU);
		request.setAttribute("userName",user);
		}
		
		String opcionS = request.getParameter("op");
		int opcion = 0;
		if (opcionS != null)
			opcion = Integer.parseInt(opcionS);

		JSONObject json = null;

		switch(opcion){
		case 1:
			String obraId = request.getParameter("obra");
			int id = 0;
			if (obraId != null)
				id = Integer.parseInt(obraId);

			json = loadSelectorCap(id);

			break;

		case 2:
			String capId = request.getParameter("capitulo");
			int idC = 0;
			if (capId != null)
				idC = Integer.parseInt(capId);

			json = loadCapitulo(idC);
			break;
		default:
			break;
		}





		response.setContentType("application/x-json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter out = response.getWriter();

		out.print(json);
		out.flush();
		out.close();
	}

	private JSONObject loadSelectorCap(int idC) {
		packBeans.Obra obra = GestorBD.getGestorBD().getObraBeans(idC);
		JSONObject jsonObra = new JSONObject(); 
		jsonObra.put("titulo", obra.getTitulo());
		jsonObra.put("resumen", obra.getResumen());
		jsonObra.put("imagen", obra.getPortada());


		ArrayList<packBeans.Capitulo> listCap = GestorBD.getGestorBD().getCapituloBeans(idC);
		Iterator<packBeans.Capitulo> itC = listCap.iterator();
		packBeans.Capitulo cap = null;
		JSONObject jsonCap = null;
		
		ArrayList<JSONObject> array = new ArrayList<>();
		while (itC.hasNext()){
			jsonCap = new JSONObject();
			cap = itC.next();
			jsonCap.put("idC", cap.getId());
			jsonCap.put("nombre", cap.getNombre());
			array.add(jsonCap);
		}
		jsonObra.put("capitulos", array);
		return jsonObra;
	}
	
	private JSONObject loadCapitulo(int id) {
		packBeans.Capitulo cap = GestorBD.getGestorBD().getCapitulosBeans(id);

		JSONObject jsonCapitulo = new JSONObject(); 
		jsonCapitulo.put("titulo", cap.getNombre());
		JSONArray array = new JSONArray();	
		for (String x:cap.getText())
			{
				array.put(x);
			}
		jsonCapitulo.put("capitulo", array);
		jsonCapitulo.put("comentarioA", cap.getComentarios_autor());
		
		jsonCapitulo.put("imagen", cap.getImagen());


		return jsonCapitulo;
	}

}
