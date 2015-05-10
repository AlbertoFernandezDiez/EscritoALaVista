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
import packClases.Usuario;

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
		if (!folder.exists())
		{
			folder.mkdirs();
		}

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Obra obra = GestorBD.getGestorBD().getObra(3);
		ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(3);
		Usuario autor = GestorBD.getGestorBD().getAutor(3);
		/*	try {

            // create new EPUB document
            Publication epub = new Publication();

            // set up title and author
     //       epub.addDCMetadata("title", obra.getTitulo());
            epub.addDCMetadata("creator", System.getProperty("user.name"));
            epub.addDCMetadata("language", "es");

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


            Iterator<Capitulo> it = lista.getIterator();
            OPSResource chapter;
            OPSDocument chapterDOC;
            TOCEntry chapterTOCEntry;
            Element body;
            Element header;
            Capitulo cap;
            String[] paragraphs;
            Element paragraph;
            for(int i = 1; it.hasNext(); i++){
                cap = it.next();

            	chapter = epub.createOPSResource("OPS/chapter" + i + ".html");

            	chapterDOC = chapter.getDocument();

                // link our stylesheet
            	chapterDOC.addStyleResource(style);


                // add chapter to the table of contents
                chapterTOCEntry = toc.createTOCEntry(cap.getNombre(),
                		chapterDOC.getRootXRef());
                rootTOCEntry.add(chapterTOCEntry);

                // chapter XHTML body element
                body = chapterDOC.getBody();

                // add a header
                header = chapterDOC.createElement("h1");
                //header.add(cap.getNombre());
                header.add("on");
                body.add(header);


                paragraphs = cap.getTexto();

                // add a paragraph
//                paragraph = chapterDOC.createElement("p");
                for (int j = 0; j < paragraphs.length; j++)
                {
                	paragraph = chapterDOC.createElement("p");
                	paragraph.add(paragraphs[j]);
                	body.add(paragraph);
                }

            }

            // save EPUB to an OCF container
            OCFContainerWriter writer = new OCFContainerWriter(
                            new FileOutputStream(new File(folder,obra.getTitulo()+".epub")));
            epub.serialize(writer);

    } catch (Exception e) {
            e.printStackTrace();
    }*/
		try{
			// create new EPUB document
			Publication epub = new Publication();

			// set up title and author
			epub.addDCMetadata("title", obra.getTitulo());
			epub.addDCMetadata("creator", autor.getNombre());
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


			// style portada h4
			Selector portadah4Selector = stylesheet.getSimpleSelector("h5", null);
			Rule portadah4Rule = stylesheet.getRuleForSelector(portadah4Selector);
			portadah4Rule.set("margin-top", "40%");	
			portadah4Rule.set("text-align", "center");


			// style portada h5
			Selector portadah5Selector = stylesheet.getSimpleSelector("h4", null);
			Rule portadah5Rule = stylesheet.getRuleForSelector(portadah5Selector);
			portadah5Rule.set("margin-top", "15%");
			portadah5Rule.set("text-align", "center");


			Iterator<Capitulo> it = lista.getIterator();
			Capitulo cap;

			OPSResource chapter1;
			OPSDocument chapter1Doc;
			TOCEntry chapter1TOCEntry;
			Element body1;
			Element header1;
			Element paragraph1;
			
			chapter1 = epub.createOPSResource("OPS/portada.html");
			epub.addToSpine(chapter1);
			chapter1Doc = chapter1.getDocument();

			// link our stylesheet
			chapter1Doc.addStyleResource(style);
			
			// chapter XHTML body element
			body1 = chapter1Doc.getBody();

			// add a header
			header1 = chapter1Doc.createElement("h4");
			header1.add(obra.getTitulo());
			body1.add(header1);
			header1 = chapter1Doc.createElement("h5");
			header1.add(autor.getNombre());
			body1.add(header1);
			
			for (int j = 1; it.hasNext(); j++ ){
				// create first chapter resource
				cap = it.next();

				chapter1 = epub.createOPSResource("OPS/chapter"+j+".html");
				epub.addToSpine(chapter1);

				// get chapter document
				chapter1Doc = chapter1.getDocument();

				// link our stylesheet
				chapter1Doc.addStyleResource(style);

				// add chapter to the table of contents
				//chapter1TOCEntry = toc.createTOCEntry("Chapter " + j,
				chapter1TOCEntry = toc.createTOCEntry(cap.getNombre(),
						chapter1Doc.getRootXRef());
				rootTOCEntry.add(chapter1TOCEntry);

				// chapter XHTML body element
				body1 = chapter1Doc.getBody();

				// add a header
				header1 = chapter1Doc.createElement("h1");
				header1.add(cap.getNombre());
				body1.add(header1);

				String[] par = cap.getTexto();
				for (int z=0; z < par.length; z++){
					// add a paragraph
					paragraph1 = chapter1Doc.createElement("p");
					StringBuffer sb1 = new StringBuffer();
					// for (int i = 1; i <= 6; i++)
					paragraph1.add(par[z]);
					body1.add(paragraph1);
				}
			}

			chapter1 = epub.createOPSResource("OPS/sobreautor.html");
			epub.addToSpine(chapter1);

			// get chapter document
			chapter1Doc = chapter1.getDocument();

			// link our stylesheet
			chapter1Doc.addStyleResource(style);

			// add chapter to the table of contents
			//chapter1TOCEntry = toc.createTOCEntry("Chapter " + j,
			chapter1TOCEntry = toc.createTOCEntry("Sobre el autor: " + autor.getNombre(),
					chapter1Doc.getRootXRef());
			rootTOCEntry.add(chapter1TOCEntry);

			// chapter XHTML body element
			body1 = chapter1Doc.getBody();

			// add a header
			header1 = chapter1Doc.createElement("h1");
			header1.add("Sobre el autor: " + autor.getNombre());
			body1.add(header1);

			String[] par = autor.getAbout();
			for (int z=0; z < par.length; z++){
				// add a paragraph
				paragraph1 = chapter1Doc.createElement("p");
				StringBuffer sb1 = new StringBuffer();
				// for (int i = 1; i <= 6; i++)
				paragraph1.add(par[z]);
				body1.add(paragraph1);
			}


			// save EPUB to an OCF container
			OCFContainerWriter writer = new OCFContainerWriter(
					new FileOutputStream(new File(folder,obra.getTitulo() + ".epub")));
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
