package modele.phenotype;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Face {

	private Ear ear = null;
	private Eye eye = null;
	private Hair hair = null;
	private Mouth mouth = null;
	private Nose nose = null;
	private Sourcils sourcils = null;

	public double hauteurVisage;
	public DoubleProperty hauteurVisageProp = new SimpleDoubleProperty();

	public double largeurVisage;
	public DoubleProperty largeurVisageProp = new SimpleDoubleProperty();

	public Ear getEar() {
		return ear;
	}

	public void setEar(Ear ear) {
		this.ear = ear;
	}

	public Eye getEye() {
		return eye;
	}

	public void setEye(Eye eye) {
		this.eye = eye;
	}

	public Hair getHair() {
		return hair;
	}

	public void setHair(Hair hair) {
		this.hair = hair;
	}

	public Mouth getMouth() {
		return mouth;
	}

	public void setMouth(Mouth mouth) {
		this.mouth = mouth;
	}

	public Nose getNose() {
		return nose;
	}

	public void setNose(Nose nose) {
		this.nose = nose;
	}

	public Sourcils getSourcils() {
		return sourcils;
	}

	public void setSourcils(Sourcils sourcils) {
		this.sourcils = sourcils;
	}

	public double getHauteurVisage() {
		return hauteurVisage;
	}

	public void setHauteurVisage(double hauteurVisage) {
		this.hauteurVisage = hauteurVisage;
	}

	public DoubleProperty getHauteurVisageProp() {
		return hauteurVisageProp;
	}

	public void setHauteurVisageProp(DoubleProperty hauteurVisageProp) {
		this.hauteurVisageProp = hauteurVisageProp;
	}

	public double getLargeurVisage() {
		return largeurVisage;
	}

	public void setLargeurVisage(double largeurVisage) {
		this.largeurVisage = largeurVisage;
	}

	public DoubleProperty getLargeurVisageProp() {
		return largeurVisageProp;
	}

	public void setLargeurVisageProp(DoubleProperty largeurVisageProp) {
		this.largeurVisageProp = largeurVisageProp;
	}

}
