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

public class TestAutor {

	private static WebDriver driver;
	private static String autor;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/TFG");
		WebElement button = driver.findElement(By.id("autor"));
		autor = button.getAttribute("href");

	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}
	
	@Before
	public void setUp() throws Exception {
		driver.get(autor);
	}

	
	@Test
	public void testDireccionarObra() {
		
		String tituloLink, tituloPagina;

	
		WebElement button = driver.findElement(By.id("obra"));
		tituloLink = button.getText();
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);



	}

	@Test
	public void testDireccionarAutor() {
		String tituloLink, tituloPagina;

		WebElement button = driver.findElement(By.id("autor"));
		tituloLink = button.getText();
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
	}
}
