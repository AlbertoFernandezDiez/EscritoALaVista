package packBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestorBD {
	private static GestorBD myGestorBD = null;

	private GestorBD(){
	}

	public static GestorBD getGestorBD()
	{
		if (myGestorBD == null){
			myGestorBD = new GestorBD();
		}
		return myGestorBD;
	}

	public String getCapitulos(){
		String cap = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/tfg", "root", "root");
			Statement st = conexion.createStatement();
			ResultSet rs = st.executeQuery("select * from capitulo" );
			while (rs.next())	   
			{
				cap += rs.getInt("id") + "<br>\n";
				cap += rs.getString("titulo") + "<br>\n";
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
	}
}
