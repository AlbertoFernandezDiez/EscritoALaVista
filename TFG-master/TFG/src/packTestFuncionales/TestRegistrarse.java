package packTestFuncionales;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import packBD.GestorBD;
import packBeans.Autor;

public class TestRegistrarse {


	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}
	
	@Before
	public void setUp(){
		driver.get("http://localhost:8080/TFG/registrarse.jsp");
	}

	@Test
	public void testRegistrarseCorrectamente() {
		String aux = String.valueOf(System.currentTimeMillis());

		driver.findElement(By.id("usuario")).sendKeys(aux);

		driver.findElement(By.id("email")).sendKeys(aux + "@" + aux);

		driver.findElement(By.id("contrasena1")).sendKeys(aux);

		driver.findElement(By.id("contrasena2")).sendKeys(aux);

		driver.findElement(By.id("pais")).sendKeys(aux);

		driver.findElement(By.id("nacimiento")).sendKeys("2012-12-12");

		driver.findElement(By.id("about")).sendKeys(aux);

		long time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		driver.findElement(By.id("submit")).click();

		time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		assertEquals("http://localhost:8080/TFG/Index", driver.getCurrentUrl());

		Autor autor = null;

		autor = GestorBD.getGestorBD().getAutorBeansByEmail(aux + "@" + aux);

		assertNotEquals(null, autor);

		GestorBD.getGestorBD().deleteAutor(autor.getId());

	}

	
	
	@Test
	public void testRegistrarseCamposErroneos() {
		String aux = String.valueOf(System.currentTimeMillis());

		driver.findElement(By.id("usuario")).sendKeys(aux);

		driver.findElement(By.id("email")).sendKeys(aux);

		driver.findElement(By.id("contrasena1")).sendKeys(aux);

		driver.findElement(By.id("contrasena2")).sendKeys(aux);

		driver.findElement(By.id("pais")).sendKeys(aux);

		driver.findElement(By.id("nacimiento")).sendKeys("2012/12/12");

		driver.findElement(By.id("about")).sendKeys(aux);

		long time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		driver.findElement(By.id("submit")).click();

		time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		assertEquals("http://localhost:8080/TFG/registrarse.jsp", driver.getCurrentUrl());

		assertEquals("http://localhost:8080/TFG/registrarse.jsp", driver.getCurrentUrl());

		Autor autor = null;

		autor = GestorBD.getGestorBD().getAutorBeansByEmail(aux);

		assertEquals(null, autor);
	}
	
	@Test
	public void testRegistrarseSinTodosCampos() {
		String aux = String.valueOf(System.currentTimeMillis());

		driver.findElement(By.id("usuario")).sendKeys(aux);

		driver.findElement(By.id("email")).sendKeys(aux);

		driver.findElement(By.id("contrasena1")).sendKeys(aux);


		driver.findElement(By.id("pais")).sendKeys(aux);

		driver.findElement(By.id("nacimiento")).sendKeys("2012/12/12");


		long time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		driver.findElement(By.id("submit")).click();

		time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		assertEquals("http://localhost:8080/TFG/registrarse.jsp", driver.getCurrentUrl());


		Autor autor = null;

		autor = GestorBD.getGestorBD().getAutorBeansByEmail(aux);

		assertEquals(null, autor);
	}

	@Test
	public void testValidacionUsuarioViejo(){

		String aux = String.valueOf(System.currentTimeMillis());

		int idU = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(System.currentTimeMillis()), String.valueOf(aux),null);// String.valueOf(aux));

		long time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		
		assertFalse(driver.findElement(By.id("usado")).isDisplayed());

		driver.findElement(By.id("usuario")).sendKeys(aux);

		driver.findElement(By.id("email")).sendKeys(aux);
		
		time = System.currentTimeMillis() + 8000;

		while (System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("usado")).isDisplayed());


		GestorBD.getGestorBD().deleteAutor(idU);
	}
	
	@Test
	public void testValidacionUsuarioNuevo(){

		String aux = String.valueOf(System.currentTimeMillis());


		assertFalse(driver.findElement(By.id("usado")).isDisplayed());

		driver.findElement(By.id("usuario")).sendKeys(aux);

		long time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		assertFalse(driver.findElement(By.id("usado")).isDisplayed());

	}
	
	@Test
	public void testValidacionEmailViejo(){

		String aux = String.valueOf(System.currentTimeMillis());

		int idU = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(System.currentTimeMillis()), String.valueOf(aux),null);// String.valueOf(aux));

		long time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}
		
		assertFalse(driver.findElement(By.id("usadoEmail")).isDisplayed());

		driver.findElement(By.id("email")).sendKeys(aux);
	
		driver.findElement(By.id("usuario")).sendKeys(aux);
		
		time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("usadoEmail")).isDisplayed());


		GestorBD.getGestorBD().deleteAutor(idU);
	}
	
	@Test
	public void testValidacionEmailNuevo(){

		String aux = String.valueOf(System.currentTimeMillis());


		long time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}
		
		assertFalse(driver.findElement(By.id("usadoEmail")).isDisplayed());

		driver.findElement(By.id("email")).sendKeys(aux);
	
		driver.findElement(By.id("usuario")).sendKeys(aux);
		
		time = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < time){}

		assertFalse(driver.findElement(By.id("usadoEmail")).isDisplayed());


	}
}
