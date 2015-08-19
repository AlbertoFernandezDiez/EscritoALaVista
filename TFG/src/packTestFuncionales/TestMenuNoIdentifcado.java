package packTestFuncionales;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import packBD.GestorBD;

public class TestMenuNoIdentifcado {

	private static WebDriver driver;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	}
	
	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}
	
	@Test
	public void testInicio() {
		String tituloLink, tituloPagina;

		
		WebElement button = driver.findElement(By.id("inicio"));
		tituloLink = "Inicio";
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
	}
	
	@Test
	public void testIdentificarse() {
		String tituloLink, tituloPagina;

		
		WebElement button = driver.findElement(By.id("logIn"));
		tituloLink = "LogIn";
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
	}
	
	@Test
	public void testRegistrarse() {
		String tituloLink, tituloPagina;

		
		WebElement button = driver.findElement(By.id("registrarse"));
		tituloLink = "Registrarse";
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
		}
	
	@Test
	public void testAdministrador() {
		String tituloLink, tituloPagina;

		
		WebElement button = driver.findElement(By.id("administrador"));
		tituloLink = "LogIn Administrador";
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
	}

}
