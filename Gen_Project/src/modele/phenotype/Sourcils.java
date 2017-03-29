package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Sourcils {

	public double hauteurSourcils;
	public DoubleProperty hauteurSourcilsProp = new SimpleDoubleProperty();

	public double distanceSourcils;
	public DoubleProperty distanceSourcilsProp = new SimpleDoubleProperty();

	public double getHauteurSourcils() {
		return hauteurSourcils;
	}

	public void setHauteurSourcils(double hauteurSourcils) {
		this.hauteurSourcils = hauteurSourcils;
	}

	public DoubleProperty getHauteurSourcilsProp() {
		return hauteurSourcilsProp;
	}

	public void setHauteurSourcilsProp(DoubleProperty hauteurSourcilsProp) {
		this.hauteurSourcilsProp = hauteurSourcilsProp;
	}

	public double getDistanceSourcils() {
		return distanceSourcils;
	}

	public void setDistanceSourcils(double distanceSourcils) {
		this.distanceSourcils = distanceSourcils;
	}

	public DoubleProperty getDistanceSourcilsProp() {
		return distanceSourcilsProp;
	}

	public void setDistanceSourcilsProp(DoubleProperty distanceSourcilsProp) {
		this.distanceSourcilsProp = distanceSourcilsProp;
	}

}
