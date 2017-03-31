package modele.phenotype;

import java.util.Map;

import javafx.scene.paint.Color;
import modele.genome.Allele;
import modele.genome.BlueEyesGenes;
import modele.genome.TargetSNPs;
import modele.genome.GreenEyesGenes;
import modele.genome.BrownEyesGenes;
import utils.Mapable;

public enum EyeColor {

	BLUE("Bleu", Color.BLUE, Mapable.valuesAsMap(BlueEyesGenes::values)), GREEN("Vert", Color.GREEN,
			Mapable.valuesAsMap(GreenEyesGenes::values)), BROWN("Brun", Color.BROWN,
					Mapable.valuesAsMap(BrownEyesGenes::values));

	private Color color = null;
	private String name = null;
	private Map<TargetSNPs, Allele[]> genes = null;

	private EyeColor(String name, Color color, Map<TargetSNPs, Allele[]> genes) {
		this.name = name;
		this.color = color;
		this.genes = genes;
	}

	public String getName() {
		return this.name;
	}

	public Color getColor() {
		return this.color;
	}

	public Map<TargetSNPs, Allele[]> getGenes() {
		return this.genes;
	}

	@Override
	public String toString() {
		return getName();
	}

}
