package packServlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
import packBeans.Autor;

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
	public String username,password;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public uploadBookChapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init( ){
		// Get the file location where it would be stored.

		username =getServletContext().getInitParameter("username"); 
		password = getServletContext().getInitParameter("password"); 
		

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
					if(filePartCapi.getSize()==0)
						GestorBD.getGestorBD().insertarCapitulo(idOb, tituloCap, capitulo, comentario,null);
					else
						GestorBD.getGestorBD().insertarCapitulo(idOb, tituloCap, capitulo, comentario,loadFile(request,"fileCapi"));
				}
			}
			//Mandamos un email a los usuario suscritos a la historia
			mandarEmailSeguimiento(idOb,tituloObra);
		}



		response.sendRedirect("Index");



	}

	private void mandarEmailSeguimiento(int id, String tituloObra) {
		// TODO Auto-generated method stub
		ArrayList<Autor> lista = GestorBD.getGestorBD().getSuscriptores(id);
		
		if (lista.size() == 0)
			return;
		
	/*	username = "afalbertofd47@gmail.com";
		final String password = "4wApEfE8";
		*/
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("afalbertofd47@gmail.com"));
		
			for (int i = 0; i < lista.size(); i++)
			{
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(lista.get(i).getEmail()));
			}
				
			message.setSubject("Actualización de la obra" + tituloObra);
			message.setText("Hola!\n" +
					"La obra " + tituloObra + " , de la que eres seguidor se ha actualizado.\n\n Disfrutala");

			Transport.send(message);


		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	

	private String loadFile(HttpServletRequest request, String fileID) throws IOException,
	ServletException, FileNotFoundException {
		Part file = request.getPart(fileID);
		String filepath =null;


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
		}
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
