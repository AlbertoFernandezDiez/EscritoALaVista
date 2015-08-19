package packTestFuncionales;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestMenuAdministrador {

	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG/administrador.jsp");
		driver.findElement(By.id("contrasena")).sendKeys("alberto");
		driver.findElement(By.id("submit")).click();	
	}

	@After
	public void tearDown() throws Exception {
		driver.get("http://localhost:8080/TFG/LogOutAdmin");
	}

	@Test
	public void testInicio() {
		driver.findElement(By.id("inicio")).click();

		assertEquals("Administración", driver.getTitle());
	}

	@Test
	public void testLogout(){
		driver.findElement(By.id("logout")).click();

		assertEquals("Inicio", driver.getTitle());
	}

}
