package modele.genome;

import java.util.HashMap;
import java.util.Map;

import utils.Mapable;

public enum BrownEyesGenes implements Mapable<TargetSNPs, Allele[]> {

	SNP1(TargetSNPs.RS16891982, Allele.HOMOC), SNP2(TargetSNPs.RS6119471, Allele.HOMOG), SNP3(TargetSNPs.RS12913832,
			Allele.HETEROAG);

	private TargetSNPs snp = null;
	private Allele[] alleles = null;

	private BrownEyesGenes(TargetSNPs snp, Allele[] alleles) {
		this.alleles = alleles;
		this.snp = snp;
	}

	/*public static Map<TargetSNPs, Allele[]> valuesAsMaps() {
		Map<TargetSNPs, Allele[]> out = new HashMap<>();
		BrownEyesGenes[] tab = values();
		
		for (BrownEyesGenes g : tab) {
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
