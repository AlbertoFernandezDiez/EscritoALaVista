package packServlets;

import java.io.IOException;
import java.util.ArrayList;
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

import packBD.GestorBD;
import packBeans.Autor;
import packBeans.BreadCrumb;

/**
 * Servlet implementation class VerAutor
 */
@WebServlet("/VerAutor")
public class VerAutor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerAutor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		int idU = 0;
		
		try {
		idU = (int) session.getValue("id");
		}
		catch(NullPointerException e){
			e.printStackTrace();
		}
		if (idU != 0)
		{
		request.setAttribute("userId",idU);
		request.setAttribute("userName",user);
		}
		
		String idS = request.getParameter("autor");
		int id = 0;
		if (idS != null){
			id = Integer.parseInt(idS);
			packBeans.Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);
			updateBreadCrumb(request, session, autor);
			request.setAttribute("autor",autor);
			
			ArrayList<packBeans.Obra> lista = GestorBD.getGestorBD().getObrasBeans(1000, 0, id);
			request.setAttribute("obras", lista);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/autor.jsp");
	        rd.forward(request, response);
		}
	}

	private void updateBreadCrumb(HttpServletRequest request,
			HttpSession session,Autor autor) {
		Stack<BreadCrumb> breadcrumb = null;
		BreadCrumb bread = new BreadCrumb();
		bread.setName(autor.getNombre());
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
			System.out.println(breadcrumb.indexOf(bread));
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
