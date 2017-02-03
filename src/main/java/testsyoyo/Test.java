package testsyoyo;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.ProteinSequence;
import org.biojava.nbio.core.sequence.io.FastaReaderHelper;

public class Test {

	public static void main(String[] args){
		
		LinkedHashMap<String, DNASequence> a = null;
		try {
			a = FastaReaderHelper.readFastaDNASequence(new File("C:/Users/Client/Desktop/zebrafish3.fa"), true);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//System.out.println(a.get("GENSCAN00000000001 pep scaffold:GRCz10:KN149948.1:111597:111899:-1 transcript:GENSCAN00000000001 transcript_biotype:protein_coding"));

		for (Entry<String, DNASequence> entry : a.entrySet()) {
			System.out.println(entry.getValue().getOriginalHeader() + "=" + entry.getValue().getSequenceAsString());
		}
	}
}