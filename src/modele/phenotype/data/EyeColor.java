package modele.phenotype.data;

import java.util.Map;

import javafx.scene.paint.Color;
import modele.genome.data.Allele;
import modele.genome.data.BlueEyesGenes;
import modele.genome.data.BrownEyesGenes;
import modele.genome.data.GreenEyesGenes;
import modele.genome.data.TargetSNPs;
import utils.Mapable;

public enum EyeColor {

	BLUE("Bleu", Color.ROYALBLUE, Mapable.valuesAsMap(BlueEyesGenes::values)), GREEN("Vert", Color.FORESTGREEN,
			Mapable.valuesAsMap(GreenEyesGenes::values)), BROWN("Brun", Color.SIENNA,
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
