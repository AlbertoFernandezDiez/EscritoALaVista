package packClases;

public class Usuario {

	private int id;
	private String pais;
	private String nacimiento;
	private String nombre;
	private String about;
	private ListaObras listaObras;
	private ListaComentarios listaComentarios;

	public int getId() {
		return this.id;
	}

	public String getPais() {
		return this.pais;
	}

	public String getNacimiento() {
		return this.nacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getAbout() {
		return this.about;
	}

	public ListaComentarios getListaComentarios() {
		return this.listaComentarios;
	}

}