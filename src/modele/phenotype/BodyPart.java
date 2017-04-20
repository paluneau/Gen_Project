package modele.phenotype;

import java.util.ArrayList;

public class BodyPart {

	private ArrayList<String> subParts = null;
	private ArrayList<String> ignore = null;

	public BodyPart(String... groups) {
		this.subParts = new ArrayList<>();
		this.ignore = new ArrayList<>();
		for (String gr : groups) {
			this.subParts.add(gr);
		}
	}

	public BodyPart(BodyPart... groups) {
		this.subParts = new ArrayList<>();
		this.ignore = new ArrayList<>();
		for (BodyPart gr : groups) {
			for (String subgr : gr.getSubParts())
				this.subParts.add(subgr);
		}
	}

	public ArrayList<String> getSubParts() {
		return subParts;
	}

	public void setIgnore(String... ign) {
		this.ignore.clear();
		for (String gr : ign) {
			this.ignore.add(gr);
		}

	}

	public ArrayList<String> getIgnore() {
		return ignore;
	}
}
