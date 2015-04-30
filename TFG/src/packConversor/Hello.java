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
import packClases.Obra;

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
import com.itextpdf.text.pdf.BaseFont;
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
			
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			PdfOutline root = writer.getRootOutline();
			PdfOutline marcador = null;
			
			/**
			 * Aquí generamos la portada
			 */
			Obra obra = GestorBD.getGestorBD().getObra(3);
			document.addTitle(obra.getTitulo());
			
			document.add(this.addPortada(obra, document));
			//this.addPortada(obra, document);
			//document.add(this.addAutor(obra));
			
			
			document.newPage();
			/*
			 * En esta sección se comienza a 
			 * insertar los capítulos con
			 * sus correspondientes maracadores
			 */
			String[] parrafos;
			ListaCapitulos lista = GestorBD.getGestorBD().getCapitulos(3);
			Capitulo aux = null;
			Iterator<Capitulo> it = lista.getIterator();
		/*	Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			PdfOutline root = writer.getRootOutline();
			PdfOutline marcador = null;*/


			while (it.hasNext()){
				aux = it.next();
				parrafos = aux.getTexto();
				//Creamos el marcador para el siguiente
				//titulo (el del capitulo)
				marcador = new PdfOutline(root, new PdfDestination(PdfDestination.FITH), aux.getNombre());

				document.add(this.addTituloC(aux.getNombre()));

				document.add(Chunk.NEWLINE);

				for (int i = 0; i < parrafos.length;i++)
				{
					document.add(this.addTexto(parrafos[i]));
					document.add(Chunk.NEWLINE);

				}
				document.newPage();
			}
			document.close();
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

	private Paragraph addPortada(Obra obra, Document document) {
		Paragraph preface = new Paragraph();  
		Font font = new Font(
				FontFamily.HELVETICA,14 , Font.BOLD, BaseColor.BLACK);
		Chunk id = new Chunk(obra.getTitulo(), font);
		preface.add(id);
	//	preface.setAlignment(Element.ALIGN_CENTER);
		preface.setAlignment(Element.ALIGN_BOTTOM);
		
		return preface;
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

	private Paragraph addTituloC(String titulo) {
		Paragraph preface = new Paragraph();
		Font font = new Font(
				FontFamily.HELVETICA, 25, Font.BOLD, BaseColor.BLUE);
		Chunk id = new Chunk(titulo, font);
		preface.add(id);
		preface.setAlignment(Element.ALIGN_CENTER);
		
		return preface;
	}


}
