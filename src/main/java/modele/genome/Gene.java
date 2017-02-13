package modele.genome;

import java.util.ArrayList;

import org.biojava.nbio.core.sequence.DNASequence;

public class Gene {
	private Allele allele = null;
	private String name  = "";
	private DNASequence seq = null; //La séquence actuelle
	private ArrayList<SNP> snips = null;
	

}
