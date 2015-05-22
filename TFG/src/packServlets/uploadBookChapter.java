package packServlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;

/**
 * Servlet implementation class uploadBookChapter
 */
@WebServlet("/uploadBookChapter")
@javax.servlet.annotation.MultipartConfig
public class uploadBookChapter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadBookChapter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String capitulo = request.getParameter("capitulo");
		String comentario = request.getParameter("comentarios");
		String resumen = request.getParameter("resumen");
		String idSCap = request.getParameter("selectCapitulo");
		String idSOb = request.getParameter("selectObra");
		String tituloCap = request.getParameter("titCap");
		String tituloObra = request.getParameter("titOb");
		
		
		if (tituloCap != null && tituloObra != null 
				&& idSCap != null && idSOb != null
				&& capitulo != null && resumen != null){
			int idCap = Integer.parseInt(idSCap);
			int idOb = Integer.parseInt(idSOb);
			
			if (idOb == 0)
			{
				//Obra nueva
				int id = GestorBD.getGestorBD().insertarObra(1, tituloObra, resumen);
				if (id != 0)
					GestorBD.getGestorBD().insertarCapitulo(id, tituloCap, capitulo, comentario);
				
			}
			else
			{
				//Update o inserccion de una obra ya existente
			}
		}
	}

}
