package modele.genome.data;

import utils.Mappable;

public enum BrownEyesGenes implements Mappable<TargetSNPs, Allele[]> {

	SNP1(TargetSNPs.RS16891982, Allele.HOMOC), 
	SNP2(TargetSNPs.RS6119471, Allele.HOMOG), 
	SNP3(TargetSNPs.RS12913832,Allele.HETEROAG);

	private TargetSNPs snp = null;
	private Allele[] alleles = null;

	private BrownEyesGenes(TargetSNPs snp, Allele[] alleles) {
		this.alleles = alleles;
		this.snp = snp;
	}

	@Override
	public TargetSNPs getKey() {
		return snp;
	}

	@Override
	public Allele[] getValue() {
		return alleles;
	}

}
