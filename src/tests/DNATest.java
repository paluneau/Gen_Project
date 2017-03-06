package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.genome.DNA;

public class DNATest {

	DNA d1;

	@Before
	public void setUp() throws Exception {
		d1 = new DNA();
	}

	@Test
	public void testGetChrPairs() {
		System.out.println("TEST:--------------------------");
		System.out.println(d1.getChrPairs().size());
		System.out.println(d1.getChrPairs().get(0)[0].getName());
		System.out.println(d1.getChrPairs().get(0)[1].getName());
		System.out.println(d1.getChrPairs().get(0)[0].getSNPByRS("rs1426654"));
	}

}
