package packConversor;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("application/pdf");
		try {
			Document document = new Document();
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();

			//document.add(new Paragraph(this.addTitulo(GestorBD.getGestorBD().getCapitulos())));
			document.add(this.addTitulo(GestorBD.getGestorBD().getCapitulos()));
			
			//document.add(new Paragraph(this.addTexto(GestorBD.getGestorBD().getCapitulos())));
			document.add(this.addTexto(GestorBD.getGestorBD().getCapitulos()));
			
			document.close();
		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
	}

	private Paragraph addTexto(String texto) {
		Paragraph preface = new Paragraph();  
		Font font = new Font(
				FontFamily.HELVETICA,15 , Font.BOLD, BaseColor.BLACK);
		Chunk id = new Chunk(texto, font);	
		preface.add(id);
		preface.setAlignment(Element.ALIGN_JUSTIFIED);
		return preface;
	}

	private Paragraph addTitulo(String titulo) {
		Paragraph preface = new Paragraph();
		Font font = new Font(
				FontFamily.HELVETICA, 15, Font.BOLD, BaseColor.BLUE);
		Chunk id = new Chunk(titulo, font);
		preface.add(id);
		preface.setAlignment(Element.ALIGN_CENTER);
		return preface;
	}
}
