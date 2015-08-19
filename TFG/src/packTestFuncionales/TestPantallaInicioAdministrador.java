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

public class TestPantallaInicioAdministrador {

	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/TFG/administrador.jsp");
		driver.findElement(By.id("contrasena")).sendKeys("alberto");
		driver.findElement(By.id("submit")).click();	
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.get("http://localhost:8080/TFG/LogOutAdmin");
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG/LogInAdmin");
	}


	@Test
	public void testCambioContrasena() {
		driver.findElement(By.id("cambiocontrasena")).click();

		assertEquals("Cambiar Contraseña Administrador", driver.getTitle());
	}

	@Test
	public void testGestionObra(){
		driver.findElement(By.id("deshabilitarobra")).click();

		assertEquals("Gestionar Obras", driver.getTitle());
	}

	@Test
	public void testGestionUsuario(){
		driver.findElement(By.id("deshabilitarusuario")).click();

		assertEquals("Gestionar Autores", driver.getTitle());
	}
	
	@Test
	public void testEliminarDeshabilitados(){
		driver.findElement(By.id("eliminardeshabilitado")).click();
		
		assertEquals("Eliminar Elementos Deshabilitados", driver.getTitle());


	}
	

	

	

}
