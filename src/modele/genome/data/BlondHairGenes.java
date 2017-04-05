package modele.genome.data;

import utils.Mapable;

public enum BlondHairGenes implements Mapable<TargetSNPs, Allele[]> {
	SNP1(TargetSNPs.RS4904868, Allele.HOMOC), SNP2(TargetSNPs.RS2402130, Allele.HOMOA), SNP3(TargetSNPs.RS1805005,
			Allele.HOMOG), SNP4(TargetSNPs.RS885479, Allele.HOMOG);

	TargetSNPs snp = null;
	Allele[] alleles = null;

	private BlondHairGenes(TargetSNPs snp, Allele[] alleles) {
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
