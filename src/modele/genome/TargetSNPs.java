package modele.genome;

public enum TargetSNPs {
	// TODO AJOUTER LES SNPS CIBLÉS
	RS12913832("12913832", "HERC2", "15"), RS1545397("1545397", "OCA2", "15"), RS1426654("1426654", "SLC24A5",
			"15"), RS16891982("16891982", "SLC45A2", "5");

	private String id = null;
	private String gene = null;
	private String chromosomeNbr = null;

	private TargetSNPs(String id, String gene, String chromosomeNbr) {
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
	
	//TODO methode pour obtenir la séquence d'ADN

}
