package packCordova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import packBD.GestorBD;

/**
 * Servlet implementation class ComprobarTitulo
 */
@WebServlet("/api/ComprobarEmailAPI")
public class ComprobarEmailAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComprobarEmailAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		boolean resultado = false;
		
		if(email != null)
		resultado = GestorBD.getGestorBD().comprobarEmail(email);

		PrintWriter pw = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		pw.write(new JSONObject().put("value", resultado).toString());
	}

}
