package vue;

import java.io.File;
import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

/**
 * Classe qui permet de choisir le fichier de destination
 * 
 * @author Les génies du génome
 *
 */
public class FichierChooser {

	private File fichierChoisi = null;

	public FichierChooser(Window window) {
		DirectoryChooser fenetre = new DirectoryChooser();
		fenetre.setTitle("Choisir le dossier de destination");
		File repDebut = new File(System.getProperty("user.home"));
		fenetre.setInitialDirectory(repDebut);

		fichierChoisi = fenetre.showDialog(window);
	}

	public File getFichierChoisi() {
		return fichierChoisi;
	}

	
	
}
