package packConversor;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class IndexEvent extends PdfPageEventHelper {
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
