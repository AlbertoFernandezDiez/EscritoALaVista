package packServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import packBD.GestorBD;
import packBeans.Capitulo;
import packClases.ListaCapitulos;
import packClases.Obra;

/**
 * Servlet implementation class VerHistoria
 */
@WebServlet("/VerHistoria")
public class VerHistoria extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerHistoria() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idOS = request.getParameter("op");
		String idHS = request.getParameter("hi");
		String idCS = request.getParameter("ca");
		int idH = 0;
		int idC = 0;
		int idO = 0;
		if (idHS !=  null && idOS != null){
			idO = Integer.parseInt(idOS);
			idH = Integer.parseInt(idHS);
		}
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		int id = 0;
		
		try {
		id = (int) session.getValue("id");
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
		if (id != 0)
		{
		request.setAttribute("userId",id);
		request.setAttribute("userName",user);
		}
		
		switch (idO) {
		case 0:
			cargarHistoria(idH, request);
			break;
		case 1:
			idC = Integer.parseInt(idCS);
			cargarCapitulo(idH,idC, request);
			break;
		
		default:
			cargarHistoria(idO, request);
			break;
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
        rd.forward(request, response);
	}

	private void cargarHistoria(int idO, HttpServletRequest request) {
		int idC = GestorBD.getGestorBD().getIndiceCapituloUno(idO);
		
		if (idC != 0)
		{
			cargarCapitulo(idO, idC, request);
		}
		else{
			System.out.println("Ha devuelto 0");
		}
		
	}

	private void cargarCapitulo(int idO, int idC, HttpServletRequest request) {
		 ArrayList<Capitulo> lista = GestorBD.getGestorBD().getCapituloBeans(idO);
		 Obra obra = GestorBD.getGestorBD().getObra(idO);
		 request.setAttribute("chapterList", lista);
		 request.setAttribute("chapter", idC);
		 request.setAttribute("tit", obra.getTitulo());
		 request.setAttribute("id", idO);
		
	}

}
