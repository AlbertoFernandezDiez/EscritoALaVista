package packConversor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;
import packClases.Capitulo;
import packClases.ListaCapitulos;
import packClases.Obra;














import packClases.Usuario;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.SimpleBookmark;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.itextpdf.text.pdf.draw.LineSeparator;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Itext extends HttpServlet {

	private float pageWidth,pageHeight;
	private PdfContentByte canvas;
	private String filePath;
	private File folder;
	private static Font titulo = new Font(FontFamily.HELVETICA, 35, Font.BOLD, BaseColor.BLUE);
	private static Font capitulo = new Font(FontFamily.HELVETICA, 25, Font.BOLD, BaseColor.BLUE);
	private static Font parrafo = new Font(FontFamily.HELVETICA,17 , Font.BOLD, BaseColor.BLACK);
	private Image img;
	private File image;

	private static Rectangle[] pageSize ={new Rectangle(600,800),
		new Rectangle(758,1024)};
	
	private static Rectangle[] titlepage = {new Rectangle(400,600),
		new Rectangle(558,824)};

	private static Rectangle[] artBoxSize = {new Rectangle(50, 50, 550, 750),
		new Rectangle(50, 50, 708, 974)};

	private static int type = 0;


	public void init( ){
		// Get the file location where it would be stored.
		filePath =getServletContext().getInitParameter("file-upload"); 
		folder = new File(filePath,"/output/Itext");

	}

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		String idS = request.getParameter("id");
		int id = 0;
System.out.println(idS);
		try{
			id = Integer.parseInt(idS);
		}
		catch(NumberFormatException e){
			e.printStackTrace();
		}
		
		FileOutputStream pdf2 = null;
		FileOutputStream pdf1 = null;
		Obra obra = GestorBD.getGestorBD().getObra(id);
		Usuario autor = GestorBD.getGestorBD().getAutor(id);
		File file1,file2;
		
		try {
			if (!folder.exists())
				folder.mkdirs();
		
			Document document = new Document(pageSize[type], 50, 50, 50, 50);
			pageHeight = document.getPageSize().getTop();
			pageWidth = document.getPageSize().getRight();
			file2 = new File(folder,obra.getTitulo() + "2.pdf");
			pdf2 = new FileOutputStream(file2);
			PdfWriter writer = PdfWriter.getInstance(document, pdf2);
			new Rectangle(50, 50, 550, 750);
			writer.setBoxSize("art", artBoxSize[type]);
			writer.setPageEvent(new HeaderFooter(obra.getTitulo()));	
			ContentEvent event = new ContentEvent();

			writer.setPageEvent( event);



			document.open();

			canvas = writer.getDirectContent();
			/**
			 * Aquí generamos la portada
			 */
			//obra = GestorBD.getGestorBD().getObra(3);
			document.addTitle(obra.getTitulo());




			/*
			 * En esta sección se comienza a 
			 * insertar los capítulos con
			 * sus correspondientes maracadores
			 */
			String[] parrafos;
			ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(id);
			Capitulo aux = null;
			Iterator<Capitulo> it = lista.getIterator();
			ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
			writer.getBoxSize("art");
			LineSeparator UNDERLINE = new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2);
			Chapter chapter;
			int nCapitulo = 1;
			while (it.hasNext()){
				aux = it.next();
				parrafos = aux.getTexto();
				//Creamos el marcador para el siguiente
				//titulo (el del capitulo)
				chapter = new Chapter(new Paragraph(this.addTituloC(aux.getNombre())), nCapitulo++);
				document.add(chapter);
				chapterList.add(chapter);
				document.add(UNDERLINE);
				document.add(Chunk.NEWLINE);
				
				if (aux.getImagen() != null)
				anadirImagen(document, aux.getImagen());

				for (int i = 0; i < parrafos.length;i++)
				{
					document.add(this.addTexto(parrafos[i]));
					document.add(Chunk.NEWLINE);

				}
				if ((writer.getPageNumber() % 2) == 1)
				{					
					document.newPage();
				}
			}

			/**
			 * Añadimos la información sobre el autor al final
			 * del documento.
			 */
			chapter = new Chapter(new Paragraph(this.addTituloC("Sobre el autor: " + autor.getNombre())), nCapitulo++);
			document.add(chapter);
			chapterList.add(chapter);
			document.add(UNDERLINE);

			
			if (autor.getImagen() != null) 
			anadirImagen(document, autor.getImagen());
			
			
			parrafos = autor.getAbout();
			for (int i = 0; i < parrafos.length;i++)
			{
				document.add(this.addTexto(parrafos[i]));
				document.add(Chunk.NEWLINE);

			}

			/**
			 * Cerramos el documento con los
			 * capitulos e información sobre el 
			 * autor e iniciamos la creación de 
			 * la portada y el indice
			 */
			document.close();
			writer.close();
			pdf2.close();




			Document d = new Document(pageSize[type], 50, 50, 50, 50);
			// add index page.
			file1 = new File(folder, obra.getTitulo()+"1.pdf");
			pdf1 = new FileOutputStream(file1);

			PdfWriter w = PdfWriter.getInstance(d, pdf1);
			IndexEvent indexEvent = new IndexEvent();
			w.setPageEvent(indexEvent);
			d.open();
			
			if (obra.getPortada() != null)
			image = new File(filePath,obra.getPortada());
			
			Chunk secTitle = new Chunk(obra.getTitulo() ,new Font(FontFamily.HELVETICA, 35, Font.BOLD, BaseColor.BLUE));
			PdfContentByte canvas = w.getDirectContent();
			ColumnText ct= new ColumnText(w.getDirectContent());
			if (image != null && image.exists()){
			img = Image.getInstance(image.getAbsolutePath());
			//img.scaleToFit(pageSize[type]);
		//	img.scaleToFit(pageSize[type].getWidth(), pageSize[type].getHeight());
			//img.scaleAbsolute(pageSize[type]);
		//	img.scaleAbsolute(writer.getBoxSize("art"));
			img.scaleAbsolute(titlepage[type]);
			img.setAbsolutePosition((pageSize[type].getWidth() - titlepage[type].getWidth())/2, (pageSize[type].getHeight() - titlepage[type].getHeight())/2);
		//	img.setAbsolutePosition(document.getPageSize().getRight()/2, document.getPageSize().getTop()/2);
			canvas.addImage(img);
			}
			ct.showTextAligned(canvas, Element.ALIGN_CENTER, new Phrase(secTitle), document.getPageSize().getRight()/2, document.getPageSize().getTop()/2, 0);
			ct.showTextAligned(canvas, Element.ALIGN_CENTER, new Phrase(autor.getNombre()), document.getPageSize().getRight()/2, document.getPageSize().getTop()/2 - secTitle.getFont().getSize() , 0);


			Chapter indexChapter = new Chapter(new Paragraph(new Chunk("Índice", capitulo)),-1);
			indexChapter.setNumberDepth(-1); // not show number style
			PdfPTable tables = new PdfPTable(2);
			for(Map.Entry<String, Integer> index: event.index.entrySet()) {
				PdfPCell left = new PdfPCell(new Phrase(new Chunk(index.getKey(),parrafo)));
				left.setBorder(Rectangle.NO_BORDER);

				Chunk pageno = new Chunk(index.getValue()+"");
				//	pageno.setLocalGoto(index.getKey());
				PdfPCell right = new PdfPCell(new Phrase(pageno));
				right.setHorizontalAlignment(Element.ALIGN_RIGHT);
				right.setBorder(Rectangle.NO_BORDER);

				tables.addCell(left);
				tables.addCell(right);
			}
			//indexChapter.add(table);
			d.add(indexChapter);
			d.add(Chunk.NEWLINE);
			d.add(tables);
			

			d.close();
			w.close();
			pdf1.close();


		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
		try {
			String fileName = joinPDF(file1,file2,obra,autor,response);

			PrintWriter pw = response.getWriter();
			pw.write("<!DOCTYPE html><html><head><meta charset='UTF-8'>"
					+ "<title>Registrarse</title></head><body><a href='output/Itext/"+fileName+"'>"+obra.getTitulo()+"</a></body></html>");
			pw.close();

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private void anadirImagen(Document document, String imagen)
			throws BadElementException, MalformedURLException, IOException,
			DocumentException {
		image = new File(filePath,imagen);
		if (image.exists()){
		img = Image.getInstance(image.getAbsolutePath());
		img.setAlignment(Chunk.ALIGN_CENTER);
		img.scalePercent(25);
		img.setSpacingAfter(50);
		img.setSpacingBefore(25);
		document.add(img);}
		img = null;
		image = null;
	}




	private String joinPDF(File pdf1, File pdf2, Obra obra,Usuario autor, HttpServletResponse response) throws IOException, DocumentException {
		// TODO Auto-generated method stub
		PdfReader reader ;//= new PdfReader(new RandomAccessFileOrArray(pdf1.getAbsolutePath()), null);
		String src[] = {pdf1.getAbsolutePath(),pdf2.getAbsolutePath()};
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		File file = new File(folder, obra.getTitulo()+".pdf");
		FileOutputStream pdf = new FileOutputStream(file);
		
		PdfCopy copy = new PdfCopy(document, pdf);
		document.open();

		document.addTitle(obra.getTitulo());
		document.addSubject(obra.getResumen());
		document.addAuthor(autor.getNombre());

		int page_offset = 0;
		int n;
		// Create a list for the bookmarks
		ArrayList<HashMap<String, Object>> bookmarks = new ArrayList<HashMap<String, Object>>();
		java.util.List<HashMap<String,Object>> tmp;
		for (int i  = 0; i < src.length; i++) {
			reader = new PdfReader(src[i]);
			// merge the bookmarks
			tmp = SimpleBookmark.getBookmark(reader);
			if (tmp != null)
			{
				SimpleBookmark.shiftPageNumbers(tmp, page_offset, null);
				bookmarks.addAll(tmp);
			}
			// add the pages
			n = reader.getNumberOfPages();
			page_offset += n;
			for (int page = 0; page < n; ) {
				copy.addPage(copy.getImportedPage(reader, ++page));
			}
			//	reader.close();
			copy.freeReader(reader);
			reader.close();
		}
		// Add the merged bookmarks
		copy.setOutlines(bookmarks);
		// step 5
		document.close();
		copy.close();
		pdf.close();
		pdf1.delete();
		pdf2.delete();

		return file.getName();

	}








	
	private Paragraph addTexto(String texto) {
		Paragraph preface = new Paragraph();  

		Chunk id = new Chunk(texto, parrafo);	
		preface.add(id);
		preface.setAlignment(Element.ALIGN_JUSTIFIED);
		return preface;
	}

	private Chunk addTituloC(String string) {
		Chunk id = new Chunk(string, capitulo);
		return id;
	}


	private static class ContentEvent extends PdfPageEventHelper {
		private int page;
		Map<String, Integer> index = new LinkedHashMap<String, Integer>();

		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			page++;
		}

		@Override
		public void onChapter(PdfWriter writer, Document document,
				float paragraphPosition, Paragraph title) {

			index.put(title.getContent(), page);
		}

		@Override
		public void onSection(PdfWriter writer, Document document,
				float paragraphPosition, int depth, Paragraph title) {
			onChapter(writer, document, paragraphPosition, title);
		}
	}

	/**
	 * EventListner for Index
	 */
	private static class IndexEvent extends PdfPageEventHelper {
		private int page;
		private boolean body;
		@Override
		public void onEndPage(PdfWriter writer, Document document) {
			// set page number on content
			if(body) {
				page++;
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, 
						new Phrase(page+""), (document.right() + document.left())/2 , document.bottom() - 18, 0);
			}
		}

	}

}
