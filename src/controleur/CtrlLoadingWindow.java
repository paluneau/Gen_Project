package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.fxml.Initializable;

public class CtrlLoadingWindow implements Initializable {
	
    @FXML
    private ProgressBar progBar;

	public CtrlLoadingWindow() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//progBar.progressProperty().bind(observable);

	}

}
