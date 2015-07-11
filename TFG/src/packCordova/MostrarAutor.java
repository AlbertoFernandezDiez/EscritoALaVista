package packCordova;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import packBD.GestorBD;
import packBeans.Autor;

/**
 * Servlet implementation class MostrarAutor
 */
@WebServlet("/api/MostrarAutor")
public class MostrarAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostrarAutor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
			
				for (String aux : autor.getAbout()){
					array.put(new JSONObject().put("text", aux));
				}
				json.put("url", url);
				json.put("nombre", autor.getNombre());
				json.put("pais", autor.getPais());
				json.put("imagen", autor.getImagen());
				json.put("nacimiento", format.format(autor.getNacimiento()));
				json.put("about", array);
				
				System.out.println("pedido");
				response.setContentType("application/json");
				response.setHeader("Access-Control-Allow-Origin", "*");
				PrintWriter pw = response.getWriter();
				pw.write(json.toString());
		}
		}
	}

}
