package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ChromosomeTest.class, DNATest.class, FastaSequenceReaderTest.class, ListToolsTest.class,
		SNPTest.class })
public class AllTests {

}
