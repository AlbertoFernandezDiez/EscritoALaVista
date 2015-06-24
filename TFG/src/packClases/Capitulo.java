/*
package packClases;

import java.sql.Date;

public class Capitulo {

	private int id;
	private String nombre;
	private String[] texto;
	private String comentarios_autor;
	private Date fecha_comentario;
	private ListaComentarios listaComentarios;
	private String imagen;
	
	
	public Capitulo(int pId, String pNombre, String pTexto,String pComentarios_autor, Date pFecha_comentario, String pImagen) {
		this.id = pId;		
		this.nombre = pNombre;
		this.texto =  pTexto.split("\n");
		this.comentarios_autor = pComentarios_autor;
		this.fecha_comentario = pFecha_comentario;
		this.imagen = pImagen;
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

	public String getImagen() {
		// TODO Auto-generated method stub
		return this.imagen;
	}

	public String getTextoC() {
		// TODO Auto-generated method stub
		String t = new String();
		for (int i = 0; i < texto.length; i++){t += texto[i];}
		return t;
	}

}
*/