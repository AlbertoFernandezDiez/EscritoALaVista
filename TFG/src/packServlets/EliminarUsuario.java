package packServlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import packBD.GestorBD;
import packBeans.Autor;
import packBeans.Capitulo;
import packBeans.Obra;

/**
 * Servlet implementation class EliminarUsuario
 */
@WebServlet({ "/EliminarUsuario", "/EU" })
public class EliminarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String filePath = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(){
		// TODO Auto-generated method stub
		filePath =getServletContext().getInitParameter("file-upload"); 
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		boolean admin = false; 
		try{
			admin =	(boolean)session.getAttribute("admin");
		}
		catch (NullPointerException e)
		{
			e.printStackTrace();
		}

		if (admin)
		{
			ArrayList<Autor> lista = GestorBD.getGestorBD().getAutoresBeans();
System.out.println(lista.size());
			request.setAttribute("autores", lista);
			request.setAttribute("admin", true);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/Gestion/eliminarAutor.jsp");
			rd.forward(request, response);
		}
		else
		{
			response.sendRedirect("/Error/noEresAdmin.html");
		}
	}

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

		if (admin)
		{
			int id = Integer.parseInt(request.getParameter("id"));
			Autor autor = GestorBD.getGestorBD().getAutorBeans(id);
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
			
			img = new File(filePath, autor.getImagen());
			img.delete();
			}
			catch(NullPointerException e)
			{
				e.printStackTrace();
			}
			result = GestorBD.getGestorBD().deleteAutor(id);
			
			PrintWriter pw = response.getWriter();
			pw.print(String.valueOf(result));
		}
		else
		{
			response.sendRedirect("/Error/noEresAdmin.html");
		}
	}

}
