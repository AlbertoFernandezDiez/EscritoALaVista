package packBeans;

import java.sql.Date;
import java.sql.Timestamp;

public class Comentario {
	private int autor,obra,capitulo,comentario;
	private String texto;
	private Timestamp fecha_comentario;
	
	public Comentario(){}

	public int getAutor() {
		return autor;
	}

	public void setAutor(int autor) {
		this.autor = autor;
	}

	
	public int getObra() {
		return obra;
	}

	public void setObra(int obra) {
		this.obra = obra;
	}

	public int getCapitulo() {
		return capitulo;
	}

	public void setCapitulo(int capitulo) {
		this.capitulo = capitulo;
	}

	public int getComentario() {
		return comentario;
	}

	public void setComentario(int comentario) {
		this.comentario = comentario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Timestamp getFecha_comentario() {
		return fecha_comentario;
	}

	public void setFecha_comentario(Timestamp fecha_comentario) {
		this.fecha_comentario = fecha_comentario;
	}
	
	
	
}
