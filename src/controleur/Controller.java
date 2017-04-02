package controleur;

import javafx.collections.ObservableList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import modele.EnvironmentThreeD;
import modele.MusicPlayer;
import modele.phenotype.data.EyeColor;
import modele.phenotype.data.SkinColor;

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
	private CheckMenuItem muteButton;

	private EnvironmentThreeD envirnm = null;
	private MusicPlayer player = null;
	private BooleanProperty modeADNProperty = null;

	@FXML
	public void initialize() {
		this.player = new MusicPlayer(MusicPlayer.SONG1);
		choiceBoxLongueurCheveux.setItems(FXCollections.observableArrayList("Aucun", "Court", "Long"));
		choiceBoxCouleurCheveux.setItems(FXCollections.observableArrayList("Blond", "Brun", "Roux"));
		modeADNProperty = new SimpleBooleanProperty();
		setModeADN(false);
		buildEyeColorBox();
		buildSkinColorBox();
		setSlidersValue();
		ajouterEcouteurs();
		envirnm = new EnvironmentThreeD(choiceBoxYeux.getValue(), choiceBoxSkin.getValue());
		pane3D.getChildren().add(envirnm.buildWorld(pane3D, (int) pane3D.getPrefWidth(), (int) pane3D.getPrefHeight()));
	}

	/**
	 * Détermine les valeurs initiales des contrôles
	 */
	private void setSlidersValue() {
		/*
		 * sliderHauteurVisage.setMin(-3); sliderHauteurVisage.setMax(3);
		 * sliderHauteurVisage.setValue(2); sliderLargeurVisage.setMin(-3);
		 * sliderLargeurVisage.setMax(3); sliderLargeurVisage.setValue(2);
		 * sliderDistanceYeux.setMin(-3); sliderDistanceYeux.setMax(3);
		 * sliderDistanceYeux.setValue(-2);
		 */
		sliderEcartYeux.setMin(-0.4);
		sliderEcartYeux.setMax(0.4);
		sliderEcartYeux.setValue(0);
		sliderDistanceYeux.setMax(0.000000000001);
		choiceBoxYeux.setValue(EyeColor.BROWN);
		choiceBoxSkin.setValue(SkinColor.MEDIUM);
	}

	public void setModeADN(boolean val) {
		modeADNProperty.set(val);
	}

	public boolean getModeADN() {
		return modeADNProperty.get();
	}

	public BooleanProperty getModeADNProperty() {
		return modeADNProperty;
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

	/**
	 * Ajoute les multiples écouteurs sur les contrôles
	 */
	public void ajouterEcouteurs() {

		choiceBoxYeux.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EyeColor>() {
			public void changed(ObservableValue<? extends EyeColor> ov, EyeColor old_val, EyeColor new_val) {
				envirnm.getFace().getLEye().setColor(new_val);
				envirnm.getFace().getREye().setColor(new_val);
				envirnm.changementWorld();
			}
		});

		choiceBoxSkin.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SkinColor>() {
			public void changed(ObservableValue<? extends SkinColor> ov, SkinColor old_val, SkinColor new_val) {
				envirnm.getFace().setSkinColor(new_val);
				envirnm.changementWorld();
			}
		});

		sliderEcartYeux.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
				envirnm.getFace().setEyeDistance(new_val.floatValue());
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

	public EnvironmentThreeD getEnvirnm() {
		return envirnm;
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
	 * Permet d'ouvrir le mode ADN
	 * 
	 * @param event
	 */
	@FXML
	private void ouvrirModeADN(ActionEvent event) {
		setModeADN(true);
	}

}
