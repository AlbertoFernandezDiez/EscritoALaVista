package packTestConversor;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.*;

import packBD.GestorBD;
import packBeans.Obra;
import packConversor.Itext;



public class TestItext {
	
	@Before
	public void setUp() throws Exception {
	

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		ArrayList<Obra> lista = GestorBD.getGestorBD().getObrasBeans(10, 0, 0);




		if (lista.size()!= 0)
		{
			URL url = new URL("http://127.0.0.1:8080/TFG/Itext?formato=Itext&id=" + lista.get(0).getId() + "&exportar=Enviar+consulta");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); 
			
			assertEquals("%PDF-1.4", in.readLine());
		}
	}

}
