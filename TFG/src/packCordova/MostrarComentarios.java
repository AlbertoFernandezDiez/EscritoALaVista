package packCordova;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

/**
 * Servlet implementation class MostrarComentarios
 */
@WebServlet("/api/MostrarComentarios")
public class MostrarComentarios extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarComentarios() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idCS = request.getParameter("idC");
		String idOS = request.getParameter("idO");
		//String url = request.getParameter("url");
		int idO = 0, idC = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy hh:mm");

		JSONArray array = new JSONArray();
		JSONObject json, jsonf = null;

		try{
			idC = Integer.parseInt(idCS);
			idO = Integer.parseInt(idOS);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}

		if(idC != 0 && idO != 0)
		{
			Capitulo cap = GestorBD.getGestorBD().getCapitulosBeans(idC);
			ArrayList<Comentario> lista = GestorBD.getGestorBD().getComentariosBeans(idO, idC);
			HashMap<Integer, String> map = GestorBD.getGestorBD().getHasMapAutores();

			jsonf = new JSONObject();
			jsonf.put("titulo", cap.getNombre());
			jsonf.put("idC", idC);
			
			for (Comentario com : lista){
				json = new JSONObject();
				json.put("idAutor", com.getAutor());
				json.put("texto", com.getTexto());
				json.put("fecha", format.format(new Date(com.getFecha_comentario().getTime())));
				json.put("autor", map.get(com.getAutor()));
				array.put(json);
			}
			
			jsonf.put("comentarios", array);
			//jsonf.put("url", url);

			
		}
		System.out.println("pedido");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(jsonf.toString());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idCS = request.getParameter("idC");
		String idOS = request.getParameter("idO");
		String url = request.getParameter("url");
		int idO = 0, idC = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy hh:mm");

		JSONArray array = new JSONArray();
		JSONObject json, jsonf = null;

		try{
			idC = Integer.parseInt(idCS);
			idO = Integer.parseInt(idOS);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}

		if(idC != 0 && idO != 0)
		{
			Capitulo cap = GestorBD.getGestorBD().getCapitulosBeans(idC);
			ArrayList<Comentario> lista = GestorBD.getGestorBD().getComentariosBeans(idO, idC);
			HashMap<Integer, String> map = GestorBD.getGestorBD().getHasMapAutores();

			jsonf = new JSONObject();
			jsonf.put("titulo", cap.getNombre());
			jsonf.put("idC", cap.getId());
			jsonf.put("idO", cap.getObra());
			for (Comentario com : lista){
				json = new JSONObject();
				json.put("idAutor", com.getAutor());
				json.put("texto", com.getTexto());
				json.put("fecha", format.format(new Date(com.getFecha_comentario().getTime())));
				json.put("autor", map.get(com.getAutor()));
				array.put(json);
			}
			
			jsonf.put("comentarios", array);
			jsonf.put("url", url);
			
			try{
				String idLogged = request.getParameter("loggedID");
				if (!idLogged.equals(""))
				jsonf.put("loggedID", idLogged);
			}catch(NullPointerException e){
				e.printStackTrace();
			}
		}
		System.out.println("pedido");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(jsonf.toString());
	}

	
	
}
