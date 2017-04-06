package modele.genome.data;

import utils.Mappable;

public enum DarkSkinGenes implements Mappable<TargetSNPs, Allele[]> {

	SNP1(TargetSNPs.RS6119471, Allele.HOMOG);
	

	private TargetSNPs snp = null;
	private Allele[] alleles = null;

	private DarkSkinGenes(TargetSNPs snp, Allele[] alleles) {
		this.snp = snp;
		this.alleles = alleles;
	}

	@Override
	public TargetSNPs getKey() {
		return this.snp;
	}

	@Override
	public Allele[] getValue() {
		return this.alleles;
	}

}
