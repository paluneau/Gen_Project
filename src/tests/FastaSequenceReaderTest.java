package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import utils.FastaSequenceReader;

public class FastaSequenceReaderTest {

	File f;
	LinkedList<String> wntdSnps;

	@Before
	public void setUp() throws Exception {
		f = new File(getClass().getResource("/tests/rs_tst.fas").toURI());
		wntdSnps = new LinkedList<String>();

	}

	/*@Test
	public void testReadSequenceTEST() {
		wntdSnps.add("1545397");
		try {
			f = new File(getClass().getResource("/fasta/chr_15.fas").toURI());
		} catch (URISyntaxException e1) {
			System.out.println(e1.getMessage());
		}

		FastaSequenceReader fs = null;
		try {
			fs = new FastaSequenceReader(f, wntdSnps);
		} catch (IOException e) {
			fail();
		}
		System.out.println(fs.getSequences());

	}*/

	@Test
	public void testReadSequenceFromFileFWD() {
		wntdSnps.add("3894");
		wntdSnps.add("3897");
		FastaSequenceReader fs = null;
		try {
			fs = new FastaSequenceReader(f, wntdSnps);
		} catch (IOException e) {
			fail();
		}
		assertTrue(fs.getSequences().keySet().size() == 2 && fs.getSequences().values().size() == 2);

	}

	@Test
	public void testReadSequenceFromFileBKWD() {
		wntdSnps.add("3897");
		wntdSnps.add("3894");

		FastaSequenceReader fs = null;
		try {
			fs = new FastaSequenceReader(f, wntdSnps);
		} catch (IOException e) {
			fail();
		}
		assertTrue(fs.getSequences().keySet().size() == 2 && fs.getSequences().values().size() == 2);

	}

	@Test
	public void testReadSequenceFromFileEMPTY() {
		wntdSnps.add("3897");
		wntdSnps.add("3894");
		try {
			f = new File(getClass().getResource("/tests/rs_tst_EMPTY.fas").toURI());
		} catch (URISyntaxException e1) {
			System.out.println(e1.getMessage());
		}

		FastaSequenceReader fs = null;
		try {
			fs = new FastaSequenceReader(f, wntdSnps);
			fail();
		} catch (IOException e) {
			assertNull(fs);
		}

	}

	@Test
	public void testReadSequenceFromFileUNFORMAT() {
		wntdSnps.add("3897");
		wntdSnps.add("3894");
		try {
			f = new File(getClass().getResource("/tests/rs_tst_UNFORM.fas").toURI());
		} catch (URISyntaxException e1) {
			System.out.println(e1.getMessage());
		}

		FastaSequenceReader fs = null;
		try {
			fs = new FastaSequenceReader(f, wntdSnps);
			fail();
		} catch (IOException e) {
			assertNull(fs);
		}

	}

	@Test
	public void testGetSequences() {
		wntdSnps.add("3896");
		wntdSnps.add("3897");
		FastaSequenceReader fs = null;
		try {
			fs = new FastaSequenceReader(f, wntdSnps);
		} catch (IOException e) {
			fail();
		}
		assertTrue(fs.getSequences().size() == 2);
		assertEquals(fs.getSequences().get("rs3896"),
				"gggtttatac tgacctgcca atgttaaaag ggacctaaat tcactttggg gaagtggcca gaaaggaaga agYagaaggagaa gagtgcaaga aacctccagt tgtgggggtt gagcctccag gataagaaag aaagaaatct ccagtaggggggattgagcc taacacaaac ctttggtaat agacaaggca agacatttcc aataggggag attgagtgtc acctcaaaactattaagatg ggaaataccc caggtaagat agagggtaaa aaaggataaa gctagcagca ataacattcc ccctgaaagttcCCAATAA");

	}

}
