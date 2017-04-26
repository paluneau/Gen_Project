package modele.phenotype;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point3D;
import modele.phenotype.data.EyeColor;
import modele.phenotype.data.HairColor;
import modele.phenotype.data.SkinColor;

public class Face {

	private Ear LEar = null;
	private Ear REar = null;
	private Eye LEye = null;
	private Eye REye = null;
	private Hair hair = null;
	private Mouth mouth = null;
	private BodyPart nose = null;
	private BodyPart front = null;
	private BodyPart cheveux = null;
	private BodyPart LJoue = null;
	private BodyPart RJoue = null;
	private BodyPart menton = null;
	private BodyPart arche = null;
	private BodyPart LBosse = null;
	private BodyPart RBosse = null;
	private BodyPart pointe = null;
	private Sourcils LSourcils = null;
	private Sourcils RSourcils = null;
	private SkinColor skinColor = null;
	private TransformationPoints pointsVisage = null;

	public Face(EyeColor eyeColor, SkinColor skinColor, HairColor hairColor) {
		LEar = new Ear("Oreille gauche");
		REar = new Ear("Oreille droite");
		LEye = new Eye(eyeColor, "Oeil gauche", "Blanc oeil gauche", "Noir oeil gauche", "Couleur oeil gauche");
		REye = new Eye(eyeColor, "Oeil droit", "Blanc oeil droit", "Noir oeil droit", "Couleur oeil droit");
		// LEye = new Eye(eyeColor, "face1","face1n");
		// REye = new Eye(eyeColor, "face2");
		hair = new Hair(hairColor, "Cheveux");
		mouth = new Mouth("Bouche");
		nose = new BodyPart("Bord narine", "Narine", "Nez");
		front = new BodyPart("Front");
		menton = new BodyPart("Menton");
		cheveux = new BodyPart("Cheveux");
		LSourcils = new Sourcils(hairColor, "Sourcil gauche");
		RSourcils = new Sourcils(hairColor, "Sourcil droit");
		mouth = new Mouth("Bouche");
		menton = new BodyPart("Menton");
		instantiateOreilles();
		instantiateNez();
		instantiateJoue();

		this.skinColor = skinColor;
		pointsVisage = new TransformationPoints();
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

	// TODO ajouter les parties à ignorer dans les objets BodyPart
	// TODO mettre un attribut position sur la body part? pour éviter qu'il se
	// déplace toujours? jsp
	public void setEyeDistance(float distance) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Nez");
		// groupREM.add("face2");
		pointsVisage.applyTranslation(LEye, groupREM, new Point3D(-distance, 0, 0));
		pointsVisage.applyTranslation(REye, groupREM, new Point3D(distance, 0, 0));
	}

	public void setPositionOreilles(float hauteur, float profondeur) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Cheveux");
		pointsVisage.applyTranslation(LEar, groupREM, new Point3D(0, hauteur, -profondeur));
		pointsVisage.applyTranslation(REar, groupREM, new Point3D(0, hauteur, -profondeur));
	}

	public void setPositionBouche(float hauteur) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Nez");
		pointsVisage.applyTranslation(mouth, groupREM, new Point3D(0, hauteur, 0));
	}

	public void setPositionNez(float hauteur) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Oeil droit");
		groupREM.add("Oeil gauche");
		groupREM.add("Blanc oeil droit");
		groupREM.add("Blanc oeil gauche");
		groupREM.add("Sourcil droit");
		groupREM.add("Sourcil gauche");
		pointsVisage.applyTranslation(nose, groupREM, new Point3D(0, hauteur, 0));
	}

	public void setPositionSourcils(float ecart) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Oeil droit");
		groupREM.add("Oeil gauche");
		pointsVisage.applyTranslation(LSourcils, groupREM, new Point3D(-ecart, 0, -ecart / 2));
		pointsVisage.applyTranslation(RSourcils, groupREM, new Point3D(ecart, 0, -ecart / 2));
	}

	public void setGrosseurJoues(float ecart) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Nez");
		groupREM.add("Bouche");
		pointsVisage.applyTranslation(LJoue, groupREM, new Point3D(-ecart, 0, ecart / 3));
		pointsVisage.applyTranslation(RJoue, groupREM, new Point3D(ecart, 0, ecart / 3));
	}

	public void setPositionArche(float ecart) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Bouche");
		pointsVisage.applyTranslation(arche, groupREM, new Point3D(0, ecart / 2, ecart / 2));
	}

	public void setEcartNarine(float ecart) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Bouche");
		pointsVisage.applyTranslation(LBosse, groupREM, new Point3D(-ecart, 0, 0));
		pointsVisage.applyTranslation(RBosse, groupREM, new Point3D(ecart, 0, 0));
	}

	public void setLongueurPointe(float ecart) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Bouche");
		pointsVisage.applyTranslation(pointe, groupREM, new Point3D(0, 0, ecart));
		pointsVisage.applyTranslation(arche, groupREM, new Point3D(0, 0, ecart/2));
	}

	public void setPositionFront(float ecart) {
		List<String> groupREM = new ArrayList<String>();
		groupREM.add("Cheveux");
		pointsVisage.applyTranslation(front, groupREM, new Point3D(0, 0, ecart));
		pointsVisage.applyTranslation(LSourcils, groupREM, new Point3D(0, 0, ecart));
		pointsVisage.applyTranslation(RSourcils, groupREM, new Point3D(0, 0, ecart));
		pointsVisage.applyTranslation(LEye, groupREM, new Point3D(0, 0, ecart));
		pointsVisage.applyTranslation(REye, groupREM, new Point3D(0, 0, ecart));
	}
	
	public void setGrosseurCou(float grosseur){
		pointsVisage.applyGrossissement(new BodyPart("Cou"), null, grosseur);
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

	public void setLongueurFace(float distance) {
		List<String> groupREM = new ArrayList<String>();
		pointsVisage.applyTranslation(front, groupREM, new Point3D(0, distance, 0));
		pointsVisage.applyTranslation(cheveux, groupREM, new Point3D(0, distance, 0));
		pointsVisage.applyTranslation(menton, groupREM, new Point3D(0, -distance, 0));

	}

	public void setProeminenceSourcils(float distance) {
		List<String> groupREM = new ArrayList<String>();
		pointsVisage.applyTranslation(LSourcils, groupREM, new Point3D(0, 0, distance));
		pointsVisage.applyTranslation(RSourcils, groupREM, new Point3D(0, 0, distance));
	}

	public void setProeminenceMenton(float distance) {
		List<String> groupREM = new ArrayList<String>();
		pointsVisage.applyTranslation(menton, groupREM, new Point3D(0, -(distance * 0.25), distance));
		pointsVisage.applyTranslation(mouth, groupREM, new Point3D(0, -(distance * 0.25), distance));
	}

	public TransformationPoints getPointsVisage() {
		return pointsVisage;
	}

	private void instantiateOreilles() {
		LEar = new Ear("Oreille gauche");
		REar = new Ear("Oreille droite");

	}

	private void instantiateNez() {
		arche = new BodyPart("Arche");
		LBosse = new BodyPart("Bosse gauche");
		RBosse = new BodyPart("Bosse droite");
		pointe = new BodyPart("Pointe");
		nose = new BodyPart(arche, LBosse, RBosse, pointe);
		nose.getSubParts().add("Nez");
		nose.getSubParts().add("Narine");
		nose.getSubParts().add("Bord narine");

	}

	private void instantiateJoue() {
		LJoue = new BodyPart("Joue gauche");
		RJoue = new BodyPart("Joue droite");
	}

}
