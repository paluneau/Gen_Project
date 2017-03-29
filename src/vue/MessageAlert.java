package vue;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;

public class MessageAlert {

	public MessageAlert(String message) {
		Alert alertBox = new Alert(AlertType.ERROR);

		alertBox.setTitle("Erreur");
		alertBox.setHeaderText("Erreur");
		alertBox.setContentText(message);
		alertBox.showAndWait();
	}

}
