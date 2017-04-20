package modele.phenotype;

import modele.phenotype.data.EyeColor;

public class Eye extends BodyPart {

	private EyeColor color;
	private double rotation;

	public Eye(EyeColor color, String... groups) {
		super(groups);
		this.color = color;
		rotation = 0;
	}

	public EyeColor getCouleurYeux() {
		return color;
	}

	public void setColor(EyeColor eyeColor) {
		this.color = eyeColor;
	}

	
	public double getRotation() {
		return rotation;
	}
	
	public void setRotation(double rotation){
		this.rotation = rotation;
	}

}
