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

/**
 * Classe représentant un chromosome, elle va lire dans les fichiers FASTA par
 * chromosome pour ensuite affecter les séquences aux bons SNPs
 * 
 * @author Les génies du génome
 *
 */
public class Chromosome {

	// TODO vérifier si on ne peut pas changer snips en map...
	private List<SNP> snips = null;
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

	public List<SNP> getSnips() {
		return this.snips;
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
				throw new FileNotFoundException(
						"Fichier(s) introuvable(s). Sélectionnez un répertoire contenant tous les fichiers FASTA.");
			} else {
				fsr = new FastaSequenceReader(new File(Chromosome.altSrcFile + dataSrcPath), wntdSNPs);
			}
		} else {
			fsr = new FastaSequenceReader(new File(getClass().getResource("/fasta" + dataSrcPath).toURI()), wntdSNPs);
		}

		Map<String, String> sequences = fsr.getSequences();

		sequences.forEach((desc, seq) -> {
			snips.add(new SNP(desc, seq));
		});
	}

	/**
	 * Retourne un SNP selon son RS (son numero rsXXXXX)
	 * 
	 * @param rs
	 *            le rs
	 * @return le SNP
	 */
	// TODO vrm plus efficace si snips -> map
	public SNP getSNPByRS(String rs) {
		SNP snpID = null;

		for (SNP snip : snips) {
			if (snip.getRS().equals(rs)) {
				snpID = snip;
			}
		}

		return snpID;
	}

	/**
	 * Définit un chemin d'accès alternatif vers un autre répertoire si jamais
	 * les fichiers sont introuvables dans les ressources
	 * 
	 * @param newFile
	 *            le répertoire où les fichiers FASTA sont localisés
	 */
	public static void setAltSrcFile(File newFile) {
		Chromosome.altSrcFile = newFile;
		;
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
}
