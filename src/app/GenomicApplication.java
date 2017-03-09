package app;

import controleur.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class GenomicApplication extends Application {

	private Controller ctrl = null;
	
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

		Scene scene = new Scene(root);
		// ctrl.ajouterEcouteurs();
		ctrl.bindingModif();

		stage.setTitle("Genomic Physionomy Viewer");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

	}

}
