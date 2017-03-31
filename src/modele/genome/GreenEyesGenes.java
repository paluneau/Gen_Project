package modele.genome;

import java.util.HashMap;
import java.util.Map;

import utils.Mapable;

public enum GreenEyesGenes implements Mapable<TargetSNPs, Allele[]> {

	// TODO revérifier les snps...
	SNP1(TargetSNPs.RS12203592, Allele.HOMOT), SNP2(TargetSNPs.RS12913832, Allele.HOMOG), SNP3(TargetSNPs.RS16891982,
			Allele.HOMOC), SNP4(TargetSNPs.RS1426654, Allele.HOMOA);

	private TargetSNPs snp = null;
	private Allele[] alleles = null;

	private GreenEyesGenes(TargetSNPs snp, Allele[] alleles) {
		this.alleles = alleles;
		this.snp = snp;
	}

/*	public static Map<TargetSNPs, Allele[]> valuesAsMaps() {
		Map<TargetSNPs, Allele[]> out = new HashMap<>();
		// TODO ici mettre un tableau d'interface et passer la methode values en
		// parametre (interface fonctionnelle?)
		GreenEyesGenes[] tab = values();

		for (GreenEyesGenes g : tab) {
			out.put(g.snp, g.alleles);
		}

		return out;
	}*/

	@Override
	public TargetSNPs getKey() {
		return snp;
	}

	@Override
	public Allele[] getValue() {
		return alleles;
	}
}
