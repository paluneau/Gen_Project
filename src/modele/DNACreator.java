package modele;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import exception.ConstructionException;
import modele.genome.Allele;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.TargetSNPs;
import modele.phenotype.Face;

/**
 * Classe qui va créer l'ADN selon le visage, donc affecter les variations
 * appropriées au génome
 * 
 * @author Les génies du génome
 *
 */
public class DNACreator {

	private DNA dna = null;
	private Face face = null;

	public DNACreator(Face f) throws ConstructionException, IOException, URISyntaxException {
		if (f != null) {
			this.dna = new DNA(chrSymByTargets());
			this.face = f;
			System.out.println(this.face.getLEye().getCouleurYeux());
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
	 * trouver (le Set élimine les doublons)
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
	 * Affecte les bonnes variations sur l'ADN
	 * 
	 * @param map
	 *            contient comme clés les SNP et comme valeurs les alleles
	 */
	private void setGenes(Map<TargetSNPs, Allele[]> map) {
		map.forEach((snp, alleles) -> {
			int pos = 0;
			TargetSNPs current = snp;
			for (Chromosome chr : getDna().getChrPair(current.getChromosomeNbr())) {
				chr.getSNPByRS("rs" + current.getId()).setAllele(alleles[pos]);
				pos++;
			}
		});

	}

	// TODO A FAIRE COMME POUR LES YEUX ENUM + MAPS
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
		setGenes(this.face.getLEye().getCouleurYeux().getGenes());

	}

}
