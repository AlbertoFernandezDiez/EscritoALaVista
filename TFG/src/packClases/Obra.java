package packClases;

public class Obra {

	private String titulo;
	private String resumen;
	private String fecha_in;
	private String fecha_mod;
	private Capitulo[] listaCapitulos;
	private int id;

	public String getTitulo() {
		return this.titulo;
	}

	public String getResumen() {
		return this.resumen;
	}

	public String getFecha_mod() {
		return this.fecha_mod;
	}

}