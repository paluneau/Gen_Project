package modele.genome.data;

import utils.Mapable;

public enum BlackHairGenes implements Mapable<TargetSNPs, Allele[]> {

	SNP1(TargetSNPs.RS16891982, Allele.HOMOG), 
	SNP2(TargetSNPs.RS12203592, Allele.HOMOC), 
	SNP3(TargetSNPs.RS28777,Allele.HOMOA), 
	SNP4(TargetSNPs.RS26722,Allele.HOMOG), 
	SNP5(TargetSNPs.RS4959270, Allele.HOMOC), 
	SNP6(TargetSNPs.RS12913832, Allele.HOMOC);

	TargetSNPs snp = null;
	Allele[] alleles = null;

	private BlackHairGenes(TargetSNPs snp, Allele[] alleles) {
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
