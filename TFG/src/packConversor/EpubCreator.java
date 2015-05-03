package packConversor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import com.adobe.dp.epub.io.DataSource;
import com.adobe.dp.epub.io.OCFContainerWriter;
import com.adobe.dp.epub.io.ResourceDataSource;
import com.adobe.dp.epub.ncx.TOCEntry;
import com.adobe.dp.epub.opf.BitmapImageResource;
import com.adobe.dp.epub.opf.NCXResource;
import com.adobe.dp.epub.opf.OPSResource;
import com.adobe.dp.epub.opf.Publication;
import com.adobe.dp.epub.opf.StyleResource;
import com.adobe.dp.epub.ops.Element;
import com.adobe.dp.epub.ops.HyperlinkElement;
import com.adobe.dp.epub.ops.ImageElement;
import com.adobe.dp.epub.ops.OPSDocument;
import com.adobe.dp.epub.ops.SVGElement;
import com.adobe.dp.epub.style.Rule;
import com.adobe.dp.epub.style.Selector;
import com.adobe.dp.epub.style.Stylesheet;
import com.adobe.dp.otf.DefaultFontLocator;
import com.adobe.dp.otf.FontLocator;

import packBD.GestorBD;
import packClases.Capitulo;
import packClases.ListaCapitulos;
import packClases.Obra;

/**
 * Servlet implementation class EpubCreator
 */
@WebServlet("/EpubCreator")
public class EpubCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String filePath;
	private File folder;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EpubCreator() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(){
		// TODO Auto-generated method stub
		filePath = getServletContext().getInitParameter("file-upload"); 
		folder = new File(filePath,"/output/temp");

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Obra obra = GestorBD.getGestorBD().getObra(3);
		System.out.println(obra.getTitulo());
		ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(3);
		//Creamos un nuevo objeto 
		//libro
	/*	Book book = new Book();
		Capitulo cap = null;
		//Le añadimos los 
		//metadatos
		book.getMetadata().addDescription(obra.getResumen());
		book.getMetadata().addTitle(obra.getTitulo());
		book.getMetadata().addPublisher("Mi pagina web");
		
		/**
		 * Recorreomos la lista
		 * de capitulos y generamos
		 * un fichero html por cada
		 * uno de ellos
		 */
		/*String page;
		Iterator<Capitulo> it = lista.getIterator();
		int i = 1;
		while (it.hasNext()){
			cap = it.next();
			
			page = this.createHTML(cap, i);
			//book.addSection(cap.getNombre(),new Resource(page));
			book.addResource(new Resource(page));
		}
			 
		EpubWriter epubWriter = new EpubWriter();
		
		epubWriter.write(book, new FileOutputStream(new File(folder, obra.getTitulo()+".epub")));
		*/ 
		try {

            // create new EPUB document
            Publication epub = new Publication();

            // set up title and author
            epub.addDCMetadata("title", "My Second EPUB");
            epub.addDCMetadata("creator", System.getProperty("user.name"));
            epub.addDCMetadata("language", "en");

            // prepare table of contents
            NCXResource toc = epub.getTOC();
            TOCEntry rootTOCEntry = toc.getRootTOCEntry();

            // create a stylesheet
            StyleResource style = epub.createStyleResource("OPS/styles.css");
            Stylesheet stylesheet = style.getStylesheet();

            // style h1 element
            Selector h1Selector = stylesheet.getSimpleSelector("h1", null);
            Rule h1Rule = stylesheet.getRuleForSelector(h1Selector);
            h1Rule.set("color", "gray");
            h1Rule.set("border-bottom", "2px solid gray");
            h1Rule.set("text-align", "right");
            h1Rule.set("margin", "2em 8px 1em 0px");

            // style p element
            Selector pSelector = stylesheet.getSimpleSelector("p", null);
            Rule pRule = stylesheet.getRuleForSelector(pSelector);
            pRule.set("margin", "0px");
            pRule.set("text-indent", "1em");
            pRule.set("text-align", "justify");

            // create first chapter resource
            OPSResource chapter1 = epub.createOPSResource("OPS/chapter1.html");
            epub.addToSpine(chapter1);

            // get chapter document
            OPSDocument chapter1Doc = chapter1.getDocument();

            // link our stylesheet
            chapter1Doc.addStyleResource(style);

            // add chapter to the table of contents
            TOCEntry chapter1TOCEntry = toc.createTOCEntry("Chapter 1",
                            chapter1Doc.getRootXRef());
            rootTOCEntry.add(chapter1TOCEntry);

            // chapter XHTML body element
            Element body1 = chapter1Doc.getBody();

            // add a header
            Element header1 = chapter1Doc.createElement("h1");
            header1.add("One");
            body1.add(header1);

            // add a paragraph
            Element paragraph1 = chapter1Doc.createElement("p");
            StringBuffer sb1 = new StringBuffer();
            for (int i = 1; i <= 6; i++)
                    sb1.append("This is sentence " + i
                                    + " of the first chapter's first paragraph. ");
            paragraph1.add(sb1.toString());
            body1.add(paragraph1);

            // create second chapter resource
            OPSResource chapter2 = epub.createOPSResource("OPS/chapter2.html");
            epub.addToSpine(chapter2);

            // get chapter document
            OPSDocument chapter2Doc = chapter2.getDocument();

            // link our stylesheet
            chapter2Doc.addStyleResource(style);

            // add chapter to the table of contents
            TOCEntry chapter2TOCEntry = toc.createTOCEntry("Chapter 2",
                            chapter2Doc.getRootXRef());
            rootTOCEntry.add(chapter2TOCEntry);

            // chapter XHTML body element
            Element body2 = chapter2Doc.getBody();

            // add a header
            Element header2 = chapter1Doc.createElement("h1");
            header2.add("Two");
            body2.add(header2);

            // add a paragraph
            Element paragraph2 = chapter2Doc.createElement("p");
            StringBuffer sb2 = new StringBuffer();
            for (int i = 1; i <= 6; i++)
                    sb2.append("This is sentence " + i
                                    + " of the second chapter's first paragraph. ");
            paragraph2.add(sb2.toString());
            body2.add(paragraph2);

            // and another one
            Element paragraph3 = chapter2Doc.createElement("p");
            StringBuffer sb3 = new StringBuffer();
            for (int i = 1; i <= 6; i++)
                    sb3.append("This is sentence " + i
                                    + " of the second chapter's second paragraph. ");
            paragraph3.add(sb3.toString());
            body2.add(paragraph3);

            // save EPUB to an OCF container
            OCFContainerWriter writer = new OCFContainerWriter(
                            new FileOutputStream(new File(folder,"hello.epub")));
            epub.serialize(writer);

    } catch (Exception e) {
            e.printStackTrace();
    }
	}

	private String createHTML(Capitulo cap, int n) throws IOException 
	{
		FileWriter file = null;
        PrintWriter pw = null;
		File output = new File(folder,cap.getNombre() + ".html");
        file = new FileWriter(output);
        pw = new PrintWriter(file);
        String toWrite;
        
        toWrite = "<!DOCTYPE html>\n<html>\n<head><title>" +
        cap.getNombre() + "</title>\n"
        		+ "<meta charset='UTF-8'>\n</head>\n<body>";
        
		toWrite +="<h1>" + cap.getNombre() + "</h1><body>\n";
		
		
		String[] parrafos = cap.getTexto();
		
		for (int i = 0; i < parrafos.length; i++)
		{
			toWrite += "<p>" + parrafos[i] + "</p>\n";
		}
		
		toWrite += "</body>\n</html>";
		
		pw.print(toWrite);
		file.close();
		
		System.out.println(output.getAbsolutePath());
		
		
		return output.getPath();
	}

}
