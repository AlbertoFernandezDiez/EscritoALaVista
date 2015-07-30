package packCordova;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
import packBeans.Comentario;
import packBeans.Obra;

/**
 * Servlet implementation class CambiarObraApi
 */
@WebServlet("/api/CambiarObraApi")
public class CambiarObraApi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CambiarObraApi() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String /*url = request.getParameter("url"),*/ loggedId = request.getParameter("loggedid");
		int id = 0;
		JSONObject json, json1 = new JSONObject();
		JSONArray array = new JSONArray();

		/*try{
			id = UsuariosLoggeados.getMyUsuariosLogeados().getUsuario(loggedId);
		}catch(NullPointerException e){}*/
		
		try{
			id = Integer.parseInt(loggedId);
		}catch(NumberFormatException e){}

		//json1.put("url", url);
		json1.put("loggedId", id);

		if(id != 0)
		{
			ArrayList<Obra> listaArrayList = GestorBD.getGestorBD().getObrasBeans( 0, 0, id);
			
			for (Obra obra : listaArrayList)
			{
				json = new JSONObject();
				System.out.println(obra.getTitulo());
				json.put("id", obra.getId());
				json.put("nombre", obra.getTitulo());
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
