package modele.phenotype;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import modele.genome.Allele;
import modele.genome.BlueEyesGenes;
import modele.genome.BrownEyesGenes;
import modele.genome.GreenEyesGenes;
import modele.genome.TargetSNPs;
import utils.Mapable;

public class Eye {

	private static final Map<TargetSNPs, Allele[]> BLUEEYES = Mapable.valuesAsMap(BlueEyesGenes::values);
	private static final Map<TargetSNPs, Allele[]> BROWNEYES = Mapable.valuesAsMap(BrownEyesGenes::values);
	private static final Map<TargetSNPs, Allele[]> GREENEYES = Mapable.valuesAsMap(GreenEyesGenes::values);

	private EyeColor color;
	private float largeur = 0;
	private float height = 0;
	private ObservableFloatArray iniPoints = null;
	private ObservableFloatArray pointsUpdater = null;

	public Eye(EyeColor color, float largeur, float height) {
		this.color = color;
		setLargeur(largeur);
		setHeight(height);
		iniPoints = FXCollections.observableFloatArray();
		pointsUpdater = FXCollections.observableFloatArray();
	}

	public EyeColor getCouleurYeux() {
		return color;
	}

	public void setColor(EyeColor eyeColor) {
		this.color = eyeColor;
	}

	public float getLargeur() {
		return this.largeur;
	}

	public void setLargeur(float distance) {
		this.largeur = distance;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setIniPoints(ObservableFloatArray iniPoints, ObservableFloatArray pointsUpdater) {
		this.iniPoints = iniPoints;
		this.pointsUpdater = pointsUpdater;
	}

	public ObservableFloatArray getPointsUpdater() {
		return pointsUpdater;
	}

	public void updateDistanceNez(float distance) {
		for (int i = 0; i < pointsUpdater.size() / 3; i++) {
			pointsUpdater.set(2 + (3 * i), iniPoints.get(2 + (3 * i)) + distance);
		}
	}
	

}
