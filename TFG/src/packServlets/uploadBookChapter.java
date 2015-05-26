package packServlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;










import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.adobe.dp.epub.util.Base64.InputStream;

import packBD.GestorBD;

/**
 * Servlet implementation class uploadBookChapter
 */
@WebServlet("/uploadBookChapter")
@javax.servlet.annotation.MultipartConfig
public class uploadBookChapter extends HttpServlet {
	private static final long serialVersionUID = 1L;


	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 1024 * 1024 * 3;//3M
	private int maxMemSize = 1024 * 1024 * 3;
	private File file ;

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
Part file = request.getPart("file");
String filename = getFileName(file);
java.io.InputStream is = file.getInputStream();
System.out.println(file.getContentType());
System.out.println(file.getName());
System.out.println(file.getHeaderNames());
System.out.println(request.getParameter("file"));
String filepath = "imagenes" + File.separator + System.currentTimeMillis() + filename.substring(filename.lastIndexOf('.'));
OutputStream outputStream = new FileOutputStream(new File(getServletContext().getInitParameter("file-upload") +File.separator +  
		filepath));

int read = 0;
byte[] bytes = new byte[1024];

while ((read = is.read(bytes)) != -1) {
	outputStream.write(bytes, 0, read);
}
outputStream.close();
System.out.println("Done!");

			if (tituloCap != null && tituloObra != null 
				&& idSCap != null && idSOb != null
				&& capitulo != null && resumen != null){
			int idCap = Integer.parseInt(idSCap);
			int idOb = Integer.parseInt(idSOb);

			if (idOb == 0)
			{
				//Obra nueva
				int id = GestorBD.getGestorBD().insertarObra(1, tituloObra, resumen);
				if (idCap == 0)
					GestorBD.getGestorBD().insertarCapitulo(id, tituloCap, capitulo, comentario);

			}
			else
			{
				//Update o inserccion de una obra ya existente

			/*	GestorBD.getGestorBD().updateObra(idOb,tituloObra,resumen,fileMap.get("portada"));

				if (idCap != 0)
					GestorBD.getGestorBD().updateChapter(idCap,tituloCap,capitulo,resumen,fileMap.get("imagen"));
				else
					GestorBD.getGestorBD().insertarCapitulo(idOb, tituloCap, capitulo, comentario);*/
			}
		}

		

	

		
	}

		public static String getFileName(Part filePart)
		{
		    String header = filePart.getHeader("content-disposition");
		    for(String headerPart : header.split(";"))
		    {
		        if(headerPart.trim().startsWith("filename"))
		        {
		            return headerPart.substring(headerPart.indexOf('=') + 1).trim()
		                             .replace("\"", "");
		        }
		    }
		    return null;
		}
	}
