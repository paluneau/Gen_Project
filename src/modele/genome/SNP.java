package modele.genome;

import java.util.ArrayList;
import java.util.List;

public class SNP {
	private String rs = null;
	private String seqModel = null;
	private String seq = null;
	private Allele allele = null;
	private int varPos = 0;

	public SNP(String id, String seq) {
		this.rs = id;
		this.seqModel = seq;
		this.seq = "";
		this.allele = Allele.N;
	}

	public String getRS() {
		return this.rs;
	}

	public String getSeq() {
		return this.seq;
	}

	public String getSeqModel() {
		return seqModel;
	}

	public Allele getAllele() {
		return allele;
	}

	/**
	 * Trouve les caractères possible des allèles à partir de l'énum Allele
	 * 
	 * @return La liste des caractères possibles d'allèles
	 */
	private List<Character> getAlleleChars() {
		List<Character> ls = new ArrayList<>();

		for (Allele a : Allele.values()) {
			ls.add(new Character(a.getPrimarySymbol()));
			ls.add(new Character(a.getSecondarySymbol()));
		}
		ls.add(new Character(' '));
		ls.remove(new Character('N'));
		return ls;
	}

	public void setAllele(Allele a) {
		this.allele = a;
		applyAlleleOnSeq();
	}

	public int getVarPos() {
		return this.varPos;
	}

	/**
	 * Utilise la séquence modèle pour créer la "vraie" séquence en intégrant
	 * l'allèle du SNP
	 */
	private void applyAlleleOnSeq() {
		List<Character> chrAllele = getAlleleChars();
		boolean replaced = false;
		int size = seqModel.length();

		for (int i = 0; i < size; i++) {
			Character current = new Character(seqModel.charAt(i));
			if (replaced || chrAllele.contains(current)) {
				seq += current.toString();
			} else {
				seq += new Character(getAllele().getPrimarySymbol()).toString();
				this.varPos = i;
				replaced = true;
			}
		}
	}

}
