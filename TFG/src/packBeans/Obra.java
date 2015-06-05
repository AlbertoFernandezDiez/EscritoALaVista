package packBeans;

import java.sql.Date;
import java.sql.Timestamp;


public class Obra {
	private int id,autor;
	private String titulo,resumen,portada;
	private Date fecha_in;
	private Timestamp fecha_mod;
	
	public Obra(){	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAutor() {
		return autor;
	}

	public void setAutor(int autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	public String getPortada() {
		return portada;
	}

	public void setPortada(String portada) {
		this.portada = portada;
	}

	public Date getFecha_in() {
		return fecha_in;
	}

	public void setFecha_in(Date fecha_in) {
		this.fecha_in = fecha_in;
	}

	public Timestamp getFecha_mod() {
		return fecha_mod;
	}

	public void setFecha_mod(Timestamp fecha_mod) {
		this.fecha_mod = fecha_mod;
	}
	
	
}
