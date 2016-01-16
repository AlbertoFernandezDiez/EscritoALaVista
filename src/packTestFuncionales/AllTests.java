package packTestFuncionales;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAutor.class, TestCambioContrasena.class,
		TestCrearEditarObra.class, TestEliminarElementos.class,
		TestGestionarObras.class, TestGestionarUsuarios.class,
		TestIdentificarse.class, TestIdentificarseAdministrador.class,
		TestMenuAdministrador.class, TestMenuIdentificado.class,
		TestMenuNoIdentifcado.class, TestModificarDatos.class, TestObra.class,
		TestPaginaInicio.class, TestPantallaInicioAdministrador.class,
		TestRecuperacionContrasena.class, TestRegistrarse.class })
public class AllTests {

}
