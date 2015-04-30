package packClases;

import java.sql.Date;

public class Capitulo {

	private int id;
	private String nombre;
	private String[] texto;
	private String comentarios_autor;
	private Date fecha_comentario;
	private ListaComentarios listaComentarios;
	
	
	public Capitulo(int pId, String pNombre, String pTexto,String pComentarios_autor, Date pFecha_comentario) {
		this.id = pId;		
		this.nombre = pNombre;
		this.texto =  pTexto.split("\n");
		this.comentarios_autor = pComentarios_autor;
		this.fecha_comentario = pFecha_comentario;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String[] getTexto() {
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