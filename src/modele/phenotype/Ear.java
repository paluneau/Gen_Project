package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Ear extends BodyPart {

	public Ear(String... groups) {
		super(groups);
	}

	public DoubleProperty heightProperty = new SimpleDoubleProperty();

	public DoubleProperty sizeProperty = new SimpleDoubleProperty();

	public double getHeight() {
		return this.heightProperty.get();
	}

	public void setHauteurOreille(double height) {
		this.heightProperty.set(height);
	}

	public DoubleProperty heightProperty() {
		return this.heightProperty;
	}

	public double getSize() {
		return this.sizeProperty.get();
	}

	public void setSize(double size) {
		this.sizeProperty.set(size);
	}

	public DoubleProperty sizeProperty() {
		return this.sizeProperty;
	}

}
