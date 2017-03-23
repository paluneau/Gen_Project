package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import modele.genome.Chromosome;
import modele.genome.DNA;
import modele.genome.SNP;

public class FastaExporter {

	private String allele = null;
	private String rs = null;
	private String sequence = null;

	public void sauvegarder(DNA adn) {

		File fichierSave = new File("ADN.fas");
		try {
			PrintWriter printW = new PrintWriter(new FileWriter(fichierSave));
			for (String s : adn.getChrSymbols()) {
				Chromosome[] pairChromo = adn.getChrPair(s);

				for (int i = 0; i < pairChromo.length; i++) {
					Chromosome chromosome = pairChromo[i];

					for (SNP snp : chromosome.snips) {

						allele = snp.getAllele().toString();
						rs = snp.getRS();
						sequence = snp.getSeq();

						printW.println(">GPV | Chromosomes :" + s + ":" + i + " |allele = " + allele + "| rs = " + rs);
						printW.println(sequence);
						printW.println();

					}

				}

			}
			printW.close();
		} catch (IOException e) {
			System.out.println(e);
		}

	}

}
