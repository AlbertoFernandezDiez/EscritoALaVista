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
import packBeans.Obra;

/**
 * Servlet implementation class mostrarlistado
 */
@WebServlet("/api/Mostrarlistado" )
public class Mostrarlistado extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Mostrarlistado() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Obra> lista = GestorBD.getGestorBD().getObrasBeans(0, 0, 0);
		HashMap<Integer, String> map = GestorBD.getGestorBD().getHasMapAutores();
		JSONArray array = new JSONArray();
		JSONObject json;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");

		for (Obra obra : lista){
			json = new JSONObject();
			json.put("titulo", obra.getTitulo());
			json.put("id", obra.getId());
			json.put("autor", map.get(obra.getAutor()));
			json.put("autorId",obra.getAutor());
			json.put("resumen", obra.getResumen());
			json.put("fechamod", format.format(new Date(obra.getFecha_mod().getTime())) );
			json.put("fechain", format.format(obra.getFecha_in()));
			array.put(json);
		}
		System.out.println("pedido");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(array.toString());
	}



}
