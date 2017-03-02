package modele.phenotype;

public class Hair {
	public enum enumLongueurCheveux {
		LONG, COURT, AUCUN
	};

	public enum enumCouleurCheveux {
		BLOND, BRUN, ROUX
	};

	public enumCouleurCheveux couleurCheveux;
	public enumLongueurCheveux longueurCheveux;

	public enumCouleurCheveux getCouleurCheveux() {
		return couleurCheveux;
	}

	public void setCouleurCheveux(enumCouleurCheveux couleurCheveux) {
		this.couleurCheveux = couleurCheveux;
	}

	public enumLongueurCheveux getLongueurCheveux() {
		return longueurCheveux;
	}

	public void setLongueurCheveux(enumLongueurCheveux longueurCheveux) {
		this.longueurCheveux = longueurCheveux;
	}

}
