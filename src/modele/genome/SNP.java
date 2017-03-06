package modele.genome;

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import utils.FastaSequenceReader;

public class SNP {
	private String rs = null;
	private String seq = null;
	private Allele allele = null;

	public SNP(String id, String seq) {
		this.rs = id;
		this.seq = seq;
	}
	
	public String getRS(){
		return this.rs;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSeq() {
		return this.seq;
	}

	public String setSeq() {
		return seq;

	}

	public Allele getAllele() {
		return allele;
	}

	public void setAllele(Allele a) {
		this.allele = a;
	}

}
