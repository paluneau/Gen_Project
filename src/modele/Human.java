package modele;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import exception.ConstructionException;
import javafx.scene.paint.Color;
import modele.genome.Allele;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.Gene;
import modele.genome.SNP;
import modele.genome.TargetSNPs;
import modele.phenotype.Face;

public class Human {

	private DNA dna = null;
	private Face face = null;

	public Human() throws ConstructionException, IOException, URISyntaxException {
		this.dna = new DNA(chrSymByTargets());
		this.face = new Face();
	}

	public DNA getDna() {
		return dna;
	}

	public void setDna(DNA dna) {
		this.dna = dna;
	}

	public Face getFace() {
		return face;
	}

	public void setFace(Face face) {
		this.face = face;
	}

	/**
	 * Retourne la liste des différents chromosomes à inspecter selon les snp à
	 * trouver
	 * 
	 * @param chrNbr
	 *            le numero du chromosome
	 * @return la liste des identifiants des SNPs
	 */
	private Set<String> chrSymByTargets() {
		TargetSNPs[] tgt = TargetSNPs.values();
		Set<String> chrSym = new HashSet<String>();

		for (TargetSNPs t : tgt) {
			chrSym.add(t.getChromosomeNbr());
		}

		return chrSym;
	}

	// TODO algo des yeux, et quand il y a une probabilité, utiliser le % sur le
	// site du NCBI pour l'europe
	private void setBrownEyeGene() {
		if (getFace().getEye().getCouleurYeux().equals(Color.BROWN)) {
			for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS16891982.getChromosomeNbr())) {
				chr.getSNPByRS("rs" + TargetSNPs.RS16891982.getId()).setAllele(Allele.C);
			}

			for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS6119471.getChromosomeNbr())) {
				chr.getSNPByRS("rs" + TargetSNPs.RS6119471.getId()).setAllele(Allele.G);
			}

		}
	}

	private void setBlueEyeGene() {

	}

	private void setGreenEyeGene() {

	}

	/**
	 * Utilise le pourentage des haplotypes dans la population européenne pour
	 * déterminer l'allèle
	 */
	private void setNotBrownEyeGene() {

	}

	private void setNotBlueEyeGene() {
		

	}

	/**
	 * Met à jour l'ADN selon l'aspect actuel du visage
	 */
	public void updateDNA() {

	}

}
