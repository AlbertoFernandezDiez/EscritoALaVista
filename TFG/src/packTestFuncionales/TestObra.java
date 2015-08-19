package packTestFuncionales;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import packBD.GestorBD;

public class TestObra {

	private static WebDriver driver;
	private static int idO = 0, idU = 0, idC = 0;
	private static long aux;
	private static File path;

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

		path = new File("tempFile");
		path.mkdir();
		FirefoxProfile prof = new FirefoxProfile();	
		prof.setPreference("browser.download.dir",path.getAbsolutePath());
		prof.setPreference("browser.download.folderList", 2);
		prof.setPreference("browser.helperApps.neverAsk.saveToDisk", "APPLICATION/OCTET-STREAM");

		driver = new FirefoxDriver(prof);
		aux = System.currentTimeMillis();

		idU = GestorBD.getGestorBD().addUser(String.valueOf(aux), String.valueOf(aux), toSha512(String.valueOf(aux)), String.valueOf(aux),new Date(aux), String.valueOf(aux),null);// String.valueOf(aux));

		idO = GestorBD.getGestorBD().insertarObra(idU, String.valueOf(aux), String.valueOf(aux), null);


		idC = GestorBD.getGestorBD().insertarCapitulo(idO, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), null);

		long aux2 = System.currentTimeMillis();

		GestorBD.getGestorBD().insertarCapitulo(idO, String.valueOf(aux), String.valueOf(aux), String.valueOf(aux), null);

		GestorBD.getGestorBD().addComment(idU, idC, idO, "comentario de prueba");


	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		GestorBD.getGestorBD().deleteAutor(idU);
		driver.close();
		try {
			FileUtils.deleteDirectory(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Before
	public void setUp() throws Exception {
		driver.get("http://localhost:8080/TFG/VerHistoria?op=0&hi=" + idO);
	}

	@Test
	public void testCambiarCapitulo() {
		String previousURL = driver.getCurrentUrl();
		List<WebElement> capitulos = driver.findElements(By.className("list-group-item"));

		Iterator <WebElement> it = capitulos.iterator();

		WebElement element = null;
		String className;
		boolean exit = false;

		while(it.hasNext() && !exit)
		{
			element = it.next();
			className = element.getAttribute("class");
			if (!className.contains("active"))
				exit = true;
		}
		// Sleep until the div we want is visible or 5 seconds is over



		element.click();

		assertNotEquals(previousURL, driver.getCurrentUrl());
	}

	@Test
	public void testDireccionarAutorComentario() {

		WebElement autor = driver.findElement(By.id("autor"));
		String autorName = autor.getText();



		autor.click();



		assertEquals(autorName, driver.getTitle());


	}

	@Test
	public void testAbrirMenuExportar(){

		WebElement form = driver.findElement(By.id("exportar"));

		assertFalse(form.isDisplayed());

		WebElement button = driver.findElement(By.id("formtitle"));

		button.click();

		assertTrue(form.isDisplayed());




	}

	@Test
	public void testExportarObra() {

		WebElement form = driver.findElement(By.id("exportar"));

		assertFalse(form.isDisplayed());

		WebElement button = driver.findElement(By.id("formtitle"));

		button.click();

		assertTrue(form.isDisplayed());

		long time = System.currentTimeMillis() + 2000;
		while(System.currentTimeMillis() < time){}

		List<WebElement> list = driver.findElements(By.name("formato"));

		WebElement exportar;


		for (WebElement opcion : list){
			opcion.click();
			exportar = driver.findElement(By.id("botonExportar"));

			exportar.click();

			time = System.currentTimeMillis() + 20000;
			while(System.currentTimeMillis() < time){}
		}

		String[] downloadList = path.list();
		File auxFile;
		String type = null;
		for (String filepath : downloadList){
			auxFile = new File(filepath);

			try {
				type = Files.probeContentType(auxFile.toPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (type != null)
				if(type.equals("application/pdf"))
					assertTrue(true);

				else{
					if(filepath.endsWith("epub"))
						assertTrue(true);
					else
						assertTrue(false);
				}
			auxFile.delete();
		}
	}

	@Test
	public void testSeguimientoNoIdentificado() {
		WebElement seguir = driver.findElement(By.id("seguir"));

		assertFalse(seguir.isSelected());

		seguir.click();

		assertFalse(seguir.isSelected());
	}

	@Test
	public void testSegimientoIdentificado(){

		driver.get("http://localhost:8080/TFG/login.jsp");

		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys(String.valueOf(aux));

		WebElement contra = driver.findElement(By.id("contrasena"));
		contra.sendKeys(String.valueOf(aux));



		driver.findElement(By.id("submit")).click();


		driver.get("http://localhost:8080/TFG/VerHistoria?op=0&hi=" + idO);

		WebElement seguir = driver.findElement(By.id("seguir"));

		assertFalse(seguir.isSelected());

		seguir.click();

		assertTrue(seguir.isSelected());

		driver.get("http://localhost:8080/TFG/LogOut");


	}

	@Test
	public void testEnvioComentarioNoIdentificado() {
		WebElement form = null;
		try{
			form = driver.findElement(By.id("formcomentarios"));
		}catch(NoSuchElementException e){}
		assertEquals(null, form);

	}

	@Test
	public void testEnvioComentarioIdentificado() {
		driver.get("http://localhost:8080/TFG/login.jsp");

		WebElement usuario = driver.findElement(By.id("usuario"));
		usuario.sendKeys(String.valueOf(aux));

		WebElement contra = driver.findElement(By.id("contrasena"));
		contra.sendKeys(String.valueOf(aux));



		driver.findElement(By.id("submit")).click();


		driver.get("http://localhost:8080/TFG/VerHistoria?op=0&hi=" + idO);




		WebElement form = null;
		try{
			form = driver.findElement(By.id("formcomentarios"));
		}catch(NoSuchElementException e){}
		assertNotEquals(null, form);

		List<WebElement> autores = driver.findElements(By.id("autor"));

		assertEquals(1, autores.size());


		driver.findElement(By.id("comentario")).sendKeys("comentario añadido con junit");



		driver.findElement(By.id("botonEnviarComentario")).click();

		long end = System.currentTimeMillis() + 3000;

		while (System.currentTimeMillis() < end) {
		}

		autores = driver.findElements(By.id("autor"));


		driver.get("http://localhost:8080/TFG/LogOut");

		assertEquals(2, autores.size());



	}
}
