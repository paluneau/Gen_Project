package modele;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import exception.ConstructionException;
import javafx.scene.paint.Color;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.Gene;
import modele.genome.SNP;
import modele.phenotype.Face;

public class Human {

	private DNA dna = null;
	private Face face = null;
	
	public Human() throws ConstructionException, IOException, URISyntaxException{
		this.dna = new DNA();
	}

	public DNA getDna() {
		return dna;
	}

	public void setDna(DNA dna) {
		this.dna = dna;
	}

	public Face getFace() {
		return face;
	}

	public void setFace(Face face) {
		this.face = face;
	}
	
}
