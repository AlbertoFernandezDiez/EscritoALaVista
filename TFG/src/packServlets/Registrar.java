package packServlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import packBD.GestorBD;

/**
 * Servlet implementation class Registrar
 */
@WebServlet(description = "Servlet para registrar nuevos usuarios", urlPatterns = { "/Registrar" })
@javax.servlet.annotation.MultipartConfig
public class Registrar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Registrar() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String nombre,email, password,pais,about,ruta;
		Date nac = null;

		nombre = request.getParameter("usuario");
		email = request.getParameter("email");
		password = request.getParameter("contrasena1");
		pais = request.getParameter("pais");
		about = request.getParameter("about");
		String date = request.getParameter("nacimiento");
		
		if (nombre != null && email != null &&
				password != null && pais != null &&
				about != null &&  date != null){
			try{
				nac = Date.valueOf(date);
			}
			catch(IllegalArgumentException e){
				e.printStackTrace();
			}
			
			ruta = loadFile(request, "file");
			
			GestorBD.getGestorBD().addUser(nombre,email,password,pais,nac,about,ruta);
			
		}
		
		response.sendRedirect("Index");

	}

	private String loadFile(HttpServletRequest request, String fileID) throws IOException,
	ServletException, FileNotFoundException {
		request.setCharacterEncoding("UTF-8");


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
