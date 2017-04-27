package modele.phenotype;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import modele.phenotype.data.HairColor;

public class Sourcils extends BodyPart {

	private HairColor color = null;

	public Sourcils(HairColor color, String... groups) {
		this(color, null, groups);
	}

	public Sourcils(HairColor color, ArrayList<String> ignore, String... groups) {
		super(ignore, groups);
		setColor(color);

	}

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

	public HairColor getColor() {
		return this.color;
	}

	public void setColor(HairColor color) {
		this.color = color;
	}

}
