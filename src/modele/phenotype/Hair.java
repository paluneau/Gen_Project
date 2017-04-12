package modele.phenotype;

import modele.phenotype.data.HairColor;

public class Hair extends BodyPart {

	public HairColor couleurCheveux;

	public Hair(HairColor hc, String... groups) {
		super(groups);
		setCouleurCheveux(hc);
	}

	public HairColor getCouleurCheveux() {
		return couleurCheveux;
	}

	public void setCouleurCheveux(HairColor couleurCheveux) {
		this.couleurCheveux = couleurCheveux;
	}

}
