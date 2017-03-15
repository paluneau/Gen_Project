package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import modele.genome.Allele;
import modele.genome.SNP;

public class SNPTest {

	SNP rs123, rs456;

	String rs = "rs123";
	String seqmod = "AAACTGG TTCTGCTGRG TTCGGCTGCTA AGCAAGGTAG";
	
	@Before
	public void setUp() throws Exception {
		rs123 = new SNP(rs, seqmod);
		
		rs456 = new SNP(rs, seqmod);
		rs456.setAllele(Allele.C);
	}

	@Test
	public void testGetRS() {
		assertEquals(rs123.getRS(), rs);
		assertEquals(rs456.getRS(), rs);

	}

	@Test
	public void testGetSeq() {
		assertTrue(rs123.getSeq().isEmpty());
		assertFalse(rs456.getSeq().isEmpty());
		assertEquals(rs456.getSeq(), "AAACTGG TTCTGCTGCG TTCGGCTGCTA AGCAAGGTAG");
	}

	@Test
	public void testGetSeqModel() {
		assertEquals(rs123.getSeqModel(), seqmod);
		assertEquals(rs456.getSeqModel(), seqmod);
	}

	@Test
	public void testGetAllele() {
		assertNull(rs123.getAllele());
		assertEquals(rs456.getAllele(), Allele.C);
	}

}
