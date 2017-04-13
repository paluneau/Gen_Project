package modele.phenotype;

import java.util.ArrayList;

public class BodyPart {

	private ArrayList<String> subParts = null;

	public BodyPart(String... groups) {
		this.subParts = new ArrayList<>();
		for (String gr : groups) {
			this.subParts.add(gr);
		}
	}

	public ArrayList<String> getSubParts() {
		return subParts;
	}
}
