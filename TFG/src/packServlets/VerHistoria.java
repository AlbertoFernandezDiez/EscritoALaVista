package packServlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Request;

import packBD.GestorBD;
import packBeans.BreadCrumb;
import packBeans.Capitulo;
import packBeans.Comentario;
import packBeans.Obra;

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
			cargarHistoria(idH,id, request);
			break;
		case 1:
			idC = Integer.parseInt(idCS);

			cargarCapitulo(idH,idC, id, request);
			break;

		default:
			cargarHistoria(idO,id, request);
			break;
		}

		RequestDispatcher rd = getServletContext().getRequestDispatcher("/home.jsp");
		rd.forward(request, response);
	}

	private void cargarHistoria(int idO,int id, HttpServletRequest request) {
		int idC = GestorBD.getGestorBD().getIndiceCapituloUno(idO);

		if (idC != 0)
		{
			cargarCapitulo(idO, idC, id, request);
		}
		else{

		}

	}

	private void cargarCapitulo(int idO, int idC, int id, HttpServletRequest request) {
		ArrayList<Capitulo> lista = GestorBD.getGestorBD().getCapituloBeans(idO);
		ArrayList<Comentario> comentarios = GestorBD.getGestorBD().getComentariosBeans(idO,idC);
		HashMap<Integer, String> autores = GestorBD.getGestorBD().getHasMapAutores();
		Obra obra = GestorBD.getGestorBD().getObraBeans(idO);
		request.setAttribute("chapterList", lista);
		request.setAttribute("chapter", idC);
		request.setAttribute("tit", obra.getTitulo());
		request.setAttribute("id", idO);
		request.setAttribute("comentarios", comentarios);
		request.setAttribute("autor", autores);

		if (GestorBD.getGestorBD().getObraAutorChecked(idO,id) && id != 0)
		{
		request.setAttribute("checked", true);
		}
		updateBreadCrumb(request, request.getSession(), obra);

	}

	private void updateBreadCrumb(HttpServletRequest request,
			HttpSession session,Obra obra) {
		Stack<BreadCrumb> breadcrumb = null;
		BreadCrumb bread = new BreadCrumb();
		bread.setName(obra.getTitulo());
		String parameters = request.getQueryString();
		if (parameters == null)
			parameters ="";
		bread.setUrl(request.getRequestURL().toString() + '?' + parameters);
		breadcrumb = (Stack<BreadCrumb>) session.getAttribute("breadcrumb");
		if (breadcrumb == null)
		{			
			breadcrumb = new Stack<BreadCrumb>();
		}
		System.out.println(breadcrumb.size());

		if (breadcrumb.contains(bread))
		{
			while(!breadcrumb.peek().equals(bread))
			{
				breadcrumb.pop();
			}
		}
		else
		{
			breadcrumb.push(bread);
		}
		session.setAttribute("breadcrumb", breadcrumb);
		request.setAttribute("breadcrumb", breadcrumb);
	}
}
