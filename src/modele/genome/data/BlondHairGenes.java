package modele.genome.data;

import utils.Mapable;

public enum BlondHairGenes implements Mapable<TargetSNPs, Allele[]>{
	;
	
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
