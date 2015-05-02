package packConversor;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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





import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	
private float pageWidth,pageHeight;
private PdfContentByte canvas;
	
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("application/pdf");
		try {

			Document document = new Document(PageSize.A4, 50, 50, 50, 50);
			pageHeight = document.getPageSize().getTop();
			pageWidth = document.getPageSize().getRight();
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
		//	PdfWriter writer = PdfWriter.getInstance(document, new ByteArrayOutputStream());
			Rectangle art = new Rectangle(50, 50, 545, 792);
			writer.setBoxSize("art", art);
			writer.setPageEvent(new HeaderFooter());	
			ContentEvent event = new ContentEvent();
		
			writer.setPageEvent( event);
			
			

			document.open();
			
			canvas = writer.getDirectContent();
			/**
			 * Aquí generamos la portada
			 */
			Obra obra = GestorBD.getGestorBD().getObra(3);
			document.addTitle(obra.getTitulo());

			
		

			PdfPTable table = null;
			
			/*
			 * En esta sección se comienza a 
			 * insertar los capítulos con
			 * sus correspondientes maracadores
			 */
			String[] parrafos;
			ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(3);
			Capitulo aux = null;
			Iterator<Capitulo> it = lista.getIterator();
			ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
			Rectangle rect = writer.getBoxSize("art");
			 LineSeparator UNDERLINE = new LineSeparator(1, 100, BaseColor.BLUE, Element.ALIGN_CENTER, -2);
			Chapter chapter;
			int nCapitulo = 1;
			while (it.hasNext()){
				aux = it.next();
				parrafos = aux.getTexto();
				//Creamos el marcador para el siguiente
				//titulo (el del capitulo)
				chapter = new Chapter(new Paragraph(this.addTituloC(aux)), nCapitulo++);
				document.add(chapter);
				chapterList.add(chapter);
				document.add(UNDERLINE);
				document.add(Chunk.NEWLINE);

				for (int i = 0; i < parrafos.length;i++)
				{
					document.add(this.addTexto(parrafos[i]));
					document.add(Chunk.NEWLINE);
						
				}
				
				document.newPage();
			}
		document.close();
		
		
		Document d = new Document(PageSize.A4, 50, 50, 50, 50);
		// add index page.
		String path = "IndexPdf.pdf";
		FileOutputStream os = new FileOutputStream(path);
		//PdfWriter w = PdfWriter.getInstance(d, response.getOutputStream());
		PdfWriter w = PdfWriter.getInstance(d, os);
		IndexEvent indexEvent = new IndexEvent();
		writer.setPageEvent(indexEvent);
		d.open();
		
		Chapter indexChapter = new Chapter("Index", -1);
		indexChapter.setNumberDepth(-1); // not show number style
		PdfPTable tables = new PdfPTable(2);
		for(Map.Entry<String, Integer> index: event.index.entrySet()) {
			PdfPCell left = new PdfPCell(new Phrase(index.getKey()));
			left.setBorder(Rectangle.NO_BORDER);
			
			Chunk pageno = new Chunk(index.getValue()+"");
			pageno.setLocalGoto(index.getKey());
			PdfPCell right = new PdfPCell(new Phrase(pageno));
			right.setHorizontalAlignment(Element.ALIGN_RIGHT);
			right.setBorder(Rectangle.NO_BORDER);
			
			tables.addCell(left);
			tables.addCell(right);
		}
		//indexChapter.add(table);
		d.add(indexChapter);
		d.add(tables);
		// add content chapter
		for(Chapter c : chapterList) {
			d.add(c);
			indexEvent.body = true;
		}
		d.close();
		
		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
	}

	private Element addAutor(Obra obra) {
		Paragraph preface = new Paragraph();
		Font font = new Font(
				FontFamily.HELVETICA, 25, Font.BOLD, BaseColor.BLUE);
		return null;
	}

	/*private Paragraph addPortada(Obra obra, Document document) {
		Paragraph preface = new Paragraph();  
		Font font = new Font(
				FontFamily.COURIER,35 , Font.BOLD, BaseColor.BLACK);
		Chunk id = new Chunk(obra.getTitulo(), font);
		preface.add(id);
		//	preface.setAlignment(Element.ALIGN_CENTER);
		preface.setAlignment(Element.ALIGN_CENTER);

		return preface;
	}*/
	
	private void addPortada(Obra obra, PdfContentByte canvas )
	{
		float y = pageHeight/2;
		float x = pageWidth/2;
		Chunk secTitle = new Chunk("Chapter" ,new Font(
				FontFamily.HELVETICA,25 , Font.BOLD, BaseColor.RED));
		
		ColumnText ct= new ColumnText(canvas);
		ct.showTextAligned(canvas, Element.ALIGN_CENTER, /*new Phrase("Estoy aqui")*/new Phrase(secTitle), x, y, 0);
		ct.showTextAligned(canvas, Element.ALIGN_CENTER, /*new Phrase("Estoy aqui")*/new Phrase(secTitle), x, y + secTitle.getFont().getSize() , 0);

	}

	private Paragraph addTexto(String texto) {
		Paragraph preface = new Paragraph();  
		Font font = new Font(
				FontFamily.HELVETICA,14 , Font.BOLD, BaseColor.BLACK);
		Chunk id = new Chunk(texto, font);	
		preface.add(id);
		preface.setAlignment(Element.ALIGN_JUSTIFIED);
		return preface;
	}

	private Chunk addTituloC(Capitulo cap) {
		Font font = new Font(FontFamily.HELVETICA, 25, Font.BOLD, BaseColor.BLUE);
		Chunk id = new Chunk(cap.getNombre(), font);
		
		return id;
	}

		/*class HeaderFooter extends PdfPageEventHelper {
		Phrase[] header = new Phrase[2];
		int pagenumber;
		public void onOpenDocument(PdfWriter writer, Document document) {
			header[0] = new Phrase("Movie history");
		}
		public void onChapter(PdfWriter writer, Document document,
				float paragraphPosition, Paragraph title) {
			header[1] = new Phrase(title.getContent());
			pagenumber = 1;
		}
		public void onStartPage(PdfWriter writer, Document document) {
			pagenumber++;
		}
		public void onEndPage(PdfWriter writer, Document document) {
			Rectangle rect = writer.getBoxSize("art");
			switch(writer.getPageNumber() % 2) {
			case 0:
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_RIGHT, header[0],
						rect.getRight(), rect.getTop(), 0);
				break;
			case 1:		
				ColumnText.showTextAligned(writer.getDirectContent(),
						Element.ALIGN_LEFT, header[1],
						rect.getLeft(), rect.getTop(), 0);
				break;
			}
			ColumnText.showTextAligned(writer.getDirectContent(),
					Element.ALIGN_CENTER, new Phrase(
							String.format("page %d", pagenumber)),
							(rect.getLeft() + rect.getRight()) / 2,
							rect.getBottom() - 18, 0);
		}
	}*/

	/*class Watermark extends PdfPageEventHelper {
		Font FONT =
		new Font(FontFamily.HELVETICA, 52, Font.BOLD, new GrayColor(0.75f));
		public void onEndPage(PdfWriter writer, Document document) {
		ColumnText.showTextAligned(writer.getDirectContentUnder(),
		Element.ALIGN_CENTER, new Phrase("FOOBAR FILM FESTIVAL", FONT),
		297.5f, 421, writer.getPageNumber() % 2 == 1 ? 45 : -45);
		}
		}
	 */
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
