package packTestFuncionales;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import packBD.GestorBD;

public class TestIdentificarseAdministrador {

	private static WebDriver driver;



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	
	}
	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG/administrador.jsp");
	}

	@After
	public void tearDown() throws Exception {
driver.get("http://localhost:8080/TFG/LogOutAdmin");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}
	
	@Test
	public void testIdentificacionCorrecta() {
		driver.findElement(By.id("contrasena")).sendKeys("alberto");
		driver.findElement(By.id("submit")).click();
		
		long time = System.currentTimeMillis() + 2000;
		
		while (System.currentTimeMillis() < time){}
		
		assertEquals("Administración", driver.getTitle());
	}

	@Test
	public void testIdentificacionIncorrecta() {
		driver.findElement(By.id("contrasena")).sendKeys("alb");
		driver.findElement(By.id("submit")).click();
		
		long time = System.currentTimeMillis() + 2000;
		
		while (System.currentTimeMillis() < time){}
		
		assertEquals("Error", driver.getTitle());
	}
}
