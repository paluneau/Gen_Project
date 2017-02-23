package app;

import java.net.URL;

import controleur.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GenomicApplication extends Application {

	private Controller ctrl = null;

	URL resource;
	Media media;
	MediaPlayer mediaPlayer;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vue/interface_v.01.fxml"));

		BorderPane root = (BorderPane) loader.load();
		ctrl = loader.getController();

		// Code pour la Musique
		resource = getClass().getResource("/sons/Lobo_Loco_-_02_-_Gasriese_Zeta_II_ID_82.mp3");
		media = new Media(resource.toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setOnEndOfMedia(new Runnable() {
			public void run() {
				mediaPlayer.seek(Duration.ZERO);
			}
		});

		mediaPlayer.play(); // --- POUR JOUER LA TOUNE

		Scene scene = new Scene(root);

		stage.setTitle("Genomic Physionomy Viewer");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}
}
