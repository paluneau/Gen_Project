package modele.genome;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exception.ConstructionException;
import utils.FastaSequenceReader;

public class Chromosome {

	private List<SNP> snips = null;
	private String dataSrcPath = null;
	private String name = null;
	//TODO CHANGER POUR UNE LISTE DE TargetSNPs
	private List<String> wntdSNPs = null;

	public Chromosome(String name, List<String> targetSNP) throws ConstructionException, IOException, URISyntaxException {
		if (name != null && targetSNP != null) {
			this.name = name;
			this.wntdSNPs = targetSNP;
			this.dataSrcPath = generatePath();
			this.snips = new ArrayList<>();
			loadSNPs();
		} else {
			throw new ConstructionException("CHROMOSOME INVALIDE");
		}
	}

	/**
	 * Crée le chemin d'accès pour atteindre le fichier lié au chromosome *
	 * Format: chr_*.fas
	 * 
	 * @return le chemin d'accès
	 */
	private String generatePath() {
		return "/fasta/chr_" + getName().toUpperCase() + ".fas";
	}

	public String getName() {
		return this.name;
	}

	public void loadSNPs() throws IOException, URISyntaxException {
		FastaSequenceReader fsr = new FastaSequenceReader(new File(getClass().getResource(dataSrcPath).toURI()),
				wntdSNPs);
		Map<String, String> sequences = fsr.getSequences();
		Iterator<String> keyIterator = sequences.keySet().iterator();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			snips.add(new SNP(key, sequences.get(key)));
		}
	}

}
