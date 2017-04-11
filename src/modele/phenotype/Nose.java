package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Nose extends BodyPart {

	public Nose(String... groups) {
		super(groups);
	}

	public double hauteurNez;
	public DoubleProperty hauteurNezProp = new SimpleDoubleProperty();

	public double grosseurNez;
	public DoubleProperty grosseurNezProp = new SimpleDoubleProperty();

	public double getHauteurNez() {
		return hauteurNez;
	}

	public void setHauteurNez(double hauteurNez) {
		this.hauteurNez = hauteurNez;
	}

	public DoubleProperty getHauteurNezProp() {
		return hauteurNezProp;
	}

	public void setHauteurNezProp(DoubleProperty hauteurNezProp) {
		this.hauteurNezProp = hauteurNezProp;
	}

	public double getGrosseurNez() {
		return grosseurNez;
	}

	public void setGrosseurNez(double grosseurNez) {
		this.grosseurNez = grosseurNez;
	}

	public DoubleProperty getGrosseurNezProp() {
		return grosseurNezProp;
	}

	public void setGrosseurNezProp(DoubleProperty grosseurNezProp) {
		this.grosseurNezProp = grosseurNezProp;
	}

}
