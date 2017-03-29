package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import modele.genome.Chromosome;
import modele.genome.DNA;

public class DNATest {

	DNA d1;

	@Before
	public void setUp() throws Exception {
		Chromosome.setAltSrcFile(new File(getClass().getResource("/tests").toURI()));
		Set<String> symbols = new HashSet<>();
		symbols.add("15");
		d1 = new DNA(symbols);
	}

	@Test
	public void testGetChrPairs() {
		assertTrue(d1.getChrPair("15").length == 2);
		assertEquals(d1.getChrPair("15")[0].getName(), "15");
		assertEquals(d1.getChrPair("15")[1].getName(), "15");
	}

}
