package packCordova;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.sun.org.apache.bcel.internal.generic.NEW;

import packBD.GestorBD;
import packBeans.Autor;

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
			id = Integer.parseInt(loggedID);
		}catch(NumberFormatException e){}
		
		boolean resultado = false;
		
		Autor autor = null;
		
		try{
		autor = GestorBD.getGestorBD().getAutorBeansById(id);
		
		if (id != 0 && autor.getActive() != 0){
			resultado = GestorBD.getGestorBD().addComment(id, idC, idO, comentario);
		}
		
		}catch(NullPointerException e){}
		
		System.out.println("pedido");
		response.setContentType("application/text");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter pw = response.getWriter();
		pw.write(String.valueOf(new JSONObject().put("ok",resultado).put("desh", autor.getActive() == 0)));
		System.out.println(String.valueOf(new JSONObject().put("ok",resultado).put("desh", autor.getActive() == 0)));
	}

}
