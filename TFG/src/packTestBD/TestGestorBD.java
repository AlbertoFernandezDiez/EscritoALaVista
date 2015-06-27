package packTestBD;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.apache.commons.collections4.Get;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packBD.GestorBD;
import packBeans.*;

public class TestGestorBD {
	protected int idObra,idUs,idCap;
	protected Autor autor;
	protected Capitulo cap;
	protected Obra obra;

	@Before
	public void setUp() throws Exception {
		//fail("Not yet implemented");
	}

	@After
	public void tearDown() throws Exception {
		GestorBD.getGestorBD().deleteAutor(idUs);

	}

	@Test
	public void testAddUser() {
		long aux = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		Date nac = new Date(cal.YEAR, cal.MONTH, cal.DAY_OF_MONTH);
		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),nac, String.valueOf(aux), String.valueOf(aux));

		autor = GestorBD.getGestorBD().getAutorBeansById(idUs);

		assertEquals(nac, autor.getNacimiento());
		assertArrayEquals(String.valueOf(aux).split("\n"), autor.getAbout());
		assertEquals(String.valueOf(aux), autor.getEmail());
		assertEquals(String.valueOf(aux), autor.getImagen());
		assertEquals(String.valueOf(aux), autor.getNombre());
		assertEquals(String.valueOf(aux), autor.getPais());

	}

	@Test
	public void testInsertarObra() {

		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);

		assertEquals(idUs, obra.getAutor());
		assertEquals(String.valueOf(aux), obra.getPortada());
		assertEquals(String.valueOf(aux), obra.getResumen());
		assertEquals(String.valueOf(aux), obra.getTitulo());
	}

	@Test
	public void testInsertarCapitulo() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		cap = GestorBD.getGestorBD().getCapitulosBeans(idCap);

		assertEquals(String.valueOf(aux), cap.getComentarios_autor());
		assertEquals(String.valueOf(aux) ,cap.getImagen());
		assertEquals(String.valueOf(aux), cap.getNombre());
		assertArrayEquals(String.valueOf(aux).split("\n"), cap.getText());
		assertEquals(idObra, cap.getObra());

	}

	@Test
	public void testGetIndiceCapituloUno() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertNotEquals(0, GestorBD.getGestorBD().getIndiceCapituloUno(idObra));
	}

	@Test
	public void testGetCapituloBeans() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertNotEquals(null, GestorBD.getGestorBD().getCapituloBeans(idCap));
	}

	@Test
	public void testGetObrasBeans() {
		assertNotEquals(0, GestorBD.getGestorBD().getObrasBeans(10, 0, 0).size());
		assertTrue(GestorBD.getGestorBD().getObrasBeans(10, 0, 0).size() <= 10);
	}

	@Test
	public void testGetMaxObrasN() {
		assertNotEquals(0, GestorBD.getGestorBD().getMaxObrasN(1));
	}

	@Test
	public void testGetAutorBeans() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		autor = GestorBD.getGestorBD().getAutorBeans(idObra);

		assertArrayEquals(String.valueOf(aux).split("\n"), autor.getAbout());
		assertEquals(String.valueOf(aux), autor.getEmail());
		assertEquals(String.valueOf(aux), autor.getImagen());
		assertEquals(String.valueOf(aux), autor.getNombre());
		assertEquals(String.valueOf(aux), autor.getPais());
	}

	@Test
	public void testUpdateObra() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);

		assertEquals(idUs, obra.getAutor());
		assertEquals(String.valueOf(aux), obra.getPortada());
		assertEquals(String.valueOf(aux), obra.getResumen());
		assertEquals(String.valueOf(aux), obra.getTitulo());

		aux = System.currentTimeMillis();

		GestorBD.getGestorBD().updateObra(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));
		obra = GestorBD.getGestorBD().getObraBeans(idObra);

		assertEquals(idUs, obra.getAutor());
		assertEquals(String.valueOf(aux), obra.getPortada());
		assertEquals(String.valueOf(aux), obra.getResumen());
		assertEquals(String.valueOf(aux), obra.getTitulo());
	}

	@Test
	public void testUpdateChapter() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		cap = GestorBD.getGestorBD().getCapitulosBeans(idCap);

		assertEquals(String.valueOf(aux), cap.getComentarios_autor());
		assertEquals(String.valueOf(aux) ,cap.getImagen());
		assertEquals(String.valueOf(aux), cap.getNombre());
		assertArrayEquals(String.valueOf(aux).split("\n"), cap.getText());
		assertEquals(idObra, cap.getObra());

		GestorBD.getGestorBD().updateChapter(idCap,	 String.valueOf(aux), String.valueOf(aux),  String.valueOf(aux),  String.valueOf(aux));
	}

	@Test
	public void testGetAutoresBeans() {
		assertNotEquals(0, GestorBD.getGestorBD().getAutoresBeans().size());
	}

	@Test
	public void testGetHasMapAutores() {
		assertNotEquals(0, GestorBD.getGestorBD().getHasMapAutores().size());
	}

	@Test
	public void testCheckUser() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		assertEquals(idUs,GestorBD.getGestorBD().checkUser(String.valueOf(aux), String.valueOf(aux)));
		assertEquals(0,GestorBD.getGestorBD().checkUser(String.valueOf(aux), ""));
	}

	@Test
	public void testAddComment() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertTrue(GestorBD.getGestorBD().getCapituloBeans(idObra).size() == 0);

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertTrue(GestorBD.getGestorBD().addComment(idUs, idCap, idObra, String.valueOf(aux)));
	}

	@Test
	public void testGetComentariosBeans() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertTrue(GestorBD.getGestorBD().getCapituloBeans(idObra).size() == 0);

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertTrue(GestorBD.getGestorBD().addComment(idUs, idCap, idObra, String.valueOf(aux)));
		assertEquals(1, GestorBD.getGestorBD().getComentariosBeans(idObra, idCap).size());
	}

	@Test
	public void testComprobarNombre() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		assertFalse(GestorBD.getGestorBD().comprobarNombre(String.valueOf(aux)));
		assertTrue(GestorBD.getGestorBD().comprobarNombre(String.valueOf(System.currentTimeMillis())));
	}

	@Test
	public void testGetObraBeans() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);

		assertEquals(idUs, obra.getAutor());
		assertEquals(String.valueOf(aux), obra.getPortada());
		assertEquals(String.valueOf(aux), obra.getResumen());
		assertEquals(String.valueOf(aux), obra.getTitulo());

	}

	@Test
	public void testGetCapitulosBeans() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertTrue(GestorBD.getGestorBD().getCapituloBeans(idObra).size() == 0);

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertTrue(GestorBD.getGestorBD().getCapituloBeans(idObra).size() > 0);
	}

	@Test
	public void testGetAutorBeansById() {
		long aux = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		Date nac = new Date(cal.YEAR, cal.MONTH, cal.DAY_OF_MONTH);
		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),nac, String.valueOf(aux), String.valueOf(aux));

		autor = GestorBD.getGestorBD().getAutorBeansById(idUs);

		assertEquals(nac, autor.getNacimiento());
		assertArrayEquals(String.valueOf(aux).split("\n"), autor.getAbout());
		assertEquals(String.valueOf(aux), autor.getEmail());
		assertEquals(String.valueOf(aux), autor.getImagen());
		assertEquals(String.valueOf(aux), autor.getNombre());
		assertEquals(String.valueOf(aux), autor.getPais());

	}

	@Test
	public void testUpdateAutor() {
		long aux = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		Date nac = new Date(cal.YEAR, cal.MONTH, cal.DAY_OF_MONTH);
		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),nac, String.valueOf(aux), String.valueOf(aux));

		autor = GestorBD.getGestorBD().getAutorBeansById(idUs);

		assertEquals(nac, autor.getNacimiento());
		assertArrayEquals(String.valueOf(aux).split("\n"), autor.getAbout());
		assertEquals(String.valueOf(aux), autor.getEmail());
		assertEquals(String.valueOf(aux), autor.getImagen());
		assertEquals(String.valueOf(aux), autor.getNombre());
		assertEquals(String.valueOf(aux), autor.getPais());

		aux = System.currentTimeMillis();
		GestorBD.getGestorBD().updateAutor(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		autor = GestorBD.getGestorBD().getAutorBeansById(idUs);

		assertEquals(nac, autor.getNacimiento());
		assertArrayEquals(String.valueOf(aux).split("\n"), autor.getAbout());
		assertEquals(String.valueOf(aux), autor.getEmail());
		assertEquals(String.valueOf(aux), autor.getImagen());		
	}

	@Test
	public void testChangePassword() {
		long aux = System.currentTimeMillis();
		Calendar cal = Calendar.getInstance();
		Date nac = new Date(cal.YEAR, cal.MONTH, cal.DAY_OF_MONTH);
		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),nac, String.valueOf(aux), String.valueOf(aux));

		autor = GestorBD.getGestorBD().getAutorBeansById(idUs);

		assertEquals(idUs,GestorBD.getGestorBD().checkUser(autor.getNombre(), String.valueOf(aux)));
		GestorBD.getGestorBD().changePassword(idUs, String.valueOf(aux), "a");
		assertEquals(idUs,GestorBD.getGestorBD().checkUser(autor.getNombre(), "a"));


	}



	@Test
	public void testDeleteObra() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		assertNotEquals(null, GestorBD.getGestorBD().getObraBeans(idObra));
		assertTrue(GestorBD.getGestorBD().deleteObra(idObra));
		assertEquals(null, GestorBD.getGestorBD().getObraBeans(idObra));

	}

	@Test
	public void testDeleteAutor() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		assertNotEquals(null, GestorBD.getGestorBD().getAutorBeansById(idUs));
		assertTrue(GestorBD.getGestorBD().deleteAutor(idUs));
		assertEquals(null, GestorBD.getGestorBD().getAutorBeansById(idUs));
	}

	/*@Test
	public void testChangePasswordAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckAdmin() {
		assertTrue(GestorBD.getGestorBD().checkAdmin(password));
	}*/

}
