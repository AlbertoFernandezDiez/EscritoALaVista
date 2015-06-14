package packConversor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.sun.jndi.toolkit.url.Uri;

import packBD.GestorBD;


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
		folder = new File(filePath,"/output");
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

		String idS = request.getParameter("id");
		int id = 0;

		try{
			id = Integer.parseInt(idS);
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}


		/*Obra obra = GestorBD.getGestorBD().getObra(id);
		ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(id);
		Usuario autor = GestorBD.getGestorBD().getAutor(id);*/
		packBeans.Obra obra = GestorBD.getGestorBD().getObraBeans(id);
		ArrayList<packBeans.Capitulo> lista = GestorBD.getGestorBD().getCapituloBeans(id);
		packBeans.Autor autor = GestorBD.getGestorBD().getAutorBeans(id);

		File file = new File(folder,obra.getTitulo() +".epub");
		if (file.exists())
		{
			Timestamp modifydate = new Timestamp(file.lastModified());
			System.out.println(modifydate.toString());
			System.out.println(obra.getFecha_mod().toString());
						if (obra.getFecha_mod().compareTo(modifydate) > 0)
						{
							System.out.println("entra");
				file.delete();
				createEpub(obra, lista, autor);

			}
		}
		else{
			createEpub(obra, lista, autor);
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


	private void createEpub(/*Obra*/packBeans.Obra obra, /*ListaCapitulos*/ArrayList<packBeans.Capitulo> lista, /*Usuario*/packBeans.Autor autor) {
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

			// style bitmap class (JPEG image)
			Selector bitmapSelectorTitle = stylesheet.getSimpleSelector(null,
					"bitmaptitle");
			Rule bitmapRuleTitle = stylesheet.getRuleForSelector(bitmapSelectorTitle);
			bitmapRuleTitle.set("width", "80%");
			bitmapRuleTitle.set("max-width", "300px");

			// style bitmap class (JPEG image)
			Selector bitmapSelector = stylesheet.getSimpleSelector(null,
					"bitmap");
			Rule bitmapRule = stylesheet.getRuleForSelector(bitmapSelector);
			bitmapRule.set("width", "80%");
			bitmapRule.set("max-width", "200px");

			// style container class (container for JPEG image)
			Selector containerSelector = stylesheet.getSimpleSelector("p",
					"container");
			Rule containerRule = stylesheet
					.getRuleForSelector(containerSelector);
			containerRule.set("text-align", "center");
			containerRule.set("text-indent", "0px");
			containerRule.set("padding", "0.5em 0px");


			// style portada h4
			Selector portadah4Selector = stylesheet.getSimpleSelector("h5", null);
			Rule portadah4Rule = stylesheet.getRuleForSelector(portadah4Selector);
			portadah4Rule.set("margin-top", "40%");	
			portadah4Rule.set("text-align", "center");


			// style portada h5
			Selector portadah5Selector = stylesheet.getSimpleSelector("h4", null);
			Rule portadah5Rule = stylesheet.getRuleForSelector(portadah5Selector);
			portadah5Rule.set("margin-top", "5%");
			portadah5Rule.set("text-align", "center");


			Iterator<packBeans.Capitulo> it = lista.iterator();//lista.getIterator();
			//Capitulo cap;
					packBeans.Capitulo cap;

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
			File image = new File(filePath,obra.getPortada());
			// chapter XHTML body element
			body1 = chapter1Doc.getBody();

			// add a header
			header1 = chapter1Doc.createElement("h4");
			header1.add(obra.getTitulo());
			body1.add(header1);
			addTitle(epub, chapter1Doc, body1, image);

			header1 = chapter1Doc.createElement("h5");
			header1.add(autor.getNombre());
			body1.add(header1);



			/*	DataSource dataSource = new ResourceDataSource(EpubCreator.class,
			          image.getAbsolutePath());*/
			System.out.println(image.getName());


			// addImage(epub, chapter1Doc, body1, image);


			for (int j = 1; it.hasNext(); j++ ){
				// create first chapter resource
				cap = it.next();

				addCapitulo(epub, toc, rootTOCEntry, style, cap, j);
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


	private void addCapitulo(Publication epub, NCXResource toc,
			TOCEntry rootTOCEntry, StyleResource style, /*Capitulo*/ packBeans.Capitulo cap, int j) {
		OPSResource chapter1;
		OPSDocument chapter1Doc;
		TOCEntry chapter1TOCEntry;
		Element body1;
		Element header1;
		Element paragraph1;
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

		if (cap.getImagen() != null)
			addImage(epub, chapter1Doc, body1, new File(filePath,cap.getImagen()));

		String[] par = cap.getText();
		for (int z=0; z < par.length; z++){
			// add a paragraph
			paragraph1 = chapter1Doc.createElement("p");
			StringBuffer sb1 = new StringBuffer();
			// for (int i = 1; i <= 6; i++)
			paragraph1.add(par[z]);
			body1.add(paragraph1);
		}
	}


	private void addImage(Publication epub, OPSDocument chapter1Doc,
			Element body1, File image) {
		DataSource dataSource =  new ImgFileDataSource(image.getAbsolutePath());
		BitmapImageResource imageResource = epub.createBitmapImageResource(
				"OPS/images/" + image.getName(), "image/jpeg", dataSource);

		Element container = chapter1Doc.createElement("p");
		container.setClassName("container");
		body1.add(container);
		ImageElement bitmap = chapter1Doc.createImageElement("img");
		bitmap.setClassName("bitmap");
		bitmap.setImageResource(imageResource);
		container.add(bitmap);
	}

	private void addTitle(Publication epub, OPSDocument chapter1Doc,
			Element body1, File image) {
		DataSource dataSource =  new ImgFileDataSource(image.getAbsolutePath());
		BitmapImageResource imageResource = epub.createBitmapImageResource(
				"OPS/images/" + image.getName(), "image/jpeg", dataSource);

		Element container = chapter1Doc.createElement("p");
		container.setClassName("container");
		body1.add(container);
		ImageElement bitmap = chapter1Doc.createImageElement("img");
		bitmap.setClassName("bitmaptitle");
		bitmap.setImageResource(imageResource);
		container.add(bitmap);
	}

}
