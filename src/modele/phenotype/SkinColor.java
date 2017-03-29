package modele.phenotype;

import javafx.scene.paint.Color;

public enum SkinColor {

	LIGHT("Pâle", Color.BLANCHEDALMOND), MEDIUM("Medium", Color.BURLYWOOD), DARK("Foncée", Color.SIENNA);

	private Color color = null;
	private String name = null;

	private SkinColor(String name, Color _col) {
		this.color = _col;
		this.name = name;
	}

	public Color getColor() {
		return this.color;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
