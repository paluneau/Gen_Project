package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Eye {

	public EyeColor color;
	public DoubleProperty distanceProperty = new SimpleDoubleProperty();
	public DoubleProperty heightProperty = new SimpleDoubleProperty();

	public Eye(EyeColor color, double distance, double height) {
		this.color = color;
		distanceProperty.set(distance);
		heightProperty.set(height);
	}

	public EyeColor getCouleurYeux() {
		return color;
	}

	public void setColor(EyeColor eyeColor) {
		this.color = eyeColor;
	}

	public double getDistance() {
		return this.distanceProperty.get();
	}

	public void setDistance(double distance) {
		this.distanceProperty.set(distance);
	}

	public DoubleProperty distanceProperty() {
		return this.distanceProperty;
	}

	public double getHeight() {
		return this.heightProperty.get();
	}

	public void setHeight(double height) {
		this.heightProperty.set(height);
	}

	public DoubleProperty heightProperty() {
		return this.heightProperty;
	}

}
