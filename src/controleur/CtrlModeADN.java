package controleur;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import exception.ConstructionException;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
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

	private DoubleProperty readingProgressProperty = null;
	private DNACreator dNACreator = null;
	private Face face = null;
	private Service<Void> thread = null;

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

	/**
	 * Insère les labels au bon endroit et avec les bonnes données.
	 */
	private void buildWindow() {
		if (dNACreator != null) {
			createLabel(scrollYeux, face.getLEye().getCouleurYeux().getGenes());
			createLabel(scrollCheveux, face.getHair().getCouleurCheveux()
					.getGenes());
			createLabel(scrollPeau, face.getSkinColor().getGenes());
		} else {
			createLabel(scrollCheveux);
			createLabel(scrollYeux);
			createLabel(scrollPeau);
		}
	}

	/**
	 * Créé un label avec les infos sur les SNP
	 * 
	 * @param pane
	 *            dans quel pane mettre le label
	 * @param map
	 *            la map qui contient des infos de snp
	 */
	// TODO snp en gras
	private void createLabel(ScrollPane pane, Map<TargetSNPs, Allele[]> map) {
		Label label = new Label();
		map.forEach((k, v) -> {
			label.setText(label.getText()
					+ "Chromosome: "
					+ k.getChromosomeNbr()
					+ "\n"
					+ "Allèle: "
					+ v[0]
					+ "/"
					+ v[1]
					+ "\n"
					+ "Gène:  "
					+ k.getGene()
					+ "\n"
					+ "RS: "
					+ "rs"
					+ k.getId()
					+ "\n"
					+ "Séquence "
					+ v[0]
					+ " :"
					+ dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0]
							.getSnips().get("rs" + k.getId()).getSeq()
					+ "\nSéquence "
					+ v[1]
					+ " :"
					+ dNACreator.getDna().getChrPair(k.getChromosomeNbr())[1]
							.getSnips().get("rs" + k.getId()).getSeq() + "\n"
					+ "\n");

		});
		pane.setContent(label);
	}

	/**
	 * Créé un label par défaut
	 * 
	 * @param pane
	 *            la pane qui contient le label
	 */
	private void createLabel(ScrollPane pane) {
		Label label = new Label();
		label.setText("Erreur de lecture. Veuillez générer l'ADN");
		pane.setContent(label);

	}

	/**
	 * Permet de choisir un fichier du répertoire afin d'enregistrer
	 * l'exportation de l'ADN
	 *
	 * @param event
	 */
	@FXML
	public void ouvrirDirectoryChooser(ActionEvent event) {
		FichierChooser directoryChooser = new FichierChooser(pane.getScene()
				.getWindow());

		if (directoryChooser.getFichierChoisi() != null) {
			boolean flagError = (dNACreator == null) ? modeDNA() : false;

			if (!flagError) {
				try {
					FastaExporter.sauvegarder(dNACreator.getDna(),
							directoryChooser.getFichierChoisi()
									.getAbsolutePath());
				} catch (IOException e) {
					new MessageAlert(
							"Erreur lors de l'écriture du fichier. Échec de l'exportation");
				}

			} else {
				new MessageAlert("Échec de l'exportation");
			}

		}
	}

	/**
	 * Créer l'adn selon la face en mémoire et gère les exceptions si les
	 * fichiers à lire sont introuvables
	 *
	 * @return Vrai s'il y a eu une erreur qui empêche la construction, faux si
	 *         tout est correct
	 */
	// TODO régler flagError
	@FXML
	public boolean modeDNA() {
		boolean flagError = false;
		this.thread = new ReaderThread();
		thread.start();
		return flagError;
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
		FichierChooser directoryChooser = new FichierChooser(pane.getScene()
				.getWindow());
		return directoryChooser.getFichierChoisi();

	}

	public DNACreator getdNACreator() {
		return this.dNACreator;
	}

	/**
	 * Permet de lire les fichiers dans un htread parallèle au thread principal
	 * de l'application
	 * 
	 * @author Les géniesdu génome
	 *
	 */
	// TODO le thread n'arrete pas ...
	class ReaderThread extends Service<Void> {

		private ReaderThread() {

		}

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
				dNACreator = new DNACreator(face, readingProgressProperty);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				Platform.runLater(createThreadFileChooser(e.getMessage()));
			} catch (ConstructionException e) {
				System.out.println(e.getMessage());
				Platform.runLater(createThreadMessage(e.getMessage()));
			} catch (URISyntaxException e) {
				System.out.println(e.getMessage());
				Platform.runLater(createThreadMessage(e.getMessage()));
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
