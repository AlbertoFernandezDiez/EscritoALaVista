package packBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

import packClases.Capitulo;
import packClases.ListaCapitulos;
import packClases.Obra;

public class GestorBD {
	private static GestorBD myGestorBD = null;
	private Connection conexion;

	private GestorBD(){

	}

	public static GestorBD getGestorBD()
	{
		if (myGestorBD == null){
			myGestorBD = new GestorBD();
		}
		return myGestorBD;
	}

	/*	public String getCapitulos(){
		String cap = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg?useUnicode=true&characterEncoding=utf-8", "root", "root");

			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from capitulo" );
			while (rs.next())	   
			{
				cap += rs.getInt("id") + "<br>\n";
				cap += rs.getString("nombre") + "<br>\n";
				cap += rs.getString("texto") + "<br>\n";
			}
			rs.close();
			st.close();
			conexion.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cap;
	}*/

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
						rs.getDate("fecha_comentario"));
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
						rs.getDate("fecha_in"), rs.getDate("fecha_mod"), rs.getInt("id"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obra;
	}


}
