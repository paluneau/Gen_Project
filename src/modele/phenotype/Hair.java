package modele.phenotype;

import modele.phenotype.data.HairColor;

public class Hair {

	public HairColor couleurCheveux;

	public Hair(HairColor hc) {
		setCouleurCheveux(hc);
	}

	public HairColor getCouleurCheveux() {
		return couleurCheveux;
	}

	public void setCouleurCheveux(HairColor couleurCheveux) {
		this.couleurCheveux = couleurCheveux;
	}

}
