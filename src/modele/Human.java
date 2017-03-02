package modele;

import javafx.scene.paint.Color;
import modele.genome.DNA;
import modele.phenotype.Face;

public class Human {

	private DNA dna = null;
	private Face face = null;

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
