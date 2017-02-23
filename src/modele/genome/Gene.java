package modele.genome;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import utils.FastaSequenceReader;

public class Gene {
	private Allele allele = null;
	private String name = "";
	private static ArrayList<String> wntdSnps = null;
	private ArrayList<SNP> snips = null;

	public static void main(String[] args) {
		File f = new File("C:/Users/1534088/Desktop/ADN/rs_ch11.fas");
		wntdSnps = new ArrayList<String>();
		
		wntdSnps.add("104894195");
		wntdSnps.add("102275");
		

		FastaSequenceReader fs = new FastaSequenceReader(f.getAbsolutePath(), wntdSnps);
		HashMap<String, String> seq = fs.getSequences();

		System.out.println(seq.keySet());
		System.out.println(seq.values());
		System.out.println(seq.get(
				">gnl|dbSNP|rs137878331 rs=137878331|pos=51|len=101|taxid=9606|mol=\"genomic\"|class=1|alleles=\"C/T\"|build=144|suspect=?|GMAF=T:5008:0.00239617"));

	}

}
