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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import packBD.GestorBD;

public class TestIdentificarse {

	private static WebDriver driver;
	private static int idU = 0;
	private static long aux;

	private static String toSha512(String contrasena){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		String clave = contrasena;
		md.update(clave.getBytes());
		String output = "";
		byte[] mb = md.digest();
		for (int i = 0; i < mb.length; i++) {
			byte temp = mb[i];
			String s = Integer.toHexString(new Byte(temp));
			while (s.length() < 2) {
				s = "0" + s;
			}
			s = s.substring(s.length() - 2);
			output += s;
		}
		return output;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {



		driver = new FirefoxDriver();
		aux = System.currentTimeMillis();

		idU = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), toSha512(String.valueOf(aux)), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));

	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GestorBD.getGestorBD().deleteAutor(idU);
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG/login.jsp");
	}

	@After
	public void tearDown() throws Exception {
		GestorBD.getGestorBD().modificarVisibilidadAutor(idU, 1);
		driver.get("http://localhost:8080/TFG/LogOut");
	}

	@Test
	public void testIdentificacionCorrecta() {
		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys(String.valueOf(aux));

		WebElement contra = driver.findElement(By.id("contrasena"));
		contra.sendKeys(String.valueOf(aux));



		driver.findElement(By.id("submit")).click();
		
		assertEquals("Inicio", driver.getTitle());
	}
	
	@Test
	public void testIdentificacionErronea() {
		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys(String.valueOf(aux));

		WebElement contra = driver.findElement(By.id("contrasena"));
		contra.sendKeys("contraseñaerronea");



		driver.findElement(By.id("submit")).click();
		
		assertEquals("Error", driver.getTitle());
	}
	
	@Test
	public void testIdentificacionDeshabilitado() {
		
		GestorBD.getGestorBD().modificarVisibilidadAutor(idU, 0);
		
		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys(String.valueOf(aux));

		WebElement contra = driver.findElement(By.id("contrasena"));
		contra.sendKeys(String.valueOf(aux));



		driver.findElement(By.id("submit")).click();
		
		assertEquals("Error, usuario deshabilitado", driver.getTitle());
	}
	
	@Test
	public void testHeOlvidadoLaContrasena(){
		driver.findElement(By.id("contrasenaOlvidada")).click();
		
		assertEquals("Recuperar Contraseña", driver.getTitle());
	}
	

}
