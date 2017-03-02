package controleur;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import modele.Human;
import utils.DDDtools;

public class Controller {

	@FXML
	private Pane pane3D;

	private Human human = null;

	@FXML
	public void initialize() {
		DDDtools p = new DDDtools();
		pane3D.getChildren().add(p.buildWorld(pane3D, (int) pane3D.getPrefWidth(), (int) pane3D.getPrefHeight()));
	}

}
