package packBeans;

import java.io.Serializable;
import java.sql.Date;

public class Capitulo implements Serializable {
	private int id, obra;
	private String nombre,comentarios_autor,imagen;
	private String[] text;
	private Date fecha_comentario;
	
	public Capitulo(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getObra() {
		return obra;
	}

	public void setObra(int obra) {
		this.obra = obra;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String[] getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text.split("\n");
	}

	public String getComentarios_autor() {
		return comentarios_autor;
	}

	public void setComentarios_autor(String comentarios_autor) {
		this.comentarios_autor = comentarios_autor;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Date getFecha_comentario() {
		return fecha_comentario;
	}

	public void setFecha_comentario(Date fecha_comentario) {
		this.fecha_comentario = fecha_comentario;
	}
	
}
