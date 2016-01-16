package packCordova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import packBD.GestorBD;
import packBeans.Autor;
import packBeans.Obra;

/**
 * Servlet implementation class EliminarObraAPI
 */
@WebServlet("/api/EliminarObraAPI")
public class EliminarObraAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarObraAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedID = "", obraID = "";
		int idO = 0, idU = 0;
		boolean resultado = false;

		try{
			loggedID = request.getParameter("loggedid");
			obraID = request.getParameter("obra");
		}catch(NullPointerException e){}


		try{
			idU = Integer.parseInt(loggedID);
			idO = Integer.parseInt(obraID);
		}
		catch(NumberFormatException e){
		}

		Autor autor = null;
		try{
			autor = GestorBD.getGestorBD().getAutorBeansById(idU);
			}catch(NullPointerException e){}
			
		if (autor.getActive() != 0){
		Obra obra = GestorBD.getGestorBD().getObraBeans(idO);

		if (obra != null){
			if(obra.getAutor() == idU){
				resultado = GestorBD.getGestorBD().deleteObra(idO);
			}
		}
		}
		response.setContentType("application/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(new JSONObject().put("value", resultado).put("desh", autor.getActive() == 0).toString());
		System.out.println(new JSONObject().put("value", resultado).put("desh", autor.getActive() == 0).toString());
	}

}
