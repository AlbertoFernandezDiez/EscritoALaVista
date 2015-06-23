package packTestBeans;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packBeans.Capitulo;

public class TestCapitulo {
protected Capitulo cap;
protected Date fecha;
protected String texto = "hola\nque\tal",comentario = "comentarios_autor", imagen = "imagen" ;

	@Before
	public void setUp() throws Exception {
		fecha = new Date(System.currentTimeMillis());
	cap = new Capitulo();
	cap.setComentarios_autor(comentario);
	cap.setFecha_comentario(fecha);
	cap.setId(1);
	cap.setImagen(imagen);
	cap.setNombre("nombre");
	cap.setObra(2);
	cap.setText(texto);
	}

	@After
	public void tearDown() throws Exception {
		cap = null;
		fecha = null;
	}

	@Test
	public void testGetId() {
		assertEquals(1, cap.getId());
	}

	@Test
	public void testSetId() {
		cap.setId(0);
		assertEquals(0, cap.getId());
	}

	@Test
	public void testGetObra() {
		assertEquals(2, cap.getObra());
	}

	@Test
	public void testSetObra() {
		cap.setObra(0);
		assertEquals(0, cap.getObra());
	}

	@Test
	public void testGetNombre() {
		assertEquals("nombre", cap.getNombre());
	}

	@Test
	public void testSetNombre() {
		cap.setNombre("n");
		assertEquals("n", cap.getNombre());
	}

	@Test
	public void testGetText() {
		assertArrayEquals(texto.split("\n"), cap.getText());
	}

	@Test
	public void testSetText() {
	String aux = "aeiou";
	cap.setText(aux);
		assertArrayEquals(aux.split("\n"), cap.getText());
	}

	@Test
	public void testGetComentarios_autor() {
		assertEquals(comentario, cap.getComentarios_autor());
	}

	@Test
	public void testSetComentarios_autor() {
		String aux = "abcd";
		cap.setComentarios_autor(aux);
		assertEquals(aux, cap.getComentarios_autor());
	}

	@Test
	public void testGetImagen() {
		assertEquals(imagen, cap.getImagen());
	}

	@Test
	public void testSetImagen() {
		String aux = "img";
		cap.setImagen(aux);
		assertEquals(aux, cap.getImagen());
	}

	@Test
	public void testGetFecha_comentario() {
	assertEquals(fecha, cap.getFecha_comentario());
	}

	@Test
	public void testSetFecha_comentario() {
		Date aux = new Date(System.currentTimeMillis());
		cap.setFecha_comentario(aux);
		assertEquals(aux, cap.getFecha_comentario());
	}

}
