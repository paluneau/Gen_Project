package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.SNP;

public class FastaExporter {

	public static void sauvegarder(DNA adn, String path) throws IOException {

		File fichierSave = new File(path + "/GPV - ADN.fas");
			System.out.println(fichierSave);
			PrintWriter printW = new PrintWriter(new FileWriter(fichierSave));
			for (String s : adn.getChrSymbols()) {
				Chromosome[] pairChromo = adn.getChrPair(s);

				for (int i = 0; i < pairChromo.length; i++) {
					Chromosome chromosome = pairChromo[i];

					for (SNP snp : chromosome.getSnips()) {

						String allele = snp.getAllele().toString();
						String rs = snp.getRS();
						String sequence = snp.getSeq();

						printW.println("> GPV | Chromosome " + s + "." + (i + 1) + " | allele=" + allele + " at "
								+ snp.getVarPos() + " | rs=" + rs);
						printW.println(sequence);
						printW.println();

					}

				}

			}
			printW.close();
	}
}
