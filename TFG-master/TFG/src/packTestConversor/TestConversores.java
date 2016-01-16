package packTestConversor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestEpubCreator.class, TestItext.class, TestLatexCreator.class })
public class TestConversores {

}
