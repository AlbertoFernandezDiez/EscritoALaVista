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

public class TestCambioContrasena {

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
	public void testContrasenaIncorrecta() {
		driver.get("http://localhost:8080/TFG/CCA");
		
		driver.findElement(By.id("contraV")).sendKeys("albertoa");
		
		driver.findElement(By.id("contraN")).sendKeys("alberto");
			
		assertFalse(driver.findElement(By.id("bad")).isDisplayed());
		
		driver.findElement(By.id("cambiar")).click();
		
		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertTrue(driver.findElement(By.id("bad")).isDisplayed());

	}
	
	@Test
	public void testContrasenaCorrecta() {
		driver.get("http://localhost:8080/TFG/CCA");
		
		driver.findElement(By.id("contraV")).sendKeys("alberto");
		
		driver.findElement(By.id("contraN")).sendKeys("alberto");
			
		assertFalse(driver.findElement(By.id("ok")).isDisplayed());
		
		driver.findElement(By.id("cambiar")).click();
		
		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertTrue(driver.findElement(By.id("ok")).isDisplayed());

	}
	
	@Test
	public void testContrasenaNoValida() {
		driver.get("http://localhost:8080/TFG/CCA");
		
		driver.findElement(By.id("contraV")).sendKeys("alberto");
		
		driver.findElement(By.id("contraN")).sendKeys("alb");
			
		assertFalse(driver.findElement(By.id("fail")).isDisplayed());
		
		driver.findElement(By.id("cambiar")).click();
		
		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertTrue(driver.findElement(By.id("fail")).isDisplayed());

	}

}
