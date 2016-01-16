package packTestCordova;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestActualizarObraCapituloAPI.class,
		TestCambiarContrasenaAPI.class, TestCambiarDatosApi.class,
		TestCambiarDatosBDApi.class, TestCambiarObraApi.class,
		TestCargarCapituloAPI.class, TestComprobarEmailAPI.class,
		TestComprobarTituloAPI.class, TestComprobarUsuarioAPI.class,
		TestEliminarCapituloAPI.class, TestEliminarObraAPI.class,
		TestListarCapitulosAPI.class, TestLogInApi.class,
		TestMostrarAutor.class, TestMostrarCapitulo.class,
		TestMostrarComentarios.class, TestMostrarlistado.class,
		TestMostrarObra.class, TestRegistrarseApi.class,
		TestSeguimientoAPI.class, TestSendComentarioApi.class })
public class TestPackCordova {

}
