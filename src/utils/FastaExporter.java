package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.SNP;

public class FastaExporter {

	/**
	 * Sauvegarder en format FASTA (.fas) les informations tiré du modèle dans un fichier texte
	 * @param adn
	 * @param path
	 */
	public static void sauvegarder(DNA adn, String path) {

		File fichierSave = new File(path + "/GPV - ADN.fas");
		try {
			System.out.println(fichierSave);
			PrintWriter printW = new PrintWriter(new FileWriter(fichierSave));
			for (String s : adn.getChrSymbols()) {
				Chromosome[] pairChromo = adn.getChrPair(s);

				for (int i = 0; i < pairChromo.length; i++) {
					Chromosome chromosome = pairChromo[i];

					for (SNP snp : chromosome.snips) {

						String allele = snp.getAllele().toString();
						String rs = snp.getRS();
						String sequence = snp.getSeq();

						printW.println("> GPV | Chromosome " + s + "." + i + " | allele=" + allele + " | rs=" + rs);
						printW.println(sequence);
						printW.println();

					}

				}

			}
			printW.close();
		} catch (IOException e) {
			//TODO Gérer l'exception autrement
			System.out.println(e);
		}

	}

}
