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

import packBD.GestorBD;
import packBeans.BreadCrumb;
import packBeans.Obra;

/**
 * Servlet implementation class Index
 */
@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String showS, posS;
		posS = request.getParameter("pos");
		showS = request.getParameter("show");
		int pos, show;
		if (posS != null && showS != null){
			pos = Integer.parseInt(posS);
			show = Integer.parseInt(showS);
		}
		else{
			//Por defecto mostraremos 10 obras
			
			pos = 0;
			show = 10;
		}
		
		HttpSession session = request.getSession();
		updateBreadCrumb(request, session);
	
		
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
		
		ArrayList<Obra> lista = GestorBD.getGestorBD().getObrasBeans(show, show*pos,0);
		HashMap<Integer, String> autores = GestorBD.getGestorBD().getHasMapAutores();
		int max = GestorBD.getGestorBD().getMaxObrasN(show);
		request.setAttribute("obras", lista);
		request.setAttribute("pos",pos);
		request.setAttribute("show", show);
		request.setAttribute("max", max);
		request.setAttribute("autores", autores);
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        rd.forward(request, response);
	}

	private void updateBreadCrumb(HttpServletRequest request,
			HttpSession session) {
		Stack<BreadCrumb> breadcrumb = null;
		BreadCrumb bread = new BreadCrumb();
		bread.setName("Index");
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
