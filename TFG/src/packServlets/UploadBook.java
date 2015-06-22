package packServlets;

import java.io.IOException;
import java.util.ArrayList;
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
 * Servlet implementation class UploadBook
 */
@WebServlet("/UploadBook")
public class UploadBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	HttpSession session = request.getSession();
		
		String user = (String) session.getAttribute("username");
		int id = 0;
		
		try {
		id = (int) session.getValue("id");
		// TODO Auto-generated method stub
		if (id != 0)
		{
			updateBreadCrumb(request, session);
		request.setAttribute("userId",id);
		request.setAttribute("userName",user);
		}
		
		ArrayList<packBeans.Obra> listaO = GestorBD.getGestorBD().getObrasBeans(0, 0, id);
		request.setAttribute("obras", listaO);
		request.setAttribute("autor",id);
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/uploadBook.jsp");
        rd.forward(request, response);
		}
		catch(NullPointerException e){
			e.printStackTrace();
			response.sendRedirect("Error/NoLogeado.html");
		}
		
	
	}

	private void updateBreadCrumb(HttpServletRequest request,
			HttpSession session) {
		Stack<BreadCrumb> breadcrumb = null;
		BreadCrumb bread = new BreadCrumb();
		bread.setName("CrearLibro");
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
