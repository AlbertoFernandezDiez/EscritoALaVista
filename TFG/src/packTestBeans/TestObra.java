package packTestBeans;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sun.org.glassfish.external.statistics.TimeStatistic;

import packBeans.Obra;

public class TestObra {
	protected Obra obra;
	protected Date in;
	protected Timestamp mod;
	protected String resumen;

	@Before
	public void setUp() throws Exception {
		mod = new Timestamp(System.currentTimeMillis());
		in = new Date(System.currentTimeMillis());
		resumen = "hola\nque\ntal";
		obra = new Obra();
		obra.setAutor(1);
		obra.setFecha_in(in);
		obra.setFecha_mod(mod);
		obra.setId(1);
		obra.setPortada("portada");
		obra.setResumen(resumen);
		obra.setTitulo("titulo");
		obra.setActive(0);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetId() {
		assertEquals(1, obra.getId());
	}

	@Test
	public void testSetId() {
		obra.setId(0);
		assertEquals(0, obra.getId());
	}

	@Test
	public void testGetAutor() {
		assertEquals(1, obra.getAutor());
	}

	@Test
	public void testSetAutor() {
		obra.setAutor(0);
		assertEquals(0, obra.getAutor());
	}

	@Test
	public void testGetTitulo() {
		assertEquals("titulo", obra.getTitulo());
	}

	@Test
	public void testSetTitulo() {
		obra.setTitulo("tit");
		assertEquals("tit", obra.getTitulo());
	}

	@Test
	public void testGetResumen() {
		assertEquals(resumen, obra.getResumen());
	}

	@Test
	public void testSetResumen() {
		String aux = "abcd";
		obra.setResumen(aux);
		assertEquals(aux, obra.getResumen());
	}

	@Test
	public void testGetPortada() {
		assertEquals("portada", obra.getPortada());
	}

	@Test
	public void testSetPortada() {
		obra.setPortada("por");
		assertEquals("por", obra.getPortada());

	}

	@Test
	public void testGetFecha_in() {
		assertEquals(in, obra.getFecha_in());
	}

	@Test
	public void testSetFecha_in() {
		Date aux = new Date(0);
		obra.setFecha_in(aux);
		assertEquals(aux, obra.getFecha_in());
	}

	@Test
	public void testGetFecha_mod() {
		assertEquals(mod, obra.getFecha_mod());
	}

	@Test
	public void testSetFecha_mod() {
		Timestamp aux = new Timestamp(System.currentTimeMillis());
		obra.setFecha_mod(aux);
		assertEquals(mod, obra.getFecha_mod());
	}

	@Test
	public void testGetActive(){
		assertEquals(0, obra.getActive());
	}

	@Test
	public void testSetActive(){
		assertEquals(0, obra.getActive());
obra.setActive(1);
assertEquals(1, obra.getActive());
	}
}

