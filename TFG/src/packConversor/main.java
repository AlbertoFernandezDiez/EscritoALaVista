package packConversor;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;





import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

//import packBD.GestorBD;

/*public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
    	//String enc = writer.getEncoding();
		String enc = GestorBD.getGestorBD().getCapitulos();
		        System.out.println(enc/*GestorBD.getGestorBD().getCapitulos());
		    }
	
	

}*/

public class main {
	 
	/**
	 * EventListner for Content
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
	
	public static void main(String[] args) throws Exception {
		// make content pdf (calculate page number)
		Document content = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter contentWriter = PdfWriter.getInstance(content, new ByteArrayOutputStream());
		ContentEvent event = new ContentEvent();
		contentWriter.setPageEvent(event);
		content.open();
		ArrayList<Chapter> chapterList = new ArrayList<Chapter>();
		for (int i = 1; i <= 10; i++) {
			Chunk chapTitle = new Chunk("Chapter" + i,new Font(
					FontFamily.HELVETICA,14 , Font.BOLD, BaseColor.RED));
			Chapter chapter = new Chapter(new Paragraph(chapTitle), i);
			chapTitle.setLocalDestination(chapter.getTitle().getContent());
			
			for (int j = 0; j < i; j++) {
				Chunk secTitle = new Chunk("Section" + (j+1));
				Section section = chapter.addSection(new Paragraph(secTitle));
				secTitle.setLocalDestination(section.getTitle().getContent());
				section.setIndentationLeft(10);
				section.add(new Paragraph("sentense sentense\nsentense sentense"));
			}
			content.add(chapter);
			chapterList.add(chapter);
		}
		content.close();
 
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		// add index page.
		String path = "IndexPdf.pdf";
		FileOutputStream os = new FileOutputStream(path);
		PdfWriter writer = PdfWriter.getInstance(document, os);
		IndexEvent indexEvent = new IndexEvent();
		writer.setPageEvent(indexEvent);
		
		document.open();
		
		Chapter indexChapter = new Chapter("Index", -1);
		indexChapter.setNumberDepth(-1); // not show number style
		PdfPTable table = new PdfPTable(2);
		for(Map.Entry<String, Integer> index: event.index.entrySet()) {
			PdfPCell left = new PdfPCell(new Phrase(index.getKey()));
			left.setBorder(Rectangle.NO_BORDER);
			
			Chunk pageno = new Chunk(index.getValue()+"");
			pageno.setLocalGoto(index.getKey());
			PdfPCell right = new PdfPCell(new Phrase(pageno));
			right.setHorizontalAlignment(Element.ALIGN_RIGHT);
			right.setBorder(Rectangle.NO_BORDER);
			
			table.addCell(left);
			table.addCell(right);
		}
		indexChapter.add(table);
		document.add(indexChapter);
		// add content chapter
		for(Chapter c : chapterList) {
			document.add(c);
			indexEvent.body = true;
		}
		document.close();
		os.close();
	}
 
}