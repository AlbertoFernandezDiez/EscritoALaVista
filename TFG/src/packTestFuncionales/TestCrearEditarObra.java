package packTestFuncionales;

import static org.junit.Assert.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import packBD.GestorBD;

public class TestCrearEditarObra {

	private static WebDriver driver;
	private static int idO = 0, idU = 0, idC = 0;
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
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GestorBD.getGestorBD().deleteAutor(idU);
		driver.close();
	}

	@Before
	public void  setUp() throws Exception {
		aux = System.currentTimeMillis();
		
		idU = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), toSha512(String.valueOf(aux)), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));

		idO = GestorBD.getGestorBD().insertarObra(idU, String.valueOf(aux), String.valueOf(aux), null);


		idC = GestorBD.getGestorBD().insertarCapitulo(idO, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), null);

		long aux2 = System.currentTimeMillis();

		GestorBD.getGestorBD().insertarCapitulo(idO, String.valueOf(aux2), String.valueOf(aux2), String.valueOf(aux2), null);


		long time = System.currentTimeMillis() + 3000;

		while(System.currentTimeMillis() < time){}

		driver.get("http://localhost:8080/TFG/login.jsp");

		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys(String.valueOf(aux));

		WebElement contra = driver.findElement(By.id("contrasena"));
		contra.sendKeys(String.valueOf(aux));



		driver.findElement(By.id("submit")).click();

		assertEquals("Inicio", driver.getTitle());

		driver.get("http://localhost:8080/TFG/UploadBook");
	}

	@After
	public void tearDown() throws Exception{
		GestorBD.getGestorBD().deleteObra(idU);
	}

	@Test
	public void testEliminarCapitulo(){

		
		Select selectoObra = new Select(driver.findElement(By.id("selectObra")));
		assertTrue(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		selectoObra.selectByIndex(1);
		assertFalse(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}

		Select selectCapi = new Select(driver.findElement(By.id("selectCapitulo")));

		List<WebElement> option = selectCapi.getOptions(); 

		assertTrue(driver.findElement(By.id("eliminarCapitulo")).getAttribute("class").contains("disabled"));

		selectCapi.selectByIndex(1);

		assertEquals(3, option.size());

		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}

		assertFalse(driver.findElement(By.id("eliminarCapitulo")).getAttribute("class").contains("disabled"));

		driver.findElement(By.id("eliminarCapitulo")).click();

		time = System.currentTimeMillis() + 2000;
		while (System.currentTimeMillis() < time){}

		driver.findElement(By.id("deleteCapitulo")).click();

		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}

		selectoObra = new Select(driver.findElement(By.id("selectObra")));
		selectoObra.selectByIndex(1);

		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}

		selectCapi = new Select(driver.findElement(By.id("selectCapitulo")));

		option = selectCapi.getOptions();

		assertEquals(2, option.size());



	}

	@Test
	public void testEliminarObra(){
		
	
		
		driver.get("http://localhost:8080/TFG/UploadBook");
		
		Select selectObra = new Select(driver.findElement(By.id("selectObra")));
		assertTrue(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		selectObra.selectByIndex(1);
		assertFalse(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		List<WebElement> option = selectObra.getOptions(); 

		assertEquals(2, option.size());

		driver.findElement(By.id("eliminarObra")).click();		

		assertTrue(driver.findElement(By.id("eliminarCapitulo")).getAttribute("class").contains("disabled"));

		long time = System.currentTimeMillis() + 2000;
		while (System.currentTimeMillis() < time){}

		driver.findElement(By.id("deleteObra")).click();

		time = System.currentTimeMillis() + 2000;
		while (System.currentTimeMillis() < time){}

		selectObra = new Select(driver.findElement(By.id("selectObra")));

		option = selectObra.getOptions(); 

		assertEquals(1, option.size());

	}

	@Test
	public void testNuevaObra(){
		
		Select selectoObra = new Select(driver.findElement(By.id("selectObra")));
		assertTrue(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		selectoObra.selectByIndex(0);

		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}


		assertTrue(driver.findElement(By.id("titOb")).getAttribute("value").matches(""));
		assertTrue(driver.findElement(By.id("resumen")).getAttribute("value").matches(""));
		
		driver.findElement(By.id("titCap")).sendKeys(String.valueOf(System.currentTimeMillis()));
		driver.findElement(By.id("capitulo")).sendKeys(String.valueOf(System.currentTimeMillis()));

		
		Select selectCapi = new Select(driver.findElement(By.id("selectCapitulo")));

		List<WebElement> option = selectCapi.getOptions(); 

		assertTrue(driver.findElement(By.id("eliminarCapitulo")).getAttribute("class").contains("disabled"));

		selectCapi.selectByIndex(0);

		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertEquals(1, option.size());
		
		
		driver.findElement(By.id("submit")).click();
		
		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertEquals("Inicio", driver.getTitle());
		
	}

	@Test
	public void testSeleccionarObraExistente(){
				
		Select selectoObra = new Select(driver.findElement(By.id("selectObra")));
		assertTrue(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		selectoObra.selectByIndex(1);
		assertFalse(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}


		assertFalse(driver.findElement(By.id("titOb")).getAttribute("value").matches(""));
		assertFalse(driver.findElement(By.id("resumen")).getAttribute("value").matches(""));

	}

	@Test
	public void testCrearCapitulo(){

		Select selectoObra = new Select(driver.findElement(By.id("selectObra")));
		assertTrue(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		selectoObra.selectByIndex(1);
		assertFalse(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}


		assertFalse(driver.findElement(By.id("titOb")).getAttribute("value").matches(""));
		assertFalse(driver.findElement(By.id("resumen")).getAttribute("value").matches(""));
		
		driver.findElement(By.id("titCap")).sendKeys(String.valueOf(aux));
		driver.findElement(By.id("capitulo")).sendKeys(String.valueOf(aux));
		
		driver.findElement(By.id("submit")).click();
		
		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertEquals("Inicio", driver.getTitle());
		
	}

	@Test
	public void testEditarCapitulo(){

		Select selectoObra = new Select(driver.findElement(By.id("selectObra")));
		assertTrue(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		selectoObra.selectByIndex(1);
		assertFalse(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}


		assertFalse(driver.findElement(By.id("titOb")).getAttribute("value").matches(""));
		assertFalse(driver.findElement(By.id("resumen")).getAttribute("value").matches(""));
		
		driver.findElement(By.id("titCap")).sendKeys(String.valueOf(aux));
		driver.findElement(By.id("capitulo")).sendKeys(String.valueOf(aux));

		
		Select selectCapi = new Select(driver.findElement(By.id("selectCapitulo")));

		List<WebElement> option = selectCapi.getOptions(); 

		assertTrue(driver.findElement(By.id("eliminarCapitulo")).getAttribute("class").contains("disabled"));

		selectCapi.selectByIndex(1);

		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertEquals(3, option.size());
		
		driver.findElement(By.id("titCap")).sendKeys(String.valueOf(System.currentTimeMillis()));
		driver.findElement(By.id("capitulo")).sendKeys(String.valueOf(System.currentTimeMillis()));
		
		driver.findElement(By.id("submit")).click();
		
		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertEquals("Inicio", driver.getTitle());
	}

	@Test
	public void testEditarObra(){

		Select selectoObra = new Select(driver.findElement(By.id("selectObra")));
		assertTrue(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		selectoObra.selectByIndex(1);
		assertFalse(driver.findElement(By.id("eliminarObra")).getAttribute("class").contains("disabled"));

		long time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}


		assertFalse(driver.findElement(By.id("titOb")).getAttribute("value").matches(""));
		assertFalse(driver.findElement(By.id("resumen")).getAttribute("value").matches(""));
		
		driver.findElement(By.id("titCap")).sendKeys(String.valueOf(System.currentTimeMillis()));
		driver.findElement(By.id("capitulo")).sendKeys(String.valueOf(System.currentTimeMillis()));

		
		Select selectCapi = new Select(driver.findElement(By.id("selectCapitulo")));

		List<WebElement> option = selectCapi.getOptions(); 

		assertTrue(driver.findElement(By.id("eliminarCapitulo")).getAttribute("class").contains("disabled"));

		selectCapi.selectByIndex(1);

		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertEquals(3, option.size());
		
		
		driver.findElement(By.id("submit")).click();
		
		time = System.currentTimeMillis() + 3000;
		while (System.currentTimeMillis() < time){}
		
		assertEquals("Inicio", driver.getTitle());
		
	}

	@Test
	public void testTituloYaExistente(){
	
		assertFalse(driver.findElement(By.id("usado")).isDisplayed());

		
		driver.findElement(By.id("titOb")).sendKeys(String.valueOf(aux));
		
		driver.findElement(By.id("resumen")).sendKeys(String.valueOf(aux));
		
		long time = System.currentTimeMillis() + 3000;
		
		while(System.currentTimeMillis() < time){}
		
		assertTrue(driver.findElement(By.id("usado")).isDisplayed());

	}
}
