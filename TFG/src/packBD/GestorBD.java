package packBD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.PreparedStatement;

import packBeans.Autor;
import packBeans.Comentario;
import packClases.Capitulo;
import packClases.ListaCapitulos;
import packClases.ListaObras;
import packClases.Obra;
import packClases.Usuario;

public class GestorBD {
	private static GestorBD myGestorBD = null;
	private Connection conexion = null;

	private GestorBD(){

	}

	public static GestorBD getGestorBD()
	{
		if (myGestorBD == null){
			myGestorBD = new GestorBD();
		}
		return myGestorBD;
	}

/*	public ListaObras getObras()
	{
		ListaObras lista = new ListaObras();
		Obra obra = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from obra");
			ResultSet rs = st.executeQuery();

			while(rs.next())
			{			
				obra = new Obra(rs.getString("titulo"), rs.getString("resumen"),
						rs.getDate("fecha_in"), rs.getTimestamp("fecha_mod"), rs.getInt("id"),rs.getString("portada"));
				lista.addObra(obra);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public ListaCapitulos getCapitulos(int pObra){
		ListaCapitulos lista = new ListaCapitulos();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			Capitulo cap = null;
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from capitulo where obra = ? order by id;");
			//Statement st = conexion.createStatement();
			st.setInt(1, pObra);
			ResultSet rs = st.executeQuery();//st.executeQuery("select * from capitulo where obra =" + obra + " order by id;");
			while (rs.next())	   
			{
				cap = new Capitulo(rs.getInt("id"), rs.getString("nombre"),
						rs.getString("texto"), rs.getString("comentarios_autor"),
						rs.getDate("fecha_comentario"),rs.getString("imagen"));
				
				lista.addCapitulo(cap);
			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public Capitulo getCapitulo(int pCapitulo){
		Capitulo capitulo = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			Capitulo cap = null;
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from capitulo where id = ?;");
			st.setInt(1, pCapitulo);
			ResultSet rs = st.executeQuery();

			if(rs.next())
				capitulo = new Capitulo(rs.getInt("id"), rs.getString("nombre"),
						rs.getString("texto"), rs.getString("comentarios_autor"),
						rs.getDate("fecha_comentario"),rs.getString("imagen"));
		}
		catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return capitulo;
	}

	public Obra getObra(int pObra)
	{
		Obra obra = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from obra where id = ?");
			st.setInt(1, pObra);
			ResultSet rs = st.executeQuery();

			if(rs.next())
			{			
				obra = new Obra(rs.getString("titulo"), rs.getString("resumen"),
						rs.getDate("fecha_in"), rs.getTimestamp("fecha_mod"), rs.getInt("id"),rs.getString("portada"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obra;
	}

	public Usuario getAutor(int pAutor) {
		Usuario autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select autor.* from autor,obra "
					+ "where obra.autor = autor.id and obra.id = ?");
			st.setInt(1, pAutor);
			ResultSet rs = st.executeQuery();

			if(rs.next())
			{		
				autor = new Usuario(rs.getInt("id"), rs.getString("pais"), rs.getDate("nacimiento"), rs.getString("nombre"), rs.getString("about"),rs.getString("imagen"));
				//autor = rs.getString("nombre");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autor;
	}
*/
	public int insertarObra(int pAutor, String pTitulo, String pResumen, String pRuta){
		int id = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `obra` (`autor`, `titulo`, `resumen`, "
					+ "`fecha_in` , `fecha_mod`,`portada`) VALUES ( ?, ?, ?, ?, ?,?)");
			st.setInt(1, pAutor);
			st.setString(2, pTitulo);
			st.setString(3, pResumen);
			st.setDate(4, new Date(System.currentTimeMillis()));
			st.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
			st.setString(6, pRuta);
			st.execute();

			st = (PreparedStatement) conexion.prepareStatement("SELECT id FROM obra where autor = ? and titulo = ? and resumen = ?");
			st.setInt(1, pAutor);
			st.setString(2, pTitulo);
			st.setString(3, pResumen);
			ResultSet rs = st.executeQuery();
			rs.first();
			id = rs.getInt("id");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	public void insertarCapitulo(int pObra, String pTitulo, String pCapitulo, String pComentario,String pRuta){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `capitulo` (`obra`, `nombre`, `texto`,"
					+ " `comentarios_autor`, `fecha_comentario`,`imagen`) VALUES (?, ?, ?, ?, ?,?)");
			st.setInt(1, pObra);
			st.setString(2, pTitulo);
			st.setString(3, pCapitulo);
			st.setString(4, pComentario);
			st.setDate(5, new Date(System.currentTimeMillis()));
			st.setString(6, pRuta);
			st.execute();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getIndiceCapituloUno(int idO) {
		int id = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT id FROM capitulo WHERE obra = ? ORDER BY id ASC LIMIT 1");
			st.setInt(1, idO);
			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				id = rs.getInt("id");
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	public ArrayList<packBeans.Capitulo> getCapituloBeans(int idO) {
		ArrayList<packBeans.Capitulo> lista = new ArrayList<packBeans.Capitulo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			packBeans.Capitulo cap = null;
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from capitulo where obra = ? order by id asc;");
			//Statement st = conexion.createStatement();
			st.setInt(1, idO);
			ResultSet rs = st.executeQuery();//st.executeQuery("select * from capitulo where obra =" + obra + " order by id;");
			while (rs.next())	   
			{
				cap = new packBeans.Capitulo();
				cap.setId(rs.getInt("id"));
				cap.setNombre(rs.getString("nombre"));
				cap.setObra(rs.getInt("obra"));
				cap.setComentarios_autor(rs.getString("comentarios_autor"));
				cap.setText(rs.getString("texto"));
				cap.setImagen(rs.getString("imagen"));
				cap.setFecha_comentario(rs.getDate("fecha_comentario"));

				lista.add(cap);
			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public ArrayList<packBeans.Obra> getObrasBeans(int limit, int offset, int id){
		ArrayList<packBeans.Obra> lista = new ArrayList<packBeans.Obra>();
		packBeans.Obra aux = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st;
			if (limit != 0)
			{
				if (id == 0){
					st = (PreparedStatement) conexion.prepareStatement("select * from obra order by fecha_mod asc limit ? offset ?");
					st.setInt(1, limit);
					st.setInt(2, offset);
				}
				else{
					st = (PreparedStatement) conexion.prepareStatement("select * from obra where autor = ? order by fecha_mod asc limit ? offset ?");
					st.setInt(1, id);
					st.setInt(2, limit);
					st.setInt(3, offset);}
			}
			else{
				if (id == 0){
					st = (PreparedStatement) conexion.prepareStatement("select * from obra order by fecha_mod asc");
				}
				else{
					st = (PreparedStatement) conexion.prepareStatement("select * from obra where autor = ? order by fecha_mod asc");
					st.setInt(1, id);
				}

			}

			ResultSet rs = st.executeQuery();
			while (rs.next())	   
			{
				aux = new packBeans.Obra();
				aux.setAutor(rs.getInt("autor"));
				aux.setFecha_in(rs.getDate("fecha_in"));
				aux.setFecha_mod(rs.getTimestamp("fecha_mod"));
				aux.setId(rs.getInt("id"));
				aux.setPortada(rs.getString("portada"));
				aux.setResumen(rs.getString("resumen"));
				aux.setTitulo(rs.getString("titulo"));


				lista.add(aux);
			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public int getMaxObrasN(int show) {
		int max = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT count(*)/? FROM tfg.obra;");
			st.setInt(1, show);

			ResultSet rs = st.executeQuery();
			if (rs.next())	   
			{
				max = rs.getInt(1);
				if (max != 0)
					max--;
			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max;
	}

	public Autor getAutorBeans(int id) {
		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select autor.* from autor,obra "
					+ "where obra.autor = autor.id and obra.id = ?");
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			if (rs.next())	   
			{
				autor = new Autor();
				autor.setId(rs.getInt("id"));
				autor.setImagen(rs.getString("imagen"));
				autor.setNacimiento(rs.getDate("nacimiento"));
				autor.setNombre(rs.getString("nombre"));
				autor.setPais(rs.getString("pais"));
				autor.setAbout(rs.getString("about"));
				autor.setEmail(rs.getString("email"));

			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autor;
	}

	public void updateObra(int idOb, String tituloObra, String resumen,String portada) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = null;
			if (portada != null)
			{
				st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`obra` SET "
						+ " `titulo`=?, `resumen`=?, `fecha_mod`=?, `portada`=? WHERE `id`=?;");

				st.setString(1, tituloObra);
				st.setString(2, resumen);
				st.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
				st.setString(4, portada);
				st.setInt(5, idOb);
			}
			else
			{
				st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`obra` SET "
						+ " `titulo`=?, `resumen`=?, `fecha_mod`=? WHERE `id`=?;");

				st.setString(1, tituloObra);
				st.setString(2, resumen);
				st.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis()));
				st.setInt(4, idOb);
			}

			st.executeUpdate();

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateChapter(int idCap, String tituloCap, String capitulo,
			String comentario, String pRuta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = null;
			if (pRuta != null)
			{
				st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`capitulo` SET `nombre`=?,"
						+ " `texto`=?, `comentarios_autor`=?, `imagen`=? WHERE `id`=?;");

				st.setString(1, tituloCap);
				st.setString(2, capitulo);
				st.setString(3, comentario);
				st.setString(4, pRuta);
				st.setInt(5, idCap);
			}
			else
			{
				st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`capitulo` SET `nombre`=?,"
						+ " `texto`=?, `comentarios_autor`=?  WHERE `id`=?;");
				st.setString(1, tituloCap);
				st.setString(2, capitulo);
				st.setString(3, comentario);
				st.setInt(4, idCap);
			}
			st.executeUpdate();

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public ArrayList<Autor> getAutoresBeans() {
		ArrayList<packBeans.Autor> lista = new ArrayList<packBeans.Autor>();
		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT * FROM tfg.autor;");

			ResultSet rs = st.executeQuery();
			while (rs.next())	   
			{
				autor = new Autor();
				autor.setId(rs.getInt("id"));
				autor.setImagen(rs.getString("imagen"));
				autor.setNacimiento(rs.getDate("nacimiento"));
				autor.setNombre(rs.getString("nombre"));
				autor.setPais(rs.getString("pais"));
				autor.setAbout(rs.getString("about"));
				autor.setEmail(rs.getString("email"));
				lista.add(autor);
			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lista;
	}

	public HashMap<Integer, String> getHasMapAutores() {
		HashMap<Integer, String> autores = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select id, nombre from autor;");
			ResultSet rs = st.executeQuery();
			autores = new HashMap<>();
			while(rs.next())
			{		
				autores.put(rs.getInt("id"), rs.getString("nombre"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autores;
	}

	/**
	 * Metodo que crea un nuevo usuario
	 * @param nombre	Nombre de usuario
	 * @param email	Email del usuario
	 * @param password Contraseña del usuario (Aplicado SHA-512)
	 * @param pais	Pais del usuario
	 * @param nac	Fecha de nacimiento
	 * @param about	Sobre el usuario
	 * @param ruta	Ruta de la imagen del perfil
	 */
	public void addUser(String nombre, String email, String password,
			String pais, Date nac, String about, String ruta) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			String sal = toSha512(String.valueOf(System.currentTimeMillis()));
			String contra = toSha512(toSha512(password) + sal);
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `tfg`.`autor` (`pais`, `nacimiento`, `nombre`, `password`, `sal`, `about`, `imagen`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			st.setString(1, pais);
			st.setDate(2, nac);
			st.setString(3, nombre);
			st.setString(4, contra);
			st.setString(5, sal);
			st.setString(6, about);
			st.setString(7, ruta);
			st.setString(8, email);
			st.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String toSha512(String contrasena){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String clave = contrasena;
		md.update(clave.getBytes());
		String output = "";
		byte[] mb = md.digest();
		for (int i = 0; i < mb.length; i++) {
			byte temp = mb[i];
			String s = Integer.toHexString(new Byte(temp));
			while (s.length() < 2) {
				s = "0" + s;
			}
			s = s.substring(s.length() - 2);
			output += s;
		}
		return output;
	}

	/**
	 * Metodo que devuelve el id de usuario si 
	 * la contraseña y el usuario son correctos
	 * @param username el nombre del usuario
	 * @param password la contraseña con SHA512 
	 * aplicado
	 * @return el id del usuario en BD (si los
	 * datos no son correctos se devuelve 0)
	 */
	public int checkUser(String username, String password) {
		// TODO Auto-generated method stub
		int id = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT id,password,sal"
					+ " FROM tfg.autor where nombre = ?;");
			st.setString(1, username);

			ResultSet rs = st.executeQuery();

			if (rs.next()){
				String sal = rs.getString("sal");
				String contra = rs.getString("password");
				String passw = toSha512(toSha512(password) + sal);

				if (contra.equals(passw))
					id = rs.getInt("id");
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * Metodo que añade un comentario en la BD
	 * @param id	Id del usuario en BD
	 * @param capitulo	Id del capitulo en BD
	 * @param obra	Id de la obra en BD
	 * @param texto	El texto del comentario
	 * @return	True si no ha habido errrores, false en caso contrario
	 */
	public boolean addComment(int id, int capitulo, int obra, String texto) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `tfg`.`comentario` "
					+ "(`autor`, `obra`, `capitulo`, `texto`,`fecha_comentario`) "
					+ "VALUES (?, ?, ?, ?, ?);");
			st.setInt(1, id);
			st.setInt(2, obra);
			st.setInt(3, capitulo);
			st.setString(4, texto);
			st.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
			// new java.sql.Timestamp(System.currentTimeMillis())

			st.execute();
			result = true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Metodo que devuelve los comentarios asociados
	 * a un capitulo de una obra
	 * @param idO Id de la obra en BD
	 * @param idC Id del capitulo en BD
	 * @return Lista con los comentarios
	 */
	public ArrayList<Comentario> getComentariosBeans(int idO, int idC) {
		// TODO Auto-generated method stub
		ArrayList<Comentario> lista = new ArrayList<Comentario>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT * FROM tfg.comentario "
					+ " where obra=?  and capitulo=? order by fecha_comentario desc;");
			st.setInt(1, idO);
			st.setInt(2, idC);

			ResultSet rs = st.executeQuery();
			packBeans.Comentario comentario = null;
			while (rs.next())
			{
				comentario = new Comentario();
				comentario.setAutor(rs.getInt("autor"));
				comentario.setCapitulo(rs.getInt("capitulo"));
				comentario.setComentario(rs.getInt("comentario"));
				comentario.setFecha_comentario(rs.getTimestamp("fecha_comentario"));
				comentario.setObra(rs.getInt("obra"));
				comentario.setTexto(rs.getString("texto"));

				lista.add(comentario);
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	/**
	 * Método que comprueba si el nombre introducido por el usuario existe
	 * @param nombre El nombre introducido por el usuario
	 * @return True si el nombre esta sin utilizar
	 */
	public boolean comprobarNombre(String nombre) {
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT count(nombre)"
					+ " FROM tfg.autor where nombre=?;");
			st.setString(1, nombre);


			ResultSet rs = st.executeQuery();

			if (rs.next())
				result = rs.getInt(1) == 0;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	public packBeans.Obra getObraBeans(int id) {
		packBeans.Obra obra = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from obra where id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if(rs.next())
			{		obra = new packBeans.Obra();
			obra.setAutor(rs.getInt("autor"));
			obra.setFecha_in(rs.getDate("fecha_in"));
			obra.setFecha_mod(rs.getTimestamp("fecha_mod"));
			obra.setId(rs.getInt("id"));
			obra.setPortada(rs.getString("portada"));
			obra.setResumen(rs.getString("resumen"));
			obra.setTitulo(rs.getString("titulo"));
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obra;
	}

	public packBeans.Capitulo getCapitulosBeans(int id) {
		packBeans.Capitulo cap = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from capitulo where id = ?;");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if(rs.next())
				cap = new packBeans.Capitulo();
			cap.setId(rs.getInt("id"));
			cap.setNombre(rs.getString("nombre"));
			cap.setObra(rs.getInt("obra"));
			cap.setComentarios_autor(rs.getString("comentarios_autor"));
			cap.setText(rs.getString("texto"));
			cap.setImagen(rs.getString("imagen"));
			cap.setFecha_comentario(rs.getDate("fecha_comentario"));
		}
		catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cap;
	}

	public Autor getAutorBeansById(int id) {
		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select autor.* from autor"
					+ " where autor.id = ?");
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			if (rs.next())	   
			{
				autor = new Autor();
				autor.setId(rs.getInt("id"));
				autor.setImagen(rs.getString("imagen"));
				autor.setNacimiento(rs.getDate("nacimiento"));
				autor.setNombre(rs.getString("nombre"));
				autor.setPais(rs.getString("pais"));
				autor.setAbout(rs.getString("about"));
				autor.setEmail(rs.getString("email"));

			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autor;
	}

	/**
	 * Método que permite alterar los datos para un usuario dado su id en BD
	 * @param id	Su Id en BD
	 * @param mailS	El nuevo mail
	 * @param paisS	El pais
	 * @param aboutS	Infomación sobre el
	 * @param pRuta	Ruta a su imagen de perfil
	 */
	public void updateAutor(int id, String mailS, String paisS, String aboutS,
			String pRuta) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = null;
			if (pRuta != null)
			{
				st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`autor` SET"
						+ " `pais`=?, `about`=?, `email`=? ,`imagen` = ? WHERE `id`=?;");

				st.setString(1, paisS);
				st.setString(2, aboutS);
				st.setString(3, mailS);
				st.setString(4, pRuta);
				st.setInt(5, id);
			}
			else
			{
				st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`autor` SET"
						+ " `pais`=?, `about`=?, `email`=? WHERE `id`=?;");
				st.setString(1, paisS);
				st.setString(2, aboutS);
				st.setString(3, mailS);
				st.setInt(4, id);
			}
			st.executeUpdate();

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
/**
 * Metodo que cambia la contraseña de un usuario
 * tras comprobar con la contraseña antigua si
 * realmente es el usuario
 * @param id	Identificador del usuario en BD
 * @param old	Contraseña antigua
 * @param newC	Contraseña nueva
 * @return
 */
	public boolean changePassword(int id, String old, String newC) {
		boolean result = false;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT *"
					+ " FROM tfg.autor where id = ?;");
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				String sal = rs.getString("sal");
				String contra = rs.getString("password");
				String passw = toSha512(toSha512(old) + sal);

				if (contra.equals(passw)){
					 st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`autor` "
					 		+ "SET `password`=? WHERE `id`=?;");
				st.setString(1, toSha512(toSha512(newC) + sal));
				st.setInt(2, id);
				st.execute();
				result = true;
				}
			}

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * Método que comprueba la contraseña dada
	 * e identificar si el usuario es el 
	 * administrador
	 * @param password	La contraseña con SHA-512
	 * @return True si es el administrador
	 */
public boolean checkAdmin(String password) {
	boolean admin = false;
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/tfg", "root", "root");

		PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT *"
				+ " FROM tfg.admin where id = 1;");

		ResultSet rs = st.executeQuery();

		if (rs.next()){
			String sal = rs.getString("salt");
			String contra = rs.getString("password");
			String passw = toSha512(toSha512(password) + sal);

			if (contra.equals(passw))
				admin = true;		}

	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return admin;
}

/**
 * Método que elimina una obra de la BD
 * @param id Id de la obra en BD
 * @return True si se ha eliminado 
 */
	public boolean deleteObra(int id) {
		// TODO Auto-generated method stub
		boolean result = false; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("DELETE FROM `tfg`.`obra` "
					+ "WHERE `id`=?;");
			st.setInt(1, id);
			
			st.execute();
			result = true;

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

public boolean deleteAutor(int id) {
	// TODO Auto-generated method stub
	boolean result = false; 
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conexion = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/tfg", "root", "root");

		PreparedStatement st = (PreparedStatement) conexion.prepareStatement("DELETE FROM `tfg`.`autor` "
				+ "WHERE `id`=?;");
		st.setInt(1, id);
		
		st.execute();
		result = true;

	} catch (SQLException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}

}
