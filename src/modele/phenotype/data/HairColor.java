package modele.phenotype.data;

import java.util.Map;

import javafx.scene.paint.Color;
import modele.genome.data.Allele;
import modele.genome.data.TargetSNPs;
import utils.Mapable;
import modele.genome.data.BlondHairGenes;
import modele.genome.data.BrownHairGenes;
import modele.genome.data.BlackHairGenes;
import modele.genome.data.RedHairGenes;

public enum HairColor {

	BLOND(Color.YELLOW, "Blond", Mapable.valuesAsMap(BlondHairGenes::values)), BROWN(Color.BROWN, "Brun",
			Mapable.valuesAsMap(BrownHairGenes::values)), BLACK(Color.BLACK, "Noir",
					Mapable.valuesAsMap(BlackHairGenes::values)), RED(Color.RED, "Roux",
							Mapable.valuesAsMap(RedHairGenes::values));

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

	@Override
	public String toString() {
		return this.getName();
	}

}
