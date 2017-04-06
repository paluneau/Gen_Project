package modele.phenotype;

import modele.phenotype.data.EyeColor;

public class Eye {

	private EyeColor color;
	private float largeur = 0;
	private float height = 0;

	public Eye(EyeColor color, float largeur, float height) {
		this.color = color;
		setLargeur(largeur);
		setHeight(height);
	}

	public EyeColor getCouleurYeux() {
		return color;
	}

	public void setColor(EyeColor eyeColor) {
		this.color = eyeColor;
	}

	public float getLargeur() {
		return this.largeur;
	}

	public void setLargeur(float distance) {
		this.largeur = distance;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
