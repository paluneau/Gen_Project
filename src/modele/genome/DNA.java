package modele.genome;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import exception.ConstructionException;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import modele.genome.data.TargetSNPs;

public class DNA {

	private Set<String> chrSymbols = null;
	private List<Chromosome> pair1 = null;
	private List<Chromosome> pair2 = null;
	private DoubleProperty readingProgressProperty = null;

	public DNA(Set<String> chrSymbols) throws ConstructionException, IOException, URISyntaxException {
		if (!chrSymbols.isEmpty()) {
			this.chrSymbols = chrSymbols;
			pair1 = new ArrayList<>();
			pair2 = new ArrayList<>();
			this.readingProgressProperty = new SimpleDoubleProperty(0);
			createChr();
		} else {
			throw new ConstructionException("Aucun SNP ciblé.");
		}

	}

	public Set<String> getChrSymbols() {
		return chrSymbols;
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
	 * Instancie les chromosomes selon la liste de symboles des chromosomes
	 *
	 * @throws ConstructionException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	private void createChr() throws ConstructionException, IOException, URISyntaxException {
		for (String sym : chrSymbols) {
			pair1.add(new Chromosome(sym, targetIDByChr(sym)));
			pair2.add(new Chromosome(sym, targetIDByChr(sym)));
			setReadingProgress(getReadingProgress() + (1.0 / chrSymbols.size()));
		}
	}

	/**
	 * Retourne un couple (tableau statique) de chromosome selon son symbole
	 *
	 * @return la liste de chaque paire de chromosome
	 */
	public Chromosome[] getChrPair(String symbol) {
		Chromosome[] pair = new Chromosome[2];
		int size = pair1.size();
		boolean find = false;

		for (int i = 0; i < size && !find; i++) {
			if (pair1.get(i).getName().equals(symbol)) {
				pair[0] = pair1.get(i);
				pair[1] = pair2.get(i);
				find = true;
			}
		}

		return pair;
	}

	private void computeProgress() {

	}

	public DoubleProperty readingProgressProperty() {
		return readingProgressProperty;
	}

	public double getReadingProgress() {
		return readingProgressProperty.get();
	}

	public void setReadingProgress(double val) {
		this.readingProgressProperty.set(val);
	}

}
