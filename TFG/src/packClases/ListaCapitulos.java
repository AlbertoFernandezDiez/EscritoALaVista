package packClases;

import java.util.Iterator;
import java.util.LinkedList;


public class ListaCapitulos {

	private LinkedList<Capitulo> lista;

	public ListaCapitulos(){
		lista = new LinkedList<Capitulo>();
	}
	
	public LinkedList<Capitulo> getLista() {
		return this.lista;
	}
	
	public void addCapitulo(Capitulo capitulo){
		lista.addLast(capitulo);
	}
	
	public  Iterator<Capitulo> getIterator(){
		return lista.iterator();
	}
}