package modele.genome.data;

import utils.Mappable;

public enum GreenEyesGenes implements Mappable<TargetSNPs, Allele[]> {

	SNP1(TargetSNPs.RS12203592, Allele.HOMOT), 
	SNP2(TargetSNPs.RS12913832, Allele.HOMOG), 
	SNP3(TargetSNPs.RS16891982,Allele.HOMOC), 
	SNP4(TargetSNPs.RS1426654, Allele.HOMOA);

	private TargetSNPs snp = null;
	private Allele[] alleles = null;

	private GreenEyesGenes(TargetSNPs snp, Allele[] alleles) {
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
