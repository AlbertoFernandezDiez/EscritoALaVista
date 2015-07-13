package packCordova;

import java.util.HashMap;

public class UsuariosLoggeados {
	private static UsuariosLoggeados myUsuariosLogeados = null;
	private HashMap<String, Integer> usuariosLoggeados = null;
	
	private UsuariosLoggeados(){
		usuariosLoggeados = new HashMap<String, Integer>();
	}
	
	public static UsuariosLoggeados getMyUsuariosLogeados(){
	
		if (myUsuariosLogeados == null)
			myUsuariosLogeados = new UsuariosLoggeados();
		
		return myUsuariosLogeados;
	}
	
	/**
	 * Metodo que permite añadir un nuevo usuario
	 * a la lista de usuarios loggeados para la 
	 * aplicación movil
	 * @param clave Su clave en la lista
	 * @param id	El id en BD del usuario
	 */
	public void addUsuario(String clave, Integer id){
		usuariosLoggeados.put(clave, id);
	}
	
	/**
	 * Metodo que permite eliminar a un usuario
	 * de la lista de usuarios loggeados para la 
	 * aplicación movil
	 * @param clave Su clave en la lista
	 */
	public void removeUsuario(String clave){
		usuariosLoggeados.remove(clave);
	}
	
	/**
	 * Metodo que permite recuperar el id de un
	 * usuario de la lista de usuarios loggeados
	 * @param clave	Clave del usuario en la lista
	 * @return	El id en BD del usuario, 0 si no
	 * esta loggeado
	 */
	public Integer getUsuario(String clave){
		int id = 0;
		id = usuariosLoggeados.get(clave);
		return id;
	}

}
