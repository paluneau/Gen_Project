package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import exception.ConstructionException;
import modele.genome.Chromosome;
import modele.genome.SNP;

public class ChromosomeTest {

	Chromosome chr15;
	LinkedList<String> tgt;

	@Before
	public void setUp() throws Exception {
		tgt = new LinkedList<String>();
		tgt.add("12913832");
		tgt.add("1426654");
		tgt.add("1545397");
		

		try {
			chr15 = new Chromosome("15", tgt);

		} catch (ConstructionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetName() {
		assertEquals(chr15.getName(), "15");
	}
	
	@Test
	public void testLire() {
		tgt = new LinkedList<String>();
		tgt.add("12913832");
		tgt.add("1426654");
		tgt.add("1545397");
		

		try {
			chr15 = new Chromosome("0", tgt);
			fail();
		} catch (ConstructionException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testGetSNPByID() {
		SNP rs1545397 = chr15.getSNPByRS("rs1545397");
		SNP rs1426654 = chr15.getSNPByRS("rs1426654");
		SNP rs12913832 = chr15.getSNPByRS("rs12913832");
		assertNotNull(rs1426654);
		assertNotNull(rs1545397);
		assertNotNull(rs12913832);
	}

}
