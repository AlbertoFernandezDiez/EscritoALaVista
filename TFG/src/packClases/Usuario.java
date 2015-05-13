package packClases;

import java.sql.Date;

public class Usuario {

	private int id;
	private String pais;
	private Date nacimiento;
	private String nombre;
	private String[] about;
	private ListaObras listaObras;
	private ListaComentarios listaComentarios;
	private String imagen;

	public Usuario(int pId, String pPais, Date pNacimiento, String pNombre, String pAbout, String pImagen)
	{
		this.id = pId;
		this.pais = pPais;
		this.nacimiento = pNacimiento;
		this.nombre = pNombre;
		this.about = pAbout.split("\n");
		this.listaComentarios = new ListaComentarios();
		this.listaObras = new ListaObras();
		this.imagen = pImagen;
	}
	
	public int getId() {
		return this.id;
	}

	public String getPais() {
		return this.pais;
	}

	public Date getNacimiento() {
		return this.nacimiento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String[] getAbout() {
		return this.about;
	}

	public ListaComentarios getListaComentarios() {
		return this.listaComentarios;
	}

	public String getImagen() {
		// TODO Auto-generated method stub
		return this.imagen;
	}

}