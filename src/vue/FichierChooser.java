package vue;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.Window;

public class FichierChooser {

	public FichierChooser(Window window) {
		DirectoryChooser fenetre = new DirectoryChooser();
		fenetre.setTitle("Choisir le dossier de destination");
		File repDebut = new File(System.getProperty("user.home"));
		fenetre.setInitialDirectory(repDebut);
		fenetre.showDialog(window);
	}
	
	

}
