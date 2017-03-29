package modele.phenotype;

import javafx.scene.paint.Color;

public enum EyeColor {

	BLUE("Bleu", Color.BLUE), GREEN("Vert", Color.GREEN), BROWN("Brun", Color.BROWN);

	private Color color = null;
	private String name = null;

	private EyeColor(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return this.name;
	}

	public Color getColor() {
		return this.color;
	}

	@Override
	public String toString() {
		return getName();
	}

}
