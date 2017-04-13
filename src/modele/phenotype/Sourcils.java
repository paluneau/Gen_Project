package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import modele.phenotype.data.HairColor;

public class Sourcils extends BodyPart {

	private HairColor color = null;

	public Sourcils(HairColor color, String... groups){
		super(groups);
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
	
	public HairColor getColor(){
		return this.color;
	}
	
	public void setColor(HairColor color){
		this.color = color;
	}

}
