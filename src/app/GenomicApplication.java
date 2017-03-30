package app;

import java.io.IOException;
import controleur.Controller;
import controleur.CtrlModeADN;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GenomicApplication extends Application {

	Media media;
	MediaPlayer mediaPlayer;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/interface_v.02.fxml"));

		BorderPane root = (BorderPane) loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("Genomic Physionomy Viewer");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

		Controller c = ((Controller) loader.getController());
		c.modeADN.addListener(new ChangeListener<Boolean>() {
			public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
				if (new_val == true) {
					try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/ModeADN.fxml"));
						Pane pane = (Pane) loader.load();
						((CtrlModeADN) loader.getController()).createFenetreModeADN(c.getEnvirnm());
						Stage stage = new Stage();
						Scene scene = new Scene(pane);
						stage.setScene(scene);
						stage.initModality(Modality.APPLICATION_MODAL);
						stage.setTitle("Mode ADN");
						stage.setScene(scene);
						stage.show();
						c.modeADN.set(false);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

}
