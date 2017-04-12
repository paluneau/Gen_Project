package controleur;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import exception.ConstructionException;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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

	private DNACreator dNACreator = null;
	private Face face = null;

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

	public void createFenetreModeADN(Face face) {
		this.face = face;
		modeDNA();
	}

	/**
	 * Insère les labels au bon endroit et avec les bonnes données.
	 */
	private void buildWindow() {
		if (dNACreator != null) {
			createLabel(scrollYeux, face.getLEye().getCouleurYeux().getGenes());
			createLabel(scrollCheveux, face.getHair().getCouleurCheveux().getGenes());
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
	// TODO afficher l'allèle réael ou la Wildcard??
	private void createLabel(ScrollPane pane, Map<TargetSNPs, Allele[]> map) {
		Label label = new Label();
		map.forEach((k, v) -> {
			label.setText(label.getText() + "Chromosome: " + k.getChromosomeNbr() + "\n" + "Allèle: " + v[0] + "/"
					+ v[1] + "\n" + "Gène:  " + k.getGene() + "\n" + "RS: " + "rs" + k.getId() + "\n" + "Séquence "
					+ v[0] + " :"
					+ dNACreator.getDna().getChrPair(k.getChromosomeNbr())[0].getSnips().get("rs" + k.getId()).getSeq()
					+ "\nSéquence " + v[1] + " :"
					+ dNACreator.getDna().getChrPair(k.getChromosomeNbr())[1].getSnips().get("rs" + k.getId()).getSeq()
					+ "\n" + "\n");

		});
		pane.setContent(label);
	}

	/**
	 * Créé un label par défaut
	 * 
	 * @param pane
	 *            la pane qui contient le label
	 */
	// TODO Est-ce qu'on fait afficher quand même ce qu'on peut ?
	private void createLabel(ScrollPane pane) {
		Label label = new Label();
		label.setText("Erreur de lecture");
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
		FichierChooser directoryChooser = new FichierChooser(pane.getScene().getWindow());

		if (directoryChooser.getFichierChoisi() != null) {
			boolean flagError = (dNACreator == null) ? modeDNA() : false;

			if (!flagError) {
				try {
					FastaExporter.sauvegarder(dNACreator.getDna(),
							directoryChooser.getFichierChoisi().getAbsolutePath());
				} catch (IOException e) {
					new MessageAlert("Erreur lors de l'écriture du fichier. Échec de l'exportation");
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
	public boolean modeDNA() {
		boolean flagError = false;
		Service<Void> service = createThread();
		service.start();
		buildWindow();

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
		FichierChooser directoryChooser = new FichierChooser(pane.getScene().getWindow());
		return directoryChooser.getFichierChoisi();
	}

	public DNACreator getdNACreator() {
		return this.dNACreator;
	}

	private Service<Void> createThread() {
		Service<Void> thread = new Service<Void>() {

			@Override
			protected Task<Void> createTask() {
				return new Task<Void>() {

					@Override
					protected Void call() throws Exception {
						System.out.println("PUTAMADRE");

						

						} catch (ConstructionException e) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									new MessageAlert(e.getMessage());
								}
							});
						} catch (URISyntaxException e) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									new MessageAlert(e.getMessage());
								}
							});
						}

						return null;
					}

				};
			}

		};

	return thread;
}

private class ReaderThread extends Service<Void> {

	private DNACreator dNACreator = null;
	private Face face = null;

	private ReaderThread(DNACreator dc, Face face) {

	}

	@Override
		protected Task<Void> createTask() {
			return new Task<Void>(){

				@Override
				protected Void call() throws Exception {
					try {
						dNACreator = new DNACreator(face);
						System.out.println("PUTAMADRE #2");
					} catch (IOException e) {
						System.out.println(e.getMessage());

						
								File newFolder = alertAndChooseFile(e.getMessage());
								Chromosome.setAltSrcFile(newFolder);

						try {

							dNACreator = new DNACreator(face);
						} catch (IOException e1) {
							

							// flagError = true;
						} catch (ConstructionException e1) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									new MessageAlert(e.getMessage());
								}
							});
						} catch (URISyntaxException e1) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									new MessageAlert(e.getMessage());
								}
							});
						return null;
}

};}

}}
