package packTestBD;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
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
		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),nac, String.valueOf(aux),null);

		autor = GestorBD.getGestorBD().getAutorBeansById(idUs);

		assertEquals(nac, autor.getNacimiento());
		assertArrayEquals(String.valueOf(aux).split("\n"), autor.getAbout());
		assertEquals(String.valueOf(aux), autor.getEmail());
		assertEquals(null, autor.getImagen());
		assertEquals(String.valueOf(aux), autor.getNombre());
		assertEquals(String.valueOf(aux), autor.getPais());

	}

	@Test
	public void testInsertarObra() {

		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), null); //String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);

		assertEquals(idUs, obra.getAutor());
		assertEquals(/*String.valueOf(aux)*/null, obra.getPortada());
		assertEquals(String.valueOf(aux), obra.getResumen());
		assertEquals(String.valueOf(aux), obra.getTitulo());
	}

	@Test
	public void testInsertarCapitulo() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),null);// String.valueOf(aux));

		cap = GestorBD.getGestorBD().getCapitulosBeans(idCap);

		assertEquals(String.valueOf(aux), cap.getComentarios_autor());
		assertEquals(/*String.valueOf(aux)*/ null ,cap.getImagen());
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

		assertNotEquals(0, GestorBD.getGestorBD().getCapituloBeans(idObra).size());
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

	@Test
	public void testChangePasswordAdmin() {
		assertTrue(GestorBD.getGestorBD().checkAdmin("adminprueba", toSha512("alberto")));
		
		assertTrue(GestorBD.getGestorBD().changePasswordAdmin("adminprueba", toSha512("alberto"), toSha512("albertos")));

		assertTrue(GestorBD.getGestorBD().checkAdmin("adminprueba", toSha512("albertos")));
		
		assertTrue(GestorBD.getGestorBD().changePasswordAdmin("adminprueba", toSha512("albertos"), toSha512("alberto")));

		
		assertTrue(GestorBD.getGestorBD().checkAdmin("adminprueba", toSha512("alberto")));

	}

	@Test
	public void testCheckAdmin() {
		assertTrue(GestorBD.getGestorBD().checkAdmin("adminprueba", toSha512("alberto")));
	}


	@Test
	public void testComprobarTitulo() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);

		assertEquals(idUs, obra.getAutor());
		assertEquals(String.valueOf(aux), obra.getPortada());
		assertEquals(String.valueOf(aux), obra.getResumen());
		assertEquals(String.valueOf(aux), obra.getTitulo());

		assertFalse(GestorBD.getGestorBD().comprobarNombre(obra.getTitulo()));
		assertTrue(GestorBD.getGestorBD().comprobarNombre(String.valueOf(System.currentTimeMillis())));
	}

	@Test
	public void testGetObraAutorChecked() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);
		//fail("Not yet implemented");
		assertFalse(GestorBD.getGestorBD().getObraAutorChecked(idObra, idUs));
		GestorBD.getGestorBD().AddSeguimiento(idUs, idObra);
		assertTrue(GestorBD.getGestorBD().getObraAutorChecked(idObra, idUs));
	}

	@Test
	public void testAddSeguimiento() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);
		//fail("Not yet implemented");
		assertFalse(GestorBD.getGestorBD().getObraAutorChecked(idObra, idUs));
		GestorBD.getGestorBD().AddSeguimiento(idUs, idObra);
		assertTrue(GestorBD.getGestorBD().getObraAutorChecked(idObra, idUs));
	}

	@Test
	public void testQuitSeguimiento() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);
		//fail("Not yet implemented");
		assertFalse(GestorBD.getGestorBD().getObraAutorChecked(idObra, idUs));
		GestorBD.getGestorBD().AddSeguimiento(idUs, idObra);
		assertTrue(GestorBD.getGestorBD().getObraAutorChecked(idObra, idUs));
		GestorBD.getGestorBD().QuitSeguimiento(idUs, idObra);
		assertFalse(GestorBD.getGestorBD().getObraAutorChecked(idObra, idUs));
	}

	@Test
	public void testGetSuscriptores() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);
		assertFalse(GestorBD.getGestorBD().getObraAutorChecked(idObra, idUs));
		GestorBD.getGestorBD().AddSeguimiento(idUs, idObra);
		ArrayList<Autor> lista = GestorBD.getGestorBD().getSuscriptores(idObra);
		assertTrue(lista.size() >= 1);
		assertEquals(lista.get(0).getId(), idUs);
	}

	@Test
	public void testModificarVisibilidadAutor() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		autor = GestorBD.getGestorBD().getAutorBeansById(idUs);
		assertEquals(1,autor.getActive());
		GestorBD.getGestorBD().modificarVisibilidadAutor(idUs, 0);
		autor = GestorBD.getGestorBD().getAutorBeansById(idUs);
		assertEquals(0, autor.getActive());
	}

	@Test
	public void testModificarVisibilidadObra() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		obra = GestorBD.getGestorBD().getObraBeans(idObra);

		assertEquals(1, obra.getActive());

		GestorBD.getGestorBD().modificarVisibilidadObra(idObra, 0);

		obra = GestorBD.getGestorBD().getObraBeans(idObra);

		assertEquals(0, obra.getActive());
	}

	@Test
	public void testGetObrasBeansAll() {
		long aux = System.currentTimeMillis();

		int actual = GestorBD.getGestorBD().getObrasBeansAll().size();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		GestorBD.getGestorBD().modificarVisibilidadObra(idObra, 0);

		int deshabil = GestorBD.getGestorBD().getObrasBeansAll().size();

		assertTrue(actual < deshabil);

	}

	@Test
	public void testGetAutoresBeansDeshabilitados() {
		long aux = System.currentTimeMillis();

		int actual = GestorBD.getGestorBD().getAutoresBeansDeshabilitados().size();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));


		GestorBD.getGestorBD().modificarVisibilidadAutor(idUs, 0);

		int deshabil = GestorBD.getGestorBD().getAutoresBeansDeshabilitados().size();

		assertTrue(actual < deshabil);
	}

	@Test
	public void testGetObrasBeansDeshabilitadas() {
		long aux = System.currentTimeMillis();

		int actual = GestorBD.getGestorBD().getAutoresBeansDeshabilitados().size();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));


		ArrayList<Obra> lista = GestorBD.getGestorBD().getObrasBeansDeshabilitadas();

		assertEquals(actual, lista.size());

		GestorBD.getGestorBD().modificarVisibilidadObra(idObra, 0);

		lista = GestorBD.getGestorBD().getObrasBeansDeshabilitadas();

		assertEquals(++actual, lista.size());



	}

	@Test
	public void testDeleteCapitulo() {
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));

		idObra = GestorBD.getGestorBD().insertarObra(idUs, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		idCap = GestorBD.getGestorBD().insertarCapitulo(idObra, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux));

		cap =  GestorBD.getGestorBD().getCapitulosBeans(idCap);

		assertNotEquals(null, cap);
		cap = null;

		assertTrue(GestorBD.getGestorBD().deleteCapitulo(idCap));

		cap =  GestorBD.getGestorBD().getCapitulosBeans(idCap);

		assertEquals(null, cap);


	}
	
	@Test
	public void testGetAutorBeansByEmail(){
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));
		
		Autor autor = null;
		
		assertEquals(null, autor);
		
		autor = GestorBD.getGestorBD().getAutorBeansByEmail(String.valueOf(aux));
		
		assertNotEquals(null, autor);

		assertArrayEquals(String.valueOf(aux).split("\n"), autor.getAbout());
		assertEquals(String.valueOf(aux), autor.getEmail());
		assertEquals(String.valueOf(aux), autor.getImagen());
		assertEquals(String.valueOf(aux), autor.getNombre());
		assertEquals(String.valueOf(aux), autor.getPais());
	}
	
	@Test
	public void testRecuperarContrasena(){
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));
		
		assertEquals(idUs,GestorBD.getGestorBD().checkUser(String.valueOf(aux), String.valueOf(aux)));
		
		String contra = "abcd";
		assertTrue(GestorBD.getGestorBD().recuperarContrasena(idUs, contra));
		
		assertEquals(0,GestorBD.getGestorBD().checkUser(String.valueOf(aux), String.valueOf(aux)));
		assertEquals(idUs,GestorBD.getGestorBD().checkUser(String.valueOf(aux), toSha512(contra)));
		
	}
	
	private String toSha512(String contrasena){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String clave = contrasena;
		md.update(clave.getBytes());
		String output = "";
		byte[] mb = md.digest();
		for (int i = 0; i < mb.length; i++) {
			byte temp = mb[i];
			String s = Integer.toHexString(new Byte(temp));
			while (s.length() < 2) {
				s = "0" + s;
			}
			s = s.substring(s.length() - 2);
			output += s;
		}
		return output;
	}
	
	@Test
	public void testComprobarEmail(){
		long aux = System.currentTimeMillis();

		assertTrue(GestorBD.getGestorBD().comprobarEmail(String.valueOf(aux)));
				
		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));
		
		assertFalse(GestorBD.getGestorBD().comprobarEmail(String.valueOf(aux)));
	
	}
	
	@Test
	public void testCheckAutorDeshabilitado(){
		long aux = System.currentTimeMillis();

		idUs = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux), String.valueOf(aux));
		
		assertFalse(GestorBD.getGestorBD().checkAutorDeshabilitado(idUs));
		
		GestorBD.getGestorBD().modificarVisibilidadAutor(idUs, 0);
		
		assertTrue(GestorBD.getGestorBD().checkAutorDeshabilitado(idUs));
		
		
	}
}
