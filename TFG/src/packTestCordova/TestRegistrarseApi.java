package packTestCordova;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packBD.GestorBD;
import packBeans.Autor;

public class TestRegistrarseApi {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		long aux = System.currentTimeMillis();

		PeticionPost peticion = null;
		try {
			peticion = new PeticionPost("http://127.0.0.1:8080/TFG/api/RegistrarseApi");
			JSONObject json = null;
			try {
				peticion.add("nombre", String.valueOf(aux));
				peticion.add("contrasena", String.valueOf(aux));
				peticion.add("email", String.valueOf(aux));
				peticion.add("pais", String.valueOf(aux));
				peticion.add("nacimiento", String.valueOf(aux));
				peticion.add("about", String.valueOf(aux));
				peticion.add("foto", String.valueOf(aux));


				json = new JSONObject(peticion.getRespueta());
				assertEquals(true, json.get("value"));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try{
			Autor autor = GestorBD.getGestorBD().getAutorBeansByEmail(String.valueOf(aux));
			GestorBD.getGestorBD().deleteAutor(autor.getId());
		}catch(NullPointerException e){}
	}

}
