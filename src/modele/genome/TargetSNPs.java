package modele.genome;

public enum TargetSNPs {
	//TODO AJOUTER LES SNPS CIBLÉS
	RS12913832("12913832", "HERC2", "15");

	private String id = null;
	private String gene = null;
	private String chromosomeNbr = null;

	TargetSNPs(String id, String gene, String chromosomeNbr) {
		this.id = id;
		this.gene = gene;
		this.chromosomeNbr = chromosomeNbr;
	}

	public String getId() {
		return id;
	}

	public String getGene() {
		return gene;
	}

	public String getChromosomeNbr() {
		return chromosomeNbr;
	}

}
