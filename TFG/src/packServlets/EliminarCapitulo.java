package packServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packBD.GestorBD;
import packBeans.Capitulo;
import packBeans.Obra;

/**
 * Servlet implementation class EliminarCapitulo
 */
@WebServlet("/EliminarCapitulo")
public class EliminarCapitulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;   


	public void init( ){
		// Get the file location where it would be stored.
		filePath =getServletContext().getInitParameter("file-upload"); 
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarCapitulo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		boolean result = false;
		int idUsuario = 0;
		try{
			idUsuario = (int) session.getAttribute("id");

		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}

		int id = 0;
		try{
			id = Integer.parseInt(request.getParameter("id"));
			System.out.println(id);
		}
		catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		if (id != 0){
			Capitulo cap = GestorBD.getGestorBD().getCapitulosBeans(id);
			Obra obra = GestorBD.getGestorBD().getObraBeans(cap.getObra());
			File img = null;		
			if (idUsuario != 0 && idUsuario == obra.getAutor()){

				try{
					img = new File(filePath,cap.getImagen());
					img.delete();}
				catch(NullPointerException e)
				{
					e.printStackTrace();
				}
			}
			if(GestorBD.getGestorBD().deleteCapitulo(id))
				result = true;
		}

		PrintWriter pw = response.getWriter();
		pw.print(String.valueOf(result));

	}

}
