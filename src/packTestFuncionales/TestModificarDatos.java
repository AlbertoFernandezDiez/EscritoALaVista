package packTestFuncionales;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import packBD.GestorBD;

public class TestModificarDatos {

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

		long time = System.currentTimeMillis() + 2000;

		while(System.currentTimeMillis() < time){}

		driver.get("http://localhost:8080/TFG/login.jsp");





		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys(String.valueOf(aux));

		WebElement contra = driver.findElement(By.id("contrasena"));
		contra.sendKeys(String.valueOf(aux));



		driver.findElement(By.id("submit")).click();

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GestorBD.getGestorBD().deleteAutor(idU);
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG/CambiarDatos");
	}

	@Test
	public void testCambioContrasenaViejaIncorrecta() {

		assertFalse(driver.findElement(By.id("bad")).isDisplayed());

		driver.findElement(By.id("contraV")).sendKeys(String.valueOf(aux + "5"));

		driver.findElement(By.id("contraN")).sendKeys(String.valueOf(aux));

		driver.findElement(By.id("cambiar")).click();

		long time = System.currentTimeMillis() + 5000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("bad")).isDisplayed());

	}

	@Test
	public void testCambioContrasenaNuevaNoValida() {

		assertFalse(driver.findElement(By.id("fail")).isDisplayed());

		driver.findElement(By.id("contraV")).sendKeys(String.valueOf(aux));

		driver.findElement(By.id("contraN")).sendKeys(String.valueOf("aux"));

		driver.findElement(By.id("cambiar")).click();

		long time = System.currentTimeMillis() + 5000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("fail")).isDisplayed());

	}

	@Test
	public void testCambioContrasena() {

		assertFalse(driver.findElement(By.id("ok")).isDisplayed());

		driver.findElement(By.id("contraV")).sendKeys(String.valueOf(aux));

		driver.findElement(By.id("contraN")).sendKeys(String.valueOf(aux));

		driver.findElement(By.id("cambiar")).click();

		long time = System.currentTimeMillis() + 5000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("ok")).isDisplayed());

	}

	@Test
	public void testValidarEmailUtilizado(){
		assertFalse(driver.findElement(By.id("usadoEmail")).isDisplayed());

		long aux2 = System.currentTimeMillis();
		driver.findElement(By.id("email")).clear();

		int idU2 = GestorBD.getGestorBD().addUser(String.valueOf(aux2), String.valueOf(aux2), toSha512(String.valueOf(aux2)), String.valueOf(aux),new Date(aux2), String.valueOf(aux2),null);// String.valueOf(aux));
		long time = System.currentTimeMillis() + 5000;
		while(System.currentTimeMillis() < time){}

		driver.findElement(By.id("email")).sendKeys(String.valueOf(aux2));

		driver.findElement(By.id("contraN")).sendKeys(String.valueOf(aux));

		time = System.currentTimeMillis() + 5000;
		while(System.currentTimeMillis() < time){}

		assertTrue(driver.findElement(By.id("usadoEmail")).isDisplayed());

		GestorBD.getGestorBD().deleteAutor(idU2);

	}
	
	@Test
	public void testValidarEmailNuevo(){
		assertFalse(driver.findElement(By.id("usadoEmail")).isDisplayed());

		driver.findElement(By.id("email")).clear();

		long time = System.currentTimeMillis() + 5000;
		while(System.currentTimeMillis() < time){}

		driver.findElement(By.id("email")).sendKeys(String.valueOf(System.currentTimeMillis()));

		driver.findElement(By.id("contraN")).sendKeys(String.valueOf(aux));

		time = System.currentTimeMillis() + 5000;
		while(System.currentTimeMillis() < time){}

		assertFalse(driver.findElement(By.id("usadoEmail")).isDisplayed());


	}
}
