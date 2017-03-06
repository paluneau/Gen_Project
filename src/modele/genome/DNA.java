package modele.genome;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import exception.ConstructionException;

public class DNA {

	public static final String[] CHR_SYMBOLS = { "15" };
	private List<Chromosome> pair1 = null;
	private List<Chromosome> pair2 = null;

	public DNA() throws ConstructionException, IOException, URISyntaxException {
		pair1 = new ArrayList<>();
		pair2 = new ArrayList<>();
		createChr();

	}

	/**
	 * Retourne la liste des identifiants de SNPs selon le chromosome dans
	 * lequel ils se situent
	 * 
	 * @param chrNbr
	 *            le numero du chromosome
	 * @return la liste des identifiants des SNPs
	 */
	private List<String> targetIDByChr(String chrNbr) {
		TargetSNPs[] tgt = TargetSNPs.values();
		List<String> rsID = new LinkedList<String>();

		for (TargetSNPs t : tgt) {
			if (t.getChromosomeNbr().equals(chrNbr)) {
				rsID.add(t.getId());
			}
		}

		return rsID;
	}

	/**
	 * Instancie les chromosomes selon la liste de caractères
	 * 
	 * @throws ConstructionException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private void createChr() throws ConstructionException, IOException, URISyntaxException {
		for (String sym : CHR_SYMBOLS) {
			pair1.add(new Chromosome(sym, targetIDByChr(sym)));
			pair2.add(new Chromosome(sym, targetIDByChr(sym)));
		}

	}

	/**
	 * Regroupe les chromosomes par paire (ex. les deux chromosomes no1
	 * ensemble, les deux chromosomes no2 ensemble, etc)
	 * 
	 * @return la liste de chaque paire de chromosome
	 */
	public List<Chromosome[]> getChrPairs() {
		List<Chromosome[]> listPair = new ArrayList<>();
		int size = pair1.size();

		for (int i = 0; i < size; i++) {
			Chromosome[] couple = { pair1.get(i), pair2.get(i) };
			listPair.add(couple);
		}

		return listPair;
	}

}
