package packTestBD;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Timestamp;

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
	//	fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertarObra() {
		fail("Not yet implemented");	}

	@Test
	public void testInsertarCapitulo() {
		fail("Not yet implemented");	}

	@Test
	public void testGetIndiceCapituloUno() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCapituloBeans() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateObra() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateChapter() {
		fail("Not yet implemented");
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
	}

	@Test
	public void testAddComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetComentariosBeans() {
		fail("Not yet implemented");
	}

	@Test
	public void testComprobarNombre() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetObraBeans() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCapitulosBeans() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAutorBeansById() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateAutor() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangePassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObra() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteAutor() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangePasswordAdmin() {
		fail("Not yet implemented");
	}

}
