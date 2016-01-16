package packTestBeans;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packBeans.Comentario;

public class TestComentario {
	protected Comentario com;
	protected Timestamp time;

	@Before
	public void setUp() throws Exception {
		com = new Comentario();
		time = new Timestamp(System.currentTimeMillis());
		com.setAutor(0);
		com.setCapitulo(0);
		com.setComentario(0);
		com.setFecha_comentario(time);
		com.setObra(0);
		com.setTexto("texto");
	}

	@After
	public void tearDown() throws Exception {
		com = null;
		time = null;
	}

	@Test
	public void testGetAutor() {
		assertEquals(0, com.getAutor());
	}

	@Test
	public void testSetAutor() {
		com.setAutor(1);
		assertEquals(1, com.getAutor());
	}

	@Test
	public void testGetObra() {
		assertEquals(0, com.getObra());
	}

	@Test
	public void testSetObra() {
		com.setObra(1);
		assertEquals(1, com.getObra());
	}

	@Test
	public void testGetCapitulo() {
		assertEquals(0, com.getCapitulo());
	}

	@Test
	public void testSetCapitulo() {
		com.setCapitulo(1);
		assertEquals(1, com.getCapitulo());
	}

	@Test
	public void testGetComentario() {
		assertEquals(0, com.getComentario());
	}

	@Test
	public void testSetComentario() {
		com.setComentario(1);
		assertEquals(1, com.getComentario());
	}

	@Test
	public void testGetTexto() {
		assertEquals("texto", com.getTexto());
	}

	@Test
	public void testSetTexto() {
		com.setTexto("1");
		assertEquals("1", com.getTexto());
	}

	@Test
	public void testGetFecha_comentario() {
		assertEquals(time, com.getFecha_comentario());
	}

	@Test
	public void testSetFecha_comentario() {
		Timestamp aux = new Timestamp(System.currentTimeMillis());
		com.setFecha_comentario(aux);	
		assertEquals(time, com.getFecha_comentario());
	}

}
