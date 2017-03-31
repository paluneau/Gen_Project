package modele.genome;

import java.util.HashMap;
import java.util.Map;

import utils.Mapable;

public enum BlueEyesGenes implements Mapable<TargetSNPs, Allele[]> {

	SNP1(TargetSNPs.RS12203592, Allele.HOMOT), SNP2(TargetSNPs.RS12913832, Allele.HOMOG), SNP3(TargetSNPs.RS16891982,
			Allele.HOMOG), SNP4(TargetSNPs.RS1426654, Allele.HOMOA);

	TargetSNPs snp = null;
	Allele[] alleles = null;

	private BlueEyesGenes(TargetSNPs snp, Allele[] alleles) {
		this.alleles = alleles;
		this.snp = snp;
	}

	@Override
	public TargetSNPs getKey() {
		return snp;
	}

	@Override
	public Allele[] getValue() {
		// TODO Auto-generated method stub
		return alleles;
	}

	// TODO trouver un moyen de ne pas répéter la methode 3x et +...
	/*
	 * public static Map<TargetSNPs, Allele[]> valuesAsMaps() { Map<TargetSNPs,
	 * Allele[]> out = new HashMap<>(); Mapable[] tab = values();
	 * 
	 * for (Mapable g : tab) { out.put(((BlueEyesGenes) g).snp, ((BlueEyesGenes)
	 * g).alleles); }
	 * 
	 * return out; }
	 */

}
