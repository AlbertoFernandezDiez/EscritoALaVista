package packServlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;

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
		String idS = request.getParameter("autor");
		int id = 0;
		if (idS != null){
			id = Integer.parseInt(idS);
			packBeans.Autor autor = GestorBD.getGestorBD().getAutorBeans(id);
			request.setAttribute("autor",autor);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/autor.jsp");
	        rd.forward(request, response);
		}
	}

}
