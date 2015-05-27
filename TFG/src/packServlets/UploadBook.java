package packServlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;
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
		// TODO Auto-generated method stub
		ArrayList<packBeans.Obra> listaO = GestorBD.getGestorBD().getObrasBeans(0, 0);
		ArrayList<packBeans.Autor>listaA = GestorBD.getGestorBD().getAutoresBeans();
		request.setAttribute("obras", listaO);
		request.setAttribute("autores",listaO);
		
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/uploadBook.jsp");
        rd.forward(request, response);
	}

	

}
