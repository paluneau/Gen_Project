package app;

import java.net.URL;

import controleur.Controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.DDDtools;

public class GenomicApplication extends Application {

	private Controller ctrl = null;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/interface_v.01.fxml"));

		BorderPane root = (BorderPane) loader.load();
		ctrl = loader.getController();

		// TODO METTRE LE PLAYER DANS UNE CLASSE A PART ET L'INCLURE COMME
		// ATTRIBUT DU CONTROLEUR
		// Code pour la Musique
		final URL resource = getClass().getResource("/sons/Lobo_Loco_-_02_-_Gasriese_Zeta_II_ID_82.mp3");
		final Media media = new Media(resource.toString());
		final MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});
		mediaPlayer.play();
		
		Scene scene = new Scene(root);

		stage.setTitle("Genomic Physionomy Viewer");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}
}
