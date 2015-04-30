package packClases;

import java.sql.Date;

public class Capitulo {

	private int id;
	private String nombre;
	private String texto;
	private String comentarios_autor;
	private Date fecha_comentario;
	private ListaComentarios listaComentarios;
	
	
	public Capitulo(int id, String nombre, String texto,String comentarios_autor, Date fecha_comentario) {
		this.id = id;
		this.nombre = nombre;
		this.texto = texto;
		this.comentarios_autor = comentarios_autor;
		this.fecha_comentario = fecha_comentario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getTexto() {
		return this.texto;
	}

	public Date getFecha_comentario() {
		return this.fecha_comentario;
	}

	public ListaComentarios getListaComentarios() {
		return this.listaComentarios;
	}

	public int getId() {
		return this.id;
	}
	
	public String getComentariosAutor(){
		return comentarios_autor;
	}

}