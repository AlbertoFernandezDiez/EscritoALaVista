package packServlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import packBD.GestorBD;

/**
 * Servlet implementation class CambiarContrasena
 */
@WebServlet("/CambiarContrasena")
public class CambiarContrasena extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarContrasena() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();

		String user = (String) session.getAttribute("username");
		int id = 0;

		try {
			id = (int) session.getValue("id");
			if (id != 0)
			{
				String old = null, newC = null;

				old = request.getParameter("old");
				newC = request.getParameter("newC");

			boolean result = GestorBD.getGestorBD().changePassword(id, old,newC);
			
				// TODO Auto-generated method stub
				PrintWriter pw = response.getWriter();
				pw.write(String.valueOf(result));

			}
			else
				response.sendRedirect("Error/NoLogeado.html");

		}catch(NullPointerException e){
			e.printStackTrace();
			response.sendRedirect("Error/NoLogeado.html");
		}

	}

}
