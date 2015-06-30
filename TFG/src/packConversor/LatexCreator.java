package packConversor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;

import de.nixosoft.jlr.JLRConverter;
import de.nixosoft.jlr.JLRGenerator;
import de.nixosoft.jlr.JLROpener;

/**
 * Servlet implementation class LatexCreator
 */
@WebServlet("/LatexCreator")
public class LatexCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
	private File folder,latexFolder;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LatexCreator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void init( ){
		// Get the file location where it would be stored.

		filePath =getServletContext().getInitParameter("file-upload"); 
		folder = new File(filePath,"/output");
		latexFolder = new File(filePath,"/latex");

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String idS = request.getParameter("id");
		int id = 0;

		try{
			id = Integer.parseInt(idS);
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}


		File workingDirectory = new File(latexFolder.getAbsolutePath());
		if (!workingDirectory.exists())
		{
			workingDirectory.mkdirs();
		}
		System.out.println(workingDirectory.getAbsolutePath());

	/*	Obra obra = GestorBD.getGestorBD().getObra(id);
		ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(id);
		Usuario autor = GestorBD.getGestorBD().getAutor(id);*/

		packBeans.Obra obra = GestorBD.getGestorBD().getObraBeans(id);
				ArrayList<packBeans.Capitulo> lista = GestorBD.getGestorBD().getCapituloBeans(id);
		packBeans.Autor autor = GestorBD.getGestorBD().getAutorBeans(id);

		File file = new File(folder,obra.getTitulo() +".pdf");

		if (file.exists())
		{
			Timestamp modifydate = new Timestamp(file.lastModified());
			System.out.println(modifydate.toString());
			System.out.println(obra.getFecha_mod().toString());
						if (obra.getFecha_mod().compareTo(modifydate) > 0)
						{
							System.out.println("entra");
				file.delete();
				createPDF(workingDirectory, obra, lista, autor);

			}
		}
		else{
			createPDF(workingDirectory, obra, lista, autor);
		}
		
		PrintWriter pw = response.getWriter();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition","attachment; filename=\"" + file.getName() + "\"");
		FileInputStream fileInputStream = new FileInputStream(file);  

		int i;   
		while ((i=fileInputStream.read()) != -1) {  
			pw.write(i);   
		}   
		fileInputStream.close();   
		pw.close();   

	}
	private void createPDF(File workingDirectory, packBeans.Obra obra,
			/*ListaCapitulos*/ArrayList<packBeans.Capitulo> lista, /*Usuario*/packBeans.Autor autor) {
		File invoice1 = new File(workingDirectory + File.separator + obra.getTitulo() + ".tex");

		File template = new File(workingDirectory + File.separator+ "invoiceTemplate.tex");
System.out.println(template.getAbsolutePath());
		File tempDir = new File(workingDirectory.getAbsolutePath() + File.separator + "temp");
		if (!tempDir.isDirectory()) {
			tempDir.mkdir();
		}
		try {
			JLRConverter converter = new JLRConverter(workingDirectory);

			ArrayList<ArrayList<String>> book = loadChapters(lista,autor);
			converter.replace("title", obra.getTitulo());
			converter.replace("author", "yo");
			converter.replace("portada", filePath.replace("\\", "/") + obra.getPortada());
			converter.replace("book", book);

			if (!converter.parse(template, invoice1)) {
				System.out.println(converter.getErrorMessage());
			}


			File desktop = new File(filePath + File.separator + "Output");

			JLRGenerator pdfGen = new JLRGenerator();           

			if (!pdfGen.generate(invoice1, desktop, workingDirectory)) { 
				System.out.println(pdfGen.getErrorMessage());
			}
			
			if (!pdfGen.generate(invoice1, desktop, workingDirectory)) { 
				System.out.println(pdfGen.getErrorMessage());
			}



		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
	private ArrayList<ArrayList<String>> loadChapters(/*ListaCapitulos*/ArrayList<packBeans.Capitulo> lista, /*Usuario*/packBeans.Autor autor) {
		Iterator</*Capitulo*/packBeans.Capitulo> it = lista.iterator(); //lista.getIterator();
		/*Capitulo*/ packBeans.Capitulo aux;
		ArrayList<ArrayList<String>> book = new ArrayList<>();
		ArrayList<String> capit;
		while (it.hasNext())
		{
			capit = new ArrayList<String>();
			aux = it.next();
			capit.add(aux.getNombre());
			if (aux.getImagen() == null)
				capit.add(String.valueOf(0));
			else
				capit.add(filePath.replace("\\", "/")  + aux.getImagen());

			for(int i = 0; i < aux.getText().length; i++)
				capit.add(aux.getText()[i]);

			book.add(capit);
		}
		capit = new ArrayList<String>();
		capit.add("Sobre el autor: " + autor.getNombre());
		if (autor.getImagen() == null)
			capit.add(String.valueOf(0));
		else
			capit.add(filePath.replace("\\", "/")  + autor.getImagen());
		for(int i = 0; i < autor.getAbout().length; i++)
			capit.add(autor.getAbout()[i]);

		book.add(capit);
		return book;
	}

}
