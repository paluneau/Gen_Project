package modele.genome;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
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

	private Map<String, SNP> snips = null;
	private String dataSrcPath = null;
	private String name = null;
	private List<String> wntdSNPs = null;
	private static File altSrcFile = null;


	public Chromosome(String name, List<String> targetSNP)
			throws ConstructionException, IOException, URISyntaxException {
		if (name != null && targetSNP != null) {
			this.name = name;
			this.wntdSNPs = targetSNP;
			this.dataSrcPath = generatePath();
			this.snips = new HashMap<String, SNP>();
			loadSNPs();
		} else {
			throw new ConstructionException("CHROMOSOME INVALIDE");
		}
	}

	public Map<String, SNP> getSnips() {
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

		fsr.getSequences().forEach((desc, seq) -> {
			snips.put(desc, new SNP(desc, seq));
		});
	}

	/**
	 * Retourne un SNP selon son RS (son numéro rsXXXXX)
	 *
	 * @param rs
	 *            le rs recherché
	 * @return le SNP cible
	 */
	public SNP getSNPByRS(String rs) {
		return this.snips.get(rs);
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
		System.out.println("ALT:" +altSrcFile);
	}

	/**
	 * Crée le chemin d'accès pour atteindre le fichier lié au chromosome *
	 * Format: chr_*.fas
	 *
	 * @return le chemin d'accès
	 */
	private String generatePath() {
		return "/chr_" + getName().toUpperCase() + ".fas";
	}

}
