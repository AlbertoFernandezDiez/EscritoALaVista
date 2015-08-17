package packTestFuncionales;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestPaginaInicio {

	private static WebDriver driver;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	}
	
	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG");
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
	//	WebDriver driver = new FirefoxDriver();
		String tituloLink, tituloPagina;

	//	driver.get("http://localhost:8080/TFG");
		WebElement button = driver.findElement(By.id("autor"));
		tituloLink = button.getText();
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
	}

}
