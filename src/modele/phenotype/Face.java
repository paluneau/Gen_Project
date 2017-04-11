package modele.phenotype;

import javafx.geometry.Point3D;
import modele.phenotype.data.EyeColor;
import modele.phenotype.data.HairColor;
import modele.phenotype.data.SkinColor;

//TODO est-ce qu'on a besoin de toutes ces classes métiers là ?
public class Face {

	private Ear LEar = null;
	private Ear REar = null;
	private Eye LEye = null;
	private Eye REye = null;
	private Hair hair = null;
	private Mouth mouth = null;
	private Nose nose = null;
	private Sourcils LSourcils = null;
	private Sourcils RSourcils = null;
	private SkinColor skinColor = null;
	private Points pointsVisage = null;

	private double hauteurVisage = 0;
	private double largeurVisage = 0;
	private float distanceYeux = 0;

	public Face(EyeColor eyeColor, SkinColor skinColor, HairColor hairColor) {
		LEar = new Ear();
		REar = new Ear();
		LEye = new Eye(eyeColor, "Oeil gauche", "Blanc oeil gauche", "Noir oeil gauche", "Couleur oeil gauche");
		REye = new Eye(eyeColor, "Oeil droit", "Blanc oeil droit", "Noir oeil droit", "Couleur oeil droit");
		hair = new Hair(hairColor);
		mouth = new Mouth();
		nose = new Nose();
		LSourcils = new Sourcils();
		RSourcils = new Sourcils();
		this.skinColor = skinColor;
		pointsVisage = new Points();
	}

	public Ear getLEar() {
		return LEar;
	}

	public void setLEar(Ear ear) {
		this.LEar = ear;
	}

	public Ear getREar() {
		return REar;
	}

	public void setREar(Ear ear) {
		this.REar = ear;
	}

	public Eye getLEye() {
		return LEye;
	}

	public void setLEye(Eye eye) {
		this.LEye = eye;
	}

	public Eye getREye() {
		return REye;
	}

	public void setREye(Eye eye) {
		this.REye = eye;
	}

	public void setEyeDistance(float distance) {
		this.distanceYeux = distance;
		pointsVisage.applyTranslation(LEye, new Point3D(-distance, 0, 0));
		pointsVisage.applyTranslation(REye, new Point3D(distance, 0, 0));
	}

	public void setPositionOreilles(float hauteur, float profondeur) {
		pointsVisage.updatePositionOreille(hauteur, profondeur);
	}

	public void setPositionBouche(float hauteur) {
		pointsVisage.updateBouche(hauteur);
	}

	public void setPositionNez(float hauteur) {
		pointsVisage.updateNez(hauteur);
	}

	public void setPositionSourcils(float ecart) {
		pointsVisage.updateSourcils(ecart);
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

	public Sourcils getLSourcils() {
		return LSourcils;
	}

	public void setLSourcils(Sourcils sourcils) {
		this.LSourcils = sourcils;
	}

	public Sourcils getRSourcils() {
		return RSourcils;
	}

	public void setRSourcils(Sourcils sourcils) {
		this.RSourcils = sourcils;
	}

	public double getHauteurVisage() {
		return hauteurVisage;
	}

	public void setHauteurVisage(double hauteurVisage) {
		this.hauteurVisage = hauteurVisage;
	}

	public double getLargeurVisage() {
		return largeurVisage;
	}

	public void setLargeurVisage(double largeurVisage) {
		this.largeurVisage = largeurVisage;
	}

	public SkinColor getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(SkinColor skinColor) {
		this.skinColor = skinColor;
	}

	public Points getPointsVisage() {
		return pointsVisage;
	}
}
