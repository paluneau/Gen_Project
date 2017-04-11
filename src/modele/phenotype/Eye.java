package modele.phenotype;

import modele.phenotype.data.EyeColor;

public class Eye extends BodyPart {

	private EyeColor color;

	public Eye(EyeColor color, String... groups) {
		super(groups);
		this.color = color;
	}

	public EyeColor getCouleurYeux() {
		return color;
	}

	public void setColor(EyeColor eyeColor) {
		this.color = eyeColor;
	}


}
