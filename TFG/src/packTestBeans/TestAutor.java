package packTestBeans;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packBeans.Autor;

public class TestAutor {

	protected Autor autor;
	protected Date nacimiento;
	protected String about = "hola\nque\ntal";
	@Before
	public void setUp() throws Exception {
		autor= new Autor();
		nacimiento = new Date(System.currentTimeMillis());
		autor.setAbout(about);
		autor.setEmail("email");
		autor.setId(1);
		autor.setImagen("imagen");
		autor.setNacimiento(nacimiento);
		autor.setNombre("nombre");
		autor.setPais("pais");
	}

	@After
	public void tearDown() throws Exception {
		autor = null;
		nacimiento = null;
	}

	@Test
	public void testGetEmail() {
		assertEquals("email", autor.getEmail());
	}
	
	@Test
	public void testSetEmail() {
		autor.setEmail("a");
		assertNotEquals("email", autor.getEmail());
		assertEquals("a", autor.getEmail());

	}
	
	@Test
	public void testGetId(){
		assertEquals(1, autor.getId());

	}
	
	@Test
	public void testSetId(){
		autor.setId(0);
		assertNotEquals(1, autor.getId());
		assertEquals(0, autor.getId());

	}
	
	@Test
	public void testGetPais(){
		assertEquals("pais", autor.getPais());
	}
	
	@Test
	public void testSetPais(){
		autor.setPais("a");
		assertNotEquals("pais", autor.getPais());
		assertEquals("a", autor.getPais());

	}
	
	public void testGetNombre(){
		assertEquals("nombre", autor.getNombre());

	}
	
	@Test
	public void testSetNombre(){
		autor.setNombre("q");
		assertNotEquals("nombre", autor.getNombre());
		assertEquals("q", autor.getNombre());
	}

	@Test
	public void testGetAbout(){
		assertArrayEquals(about.split("\n"), autor.getAbout());

	}
	
	@Test
	public void testSetAbout(){
		String aux = "hola\nadios";
		autor.setAbout(aux);
		assertArrayEquals(aux.split("\n"), autor.getAbout());
	}
	
	@Test
	public void testGetImagen(){
		assertEquals("imagen", autor.getImagen());

	}
	
	@Test
	public void testSetImagen(){
		autor.setImagen("img");
		assertEquals("img", autor.getImagen());
	}
	
	@Test
	public void testGetNacimiento(){
		assertEquals(nacimiento, autor.getNacimiento());

	}
	
	@Test
	public void testSetNacimiento(){
		Date aux = new Date(0);
		autor.setNacimiento(aux);
		assertNotEquals(nacimiento, autor.getNacimiento());
		assertEquals(aux, autor.getNacimiento());
	}
	
}
