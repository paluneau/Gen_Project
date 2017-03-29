package controleur;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javafx.collections.ObservableList;
import exception.ConstructionException;
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
import modele.phenotype.SkinColor;
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

	@FXML
	private ChoiceBox<EyeColor> choiceBoxYeux;

	@FXML
	private ChoiceBox<SkinColor> choiceBoxSkin;

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

	@FXML
	public void initialize() {
		this.player = new MusicPlayer();
		pane3D.getChildren().add(envirnm.buildWorld(pane3D, (int) pane3D.getPrefWidth(), (int) pane3D.getPrefHeight()));
		choiceBoxLongueurCheveux.setItems(FXCollections.observableArrayList("Aucun", "Court", "Long"));
		choiceBoxCouleurCheveux.setItems(FXCollections.observableArrayList("Blond", "Brun", "Roux"));
		buildEyeColorBox();
		setSlidersValue();
		ajouterEcouteurs();
	}

	private void setSlidersValue() {
		/*
		 * sliderHauteurVisage.setMin(-3); sliderHauteurVisage.setMax(3);
		 * sliderHauteurVisage.setValue(2); sliderLargeurVisage.setMin(-3);
		 * sliderLargeurVisage.setMax(3); sliderLargeurVisage.setValue(2);
		 * sliderDistanceYeux.setMin(-3); sliderDistanceYeux.setMax(3);
		 * sliderDistanceYeux.setValue(-2);
		 */
		sliderEcartYeux.setMin(-0.2);
		sliderEcartYeux.setMax(0.2);
		sliderEcartYeux.setValue(0);
		sliderDistanceYeux.setMax(0.000000000001);
		choiceBoxYeux.setValue(EyeColor.BROWN);
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

	/**
	 * Met les éléments dans la ChoiceBox pour la couleur des yeux.
	 */
	private void buildSkinColorBox() {
		ObservableList<SkinColor> list = FXCollections.observableArrayList();
		for (SkinColor c : SkinColor.values()) {
			list.add(c);
		}
		choiceBoxSkin.setItems(list);
	}

	// Va contenir les multiples �couteurs
	public void ajouterEcouteurs() {

		choiceBoxYeux.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EyeColor>() {
			public void changed(ObservableValue<? extends EyeColor> ov, EyeColor old_val, EyeColor new_val) {
				envirnm.getFace().getLEye().setColor(new_val);
				envirnm.getFace().getREye().setColor(new_val);
				envirnm.changementWorld();
			}
		});

		sliderEcartYeux.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				envirnm.getFace().setEyeDistance(new_val.floatValue());
				envirnm.changementWorld();
			}
		});

		sliderDistanceYeux.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				envirnm.criss = new_val.floatValue();
				envirnm.changementWorld();
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

	/**
	 * Créer l'adn selon la face en mémoire et gère les exceptions si les
	 * fichiers à lire sont introuvables
	 * 
	 * @return Vrai s'il y a eu une erreur qui empêche la construction, faux si
	 *         tout est correct
	 */
	private boolean modeDNA() {
		boolean flagError = false;

		try {
			this.dNACreator = new DNACreator(envirnm.getFace());
		} catch (IOException e) {

			File newFolder = alertAndChooseFile(e.getMessage());
			Chromosome.setAltSrcFile(newFolder);

			try {
				this.dNACreator = new DNACreator(envirnm.getFace());
			} catch (IOException e1) {
				new MessageAlert("Impossible de trouver le(s) fichier(s).");
				flagError = true;
			} catch (ConstructionException e1) {
				new MessageAlert(e1.getMessage());
			} catch (URISyntaxException e1) {
				new MessageAlert(e1.getMessage());
			}

		} catch (ConstructionException e) {
			new MessageAlert(e.getMessage());
		} catch (URISyntaxException e) {
			new MessageAlert(e.getMessage());
		}

		return flagError;
	}

	/**
	 * Permet de choisir un fichier du répertoire afin d'enregistrer
	 * l'exportation de l'ADN
	 * 
	 * @param event
	 */
	@FXML
	private void ouvrirDirectoryChooser(ActionEvent event) {

		FichierChooser directoryChooser = new FichierChooser(pane3D.getScene().getWindow());
		
		if (directoryChooser.getFichierChoisi() != null) {
			boolean error = modeDNA();
			if (!error) {
				FastaExporter.sauvegarder(dNACreator.getDna(), directoryChooser.getFichierChoisi().getAbsolutePath());
			} else {
				new MessageAlert("Échec de l'exportation");
			}

		}

	}

	/**
	 * Permet de "muter" la musique
	 * 
	 * @param event
	 */
	@FXML
	private void mutePlayer(ActionEvent event) {
		getPlayer().changeMute();
	}

	/**
	 * Affiche une erreur et ouvre un DirectoryChooser
	 * 
	 * @param message
	 *            le message a afficher
	 * @return le path du dossier sélectionné
	 */
	private File alertAndChooseFile(String message) {
		new MessageAlert(message);
		FichierChooser directoryChooser = new FichierChooser(pane3D.getScene().getWindow());
		return directoryChooser.getFichierChoisi();
	}

}
