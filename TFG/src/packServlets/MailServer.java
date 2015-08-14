package packServlets;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import packBD.GestorBD;
import packBeans.Autor;
import packBeans.Obra;

public class MailServer {
	private static MailServer myMailServer = null;
	
	private final String username;
	private final String password;
	
	
	private MailServer(String pUsername,String pPassword){
		username = pUsername;
		password = pPassword;
	}
	
	public static MailServer getMyMailServer(String pUsername,String pPassword){
		if (myMailServer == null)
			myMailServer = new MailServer(pUsername,pPassword);
		
		return myMailServer;
	}
	
	public void mandarEmailSeguimiento(int id, String tituloObra) throws MessagingException {
		// TODO Auto-generated method stub
		ArrayList<Autor> lista = GestorBD.getGestorBD().getSuscriptores(id);

		if (lista.size() == 0)
			return;

	

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.sendgrid.net");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		//try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@escritoalavista.com"));

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
	
	public void mandarEmailRecuperarContrasena(int id, String contrasena) throws MessagingException {
		// TODO Auto-generated method stub
		Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);

		if (autor == null)
			return;

		/*	username = "admin@escritoalavista.com";
		final String password = "4wApEfE8";
		 */

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.sendgrid.net");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		//try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@escritoalavista.com"));

			if (autor != null)
			{
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(autor.getEmail()));
			}

			message.setSubject("Recuperación de contraseña");
			message.setText("Hola! " + autor.getNombre() + "\n" +
					"Tu nueva contraseña es: " + contrasena + "\n "
							+ "Recuerda modificar la contraseña lo antes posible");

			Transport.send(message);


		/*} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}*/

	}
	
	public void mandarEmailAvisoObraDesha(int id) throws MessagingException {
		// TODO Auto-generated method stub
		Obra obra = GestorBD.getGestorBD().getObraBeans(id);
		Autor autor = GestorBD.getGestorBD().getAutorBeansById(obra.getAutor());

		if (autor == null)
			return;

		

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.sendgrid.net");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		//try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@escritoalavista.com"));

			if (autor != null)
			{
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(autor.getEmail()));
			}

			message.setSubject("Aviso, obra deshabilitada");
			message.setText("Hola! " + autor.getNombre() + "\n" +
					"Tu obra " + obra.getTitulo() + " ha sido deshabilitada "
							+ "por el administrador. Para más información "
							+ "ponte en contacto con el administrador de "
							+ "la web.");

			Transport.send(message);


		/*} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}*/

	}
	
	public void mandarEmailAvisoObraHabil(int id) throws MessagingException {
		// TODO Auto-generated method stub
		Obra obra = GestorBD.getGestorBD().getObraBeans(id);
		Autor autor = GestorBD.getGestorBD().getAutorBeansById(obra.getAutor());

		if (autor == null)
			return;

		

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.sendgrid.net");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		//try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@escritoalavista.com"));

			if (autor != null)
			{
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(autor.getEmail()));
			}

			message.setSubject("Aviso, obra habilitada");
			message.setText("Hola! " + autor.getNombre() + "\n" +
					"Tu obra " + obra.getTitulo() + " ha sido habilitada "
							+ "por el administrador. Para más información "
							+ "ponte en contacto con el administrador de "
							+ "la web.");

			Transport.send(message);


		/*} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}*/

	}
	
	public void mandarEmailAvisoUsuarioDesha(int id) throws MessagingException {
		// TODO Auto-generated method stub
		Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);

		if (autor == null)
			return;

		/*	username = "admin@escritoalavista.com";
		final String password = "4wApEfE8";
		 */

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.sendgrid.net");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		//try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@escritoalavista.com"));

			if (autor != null)
			{
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(autor.getEmail()));
			}

			message.setSubject("Aviso, usuario deshabilitado");
			message.setText("Hola! " + autor.getNombre() + "\n" +
					"Tu usuario ha sido deshabilitado "
							+ "por el administrador. Para más información "
							+ "ponte en contacto con el administrador de "
							+ "la web.");

			Transport.send(message);


		/*} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}*/

	}
	public void mandarEmailAvisoUsuarioHabil(int id) throws MessagingException {
		// TODO Auto-generated method stub
		Autor autor = GestorBD.getGestorBD().getAutorBeansById(id);

		if (autor == null)
			return;

		/*	username = "admin@escritoalavista.com";
		final String password = "4wApEfE8";
		 */

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.sendgrid.net");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		//try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("admin@escritoalavista.com"));

			if (autor != null)
			{
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(autor.getEmail()));
			}

			message.setSubject("Aviso, usuario habilitado");
			message.setText("Hola! " + autor.getNombre() + "\n" +
					"Tu usuario ha sido habilitado "
							+ "por el administrador. Para más información "
							+ "ponte en contacto con el administrador de "
							+ "la web.");

			Transport.send(message);



	}
}
