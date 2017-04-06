package modele.genome.data;

import utils.Mappable;

public enum LightSkinGenes implements Mappable<TargetSNPs, Allele[]> {
	
	SNP1(TargetSNPs.RS12913832, Allele.HOMOG), 
	SNP2(TargetSNPs.RS1545397, Allele.HOMOT), 
	SNP3(TargetSNPs.RS1426654,Allele.HOMOA);
	
	private TargetSNPs snp = null;
	private Allele[] alleles = null;
	
	private LightSkinGenes(TargetSNPs snp, Allele[] alleles){
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
