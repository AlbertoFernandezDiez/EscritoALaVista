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

public class TestCambiarObraApi {

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

		int idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), null);

		GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), null);


		PeticionPost peticion = null;
		try {
			peticion = new PeticionPost("http://127.0.0.1:8080/TFG/api/CambiarObraApi");
			JSONObject json = null;
			try {
				peticion.add("loggedid", String.valueOf(idUs));
				

				json = new JSONObject(peticion.getRespueta());
				assertTrue(json.getJSONArray("lista").length() > 0);
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
