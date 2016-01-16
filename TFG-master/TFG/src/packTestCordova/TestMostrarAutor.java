package packTestCordova;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packBD.GestorBD;

public class TestMostrarAutor {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		
		long aux = System.currentTimeMillis();

		int idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));
		
		PeticionPost peticion = null;
		try {
			peticion = new PeticionPost("http://127.0.0.1:8080/TFG/api/MostrarAutor");
			JSONObject json = null;
			try {
				peticion.add("id", String.valueOf(idUs));

				json = new JSONObject(peticion.getRespueta());
			assertEquals(String.valueOf(aux),json.get("nombre"));
			assertEquals(String.valueOf(aux),json.get("pais"));

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GestorBD.getGestorBD().deleteAutor(idUs);
	}
}
