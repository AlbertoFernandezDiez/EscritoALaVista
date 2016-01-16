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

public class TestActualizarObraCapituloAPI {

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
			peticion = new PeticionPost("http://127.0.0.1:8080/TFG/api/ActualizarObraCapituloAPI");
			JSONObject json = null;
			try {
				peticion.add("loggedid", String.valueOf(idUs));
				peticion.add("idO", String.valueOf(0));
				peticion.add("idC", String.valueOf(0));
				peticion.add("titulo", String.valueOf(aux));
				peticion.add("resumen", String.valueOf(aux));
				peticion.add("titulocap", String.valueOf(aux));
				peticion.add("texto", String.valueOf(aux));
				peticion.add("imagenObra", "");
				peticion.add("imagenCapi", "");
				
				json = new JSONObject(peticion.getRespueta());
				assertEquals(true,json.get("value"));
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
