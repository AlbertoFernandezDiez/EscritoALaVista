package packBD;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import packBeans.Autor;
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

	public ListaObras getObras()
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
						rs.getDate("fecha_in"), rs.getDate("fecha_mod"), rs.getInt("id"),rs.getString("portada"));
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
				/*cap += rs.getInt("id") + "<br>\n";
				cap += rs.getString("titulo") + "<br>\n";
				cap += rs.getString("texto") + "<br>\n";*/
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
						rs.getDate("fecha_in"), rs.getDate("fecha_mod"), rs.getInt("id"),rs.getString("portada"));
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

	public int insertarObra(int pAutor, String pTitulo, String pResumen){
		int id = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `obra` (`autor`, `titulo`, `resumen`, "
					+ "`fecha_in` , `fecha_mod`) VALUES ( ?, ?, ?, ?, ?)");
			st.setInt(1, pAutor);
			st.setString(2, pTitulo);
			st.setString(3, pResumen);
			st.setDate(4, new Date(System.currentTimeMillis()));
			st.setDate(5, new Date(System.currentTimeMillis()));
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

	public void insertarCapitulo(int pObra, String pTitulo, String pCapitulo, String pComentario){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("INSERT INTO `capitulo` (`obra`, `nombre`, `texto`,"
					+ " `comentarios_autor`, `fecha_comentario`) VALUES (?, ?, ?, ?, ?)");
			st.setInt(1, pObra);
			st.setString(2, pTitulo);
			st.setString(3, pCapitulo);
			st.setString(4, pComentario);
			st.setDate(5, new Date(System.currentTimeMillis()));
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

	public ArrayList<packBeans.Obra> getObrasBeans(int limit, int offset){
		ArrayList<packBeans.Obra> lista = new ArrayList<packBeans.Obra>();
		packBeans.Obra aux = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("select * from obra order by fecha_mod asc limit ? offset ?");
			st.setInt(1, limit);
			st.setInt(2, offset);
			ResultSet rs = st.executeQuery();
			while (rs.next())	   
			{
				aux = new packBeans.Obra();
				aux.setAutor(rs.getInt("autor"));
				aux.setFecha_in(rs.getDate("fecha_in"));
				aux.setFecha_mod(rs.getDate("fecha_mod"));
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

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("SELECT * FROM tfg.autor where id = ?;");
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

			PreparedStatement st = (PreparedStatement) conexion.prepareStatement("UPDATE `tfg`.`obra` SET "
					+ " `titulo`=?, `resumen`=?, `fecha_mod`=?, `portada`=? WHERE `id`='?';");

			st.setString(1, tituloObra);
			st.setString(2, resumen);
			st.setDate(3, new Date(System.currentTimeMillis()));
			st.setString(4, portada);
			st.setInt(5, idOb);
			
			st.executeUpdate();

			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateChapter(int idCap, String tituloCap, String capitulo,
			String resumen, String string) {
		// TODO Auto-generated method stub
		
	}
}
