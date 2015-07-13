package packCordova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;

/**
 * Servlet implementation class SendComentarioApi
 */
@WebServlet("/api/SendComentarioApi")
public class SendComentarioApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendComentarioApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOS ="",idCS = "",comentario = "",loggedID = "";
		int idO = 0, idC = 0, id = 0;
		
		try{
			idOS = request.getParameter("idO");
			idCS = request.getParameter("idC");
			comentario = request.getParameter("comentario");
			loggedID = request.getParameter("loggedid");
		}catch(NullPointerException e){}
		
		try{
			idO = Integer.parseInt(idOS);
			idC = Integer.parseInt(idCS);
		}catch(NumberFormatException e){}
		
		boolean resultado = false;
		
		try{
			id = UsuariosLoggeados.getMyUsuariosLogeados().getUsuario(loggedID);
		}catch(NullPointerException e){}
		
		if (id != 0){
			resultado = GestorBD.getGestorBD().addComment(id, idC, idO, comentario);
		}
		
		System.out.println("pedido");
		response.setContentType("application/text");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(String.valueOf(resultado));
	}

}
