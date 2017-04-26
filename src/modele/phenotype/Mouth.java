package modele.phenotype;

import modele.phenotype.data.EyeColor;

public class Mouth extends BodyPart {

	private double scale;

	public Mouth(String... groups) {
		super(groups);
		scale = 1;
	}

	public double getScale() {
		return scale;
	}

	public void setScale(double scale) {
		this.scale = scale;
	}
}
