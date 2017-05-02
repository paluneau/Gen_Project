package modele.phenotype;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point3D;
import modele.phenotype.data.EyeColor;
import modele.phenotype.data.HairColor;
import modele.phenotype.data.SkinColor;
import utils.MapTools;

public class Face {

	private List<BodyPart> parts = new ArrayList<BodyPart>();
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
		ArrayList<String> groupREM = new ArrayList<String>();
		groupREM.add("Cheveux");
		LEar = new Ear(groupREM, "Oreille gauche");
		REar = new Ear(groupREM, "Oreille droite");
		LEye = new Eye(eyeColor, groupREM, "Blanc oeil gauche", "Noir oeil gauche", "Couleur oeil gauche");
		REye = new Eye(eyeColor, groupREM, "Blanc oeil droit", "Noir oeil droit", "Couleur oeil droit");
		front = new BodyPart(groupREM, "Front");
		groupREM.add("Oeil droit");
		groupREM.add("Oeil gauche");
		LSourcils = new Sourcils(hairColor, groupREM, "Sourcil gauche");
		RSourcils = new Sourcils(hairColor, groupREM, "Sourcil droit");
		groupREM.clear();
		hair = new Hair(hairColor, groupREM, "Cheveux");
		mouth = new Mouth(groupREM, "Bouche");
		nose = new BodyPart(groupREM, "Bord narine", "Narine", "Nez");
		menton = new BodyPart(groupREM, "Menton");
		cheveux = new BodyPart(groupREM, "Cheveux");
		menton = new BodyPart(groupREM, "Menton");
		groupREM.add("Nez");
		mouth = new Mouth(groupREM, "Bouche");
		instantiateOreilles();
		instantiateNez();
		instantiateJoue();
		this.skinColor = skinColor;
		pointsVisage = new TransformationPoints();
		addToArray();
	}

	private void addToArray() {
		parts.add(LEar);
		parts.add(REar);
		parts.add(LEye);
		parts.add(REye);
		parts.add(hair);
		parts.add(mouth);
		parts.add(nose);
		parts.add(front);
		parts.add(cheveux);
		parts.add(LJoue);
		parts.add(RJoue);
		parts.add(menton);
		parts.add(arche);
		parts.add(LBosse);
		parts.add(RBosse);
		parts.add(pointe);
		parts.add(LSourcils);
		parts.add(RSourcils);
	}

	public BodyPart findBodyPart(String part) {
		BodyPart out = null;
		for (BodyPart e : parts) {
			for (String f : e.getSubParts()) {
				if (f.equals(part)) {
					out = e;
				}
			}
		}
		return out;

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

	// TODO mettre un attribut position sur la body part? pour éviter qu'il se
	// déplace toujours? jsp
	public void setEyeDistance(float distance) {
		pointsVisage.applyTranslation(LEye, new Point3D(-distance, 0, 0));
		pointsVisage.applyTranslation(REye, new Point3D(distance, 0, 0));
	}

	public void setPositionOreilles(float hauteur, float profondeur) {
		pointsVisage.applyTranslation(LEar, new Point3D(0, hauteur, -profondeur));
		pointsVisage.applyTranslation(REar, new Point3D(0, hauteur, -profondeur));
	}

	public void setPositionBouche(float hauteur) {
		pointsVisage.applyTranslation(mouth, new Point3D(0, hauteur, 0));
	}

	public void setPositionNez(float hauteur) {
		/*
		 * for (String e : nose.getSubParts()) { pointsVisage.addIni3DPoints(e,
		 * MapTools.createAndConvertArray(pointsVisage.getPointsUpdater(e))); }
		 */
		pointsVisage.applyTranslation(nose, new Point3D(0, hauteur, 0));
	}

	public void setPositionSourcils(float ecart) {
		pointsVisage.applyTranslation(LSourcils, new Point3D(-ecart, 0, -ecart / 2));
		pointsVisage.applyTranslation(RSourcils, new Point3D(ecart, 0, -ecart / 2));
	}

	public void setGrosseurJoues(float ecart) {
		pointsVisage.applyTranslation(LJoue, new Point3D(-ecart, 0, ecart / 3));
		pointsVisage.applyTranslation(RJoue, new Point3D(ecart, 0, ecart / 3));
	}

	public void setPositionArche(float ecart) {
		pointsVisage.applyTranslation(arche, new Point3D(0, ecart / 2, ecart / 2));
	}

	public void setEcartNarine(float ecart) {
		pointsVisage.applyTranslation(LBosse, new Point3D(-ecart, 0, 0));
		pointsVisage.applyTranslation(RBosse, new Point3D(ecart, 0, 0));
	}

	public void setLongueurPointe(float ecart) {
		pointsVisage.applyTranslation(pointe, new Point3D(0, 0, ecart));
	}

	public void setPositionFront(float ecart) {
		pointsVisage.applyTranslation(front, new Point3D(0, 0, ecart));
		pointsVisage.applyTranslation(LSourcils, new Point3D(0, 0, ecart / 2));
		pointsVisage.applyTranslation(RSourcils, new Point3D(0, 0, ecart / 2));
		pointsVisage.applyTranslation(LEye, new Point3D(0, 0, ecart / 2));
		pointsVisage.applyTranslation(REye, new Point3D(0, 0, ecart / 2));
		pointsVisage.applyTranslation(LJoue, new Point3D(0, 0, ecart / 3));
		pointsVisage.applyTranslation(RJoue, new Point3D(0, 0, ecart / 3));
	}

	public void setGrosseurCou(float grosseur) {
		ArrayList<String> groupREM = new ArrayList<>();
		groupREM.add("Cheveux");
		pointsVisage.applyGrossissement(new BodyPart(groupREM, "Cou"), groupREM, grosseur);
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
		pointsVisage.applyTranslation(front, new Point3D(0, distance, 0));
		pointsVisage.applyTranslation(cheveux, new Point3D(0, distance, 0));
		pointsVisage.applyTranslation(menton, new Point3D(0, -distance, 0));

	}

	public void setProeminenceSourcils(float distance) {
		pointsVisage.applyTranslation(LSourcils, new Point3D(0, 0, distance));
		pointsVisage.applyTranslation(RSourcils, new Point3D(0, 0, distance));
	}

	public void setProeminenceMenton(float distance) {
		pointsVisage.applyTranslation(menton, new Point3D(0, -(distance * 0.25), distance));
		pointsVisage.applyTranslation(mouth, new Point3D(0, -(distance * 0.25), distance));
	}

	public TransformationPoints getPointsVisage() {
		return pointsVisage;
	}

	private void instantiateOreilles() {
		LEar = new Ear("Oreille gauche");
		REar = new Ear("Oreille droite");
	}

	private void instantiateNez() {
		ArrayList<String> groupREM = new ArrayList<String>();
		groupREM.add("Bouche");
		arche = new BodyPart(groupREM, "Arche");
		LBosse = new BodyPart(groupREM, "Bosse gauche");
		RBosse = new BodyPart(groupREM, "Bosse droite");
		pointe = new BodyPart(groupREM, "Pointe");
		groupREM.clear();
		nose = new BodyPart(groupREM, arche, LBosse, RBosse, pointe);
		nose.getSubParts().add("Nez");
		nose.getSubParts().add("Narine");
		nose.getSubParts().add("Bord narine");
		nose.setIgnore("Oeil droit", "Oeil gauche", "Blanc oeil droit", "Blanc oeil gauche", "Sourcil droit",
				"Sourcil gauche");
	}

	private void instantiateJoue() {
		ArrayList<String> groupREM = new ArrayList<String>();
		groupREM.add("Cheveux");
		groupREM.add("Nez");
		groupREM.add("Bouche");
		LJoue = new BodyPart(groupREM, "Joue gauche");
		RJoue = new BodyPart(groupREM, "Joue droite");
	}

}
