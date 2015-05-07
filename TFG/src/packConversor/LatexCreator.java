package packConversor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;
import packClases.Capitulo;
import packClases.ListaCapitulos;
import packClases.Obra;
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
	private File folder;
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
		folder = new File(filePath,"/latex");

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		File workingDirectory = new File(folder.getAbsolutePath());
		if (!workingDirectory.exists())
		{
			workingDirectory.mkdirs();
		}
		//System.out.println(workingDirectory.getAbsolutePath());

		Obra obra = GestorBD.getGestorBD().getObra(3);
		ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(3);
		File invoice1 = new File(workingDirectory + File.separator + "invoice1.tex");
		
		File template = new File(folder + File.separator+ "invoiceTemplate.tex");
		
		File tempDir = new File(workingDirectory.getAbsolutePath() + File.separator + "temp");
		if (!tempDir.isDirectory()) {
			tempDir.mkdir();
		}
		try {
			JLRConverter converter = new JLRConverter(workingDirectory);

			ArrayList<ArrayList<String>> book = loadChapters(lista);
			   converter.replace("title", obra.getTitulo());
            converter.replace("author", "yo");

            converter.replace("book", book);

            if (!converter.parse(template, invoice1)) {
                System.out.println(converter.getErrorMessage());
            }
		

			File desktop = new File(tempDir + File.separator + "Desktop");

			JLRGenerator pdfGen = new JLRGenerator();           

			if (!pdfGen.generate(invoice1, desktop, workingDirectory)) { 
				System.out.println(pdfGen.getErrorMessage());
			}

			JLROpener.open(pdfGen.getPDF());


		} catch (IOException ex) {
			System.err.println(ex.getMessage());
		}
	}
	private ArrayList<ArrayList<String>> loadChapters(ListaCapitulos lista) {
		Iterator<Capitulo> it = lista.getIterator();
		Capitulo aux;
		ArrayList<ArrayList<String>> book = new ArrayList<>();
		ArrayList<String> capit;
		while (it.hasNext())
		{
			capit = new ArrayList<String>();
			aux = it.next();
			capit.add(aux.getNombre());
			for(int i = 0; i < aux.getTexto().length; i++)
				capit.add(aux.getTexto()[i]);
			
			book.add(capit);
		}
		return book;
	}

}
