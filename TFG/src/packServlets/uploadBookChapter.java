package packServlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
		String rutaP = request.getParameter("rutaP");
		String rutaC = request.getParameter("rutaC");
		String idAutorS = request.getParameter("autor");
Part filePartObra = request.getPart("fileObra");
Part filePartCapi = request.getPart("fileCapi");
		if (tituloCap != null && tituloObra != null 
				&& idSCap != null && idSOb != null
				&& capitulo != null && resumen != null && idAutorS != null){
			int idCap = Integer.parseInt(idSCap);
			int idOb = Integer.parseInt(idSOb);
			int idAutor = Integer.parseInt(idAutorS);
			if (idOb == 0)
			{ 
				int id;
				//Obra nueva
				if (filePartObra.getSize()==0)
					id = GestorBD.getGestorBD().insertarObra(idAutor, tituloObra, resumen,null);
				else
					id = GestorBD.getGestorBD().insertarObra(idAutor, tituloObra, resumen,loadFile(request,"fileObra"));

				if (idCap == 0){
					if (filePartCapi.getSize()==0)
						GestorBD.getGestorBD().insertarCapitulo(id, tituloCap, capitulo, comentario,null);
					else
						GestorBD.getGestorBD().insertarCapitulo(id, tituloCap, capitulo, comentario,loadFile(request,"fileCapi"));
				}
			}
			else
			{
				//Update o inserccion de una obra ya existente
				
				if (filePartObra.getSize()==0)
				GestorBD.getGestorBD().updateObra(idOb,tituloObra,resumen,null);
				else
					GestorBD.getGestorBD().updateObra(idOb,tituloObra,resumen,loadFile(request,"fileObra"));

				if (idCap != 0)
				{
					if(filePartCapi.getSize()==0)
						GestorBD.getGestorBD().updateChapter(idCap,tituloCap,capitulo,comentario,null);

						//					GestorBD.getGestorBD().updateChapter(idCap,tituloCap,capitulo,comentario,rutaC);
					else
						GestorBD.getGestorBD().updateChapter(idCap,tituloCap,capitulo,comentario,loadFile(request,"fileCapi"));

				}else{
					if(!rutaC.matches(""))
					GestorBD.getGestorBD().insertarCapitulo(idOb, tituloCap, capitulo, comentario,rutaC);
					else
						GestorBD.getGestorBD().insertarCapitulo(idOb, tituloCap, capitulo, comentario,loadFile(request,"fileCapi"));
				}
			}
		}


		response.sendRedirect("Index");



	}

	private String loadFile(HttpServletRequest request, String fileID) throws IOException,
	ServletException, FileNotFoundException {
		Part file = request.getPart(fileID);
		String filepath =null;
		System.out.println(file.getSize());


		if (file.getSize() != 0){
			String filename = getFileName(file);
			java.io.InputStream is = file.getInputStream();

			filepath = "imagenes/"  + System.currentTimeMillis() + filename.substring(filename.lastIndexOf('.'));
			OutputStream outputStream = new FileOutputStream(new File(getServletContext().getInitParameter("file-upload") +File.separator +  
					filepath));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.close();
			System.out.println("Done!");}
		return filepath;
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
