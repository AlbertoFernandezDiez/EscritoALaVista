package packServlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import packBD.GestorBD;
import packBeans.Comentario;

/**
 * Servlet implementation class UploadComment
 */
@WebServlet("/UploadComment")
public class UploadComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int id = 0;
		String obraS = null,capituloS = null,texto = null;
		int obra= 0;
		int capitulo = 0;
		try {
			id = (int) session.getValue("id");
			obraS = request.getParameter("obra");
			capituloS = request.getParameter("capitulo");
			texto = request.getParameter("texto");
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}

		try{
			obra = Integer.parseInt(obraS);
			capitulo = Integer.parseInt(capituloS);
		}
		catch(NumberFormatException e){
		}
		PrintWriter pw = response.getWriter();

		if (capitulo != 0 && id != 0 && obra != 0){
			if(!GestorBD.getGestorBD().addComment(id,capitulo,obra,texto))
				pw.write(Boolean.FALSE.toString());
			else{
				ArrayList<Comentario> lista = GestorBD.getGestorBD().getComentariosBeans(obra ,capitulo);
HashMap<Integer, String> autores = GestorBD.getGestorBD().getHasMapAutores();
				JSONArray array = new JSONArray();
				JSONObject json =null;
				for (Comentario coment : lista){
					json = new JSONObject();
					json.put("autor", coment.getAutor());
					json.put("texto", coment.getTexto());
					json.put("fechaComentario",coment.getFecha_comentario().toString());
					json.put("nombre", autores.get(coment.getAutor()));
					array.put(json);
				}
				pw.write(array.toString());
			}
		}
		else
		{
			pw.write(Boolean.FALSE.toString());
		}

	}

}
