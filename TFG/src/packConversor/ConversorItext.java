package packConversor;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;

public class ConversorItext {
	
	private Document document;
	OutputStream os = new O
	
	
	public ConversorItext(){
		document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, os)
	}
	
	public Document getDocument(){
		return document;
	}
	
	public boolean addTitulo(String titulo){
		boolean hecho = false;
		
		document.addTitle(titulo);
		document.add(new )
	}
	
	public boolean addTexto(String texto){
		
	}

}
