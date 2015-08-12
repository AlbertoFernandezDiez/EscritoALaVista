package packTestConversor;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Date;
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

		long aux = System.currentTimeMillis();

		int idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));
		

		int idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), null);
		GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), null);

	//	http://localhost:8080/TFG/Itext?formato=Itext&id=18&exportar=Exportar&tamano=small&tipo=book
			URL url = new URL("http://127.0.0.1:8080/TFG/Itext?formato=Itext&id=" + idObra + "&exportar=Enviar+consulta&tamano=small");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream())); 
			
			assertEquals("%PDF-1.4", in.readLine());
		
	}

}
