package modele.genome;

import java.util.ArrayList;

public class Chromosome {

	private ArrayList<Gene> genes = null;
	private String dataSrcPath = null;
	
	public Chromosome(String path, ArrayList<Gene> genes){
		this.dataSrcPath = path;
		this.genes = genes;
	}

}
