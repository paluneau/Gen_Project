package modele.phenotype.data;

import java.util.Map;

import javafx.scene.paint.Color;
import modele.genome.data.Allele;
import modele.genome.data.TargetSNPs;
import utils.Mappable;
import modele.genome.data.BlondHairGenes;
import modele.genome.data.BrownHairGenes;
import modele.genome.data.BlackHairGenes;
import modele.genome.data.RedHairGenes;

public enum HairColor {

	BLOND(new Color(216.0/255, 190.0/255, 120.0/255, 1), "Blond", Mappable.valuesAsMap(BlondHairGenes::values)), BROWN(Color.SADDLEBROWN, "Brun",
			Mappable.valuesAsMap(BrownHairGenes::values)), BLACK(Color.BLACK, "Noir",
					Mappable.valuesAsMap(BlackHairGenes::values)), RED(new Color(217.0/255, 62.0/255, 20.0/255, 1), "Roux",
							Mappable.valuesAsMap(RedHairGenes::values));

	Map<TargetSNPs, Allele[]> genes = null;
	Color color = null;
	String name = null;

	private HairColor(Color color, String name, Map<TargetSNPs, Allele[]> genes) {
		this.color = color;
		this.genes = genes;
		this.name = name;
	}

	public Color getColor() {
		return this.color;
	}

	public String getName() {
		return this.name;
	}

	public Map<TargetSNPs, Allele[]> getGenes() {
		return this.genes;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
