package modele.phenotype;

import javafx.geometry.Point3D;
import modele.phenotype.data.EyeColor;
import modele.phenotype.data.HairColor;
import modele.phenotype.data.SkinColor;

public class Face {

	private BodyPart LEar = null;
	private BodyPart REar = null;
	private Eye LEye = null;
	private Eye REye = null;
	private Hair hair = null;
	private BodyPart mouth = null;
	private BodyPart nose = null;
	private Sourcils LSourcils = null;
	private Sourcils RSourcils = null;
	private SkinColor skinColor = null;
	private TransformationPoints pointsVisage = null;

	public Face(EyeColor eyeColor, SkinColor skinColor, HairColor hairColor) {
		LEar = new BodyPart("Oreille gauche");
		REar = new BodyPart("Oreille droite");
		LEye = new Eye(eyeColor, "Oeil gauche", "Blanc oeil gauche",
				"Noir oeil gauche", "Couleur oeil gauche");
		REye = new Eye(eyeColor, "Oeil droit", "Blanc oeil droit",
				"Noir oeil droit", "Couleur oeil droit");
		hair = new Hair(hairColor, "Cheveux");
		mouth = new BodyPart("Bouche");
		nose = new BodyPart("Bord narine", "Narine", "Nez");
		LSourcils = new Sourcils(hairColor, "Sourcil gauche");
		RSourcils = new Sourcils(hairColor, "Sourcil droit");
		this.skinColor = skinColor;
		pointsVisage = new TransformationPoints();
	}

	public BodyPart getLEar() {
		return LEar;
	}

	public void setLEar(BodyPart ear) {
		this.LEar = ear;
	}

	public BodyPart getREar() {
		return REar;
	}

	public void setREar(BodyPart ear) {
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
		pointsVisage.applyTranslation(LEye, new Point3D(-distance, 0, 0));
		pointsVisage.applyTranslation(REye, new Point3D(distance, 0, 0));
	}

	public void setPositionOreilles(float hauteur, float profondeur) {
		pointsVisage
				.applyTranslation(LEar, new Point3D(0, hauteur, profondeur));
		pointsVisage
				.applyTranslation(REar, new Point3D(0, hauteur, profondeur));
	}

	public void setPositionBouche(float hauteur) {
		pointsVisage.applyTranslation(mouth, new Point3D(0, hauteur, 0));
	}

	public void setPositionNez(float hauteur) {
		pointsVisage.applyTranslation(nose, new Point3D(0, hauteur, 0));
	}

	public void setPositionSourcils(float ecart) {
		pointsVisage.applyTranslation(LSourcils, new Point3D(-ecart, 0, 0));
		pointsVisage.applyTranslation(RSourcils, new Point3D(ecart, 0, 0));
	}

	public Hair getHair() {
		return hair;
	}

	public void setHair(Hair hair) {
		this.hair = hair;
	}

	public BodyPart getMouth() {
		return mouth;
	}

	public void setMouth(BodyPart mouth) {
		this.mouth = mouth;
	}

	public BodyPart getNose() {
		return nose;
	}

	public void setNose(BodyPart nose) {
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

	public SkinColor getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(SkinColor skinColor) {
		this.skinColor = skinColor;
	}

	public TransformationPoints getPointsVisage() {
		return pointsVisage;
	}
}
