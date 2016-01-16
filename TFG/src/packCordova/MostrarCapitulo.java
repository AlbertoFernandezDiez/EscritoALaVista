package packCordova;

import java.io.File;
import java.io.FileNotFoundException;
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

/**
 * Servlet implementation class MostrarCapitulo
 */
@WebServlet("/api/MostrarCapitulo")
public class MostrarCapitulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
private String filePath;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MostrarCapitulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init( ){
		// Get the file location where it would be stored.
		filePath =getServletContext().getInitParameter("file-upload"); 
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getParameter("url"),idCS = request.getParameter("id");
		int idC = 0;
		JSONObject json = null;

		try{
			idC = Integer.parseInt(idCS);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}

		if(idC != 0)
		{
			Capitulo cap = GestorBD.getGestorBD().getCapitulosBeans(idC);
			ArrayList<Comentario> listaArrayList = GestorBD.getGestorBD().getComentariosBeans(cap.getObra(), cap.getId());
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");
			
			String img = "";
			
			if(cap.getImagen() != null){			
			File imgFile = new File(filePath,cap.getImagen());
			try{
			img = Encoder.getMyEncoder().encodeInBase64(imgFile);
			}catch(FileNotFoundException e){}
			}
			
			if (cap != null)
			{
				json = new JSONObject();
				json.put("comentarioAutor", cap.getComentarios_autor());
				json.put("fechaComentario", format.format(cap.getFecha_comentario()));
				json.put("id", cap.getId());
				json.put("imagen", img);
				json.put("titulo", cap.getNombre());
				json.put("obra", cap.getObra());
				json.put("url", url);
				JSONArray array = new JSONArray();
				
				try{
					String idLogged = request.getParameter("loggedID");
					if (!idLogged.equals(""))
					json.put("loggedID", idLogged);
				}catch(NullPointerException e){
					e.printStackTrace();
				}
				
				for(String aux : cap.getText()){
					array.put(new JSONObject().put("par", aux));
				}
				json.put("texto", array);
			}
		}
		
		System.out.println("pedido");
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(json.toString());
	}

}
