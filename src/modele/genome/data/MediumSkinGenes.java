package modele.genome.data;

import utils.Mapable;

public enum MediumSkinGenes implements Mapable<TargetSNPs, Allele[]> {

	SNP1(TargetSNPs.RS6119471, Allele.HOMOG), SNP2(TargetSNPs.RS1545397, Allele.HOMOT), SNP3(TargetSNPs.RS1426654,
			Allele.HOMOA);

	private TargetSNPs snp = null;
	private Allele[] alleles = null;

	private MediumSkinGenes(TargetSNPs snp, Allele[] alleles) {
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
