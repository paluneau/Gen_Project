package modele.phenotype;

public class Ear extends BodyPart {
	
	private double rotation;
	private double profondeur;

	public Ear(String... groups) {
		super(groups);
		rotation = 0;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}
	
	public double getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(double profondeur) {
		this.profondeur = profondeur;
	}
	
	
}
