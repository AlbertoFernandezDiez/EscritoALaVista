package packTestConversor;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;

import org.junit.*;

import packBD.GestorBD;
import packBeans.Obra;

public class TestEpubCreator {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {
		long aux = System.currentTimeMillis();

		int idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));
		

		int idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), null);

	GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), null);
			URL url;
			try {
				url = new URL("http://127.0.0.1:8080/TFG/EpubCreator?formato=Epub&id=" + idObra + "&exportar=Enviar+consulta");
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				assertEquals("PK", in.readLine());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//	}
	}
}
