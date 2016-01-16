package packTestBeans;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestAutor.class, TestBreadCrumb.class, TestCapitulo.class,
		TestComentario.class, TestObra.class })
public class TestBeans {

}
