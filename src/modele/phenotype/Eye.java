package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Eye {

	public enum enumCouleurYeux {
		BLEU, VERT, BRUN
	};

	public enumCouleurYeux couleurYeux;

	public double distanceYeux;
	public DoubleProperty distanceYeuxProp = new SimpleDoubleProperty();

	public double hauteurYeux;
	public DoubleProperty hauteurYeuxProp = new SimpleDoubleProperty();

	public double ecartYeux;
	public DoubleProperty ecartYeuxProp = new SimpleDoubleProperty();

	public enumCouleurYeux getCouleurYeux() {
		return couleurYeux;
	}

	public void setCouleurYeux(enumCouleurYeux couleurYeux) {
		this.couleurYeux = couleurYeux;
	}

	public double getDistanceYeux() {
		return distanceYeux;
	}

	public void setDistanceYeux(double distanceYeux) {
		this.distanceYeux = distanceYeux;
	}

	public DoubleProperty getDistanceYeuxProp() {
		return distanceYeuxProp;
	}

	public void setDistanceYeuxProp(DoubleProperty distanceYeuxProp) {
		this.distanceYeuxProp = distanceYeuxProp;
	}

	public double getHauteurYeux() {
		return hauteurYeux;
	}

	public void setHauteurYeux(double hauteurYeux) {
		this.hauteurYeux = hauteurYeux;
	}

	public DoubleProperty getHauteurYeuxProp() {
		return hauteurYeuxProp;
	}

	public void setHauteurYeuxProp(DoubleProperty hauteurYeuxProp) {
		this.hauteurYeuxProp = hauteurYeuxProp;
	}

	public double getEcartYeux() {
		return ecartYeux;
	}

	public void setEcartYeux(double ecartYeux) {
		this.ecartYeux = ecartYeux;
	}

	public DoubleProperty getEcartYeuxProp() {
		return ecartYeuxProp;
	}

	public void setEcartYeuxProp(DoubleProperty ecartYeuxProp) {
		this.ecartYeuxProp = ecartYeuxProp;
	}

}
