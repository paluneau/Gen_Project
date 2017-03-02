package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Ear {

	public double hauteurOreille;
	public DoubleProperty hauteurOreilleProp = new SimpleDoubleProperty();

	public double grosseurOreille;
	public DoubleProperty grosseurOreilleProp = new SimpleDoubleProperty();

	public double getHauteurOreille() {
		return hauteurOreille;
	}

	public void setHauteurOreille(double hauteurOreille) {
		this.hauteurOreille = hauteurOreille;
	}

	public DoubleProperty getHauteurOreilleProp() {
		return hauteurOreilleProp;
	}

	public void setHauteurOreilleProp(DoubleProperty hauteurOreilleProp) {
		this.hauteurOreilleProp = hauteurOreilleProp;
	}

	public double getGrosseurOreille() {
		return grosseurOreille;
	}

	public void setGrosseurOreille(double grosseurOreille) {
		this.grosseurOreille = grosseurOreille;
	}

	public DoubleProperty getGrosseurOreilleProp() {
		return grosseurOreilleProp;
	}

	public void setGrosseurOreilleProp(DoubleProperty grosseurOreilleProp) {
		this.grosseurOreilleProp = grosseurOreilleProp;
	}

}
