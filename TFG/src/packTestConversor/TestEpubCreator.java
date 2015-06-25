package packTestConversor;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		ArrayList<Obra> lista = GestorBD.getGestorBD().getObrasBeans(10, 0, 0);

		if (lista.size()!= 0)
		{
			URL url;
			try {
				url = new URL("http://127.0.0.1:8080/TFG/EpubCreator?formato=Epub&id=" + lista.get(0).getId() + "&exportar=Enviar+consulta");
				BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
				assertEquals("PK", in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	}
}
