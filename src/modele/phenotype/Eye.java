package modele.phenotype;

public class Eye {

	public EyeColor color;
	public float distance = 0;
	public float height = 0;

	public Eye(EyeColor color, float distance, float height) {
		this.color = color;
		setDistance(distance);
		setHeight(height);
	}

	public EyeColor getCouleurYeux() {
		return color;
	}

	public void setColor(EyeColor eyeColor) {
		this.color = eyeColor;
	}

	public float getDistance() {
		return this.distance;
	}

	public void setDistance(float distance) {
		this.distance = distance;
	}

	public float getHeight() {
		return this.height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

}
