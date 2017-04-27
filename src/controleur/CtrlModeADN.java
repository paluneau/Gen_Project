package controleur;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import exception.ConstructionException;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import modele.DNACreator;
import modele.genome.Chromosome;
import modele.genome.data.Allele;
import modele.genome.data.TargetSNPs;
import modele.phenotype.Face;
import utils.FastaExporter;
import vue.FichierChooser;
import vue.MessageAlert;

public class CtrlModeADN {

	@FXML
	private Pane pane;

	@FXML
	private ScrollPane scrollYeux;

	@FXML
	private ScrollPane scrollCheveux;

	@FXML
	private ScrollPane scrollPeau;

	@FXML
	private ProgressBar readingProgress;

	@FXML
	private TextFlow flowYeux;

	private DoubleProperty readingProgressProperty = null;
	private DNACreator dNACreator = null;
	private Face face = null;
	private Service<Void> thread = null;

	private BooleanProperty arreterThread = new SimpleBooleanProperty(true);

	public void createFenetreModeADN(Face face) {
		this.face = face;
		this.readingProgressProperty = new SimpleDoubleProperty(0);
		readingProgress.progressProperty().bind(readingProgressProperty);
		modeDNA();
	}

	public DoubleProperty readingProgressProperty() {
		return readingProgressProperty;
	}

	public double getReadingProgress() {
		return readingProgressProperty.get();
	}

	public void setReadingProgress(double val) {
		this.readingProgressProperty.set(val);
	}

	public BooleanProperty getArreterThread() {
		return arreterThread;
	}

	/**
	 * Perrmet d'arrêter le thread
	 * @param arreterThread 
	 */
	public void setArreterThread(Boolean arreterThread) {
		this.arreterThread.set(arreterThread);
		;
	}

	/**
	 * Détermine si le thread roule
	 * @return
	 */
	private boolean isReading() {
		return (thread != null);
	}


	/**
	 * Insère les labels au bon endroit et avec les bonnes données.
	 */
	private void buildWindow() {
		if (dNACreator != null) {
			flowYeux.getChildren().clear();
			createLabel(flowYeux, face.getLEye().getCouleurYeux().getGenes());
			// createLabel(scrollCheveux,
			// face.getHair().getCouleurCheveux().getGenes());
			// createLabel(scrollPeau, face.getSkinColor().getGenes());
		} else {
			// createLabel(scrollCheveux);
			createLabel(flowYeux);
			// createLabel(scrollPeau);
		}
	}

	/**
	 * Crée un label avec les infos sur les SNP
	 * 
	 * @param pane
	 *            dans quel pane mettre le label
	 * @param map
	 *            la map qui contient des infos de snp
	 */
	// TODO snp en gras
	private void createLabel(Pane pane, Map<TargetSNPs, Allele[]> map) {
		// Label label = new Label();

		map.forEach((k, v) -> {
			Label targetSNP1 = new Label(dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0].getSnips()
					.get("rs" + k.getId()).getSeq().substring(
							dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0].getSnips().get("rs" + k.getId())
									.getVarPos(),
							dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0].getSnips().get("rs" + k.getId())
									.getVarPos() + 1));
			targetSNP1.setUnderline(true);
			targetSNP1.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

			Label targetSNP2 = new Label(dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0].getSnips()
					.get("rs" + k.getId()).getSeq().substring(
							dNACreator.getDna().getChrPair(k.getChromosomeNbr())[1].getSnips().get("rs" + k.getId())
									.getVarPos(),
							dNACreator.getDna().getChrPair(k.getChromosomeNbr())[1].getSnips().get("rs" + k.getId())
									.getVarPos() + 1));
			targetSNP2.setUnderline(true);
			targetSNP2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));

			String seq11 = new String(
					dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0].getSnips().get("rs" + k.getId()).getSeq()
							.substring(0, (dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0].getSnips()
									.get("rs" + k.getId()).getVarPos() - 1)));

			String seq12 = new String(dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0].getSnips()
					.get("rs" + k.getId()).getSeq().substring((dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0]
							.getSnips().get("rs" + k.getId()).getVarPos() - 1)));

			Label label1 = new Label("Chromosome: " + k.getChromosomeNbr() + "\n" + "Allèle: " + v[0] + "/" + v[1]
					+ "\n" + "Gène:  " + k.getGene() + "\n" + "RS: " + "rs" + k.getId() + "\n" + "Séquence " + v[0]
					+ " :" + seq11);
			Label label2 = new Label(seq12 + "\nSéquence " + v[1] + " :"
					+ dNACreator.getDna().getChrPair(k.getChromosomeNbr())[1].getSnips().get("rs" + k.getId()).getSeq()
					+ "\n" + "\n");

			pane.getChildren().add(label1);
			pane.getChildren().add(targetSNP1);
			pane.getChildren().add(label2);
			// pane.getChildren().add(targetSNP2);
			// pane.getChildren().add(label3);
		});

	}

	/**
	 * Crée un label par défaut
	 * 
	 * @param pane
	 *            la pane qui contient le label
	 */
	private void createLabel(Pane pane) {
		Label label = new Label();
		label.setText("Erreur de lecture. Veuillez générer l'ADN avant qu'on l'affiche.");
		pane.getChildren().add(label);

	}

	/**
	 * Permet de choisir un fichier du répertoire afin d'enregistrer
	 * l'exportation de l'ADN
	 *
	 * @param event
	 */
	@FXML
	public void ouvrirDirectoryChooser(ActionEvent event) {
		FichierChooser directoryChooser = new FichierChooser(pane.getScene().getWindow());

		if (directoryChooser.getFichierChoisi() != null) {

			if (dNACreator != null) {
				try {
					FastaExporter.sauvegarder(dNACreator.getDna(),
							directoryChooser.getFichierChoisi().getAbsolutePath());
				} catch (IOException e) {
					new MessageAlert("Erreur lors de l'écriture du fichier. Échec de l'exportation");
				}

			} else {
				new MessageAlert("Échec de l'exportation.");
			}

		}
	}

	/**
	 * Crée l'adn selon la face en mémoire et gère les exceptions si les
	 * fichiers à lire sont introuvables
	 */
	@FXML
	public void modeDNA() {
		if (!isReading()) {
			System.out.println("Je passe je vais lire!!");
			this.thread = new ReaderThread();
			thread.start();
		} else {
			System.out.println("Je lit déjà je lirai pas encore!");
		}
	}

	/**
	 * Affiche une erreur et ouvre un DirectoryChooser
	 *
	 * @param message
	 *            le message à afficher
	 * @return le path du dossier sélectionné
	 */
	private File alertAndChooseFile(String message) {
		new MessageAlert(message);
		FichierChooser directoryChooser = new FichierChooser(pane.getScene().getWindow());
		return directoryChooser.getFichierChoisi();

	}

	public DNACreator getdNACreator() {
		return this.dNACreator;
	}

	/**
	 * Permet de lire les fichiers dans un thread parallèle au thread principal
	 * de l'application
	 * 
	 * @author Les géniesdu génome
	 *
	 */
	class ReaderThread extends Service<Void> {

		private Runnable createThreadMessage(String message) {
			return new Runnable() {

				@Override
				public void run() {
					new MessageAlert(message);
				}
			};
		}

		private Runnable createThreadFileChooser(String message) {
			return new Runnable() {

				@Override
				public void run() {
					File newFolder = alertAndChooseFile(message);
					Chromosome.setAltSrcFile(newFolder);
				}
			};
		}

		private void manageFileReading() {
			try {
				dNACreator = new DNACreator(face, readingProgressProperty, arreterThread);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				Platform.runLater(createThreadFileChooser(e.getMessage()));
				thread = null;
			} catch (ConstructionException e) {
				System.out.println(e.getMessage());
				Platform.runLater(createThreadMessage(e.getMessage()));
				thread = null;
			} catch (URISyntaxException e) {
				System.out.println(e.getMessage());
				Platform.runLater(createThreadMessage(e.getMessage()));
				thread = null;
			}
		}

		@Override
		protected Task<Void> createTask() {
			return new Task<Void>() {

				@Override
				protected Void call() throws Exception {
					manageFileReading();
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							buildWindow();
						}
					});

					return null;
				}
			};
		}
	}
}
