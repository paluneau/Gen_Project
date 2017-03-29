package modele;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import exception.ConstructionException;
import javafx.scene.paint.Color;
import modele.genome.Allele;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.TargetSNPs;
import modele.phenotype.Face;

public class DNACreator {

	private DNA dna = null;
	private Face face = null;

	public DNACreator(Face f) throws ConstructionException, IOException, URISyntaxException {
		if (f != null) {
			this.dna = new DNA(chrSymByTargets());
			this.face = f;
			updateDNA();
		} else {
			throw new ConstructionException("VISAGE INEXISTANT");
		}

	}

	public DNA getDna() {
		return dna;
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

	/**
	 * Met le SNP des yeux bruns
	 */
	private void setBrownEyeGene() {

		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS16891982.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS16891982.getId()).setAllele(Allele.C);
		}

		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS6119471.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS6119471.getId()).setAllele(Allele.G);
		}

		setNotBlueEyeGene();
	}

	/**
	 * Met le SNP des yeux bleus
	 */
	private void setBlueEyeGene() {

		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS12203592.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS12203592.getId()).setAllele(Allele.T);
		}

		setNotBrownEyeGene();

	}

	/**
	 * Met le SNP des yeux verts
	 */
	private void setGreenEyeGene() {
		double rnd = Math.random();

		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS12203592.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS12203592.getId()).setAllele(Allele.T);
		}

		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS16891982.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS16891982.getId()).setAllele(Allele.C);
		}

		if (rnd <= 0.6) {
			setNotBrownEyeGene();
		} else {
			setNotBlueEyeGene();
		}

	}

	/**
	 * Met le SNP des yeus non-bleus Utilise le pourentage des haplotypes dans
	 * la population européenne pour déterminer l'allèle. src:
	 * https://www.ncbi.nlm.nih.gov/projects/SNP/snp_ref.cgi?rs=12913832
	 */
	private void setNotBlueEyeGene() {
		double rnd = Math.random();

		if (rnd <= 0.9) {
			Chromosome[] chrPair = getDna().getChrPair(TargetSNPs.RS12913832.getChromosomeNbr());
			chrPair[0].getSNPByRS("rs" + TargetSNPs.RS12913832.getId()).setAllele(Allele.A);
			chrPair[1].getSNPByRS("rs" + TargetSNPs.RS12913832.getId()).setAllele(Allele.G);
		} else {
			for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS12913832.getChromosomeNbr())) {
				chr.getSNPByRS("rs" + TargetSNPs.RS12913832.getId()).setAllele(Allele.A);
			}
		}
	}

	/**
	 * Met le SNP des yeux non-bruns
	 */
	private void setNotBrownEyeGene() {
		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS12913832.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS12913832.getId()).setAllele(Allele.G);
		}

		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS16891982.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS16891982.getId()).setAllele(Allele.G);
		}

		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS1426654.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS1426654.getId()).setAllele(Allele.A);
		}

	}

	private void setEyeColorGene() {
		switch (face.getLEye().getCouleurYeux()) {
		case BLUE:
			setBlueEyeGene();
			break;
		case BROWN:
			setBrownEyeGene();
			break;
		case GREEN:
			setGreenEyeGene();
			break;
		}
	}

	private void setEyeSkinGene() {
		switch (face.getSkinColor()) {
		case LIGHT:
			setLightSkinGene();
			setNonDarkSkinGene();
			break;
		case MEDIUM:
			setNonDarkSkinGene();
			setNonLightSkinGene();
			break;
		case DARK:
			setNonLightSkinGene();
			break;
		}
	}

	/**
	 * Met le SNP de la peau pâle
	 */
	private void setLightSkinGene() {
		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS12913832.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS12913832.getId()).setAllele(Allele.G);
		}
	}

	/**
	 * Met le SNP de la peau non-pâle
	 */
	private void setNonLightSkinGene() {
		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS6119471.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS6119471.getId()).setAllele(Allele.G);
		}
	}

	/**
	 * Met le SNP de la peau non-foncée
	 */
	private void setNonDarkSkinGene() {
		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS1545397.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS1545397.getId()).setAllele(Allele.T);
		}
		for (Chromosome chr : getDna().getChrPair(TargetSNPs.RS1426654.getChromosomeNbr())) {
			chr.getSNPByRS("rs" + TargetSNPs.RS1426654.getId()).setAllele(Allele.A);
		}
	}

	/**
	 * Met à jour l'ADN selon l'aspect actuel du visage
	 */
	public void updateDNA() {
		setEyeColorGene();

	}

}
