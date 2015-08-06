package packCordova;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import packBD.GestorBD;
import packBeans.Autor;
import packServlets.MailServer;

/**
 * Servlet implementation class ActualizarObraCapituloAPI
 */
@WebServlet("/api/ActualizarObraCapituloAPI")
public class ActualizarObraCapituloAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath, username, password;

	public void init( ){
		// Get the file location where it would be stored.
		filePath =getServletContext().getInitParameter("file-upload"); 

		username =getServletContext().getInitParameter("username"); 
		password = getServletContext().getInitParameter("password"); 

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActualizarObraCapituloAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loggedID = "", idOS = "", idCS = "", titulo = "", resumen = "", titulocap = "",
				texto = "", comentario = "",imagenObra = "",imagenCapi = "" ;
		int id = 0,idO = 0,idC = 0;

		try{
			loggedID = request.getParameter("loggedid");
			idOS = request.getParameter("idO");
			idCS = request.getParameter("idC");
			titulo = request.getParameter("titulo");
			resumen = request.getParameter("resumen");
			titulocap = request.getParameter("titulocap");
			texto = request.getParameter("texto");
			comentario = request.getParameter("comentario");
			imagenObra = request.getParameter("imagenObra");
			imagenCapi = request.getParameter("imagenCapi");
		}catch(NullPointerException e){}

		boolean resultado = false;

		
		
		
		/*	try{
			id = UsuariosLoggeados.getMyUsuariosLogeados().getUsuario(loggedID);
		}catch(NullPointerException e){}*/

		try{
			idO = Integer.parseInt(idOS);
			idC = Integer.parseInt(idCS);
			id= Integer.parseInt(loggedID);
		}catch(NumberFormatException e){e.printStackTrace();}

		Autor autor = null;
		try{
			autor = GestorBD.getGestorBD().getAutorBeansById(id);
			}catch(NullPointerException e){}
			

		if (id != 0 && autor.getActive() != 0){
			String pathImagenObra = null;
			if (!imagenObra.equals(""))
				pathImagenObra= Encoder.getMyEncoder().decodeInBase64(imagenObra, filePath);

			String pathImagenCapitulo = null;
			if (!imagenCapi.equals(""))
				pathImagenCapitulo= Encoder.getMyEncoder().decodeInBase64(imagenCapi, filePath);


			if (idO == 0)
			{ 
				//Obra nueva
				if (imagenObra.equals(""))
					idO = GestorBD.getGestorBD().insertarObra(id, titulo, resumen,null);
				else
					idO = GestorBD.getGestorBD().insertarObra(id, titulo, resumen,pathImagenObra);


				if (idC == 0){
					if (imagenCapi.equals(""))
						resultado =	GestorBD.getGestorBD().insertarCapitulo(idO, titulocap, texto, comentario,null) != 0;
					else
						resultado =	GestorBD.getGestorBD().insertarCapitulo(idO, titulocap, texto, comentario,pathImagenCapitulo) != 0;
				}
			}
			else
			{
				//Update o inserccion de una obra ya existente
				/*	if (imagenObra.equals(""))
					GestorBD.getGestorBD().updateObra(idO,titulo,resumen,null);
					else*/
				GestorBD.getGestorBD().updateObra(idO,titulo,resumen,pathImagenObra);
				resultado = true;
				if (idC != 0)
				{
					/*if(imagenCapi.equals(""))
						GestorBD.getGestorBD().updateChapter(idC,titulocap,texto,comentario,null);
					else*/
					GestorBD.getGestorBD().updateChapter(idC,titulocap,texto,comentario,pathImagenCapitulo) ;
					GestorBD.getGestorBD().updateObra(idO,titulo,resumen,pathImagenObra);

				}else{
					/*if(imagenCapi.equals(""))
						GestorBD.getGestorBD().insertarCapitulo(idO, titulocap, texto, comentario,null);
					else*/
					resultado = GestorBD.getGestorBD().insertarCapitulo(idO, titulocap, texto, comentario,pathImagenCapitulo) != 0; 
				}
			}
			//Mandamos un email a los usuario suscritos a la historia
			try {
			//	mandarEmailSeguimiento(idO,titulo);
				MailServer.getMyMailServer(username, password).mandarEmailSeguimiento(idO, titulo);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter pw = response.getWriter();
		response.setHeader("Access-Control-Allow-Origin", "*");
		pw.write(String.valueOf(new JSONObject().put("value", resultado).put("desh", autor.getActive() == 0)));
	}

	private void mandarEmailSeguimiento(int id, String tituloObra) throws MessagingException {
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

		//try {

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


		/*} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}*/

	}

}
