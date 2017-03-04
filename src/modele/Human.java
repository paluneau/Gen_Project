package modele;

import java.util.ArrayList;

import javafx.scene.paint.Color;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.Gene;
import modele.genome.SNP;
import modele.phenotype.Face;

public class Human {

	private DNA dna = null;
	private Face face = null;
	
	public Human(){
		this.dna = loadDNA();
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
	
	public DNA loadDNA(){
		
		return null;
	}

}
