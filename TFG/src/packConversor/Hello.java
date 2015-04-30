package packConversor;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import packBD.GestorBD;
import packClases.Capitulo;
import packClases.ListaCapitulos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfDestination;
import com.itextpdf.text.pdf.PdfOutline;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	/*	protected void doGet(
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
	}*/

	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		response.setContentType("application/pdf");
		try {
			ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(2);
			Capitulo aux = null;
			Iterator<Capitulo> it = lista.getIterator();
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			PdfOutline root = writer.getRootOutline();
			PdfOutline marcador = null;
		
			
			while (it.hasNext()){
				aux = it.next();
			
				//Creamos el marcador para el siguiente
				//titulo (el del capitulo)
				marcador = new PdfOutline(root, new PdfDestination(PdfDestination.FITH), aux.getNombre());
			
				document.add(this.addTitulo(aux.getNombre()));
				
				document.add(Chunk.NEWLINE);
				
				document.add(this.addTexto(aux.getTexto()));
				document.newPage();
			}
			document.close();
		} catch (DocumentException de) {
			throw new IOException(de.getMessage());
		}
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

	private Paragraph addTitulo(String titulo) {
		Paragraph preface = new Paragraph();
		Font font = new Font(
				FontFamily.HELVETICA, 25, Font.BOLD, BaseColor.BLUE);
		Chunk id = new Chunk(titulo, font);
		preface.add(id);
		preface.setAlignment(Element.ALIGN_CENTER);
		return preface;
	}
	

}
