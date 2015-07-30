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
 * Servlet implementation class SeguimientoAPI
 */
@WebServlet("/api/SeguimientoAPI")
public class SeguimientoAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SeguimientoAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOS = "", idUS = "", checkedS = "";
		int idO = 0, idU = 0;

		boolean checked = false, respuesta = false;

		try{
			idOS = request.getParameter("idO");
			idUS = request.getParameter("idU");
			checkedS = request.getParameter("valor");
		}
		catch(NullPointerException e){}

		try{
			checked = Boolean.valueOf(checkedS);
			idO = Integer.parseInt(idOS);
			idU = Integer.parseInt(idUS);
		}catch(NumberFormatException e){}

		if (checked)
			respuesta = GestorBD.getGestorBD().AddSeguimiento(idU,idO);
		else
			respuesta = GestorBD.getGestorBD().QuitSeguimiento(idU,idO);

		PrintWriter pw = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		pw.write(new JSONObject().put("value", respuesta).toString());
	}		

}
