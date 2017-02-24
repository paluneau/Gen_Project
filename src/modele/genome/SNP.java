package modele.genome;

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import utils.FastaSequenceReader;

public class SNP {

	private String rs = null;
	private String desc = null;
	private String seq = null;
	private ArrayList<Allele> alleles = null;

	public SNP(String id) {
		this.rs = id;
	}

}
