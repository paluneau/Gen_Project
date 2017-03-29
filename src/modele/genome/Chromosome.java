package modele.genome;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import exception.ConstructionException;
import utils.FastaSequenceReader;

public class Chromosome {

	public List<SNP> snips = null;
	private String dataSrcPath = null;
	private String name = null;
	private List<String> wntdSNPs = null;
	private File srcFile = null;
	private static File altSrcFile = null;

	public Chromosome(String name, List<String> targetSNP)
			throws ConstructionException, IOException, URISyntaxException {
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
	 * Cr�e le chemin d'acc�s pour atteindre le fichier li� au chromosome *
	 * Format: chr_*.fas
	 * 
	 * @return le chemin d'acc�s
	 */
	private String generatePath() {
		return "/chr_" + getName().toUpperCase() + ".fas";
	}

	public String getName() {
		return this.name;
	}

	/**
	 * Permet de charger les données des fichiers de SNP
	 * 
	 * @throws IOException
	 *             si le fichier est introuvable
	 * @throws URISyntaxException
	 *             si la syntaxe du path est invalide
	 */
	public void loadSNPs() throws IOException, URISyntaxException {

		FastaSequenceReader fsr = null;

		if (getClass().getResource("/fasta" + dataSrcPath) == null) {
			
			if (Chromosome.altSrcFile == null) {
				throw new FileNotFoundException("Fichier(s) introuvable(s). Sélectionnez un répertoire contenant tous les fichiers FASTA.");
			}else{
				fsr = new FastaSequenceReader(new File(Chromosome.altSrcFile + dataSrcPath), wntdSNPs);
			}
		
		} else {
			fsr = new FastaSequenceReader(new File(getClass().getResource("/fasta" + dataSrcPath).toURI()), wntdSNPs);
		}

		Map<String, String> sequences = fsr.getSequences();
		Iterator<String> keyIterator = sequences.keySet().iterator();

		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			snips.add(new SNP(key, sequences.get(key)));
		}

	}

	public SNP getSNPByRS(String rs) {
		SNP snpID = null;

		for (SNP snip : snips) {
			if (snip.getRS().equals(rs)) {
				snpID = snip;
			}
		}

		return snpID;
	}

	public static void setAltSrcFile(File newFile) {
		Chromosome.altSrcFile = newFile;
		;
	}
}
