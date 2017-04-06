package controleur;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import modele.DNACreator;

public class CtrlLoadingWindow {
	
    @FXML
    private ProgressBar progBar;

	public void startLoading(DNACreator dna){
		progBar.progressProperty().bind(dna.getDna().readingProgressProperty());
	}



}
