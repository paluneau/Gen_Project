package modele.phenotype;

import java.util.ArrayList;

public class BodyPart {

	private ArrayList<String> subParts = null;
	private ArrayList<String> ignore = null;

	public BodyPart(String... groups) {
		this(null, groups);
	}
	
	public BodyPart(BodyPart... groups) {
		this(null, groups);
	}

	public BodyPart(ArrayList<String> ignore, String... groups) {
		this.subParts = new ArrayList<>();
		this.ignore = new ArrayList<>();
		for (String gr : groups) {
			this.subParts.add(gr);
		}
		this.ignore = ignore;
	}

	public BodyPart(ArrayList<String> ignore, BodyPart... groups) {
		this.subParts = new ArrayList<>();
		this.ignore = new ArrayList<>();
		for (BodyPart gr : groups) {
			for (String subgr : gr.getSubParts())
				this.subParts.add(subgr);
			this.ignore.addAll(gr.getIgnore());
		}
		this.ignore.addAll(ignore);
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
