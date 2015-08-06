package packCordova;

import java.io.File;
import java.io.FileNotFoundException;
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
import packBeans.Obra;

/**
 * Servlet implementation class MostrarObra
 */
@WebServlet("/api/MostrarObra")
public class MostrarObra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;   

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarObra() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init( ){
		// Get the file location where it would be stored.
		filePath =getServletContext().getInitParameter("file-upload"); 
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loggedID = request.getParameter("loggedID"), idOS = request.getParameter("id");
		int idO = 0, idU = 0;

		try{
			idO = Integer.parseInt(idOS);
			idU = Integer.parseInt(loggedID);

		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}

		if(idO != 0){
			Obra obra = GestorBD.getGestorBD().getObraBeans(idO);
			HashMap<Integer, String> map = GestorBD.getGestorBD().getHasMapAutores();
			ArrayList<Capitulo> lista = GestorBD.getGestorBD().getCapituloBeans(idO);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");

			JSONArray array = new JSONArray();
			JSONObject json2,json1 = new JSONObject();

			String img = "";
			
			boolean seguidor = GestorBD.getGestorBD().getObraAutorChecked(idO, idU);

			if(obra.getPortada() != null){
				File imgFile = new File(filePath,obra.getPortada());
				img = Encoder.getMyEncoder().encodeInBase64(imgFile);
			}

			json1 = new JSONObject();
			if (idU == 0)
			json1.put("notlogged", true);
			else
			json1.put("notlogged", false);
			
			json1.put("seguidor", seguidor);

			json1.put("titulo", obra.getTitulo());
			json1.put("id", obra.getId());
			json1.put("imagen", img);
			json1.put("autor", map.get(obra.getAutor()));
			json1.put("autorId",obra.getAutor());
			json1.put("resumen", obra.getResumen());
			json1.put("fechamod", format.format(new Date(obra.getFecha_mod().getTime())) );
			json1.put("fechain", format.format(obra.getFecha_in()));


			for (Capitulo cap : lista){
				json2 = new JSONObject();
				json2.put("idCap", String.valueOf(cap.getId()));
				json2.put("nombre",cap.getNombre());
				array.put(json2);
			}

			json1.put("capitulos", array);

			System.out.println("pedido");
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(json1.toString());
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loggedID = request.getParameter("loggedID"), idOS = request.getParameter("id");
		int idO = 0, idU = 0;

		try{
			idO = Integer.parseInt(idOS);
			idU = Integer.parseInt(loggedID);

		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}

		if(idO != 0){
			Obra obra = GestorBD.getGestorBD().getObraBeans(idO);
			HashMap<Integer, String> map = GestorBD.getGestorBD().getHasMapAutores();
			ArrayList<Capitulo> lista = GestorBD.getGestorBD().getCapituloBeans(idO);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");

			JSONArray array = new JSONArray();
			JSONObject json2,json1 = new JSONObject();

			String img = "";
			
			boolean seguidor = GestorBD.getGestorBD().getObraAutorChecked(idO, idU);

			if(obra.getPortada() != null){
				File imgFile = new File(filePath,obra.getPortada());
				try{
				img = Encoder.getMyEncoder().encodeInBase64(imgFile);
				}catch(FileNotFoundException e){}
			}

			json1 = new JSONObject();
			if (idU == 0)
			json1.put("notlogged", true);
			else
			json1.put("notlogged", false);
			
			json1.put("seguidor", seguidor);

			json1.put("titulo", obra.getTitulo());
			json1.put("id", obra.getId());
			json1.put("imagen", img);
			json1.put("autor", map.get(obra.getAutor()));
			json1.put("autorId",obra.getAutor());
			json1.put("resumen", obra.getResumen());
			json1.put("fechamod", format.format(new Date(obra.getFecha_mod().getTime())) );
			json1.put("fechain", format.format(obra.getFecha_in()));


			for (Capitulo cap : lista){
				json2 = new JSONObject();
				json2.put("idCap", String.valueOf(cap.getId()));
				json2.put("nombre",cap.getNombre());
				array.put(json2);
			}

			json1.put("capitulos", array);

			System.out.println("pedido");
			response.setContentType("application/json");
			response.setHeader("Access-Control-Allow-Origin", "*");
			PrintWriter pw = response.getWriter();
			pw.write(json1.toString());
		}
	}

	
	
}
