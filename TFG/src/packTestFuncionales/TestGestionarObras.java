package packTestFuncionales;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import packBD.GestorBD;

public class TestGestionarObras {

	private static WebDriver driver;
	private static long aux;
	private static int idU = 0, idO = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/TFG/administrador.jsp");
		driver.findElement(By.id("contrasena")).sendKeys("alberto");
		driver.findElement(By.id("submit")).click();	

		aux = System.currentTimeMillis();

		idU = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));

		idO = GestorBD.getGestorBD().insertarObra(idU, String.valueOf(aux), String.valueOf(aux), null);


	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GestorBD.getGestorBD().deleteAutor(idU);
		driver.get("http://localhost:8080/TFG/LogOutAdmin");
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG/EliminarObra");
	}


	@Test
	public void testPulsarBoton() {
		long time;
		WebElement row = driver.findElement(By.id(String.valueOf(idO)));
		WebElement habilitar = row.findElement(By.className("habilitar"));
		WebElement deshabilitar = row.findElement(By.className("deshabilitar"));

		deshabilitar.click();

		time = System.currentTimeMillis() + 2000;

		while (System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("confirmDeshabilitar")).isDisplayed());

		driver.findElement(By.id("deshabilitar")).click();

		time = System.currentTimeMillis() + 6000;

		while (System.currentTimeMillis() < time){}

		assertFalse(driver.findElement(By.id("confirmDeshabilitar")).isDisplayed());

		time = System.currentTimeMillis() + 6000;

		while (System.currentTimeMillis() < time){}

		row = driver.findElement(By.id(String.valueOf(idO)));
		habilitar = row.findElement(By.className("habilitar"));
		deshabilitar = row.findElement(By.className("deshabilitar"));

		assertTrue(habilitar.getAttribute("class").contains("active"));
		assertFalse(deshabilitar.getAttribute("class").contains("active"));

		habilitar.click();

		time = System.currentTimeMillis() + 2000;

		while (System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("confirmHabilitar")).isDisplayed());

		driver.findElement(By.id("habilitar")).click();

		time = System.currentTimeMillis() + 2000;

		while (System.currentTimeMillis() < time){}

		assertFalse(driver.findElement(By.id("confirmHabilitar")).isDisplayed());

		time = System.currentTimeMillis() + 6000;

		while (System.currentTimeMillis() < time){}

		row = driver.findElement(By.id(String.valueOf(idO)));
		habilitar = row.findElement(By.className("habilitar"));
		deshabilitar = row.findElement(By.className("deshabilitar"));

		assertFalse(habilitar.getAttribute("class").contains("active"));
		assertTrue(deshabilitar.getAttribute("class").contains("active"));


	}

}
