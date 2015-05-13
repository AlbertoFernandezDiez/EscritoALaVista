package packClases;

import java.sql.Date;

public class Obra {

	private String titulo;
	private String resumen;
	private Date fecha_in;
	private Date fecha_mod;
	private Capitulo[] listaCapitulos;
	private int id;
	private String portada;

	public String getTitulo() {
		return this.titulo;
	}

	public Obra(String pTitulo, String pResumen, Date pFecha_in,
			Date pFecha_mod, int pId, String pPortada) {
		super();
		this.titulo = pTitulo;
		this.resumen = pResumen;
		this.fecha_in = pFecha_in;
		this.fecha_mod = pFecha_mod;
		this.id = pId;
		this.portada = pPortada;
	}

	public String getResumen() {
		return this.resumen;
	}

	public Date getFecha_in() {
		return fecha_in;
	}

	public Capitulo[] getListaCapitulos() {
		return listaCapitulos;
	}

	public int getId() {
		return id;
	}

	public Date getFecha_mod() {
		return this.fecha_mod;
	}

	public String getPortada() {
		// TODO Auto-generated method stub
		return this.portada;
	}

}