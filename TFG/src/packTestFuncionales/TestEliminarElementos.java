package packTestFuncionales;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import packBD.GestorBD;

public class TestEliminarElementos {

	private static WebDriver driver;
	private static long aux;
	private static int idU = 0, idO = 0;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/TFG/administrador.jsp");
		driver.findElement(By.id("contrasena")).sendKeys("alberto");
		driver.findElement(By.id("submit")).click();	

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GestorBD.getGestorBD().deleteAutor(idU);
		driver.get("http://localhost:8080/TFG/LogOutAdmin");
		driver.close();

	}



	@Before
	public void tearDown() throws Exception {
		GestorBD.getGestorBD().deleteAutor(idU);
	}



	@Test
	public void testDesplegarAutores() {
		driver.get("http://localhost:8080/TFG/EED");

		assertFalse(driver.findElement(By.id("autores")).isDisplayed());

		driver.findElement(By.id("botonAutor")).click();

		long time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("autores")).isDisplayed());
	}

	@Test
	public void testDesplegarObras(){
		driver.get("http://localhost:8080/TFG/EED");

		assertFalse(driver.findElement(By.id("obras")).isDisplayed());

		driver.findElement(By.id("botonObra")).click();

		long time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("obras")).isDisplayed());


	}

	@Test
	public void testEliminarObra(){

		aux = System.currentTimeMillis();

		idU = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));

		idO = GestorBD.getGestorBD().insertarObra(idU, String.valueOf(aux), String.valueOf(aux), null);

		GestorBD.getGestorBD().modificarVisibilidadObra(idO, 0);

		long time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		driver.get("http://localhost:8080/TFG/EED");


		assertFalse(driver.findElement(By.id("obras")).isDisplayed());

		driver.findElement(By.id("botonObra")).click();

		time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("obras")).isDisplayed());

		WebElement table = driver.findElement(By.id("obras"));

		WebElement row = table.findElement(By.id(String.valueOf(idO)));

		row.findElement(By.tagName("button")).click();

		time = System.currentTimeMillis() + 20000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("confirmObra")).isDisplayed());

		driver.findElement(By.id("deleteObra")).click();

		time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		assertFalse(driver.findElement(By.id("confirm")).isDisplayed());

		time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}


		table = driver.findElement(By.id("obras"));

		try{
			row = table.findElement(By.id(String.valueOf(idO)));
		}catch(NoSuchElementException e){assertTrue(true);}

	}

	@Test
	public void testEliminarUsuario(){

		aux = System.currentTimeMillis();

		idU = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));

		GestorBD.getGestorBD().modificarVisibilidadAutor(idU, 0);

		long time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		driver.get("http://localhost:8080/TFG/EED");


		assertFalse(driver.findElement(By.id("autores")).isDisplayed());

		driver.findElement(By.id("botonAutor")).click();

		time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("autores")).isDisplayed());

		WebElement table = driver.findElement(By.id("autores"));

		WebElement row = table.findElement(By.id(String.valueOf(idU)));

		row.findElement(By.tagName("button")).click();

		time = System.currentTimeMillis() + 20000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("confirm")).isDisplayed());

		driver.findElement(By.id("delete")).click();

		time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		assertFalse(driver.findElement(By.id("confirm")).isDisplayed());

		time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}


		table = driver.findElement(By.id("obras"));

		try{
			row = table.findElement(By.id(String.valueOf(idO)));
		}catch(NoSuchElementException e){assertTrue(true);}

	}
}
