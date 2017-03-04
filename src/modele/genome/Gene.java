package modele.genome;

import java.util.List;
//TODO Peut être à éliminer entièrement...(attendre un peu)
public class Gene {
	private String name = "";
	private static List<String> wntdSnps = null;
	private List<SNP> snips = null;

	public Gene(String name, List<String> target) {
		this.name = name;
		this.wntdSnps = target;
	}

}
