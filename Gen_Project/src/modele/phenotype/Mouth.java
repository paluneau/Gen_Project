package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Mouth {

	public double finesseBouche;
	public DoubleProperty finesseBoucheProp = new SimpleDoubleProperty();

	public double hauteurBouche;
	public DoubleProperty hauteurBoucheProp = new SimpleDoubleProperty();

	public double getFinesseBouche() {
		return finesseBouche;
	}

	public void setFinesseBouche(double finesseBouche) {
		this.finesseBouche = finesseBouche;
	}

	public DoubleProperty getFinesseBoucheProp() {
		return finesseBoucheProp;
	}

	public void setFinesseBoucheProp(DoubleProperty finesseBoucheProp) {
		this.finesseBoucheProp = finesseBoucheProp;
	}

	public double getHauteurBouche() {
		return hauteurBouche;
	}

	public void setHauteurBouche(double hauteurBouche) {
		this.hauteurBouche = hauteurBouche;
	}

	public DoubleProperty getHauteurBoucheProp() {
		return hauteurBoucheProp;
	}

	public void setHauteurBoucheProp(DoubleProperty hauteurBoucheProp) {
		this.hauteurBoucheProp = hauteurBoucheProp;
	}

}
