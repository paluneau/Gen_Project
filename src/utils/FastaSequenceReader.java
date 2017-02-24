package utils;

import java.io.*;
import java.util.*;

/**
 * This class will read first sequence from a Fasta format file
 * 
 * @author http://www.cs.utexas.edu/~mobios/cs329e/rosetta/src/FastaSequence.
 *         java MÀJ Les génies du génome
 */

public final class FastaSequenceReader {

	private String[] description;
	private String[] sequence;
	private ArrayList<String> targets = null;
	private HashMap<String, String> sequences = null;

	public FastaSequenceReader(String filename, ArrayList<String> target) throws IOException {
		this.targets = (ArrayList<String>) ListTools.formatList(target);
		readSequenceFromFile(filename);
		sequences = createMap();
	}

	// TODO TESTS UNITAIRES AVEC FICHIERS "MAISON"
	// TODO GÉRER LES CAS D'ERREUR: INTROUVABLE, ETC.
	void readSequenceFromFile(String file) throws IOException {
		List<String> desc = new ArrayList<String>();
		List<String> seq = new ArrayList<String>();
		boolean read = false;
		int trgInitSize = targets.size();
		int index = targets.size() - 1;

		BufferedReader in = new BufferedReader(new FileReader(file));
		StringBuffer buffer = new StringBuffer();
		String line = in.readLine();

		// HEADER
		if (line == null) {
			throw new IOException(file + " is an empty file");
		}

		if (line.charAt(0) != '>') {
			throw new IOException("First line of " + file + " should start with '>'");
		} else if (line.contains(targets.get(index))) {
			desc.add(line);
			read = true;
			targets.remove(index);
		}

		// BODY
		for (line = in.readLine().trim(); line != null
				&& (!targets.isEmpty() || seq.size() != desc.size()); line = in.readLine()) {
			// LIGNE DE DESCRIPTION
			if (line.length() > 0 && line.charAt(0) == '>' && line.contains(targets.get(index))) {

				desc.add(line);
				read = true;
				targets.remove(index);

				// LIGNE DE SEQUENCE
			} else if (read) {
				buffer.append(line.trim());
			}
			// LIGNE VIDE - FIN DE SEQUENCE
			if (line.isEmpty() && (targets.size() == index)) {
				seq.add(buffer.toString());
				buffer = new StringBuffer();
				read = false;
				index--;
			}
		}

		// CLOSING
		storeData(seq, desc);

	}

	private void storeData(List<String> seq, List<String> desc) {
		description = new String[desc.size()];
		sequence = new String[seq.size()];

		for (int i = 0; i < desc.size(); i++) {
			description[i] = (String) desc.get(i);
			sequence[i] = (String) seq.get(i);
		}
	}

	private HashMap<String, String> createMap() {
		HashMap<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < description.length; i++) {
			map.put(description[i], sequence[i]);
		}

		return map;
	}

	public HashMap<String, String> getSequences() {
		return this.sequences;
	}
}