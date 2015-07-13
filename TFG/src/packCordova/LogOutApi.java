package packCordova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogOutApi
 */
@WebServlet("/api/LogOutApi")
public class LogOutApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogOutApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String loggedID;
		boolean logout = false;
		try{
			loggedID = request.getParameter("loggedid");
			UsuariosLoggeados.getMyUsuariosLogeados().removeUsuario(loggedID);
			logout = true;
		}catch(NullPointerException e){
			loggedID = "";
		}
		
		System.out.println("pedido");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(String.valueOf(logout));
		
		
		
	}

}
