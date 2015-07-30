package packCordova;

import java.io.File;
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
import packBeans.Autor;
import packBeans.Obra;

/**
 * Servlet implementation class MostrarAutor
 */
@WebServlet("/api/MostrarAutor")
public class MostrarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;   

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarAutor() {
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

		String /*url = request.getParameter("url"),*/ idS = request.getParameter("id");
		int id = 0;

		try{
			id = Integer.parseInt(idS);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}

		if(id != 0){
			Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");

			if (autor != null){
				JSONObject json = new JSONObject();
				JSONArray array = new JSONArray();

				String img ="";

				if (autor.getImagen() != null){
					File imgFile = new File(filePath ,autor.getImagen());
					img = Encoder.getMyEncoder().encodeInBase64(imgFile);
				}

				for (String aux : autor.getAbout()){
					array.put(new JSONObject().put("text", aux));
				}
				//json.put("url", url);
				json.put("nombre", autor.getNombre());
				json.put("pais", autor.getPais());
				json.put("imagen",img );
				json.put("nacimiento", format.format(autor.getNacimiento()));
				json.put("about", array);


				ArrayList<Obra> lista = GestorBD.getGestorBD().getObrasBeans(0, 0, id);
				array = new JSONArray();
				JSONObject json1;

				for (Obra obra : lista){
					json1 = new JSONObject();
					json1.put("titulo", obra.getTitulo());
					json1.put("id", obra.getId());
					json1.put("autor", autor.getNombre());
					json1.put("autorId",obra.getAutor());
					json1.put("resumen", obra.getResumen());
					json1.put("fechamod", format.format(new Date(obra.getFecha_mod().getTime())) );
					json1.put("fechain", format.format(obra.getFecha_in()));
					array.put(json1);
				}
				json.put("obras", array);


				System.out.println("pedido");
				response.setContentType("application/json");
				response.setHeader("Access-Control-Allow-Origin", "*");
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter("url"), idS = request.getParameter("id");
		int id = 0;

		try{
			id = Integer.parseInt(idS);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}

		if(id != 0){
			Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");

			if (autor != null){
				JSONObject json = new JSONObject();
				JSONArray array = new JSONArray();

				String img ="";

				if (autor.getImagen() != null){
					File imgFile = new File(filePath ,autor.getImagen());
					img = Encoder.getMyEncoder().encodeInBase64(imgFile);
				}

				for (String aux : autor.getAbout()){
					array.put(new JSONObject().put("text", aux));
				}
				json.put("url", url);
				json.put("nombre", autor.getNombre());
				json.put("pais", autor.getPais());
				json.put("imagen",img );
				json.put("nacimiento", format.format(autor.getNacimiento()));
				json.put("about", array);

				try{
					String idLogged = request.getParameter("loggedID");
					if (!idLogged.equals(""))
					json.put("loggedID", idLogged);
				}catch(NullPointerException e){
					e.printStackTrace();
				}

				ArrayList<Obra> lista = GestorBD.getGestorBD().getObrasBeans(0, 0, id);
				array = new JSONArray();
				JSONObject json1;

				for (Obra obra : lista){
					json1 = new JSONObject();
					json1.put("titulo", obra.getTitulo());
					json1.put("id", obra.getId());
					json1.put("autor", autor.getNombre());
					json1.put("autorId",obra.getAutor());
					json1.put("resumen", obra.getResumen());
					json1.put("fechamod", format.format(new Date(obra.getFecha_mod().getTime())) );
					json1.put("fechain", format.format(obra.getFecha_in()));
					array.put(json1);
				}
				json.put("obras", array);


				System.out.println("pedido");
				response.setContentType("application/json");
				response.setHeader("Access-Control-Allow-Origin", "*");
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
			}
		}
	}


}
