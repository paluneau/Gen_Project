package modele;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import exception.ConstructionException;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.data.Allele;
import modele.genome.data.TargetSNPs;
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
		System.out.println(chrSym);

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
				System.out.println(chr);
				chr.getSNPByRS("rs" + current.getId()).setAllele(alleles[pos]);
				pos++;
			}
		});

	}

	/**
	 * Met à jour l'ADN selon l'aspect actuel du visage
	 */
	public void updateDNA() {
		setGenes(this.face.getLEye().getCouleurYeux().getGenes());
		setGenes(this.face.getSkinColor().getGenes());
		setGenes(this.face.getHair().getCouleurCheveux().getGenes());

	}

}
