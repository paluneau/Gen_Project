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
			if (!a.isWildCard()) {
				ls.add(new Character(a.getPrimarySymbol()));
				ls.add(new Character(a.getSecondarySymbol()));
			}
		}
		ls.add(new Character(' '));

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

		if (seq.isEmpty()) {
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
		} else {
			char courant = seq.charAt(getVarPos());
			char latest = getAllele().getPrimarySymbol();
			this.allele = getWildCard(Allele.valueOf(Character.toString(courant)),
					Allele.valueOf(Character.toString(latest)));
			char[] letters = getSeq().toCharArray();
			letters[getVarPos()] = getAllele().getPrimarySymbol();
			this.seq = new String(letters);
		}
	}

	private Allele getWildCard(Allele x, Allele y) {
		Allele result = null;

		if (x.equals(y)) {
			result = x;
		} else if ((x.equals(Allele.A) && y.equals(Allele.G)) || (x.equals(Allele.G) && y.equals(Allele.A))) {
			result = Allele.R;
		} else if ((x.equals(Allele.C) && y.equals(Allele.T)) || (x.equals(Allele.T) && y.equals(Allele.C))) {
			result = Allele.Y;
		} else if ((x.equals(Allele.G) && y.equals(Allele.T)) || (x.equals(Allele.T) && y.equals(Allele.G))) {
			result = Allele.K;
		} else if ((x.equals(Allele.A) && y.equals(Allele.C)) || (x.equals(Allele.C) && y.equals(Allele.A))) {
			result = Allele.M;
		} else if ((x.equals(Allele.C) && y.equals(Allele.G)) || (x.equals(Allele.G) && y.equals(Allele.C))) {
			result = Allele.S;
		} else if ((x.equals(Allele.A) && y.equals(Allele.T)) || (x.equals(Allele.T) && y.equals(Allele.A))) {
			result = Allele.W;
		} else {
			result = Allele.N;
		}
		return result;
	}

}
