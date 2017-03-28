package controleur;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.sun.xml.internal.messaging.saaj.util.SAAJUtil;

import javafx.collections.ObservableList;
import exception.ConstructionException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import modele.EnvironmentThreeD;
import modele.DNACreator;
import modele.MusicPlayer;
import modele.genome.Chromosome;
import modele.phenotype.EyeColor;
import utils.FastaExporter;
import vue.FichierChooser;
import vue.MessageAlert;

public class Controller {

	@FXML
	private Slider sliderHauteurVisage;

	@FXML
	private Slider sliderDistanceYeux;

	@FXML
	private Slider sliderDistanceSourcils;

	@FXML
	private Slider sliderFinesseBouche;

	@FXML
	private Slider sliderLargeurVisage;

	@FXML
	private Slider sliderHauteurYeux;

	@FXML
	private Slider sliderHauteurSourcils;

	@FXML
	private Slider sliderHauteurBouche;

	@FXML
	private Slider sliderGrosseurOreilles;

	@FXML
	private Slider sliderHauteurOreilles;

	@FXML
	private Slider sliderGrosseurNez;

	@FXML
	private Slider sliderHauteurNez;

	@FXML
	private Slider sliderEcartYeux;

	@FXML
	private ChoiceBox<String> choiceBoxLongueurCheveux;

	@FXML
	private ChoiceBox<String> choiceBoxCouleurCheveux;

	// TODO Est-ce legit de ne pas mettre des strings?
	@FXML
	private ChoiceBox<EyeColor> choiceBoxYeux;

	@FXML
	private Pane pane3D;

	@FXML
	private MenuItem exportButton;

	@FXML
	private CheckMenuItem muteButton;

	private DNACreator dNACreator = null;

	// TODO Integrate with the Human class
	private EnvironmentThreeD envirnm = new EnvironmentThreeD();

	private MusicPlayer player = null;

	private FichierChooser directoryChooser;

	@FXML
	public void initialize() {
		this.player = new MusicPlayer();

		pane3D.getChildren().add(envirnm.buildWorld(pane3D, (int) pane3D.getPrefWidth(), (int) pane3D.getPrefHeight()));
		buildEyeColorBox();
		setSlidersValue();
		bindingModif();
		ajouterEcouteurs();
	}

	private void setSlidersValue() {
		sliderHauteurVisage.setMin(-3);
		sliderHauteurVisage.setMax(3);
		sliderHauteurVisage.setValue(2);
		sliderLargeurVisage.setMin(-3);
		sliderLargeurVisage.setMax(3);
		sliderLargeurVisage.setValue(2);
		sliderDistanceYeux.setMin(-3);
		sliderDistanceYeux.setMax(3);
		sliderDistanceYeux.setValue(-2);
	}

	// Fait les Binding et rempli les ChoiceBox
	private void bindingModif() {

		choiceBoxLongueurCheveux.setItems(FXCollections.observableArrayList("Aucun", "Court", "Long"));
		choiceBoxCouleurCheveux.setItems(FXCollections.observableArrayList("Blond", "Brun", "Roux"));

		// FIXME test 3D vectoriel
		envirnm.getCoordonnatesXProperty().bind(sliderHauteurVisage.valueProperty());
		envirnm.getCoordonnatesYProperty().bind(sliderLargeurVisage.valueProperty());
		envirnm.getCoordonnatesZProperty().bind(sliderDistanceYeux.valueProperty());

		sliderHauteurVisage.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				envirnm.changementWorld();
			}
		});

		sliderLargeurVisage.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				envirnm.changementWorld();
			}
		});

		sliderDistanceYeux.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				envirnm.changementWorld();
			}
		});

		// TODO - LES BINDING FONT DES NULLPOINTEREXCEPTION
		/*
		 * // Binding du visage
		 * human.getFace().hauteurVisageProp.bind(sliderHauteurVisage.
		 * valueProperty());
		 * human.getFace().largeurVisageProp.bind(sliderLargeurVisage.
		 * valueProperty());
		 * 
		 * // Binding des Oreilles
		 * human.getFace().getEar().grosseurOreilleProp.bind(
		 * sliderGrosseurOreilles.valueProperty());
		 * human.getFace().getEar().hauteurOreilleProp.bind(
		 * sliderHauteurOreilles.valueProperty());
		 * 
		 * // Binding des Yeux
		 * human.getFace().getEye().distanceYeuxProp.bind(sliderDistanceYeux.
		 * valueProperty());
		 * human.getFace().getEye().ecartYeuxProp.bind(sliderEcartYeux.
		 * valueProperty());
		 * human.getFace().getEye().hauteurYeuxProp.bind(sliderHauteurYeux.
		 * valueProperty());
		 * 
		 * // Binding de la bouche
		 * human.getFace().getMouth().finesseBoucheProp.bind(sliderFinesseBouche
		 * .valueProperty());
		 * human.getFace().getMouth().hauteurBoucheProp.bind(sliderHauteurBouche
		 * .valueProperty());
		 * 
		 * // Binding du Nez
		 * human.getFace().getNose().grosseurNezProp.bind(sliderGrosseurNez.
		 * valueProperty());
		 * human.getFace().getNose().hauteurNezProp.bind(sliderHauteurNez.
		 * valueProperty());
		 * 
		 * // Binding des Sourcils
		 * human.getFace().getSourcils().distanceSourcilsProp.bind(
		 * sliderDistanceSourcils.valueProperty());
		 * human.getFace().getSourcils().hauteurSourcilsProp.bind(
		 * sliderHauteurSourcils.valueProperty());
		 */
	}

	/**
	 * Met les éléments dans la ChoiceBox pour la couleur des yeux.
	 */
	private void buildEyeColorBox() {
		ObservableList<EyeColor> list = FXCollections.observableArrayList();
		for (EyeColor c : EyeColor.values()) {
			list.add(c);
		}
		choiceBoxYeux.setItems(list);
	}

	// Va contenir les multiples �couteurs
	public void ajouterEcouteurs() {

		choiceBoxYeux.valueProperty().addListener(new ChangeListener<EyeColor>() {
			@Override
			public void changed(ObservableValue<? extends EyeColor> observable, EyeColor oldValue, EyeColor newValue) {
				envirnm.getFace().getEye().setColor(newValue);
			}
		});

	}

	public Slider getSliderHauteurVisage() {
		return sliderHauteurVisage;
	}

	public void setSliderHauteurVisage(Slider sliderHauteurVisage) {
		this.sliderHauteurVisage = sliderHauteurVisage;
	}

	public Slider getSliderDistanceYeux() {
		return sliderDistanceYeux;
	}

	public void setSliderDistanceYeux(Slider sliderDistanceYeux) {
		this.sliderDistanceYeux = sliderDistanceYeux;
	}

	public Slider getSliderDistanceSourcils() {
		return sliderDistanceSourcils;
	}

	public void setSliderDistanceSourcils(Slider sliderDistanceSourcils) {
		this.sliderDistanceSourcils = sliderDistanceSourcils;
	}

	public Slider getSliderFinesseBouche() {
		return sliderFinesseBouche;
	}

	public void setSliderFinesseBouche(Slider sliderFinesseBouche) {
		this.sliderFinesseBouche = sliderFinesseBouche;
	}

	public Slider getSliderLargeurVisage() {
		return sliderLargeurVisage;
	}

	public void setSliderLargeurVisage(Slider sliderLargeurVisage) {
		this.sliderLargeurVisage = sliderLargeurVisage;
	}

	public Slider getSliderHauteurYeux() {
		return sliderHauteurYeux;
	}

	public void setSliderHauteurYeux(Slider sliderHauteurYeux) {
		this.sliderHauteurYeux = sliderHauteurYeux;
	}

	public Slider getSliderHauteurSourcils() {
		return sliderHauteurSourcils;
	}

	public void setSliderHauteurSourcils(Slider sliderHauteurSourcils) {
		this.sliderHauteurSourcils = sliderHauteurSourcils;
	}

	public Slider getSliderHauteurBouche() {
		return sliderHauteurBouche;
	}

	public void setSliderHauteurBouche(Slider sliderHauteurBouche) {
		this.sliderHauteurBouche = sliderHauteurBouche;
	}

	public MusicPlayer getPlayer() {
		return this.player;
	}

	private void modeDNA() {
		try {
			this.dNACreator = new DNACreator(envirnm.getFace());
		} catch (ConstructionException | IOException | URISyntaxException e) {
			// TODO le programme quitte tout seul à cause qu'il trouve pas le
			// fichier du chromosome 14 quand il part
			File newFolder = alertAndChooseFile(e.getMessage());
			Chromosome.setAltSrcFile(newFolder);
			modeDNA();
		}
	}

	@FXML
	private void ouvrirDirectoryChooser(ActionEvent event) {
		directoryChooser = new FichierChooser(pane3D.getScene().getWindow());
		FastaExporter.sauvegarder(dNACreator.getDna(), directoryChooser.getFichierChoisi().getAbsolutePath());
		// TODO - Tester si getAbsolutePath() est mieux de getPath(), juste voir
		// lequel marche
	}

	@FXML
	private void mutePlayer(ActionEvent event) {
		getPlayer().changeMute();
	}

	private File alertAndChooseFile(String message) {
		new MessageAlert(message);
		directoryChooser = new FichierChooser(pane3D.getScene().getWindow());
		return directoryChooser.getFichierChoisi();
	}

}
