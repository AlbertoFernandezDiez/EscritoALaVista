package packServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class EliminarObra
 */
@WebServlet({ "/EliminarObra", "/EO" })
public class EliminarObra extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String filePath = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EliminarObra() {
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
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		boolean admin = false; 
		try{
			admin =	(boolean)session.getAttribute("admin");
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}
		int id = Integer.parseInt(request.getParameter("id"));
		if (admin)
		{
			Obra obra = GestorBD.getGestorBD().getObraBeans(id);
			ArrayList<Capitulo> lista = GestorBD.getGestorBD().getCapituloBeans(id);
			boolean result = false;
			File img = null;
			try{
			for (Capitulo cap : lista){
				
				img = new File(filePath,cap.getImagen());
				img.delete();
			}
			img = new File(filePath,obra.getPortada());
			img.delete();
			
			}
			catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			result = GestorBD.getGestorBD().deleteObra(id);
			
			PrintWriter pw = response.getWriter();
			pw.print(String.valueOf(result));
		}
		else
		{
			int idUsuario = 0;
			try
			{
				idUsuario = (int) session.getAttribute("id");
				}
			catch(NullPointerException e){
				e.printStackTrace();
			}
			Obra obra = GestorBD.getGestorBD().getObraBeans(id);
			if (idUsuario != 0 && idUsuario == obra.getAutor()){
				ArrayList<Capitulo> lista = GestorBD.getGestorBD().getCapituloBeans(id);
				boolean result = false;
				File img = null;
				try{
				for (Capitulo cap : lista){
					
					img = new File(filePath,cap.getImagen());
					img.delete();
				}
				img = new File(filePath,obra.getPortada());
				img.delete();
				
				}
				catch(NullPointerException e)
				{
					e.printStackTrace();
				}
				result = GestorBD.getGestorBD().deleteObra(id);
				
				PrintWriter pw = response.getWriter();
				pw.print(String.valueOf(result));
			}
			else
			response.sendRedirect("Error/noEresAdmin.html");
		}
	}

}


