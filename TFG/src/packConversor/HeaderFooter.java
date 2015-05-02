package packConversor;

import java.util.List;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;

public	class HeaderFooter extends PdfPageEventHelper {
	Phrase[] header = new Phrase[2];
	int pagenumber;
	public void onOpenDocument(PdfWriter writer, Document document) {
		header[0] = new Phrase("Movie history");
	}
	public void onChapter(PdfWriter writer, Document document,
			float paragraphPosition, Paragraph title) {

		header[1] = new Phrase(title.getContent(), FontFactory.getFont(FontFactory.COURIER, 12, Font.ITALIC));
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
			new LineSeparator().drawLine(writer.getDirectContent(), rect.getRight() - this.getFullWidth(header[0]), rect.getRight(), document.top() - 2);
			break;
		case 1:		
			ColumnText.showTextAligned(writer.getDirectContent(),
					Element.ALIGN_LEFT, header[1],
					rect.getLeft(), rect.getTop(), 0);
			new LineSeparator().drawLine(writer.getDirectContent(), rect.getLeft(), rect.getLeft() + this.getFullWidth(header[1]), document.top() - 2);
			break;
		}
	
		ColumnText.showTextAligned(writer.getDirectContent(),
				Element.ALIGN_CENTER, new Phrase(
						String.format("-%d-", pagenumber)),
						(rect.getLeft() + rect.getRight()) / 2,
						rect.getBottom() - 18, 0);
	}
	private float getFullWidth(Phrase phrase) {
		float total = 0;
		List<Chunk> myChunks = phrase.getChunks();
		for ( int i =0;i < myChunks.size(); i++) {
			total += myChunks.get(i).getWidthPoint();
		}
	return total;
}
}
