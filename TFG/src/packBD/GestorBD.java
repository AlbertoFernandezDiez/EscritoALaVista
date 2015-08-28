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
import packBeans.Obra;


public class GestorBD {
	private static GestorBD myGestorBD = null;
	private Connection conexion = null;
	private String userBD = "escritoalavista", passBD = "admin";

	private GestorBD(){

	}


	public static GestorBD getGestorBD()
	{
		if (myGestorBD == null){
			myGestorBD = new GestorBD();
		}
		return myGestorBD;
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

	/*	public ListaObras getObras()
	{
		ListaObras lista = new ListaObras();
		Obra obra = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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

	/**
	 * Metodo que inserta una nueva obra en la BD
	 * @param pAutor	Id del autor en BD
	 * @param pTitulo	Titulo de la obra
	 * @param pResumen	Resumen de la obra
	 * @param pRuta	Ruta de la portada (si tiene)
	 * @return	Id en BD de la obra
	 */
	public int insertarObra(int pAutor, String pTitulo, String pResumen, String pRuta){
		int id = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}

	/**
	 * Metodo que inserta un nuevo capitulo en BD
	 * @param pObra	Id de la obra en BD
	 * @param pTitulo	Titulo del capitulo
	 * @param pCapitulo	Texto del capitulo
	 * @param pComentario	Comentario del autor
	 * @param pRuta	Ruta de la imagen del capitulo (si tiene)
	 * @return Indice en BD del capitulo insertado, 0 si no se ha insertado
	 */
	public int insertarCapitulo(int pObra, String pTitulo, String pCapitulo, String pComentario,String pRuta){
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `capitulo` (`obra`, `nombre`, `texto`,"
					+ " `comentarios_autor`, `fecha_comentario`,`imagen`) VALUES (?, ?, ?, ?, ?,?)");
			st.setInt(1, pObra);
			st.setString(2, pTitulo);
			st.setString(3, pCapitulo);
			st.setString(4, pComentario);
			st.setDate(5, new Date(System.currentTimeMillis()));
			st.setString(6, pRuta);
			st.execute();

			st = (PreparedStatement) conexion.prepareStatement("SELECT id FROM capitulo where"
					+ " obra = ? and nombre = ? and texto = ?");
			st.setInt(1, pObra);
			st.setString(2, pTitulo);
			st.setString(3, pCapitulo);

			ResultSet rs = st.executeQuery();

			if (rs.next())
				result = rs.getInt("id");

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Metodo que devuelve el indice del primer
	 * capitulo de una obra
	 * @param idO Id de la obra en BD
	 * @return El id en BD del primer capitulo
	 * de la obra, 0 si no lo encuentra
	 */
	public int getIndiceCapituloUno(int idO) {
		int id = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT id FROM capitulo WHERE obra = ? ORDER BY id ASC LIMIT 1");
			st.setInt(1, idO);
			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				id = rs.getInt("id");
			}

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	/**
	 * Metodo que devuelve la lista de Capitulos
	 * de una obra
	 * @param idO Id de la obra en BD
	 * @return ArrayList con los capitulos
	 */
	public ArrayList<packBeans.Capitulo> getCapituloBeans(int idO) {
		ArrayList<packBeans.Capitulo> lista = new ArrayList<packBeans.Capitulo>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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

	/**
	 * Metodo que devuelve la lista de obras
	 * @param limit El numero maximo de obras en la lista
	 * @param offset	El offset para la selección de las obras
	 * @param id Id en BD del autor de las obras (si es 0
	 * se devuelven de todos los autores)
	 * @return ArrayList con las obras
	 */
	public ArrayList<packBeans.Obra> getObrasBeans(int limit, int offset, int id){
		ArrayList<packBeans.Obra> lista = new ArrayList<packBeans.Obra>();
		packBeans.Obra aux = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st;
			if (limit != 0)
			{
				if (id == 0){
					st = (PreparedStatement) conexion.prepareStatement("select * from obra where active = 1 order by fecha_mod desc limit ? offset ?");
					st.setInt(1, limit);
					st.setInt(2, offset);
				}
				else{
					st = (PreparedStatement) conexion.prepareStatement("select * from obra where active = 1 and autor = ? order by fecha_mod desc limit ? offset ?");
					st.setInt(1, id);
					st.setInt(2, limit);
					st.setInt(3, offset);}
			}
			else{
				if (id == 0){
					st = (PreparedStatement) conexion.prepareStatement("select * from obra where active = 1 order by fecha_mod desc");
				}
				else{
					st = (PreparedStatement) conexion.prepareStatement("select * from obra where autor = ? order by fecha_mod desc");
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
				aux.setActive(rs.getInt("active"));


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

	/**
	 * Metodo que indica el numero de paginas que
	 * hay que generar en caso de mostrarse n obras 
	 * por pagina
	 * @param show	Numero de obras a mostrar
	 * @return	Numero de paginas maximo
	 */
	public int getMaxObrasN(int show) {
		int max = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT count(*)/? FROM tfg.obra;");
			st.setInt(1, show);

			ResultSet rs = st.executeQuery();
			if (rs.next())	   
			{
				max = rs.getInt(1);
				if (max > 1)
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

	/**
	 * Metodo que devuelve un autor 
	 * dado el Id de BD de una de sus obras
	 * @param id Id de una obra en BD
	 * @return	El autor de la obra
	 */
	public Autor getAutorBeans(int id) {
		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

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
				autor.setActive(rs.getInt("active"));

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
	 * Metodo que actualiza el contenido de una obra
	 * @param idOb	Id en BD de la obra
	 * @param tituloObra	Titulo de la obra
	 * @param resumen	Resumen de la obra
	 * @param portada	Ruta de la portada (si se quiere modificar)
	 */
	public void updateObra(int idOb, String tituloObra, String resumen,String portada) {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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

	/**
	 * Metodo que actualiza un capitulo
	 * @param idCap	Id del capitulo en BD
	 * @param tituloCap	Titulo del capitulo
	 * @param capitulo	Texto del capitulo
	 * @param comentario	Comentario del autor
	 * @param pRuta	Ruta de la imagen del capitulo (si se quiere cambiar)
	 */
	public void updateChapter(int idCap, String tituloCap, String capitulo,
			String comentario, String pRuta) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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

	/**
	 * Metodo que devuelv la lista de autores
	 * @return ArrayList con todos los autores
	 */
	public ArrayList<Autor> getAutoresBeans() {
		ArrayList<packBeans.Autor> lista = new ArrayList<packBeans.Autor>();
		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

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
				autor.setActive(rs.getInt("active"));
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

	/**
	 * Metodo que devuelve un HashMap con los autores
	 * conn su id como clave
	 * @return	HashMap de autores
	 */
	public HashMap<Integer, String> getHasMapAutores() {
		HashMap<Integer, String> autores = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select id, nombre from autor;");
			ResultSet rs = st.executeQuery();
			autores = new HashMap<>();
			while(rs.next())
			{		
				autores.put(rs.getInt("id"), rs.getString("nombre"));
			}

			rs.close();
			st.close();
			conexion.close();
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
	 * @return Id del autor en BD, si no se ha introducido 0
	 */
	public int addUser(String nombre, String email, String password,
			String pais, Date nac, String about, String ruta) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
			String sal = toSha512(String.valueOf(System.currentTimeMillis()));
			String contra = toSha512(toSha512(password) + sal);
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `tfg`.`autor`"
					+ " (`pais`, `nacimiento`, `nombre`, `password`, `sal`, `about`, `imagen`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
			st.setString(1, pais);
			st.setDate(2, nac);
			st.setString(3, nombre);
			st.setString(4, contra);
			st.setString(5, sal);
			st.setString(6, about);
			st.setString(7, ruta);
			st.setString(8, email);
			st.executeUpdate();


			st = (PreparedStatement) conexion.prepareStatement("SELECT id FROM autor where pais = ? and"
					+ " nacimiento = ? and nombre = ? and password = ? and sal = ? and about = ? and  email = ?;");
			st.setString(1, pais);
			st.setDate(2, nac);
			st.setString(3, nombre);
			st.setString(4, contra);
			st.setString(5, sal);
			st.setString(6, about);
			st.setString(7, email);

			ResultSet rs = st.executeQuery();

			if (rs.next())
				result = rs.getInt("id");	

			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

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
			st.close();
			conexion.close();
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

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
			st.close();
			conexion.close();
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

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
			st.close();
			conexion.close();
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT count(nombre)"
					+ " FROM tfg.autor where nombre=?;");
			st.setString(1, nombre);


			ResultSet rs = st.executeQuery();

			if (rs.next())
				result = rs.getInt(1) == 0;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Metodo que devuelve una obra
	 * @param id	Id de la obra en BD
	 * @return	La obra, si no existe null
	 */
	public packBeans.Obra getObraBeans(int id) {
		packBeans.Obra obra = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from obra where id = ?");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if(rs.next())
			{		
				obra = new packBeans.Obra();
				obra.setAutor(rs.getInt("autor"));
				obra.setFecha_in(rs.getDate("fecha_in"));
				obra.setFecha_mod(rs.getTimestamp("fecha_mod"));
				obra.setId(rs.getInt("id"));
				obra.setPortada(rs.getString("portada"));
				obra.setResumen(rs.getString("resumen"));
				obra.setTitulo(rs.getString("titulo"));
				obra.setActive(rs.getInt("active"));
			}

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obra;
	}

	/**
	 * Metodo que devuelve un capitulo
	 * @param id	Id del capitulo en BD
	 * @return	El capitulo, si no existe null
	 */
	public packBeans.Capitulo getCapitulosBeans(int id) {
		packBeans.Capitulo cap = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from capitulo where id = ?;");
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			if(rs.next())
			{	
				cap = new packBeans.Capitulo();
				cap.setId(rs.getInt("id"));
				cap.setNombre(rs.getString("nombre"));
				cap.setObra(rs.getInt("obra"));
				cap.setComentarios_autor(rs.getString("comentarios_autor"));
				cap.setText(rs.getString("texto"));
				cap.setImagen(rs.getString("imagen"));
				cap.setFecha_comentario(rs.getDate("fecha_comentario"));
			}
			st.close();
			conexion.close();
		}
		catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cap;
	}

	/**
	 * Metodo que devuelve un autor
	 * @param id Id del autor en BD
	 * @return	El autor, si no existe
	 * null
	 */
	public Autor getAutorBeansById(int id) {
		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

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
				autor.setActive(rs.getInt("active"));

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
	 * @return True si se ha acatulizado correctamente
	 */
	public boolean updateAutor(int id, String mailS, String paisS, String aboutS,
			String pRuta) {
		boolean update = false; 
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);
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
			update = true;
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return update;
	}
	/**
	 * Metodo que cambia la contraseña de un usuario
	 * tras comprobar con la contraseña antigua si
	 * realmente es el usuario
	 * @param id	Identificador del usuario en BD
	 * @param old	Contraseña antigua
	 * @param newC	Contraseña nueva
	 * @return True si se ha cambiado la contraseña
	 *  correctamente
	 */
	public boolean changePassword(int id, String old, String newC) {
		boolean result = false;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

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
			st.close();
			conexion.close();

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
	 * @param adminName Nombre del admin en BD
	 * @param password	La contraseña con SHA-512
	 * @return True si es el administrador
	 */
	public boolean checkAdmin(String adminName, String password) {
		boolean admin = false;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT *"
					+ " FROM tfg.admin where name = ?;");

			st.setString(1, adminName);

			ResultSet rs = st.executeQuery();

			if (rs.next()){
				String sal = rs.getString("salt");
				String contra = rs.getString("password");
				String passw = toSha512(toSha512(password) + sal);

				if (contra.equals(passw))
					admin = true;		}
			st.close();
			conexion.close();
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
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("DELETE FROM `tfg`.`obra` "
					+ "WHERE `id`=?;");
			st.setInt(1, id);

			st.execute();
			result = true;
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Metood que elimina un  autor, sus obras y comentarios de BD
	 * @param id Id del autor en BD
	 * @return	True si se ha eliminado correctamente
	 */
	public boolean deleteAutor(int id) {
		// TODO Auto-generated method stub
		boolean result = false; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("DELETE FROM `tfg`.`autor` "
					+ "WHERE `id`=?;");
			st.setInt(1, id);

			st.execute();
			result = true;
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Metodo que modifica la contraseña de administrador
	 * @param name Nombre del admin en BD
	 * @param old	Contraseña antigua (con SHA512 aplicado)
	 * @param newC	Contraseña nueva (con SHA512 aplicado)
	 * @param string 
	 * @return True si se ha cambiado correctamente
	 */
	public boolean changePasswordAdmin(String name,String old, String newC) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT *"
					+ " FROM tfg.admin where name = ?;");

			st.setString(1, name);

			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				String sal = rs.getString("salt");
				String contra = rs.getString("password");
				String passw = toSha512(toSha512(old) + sal);
				int id = rs.getInt("id");
				if (contra.equals(passw)){
					st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`admin` "
							+ "SET `password`=? where name = ?;");
					st.setString(1, toSha512(toSha512(newC) + sal));
					st.setString(2, name);
					st.execute();
					result = true;
				}
			}
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Metodo que comprueba si el titulo
	 * ya esta en BD
	 * @param nombre El titulo de la obra
	 * @return True si se no esta usado
	 */
	public boolean comprobarTitulo(String nombre) {
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT count(titulo)"
					+ " FROM tfg.obra where titulo=?;");
			st.setString(1, nombre);


			ResultSet rs = st.executeQuery();

			if (rs.next())
				result = rs.getInt(1) == 0;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Método que dice si un autor esta
	 * suscrito a una obra
	 * @param idO	id de la obra en BD
	 * @param id	id del autor en BD
	 * @return	true si la sigue, si no false
	 */
	public boolean getObraAutorChecked(int idO, int id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT * FROM tfg.seguimiento where"
					+ " idUsuario = ? and idObra = ?;");
			st.setInt(1, id);
			st.setInt(2, idO);

			ResultSet rs = st.executeQuery();

			if (rs.next())
				result = true;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;	
	}

	/**
	 * Metodo que añade a la lista
	 * de seguimiento una obra y un
	 * autor
	 * @param id	id del autor en BD
	 * @param obra	id de la obra en BD
	 * @return True si no ha habido fallos
	 */
	public boolean AddSeguimiento(int id, int obra) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `tfg`.`seguimiento`"
					+ " (`idUsuario`, `idObra`) VALUES (?, ?);");

			st.setInt(1, id);
			st.setInt(2, obra);

			st.execute();

			result = true;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;	
	}

	/**
	 *  Metodo que quita de la lista
	 * de seguimiento una obra y un
	 * autor
	 * @param id	id del autor en BD
	 * @param obra	id de la obra en BD
	 * @return True si no ha habido fallos
	 */
	public boolean QuitSeguimiento(int id, int obra) {
		// TODO Auto-generated method stub
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("DELETE FROM `tfg`.`seguimiento`"
					+ " WHERE `idUsuario`= ? and`idObra`= ?;");

			st.setInt(1, id);
			st.setInt(2, obra);

			st.execute();

			result = true;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;	
	}

	/**
	 * Metodo que devuelve una lista
	 * con los usuarios suscritos a una obra
	 * @param id	Id en BD de la obra
	 * @return	La lista de usuarios suscritos
	 */
	public ArrayList<Autor> getSuscriptores(int id) {
		ArrayList<packBeans.Autor> lista = new ArrayList<packBeans.Autor>();
		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT autor.* FROM tfg.seguimiento, tfg.autor "
					+ "where seguimiento.idUsuario = autor.id and seguimiento.idObra = ?;");

			st.setInt(1, id);

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

	/**
	 * Metodo que cambia la visibilidad de un Autor
	 * @param id	Id del autor en BD
	 * @param opcion	Nuevo valor del atributo de visibilidad
	 * 0 oculto, 1 visible
	 * @return
	 */
	public boolean modificarVisibilidadAutor(int id, int opcion) {
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`autor` SET `active`= ? WHERE `id`= ? ;");

			st.setInt(1, opcion);
			st.setInt(2, id);

			st.execute();

			result = true;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;	
	}

	/**
	 * Metodo que cambia la visibilidad de una Obra
	 * @param id	Id de la obra en BD
	 * @param opcion	Nuevo valor del atributo de visibilidad
	 * 0 oculto, 1 visible
	 * @return
	 */
	public boolean modificarVisibilidadObra(int id, int opcion) {
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`obra` SET `active`= ? WHERE `id`= ? ;");

			st.setInt(1, opcion);
			st.setInt(2, id);

			st.execute();

			result = true;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;	
	}

	public ArrayList<Obra> getObrasBeansAll() {
		ArrayList<packBeans.Obra> lista = new ArrayList<packBeans.Obra>();
		packBeans.Obra aux = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st;

			st = (PreparedStatement) conexion.prepareStatement("select * from obra order by fecha_mod asc");


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
				aux.setActive(rs.getInt("active"));


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

	/**
	 * Método que devuelve la lista de 
	 * autores deshabilitados
	 * @return ArrayList de autores
	 */
	public ArrayList<Autor> getAutoresBeansDeshabilitados() {
		ArrayList<packBeans.Autor> lista = new ArrayList<packBeans.Autor>();
		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT * FROM tfg.autor where active = 0;");

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
				autor.setActive(rs.getInt("active"));
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

	/**
	 * Método que comprueba si un autor
	 * esta deshabilitado
	 * @param id	Id del autor en BD
	 * @return ArrayList de autores
	 */
	public boolean checkAutorDeshabilitado(int id) {
		boolean deshabilitado = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT active FROM tfg.autor where id = ?;");
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();
			if (rs.next())	   
			{
				int activ = rs.getInt("active");
				deshabilitado = (activ == 0);

			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deshabilitado;
	}

	/**
	 * Metodo que devuelve una lista
	 * con todas las obras deshabilitadas
	 * @return Arraylist de obras
	 */
	public ArrayList<Obra> getObrasBeansDeshabilitadas() {
		ArrayList<packBeans.Obra> lista = new ArrayList<packBeans.Obra>();
		packBeans.Obra aux = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st;

			st = (PreparedStatement) conexion.prepareStatement("select * from obra where active = 0 order by fecha_mod asc");


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
				aux.setActive(rs.getInt("active"));


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

	/**
	 * Método que elimina un capitulo de la BD
	 * @param id Id del capitulo en BD
	 * @return True si se ha eliminado correctamente
	 */
	public boolean deleteCapitulo(int id) {
		boolean result = false; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("DELETE FROM `tfg`.`capitulo` WHERE `id`= ? ;");
			st.setInt(1, id);

			st.execute();
			result = true;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Método que devuelve un autor
	 * dada su dirección de email
	 * @param mail	Dirección de email 
	 * del usuario
	 * @return	Si existe devuelve el 
	 * usuario, si no devuelve null;
	 */
	public Autor getAutorBeansByEmail(String mail) {

		packBeans.Autor autor = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select autor.* from autor"
					+ " where autor.email = ?");
			st.setString(1, mail);

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
				autor.setActive(rs.getInt("active"));

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
	 * Método que establece una nueva contraseña
	 * @param id	Id del autor en BD
	 * @param contrasena	La nueva contasenaña sin SHA512
	 * @return	True si se ha cambiado correctamente
	 */
	public boolean recuperarContrasena(int id, String contrasena) {
		boolean result = false;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT *"
					+ " FROM tfg.autor where id = ?;");
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next())
			{
				String sal = rs.getString("sal");

				st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`autor` "
						+ "SET `password`=? WHERE `id`=?;");
				st.setString(1, toSha512(toSha512(toSha512(contrasena)) + sal));
				st.setInt(2, id);
				st.execute();
				result = true;
			}
			st.close();
			conexion.close();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Método que indica si el email esta siendo
	 * utilizado por otro usuario
	 * @param email	Email a comprobar en BD
	 * @return	True si el nombre esta sin utilizar
	 */
	public boolean comprobarEmail(String email) {
		boolean result = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg",userBD, passBD);

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT count(*)"
					+ " FROM tfg.autor where email = ?;");
			st.setString(1, email);


			ResultSet rs = st.executeQuery();

			if (rs.next())
				result = rs.getInt(1) == 0;

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}
}
