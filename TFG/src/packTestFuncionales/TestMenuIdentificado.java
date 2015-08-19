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

public class TestMenuIdentificado {

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

		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys(String.valueOf(aux));

		WebElement contra = driver.findElement(By.id("contrasena"));
		contra.sendKeys(String.valueOf(aux));



		driver.findElement(By.id("submit")).click();
		long aux = System.currentTimeMillis() + 2000;
		while (System.currentTimeMillis() < aux){}
	}

	@Before
	public void tearDown() throws Exception {
	
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
	public void testUsuario() {
		String tituloLink, tituloPagina;

		
		WebElement button = driver.findElement(By.id("verAutor"));
		tituloLink = String.valueOf(aux);
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
	}

	@Test
	public void testModificarDatos() {
String tituloLink, tituloPagina;

		
		WebElement button = driver.findElement(By.id("cambiarDatos"));
		tituloLink = "Modificar Datos";
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

	@Test
	public void testLogOut() {
String tituloLink, tituloPagina;

		
		WebElement button = driver.findElement(By.id("logout"));
		tituloLink = "Inicio";
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
	}

	@Test
	public void testCrearHistoria() {
String tituloLink, tituloPagina;

		
		WebElement button = driver.findElement(By.id("crearHistoria"));
		tituloLink = "Crear Historia";
		button.click();
		tituloPagina = driver.getTitle();

		assertEquals(tituloLink, tituloPagina);
	}



}
