package modele.phenotype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import utils.MapTools;
import utils.VecteurUtilitaires;

public class TransformationPoints {

	/**
	 * Une map avec comme clé les différents group et comme value les points 3D
	 * de référence de ces groupes, loader une seule fois au début du programme.
	 */
	private Map<String, ObservableFloatArray> points3DIni = null;
	/**
	 * Une map avec comme clé les différents group et comme value les points 3D
	 * de ces groupes, qu'on utilise et qu'on rafraichît durant la vie de l'app.
	 * C'est elle qui est modifiée pour faire des changements dans la vue.
	 */
	private Map<String, ObservableFloatArray> points3DUpdater = null;
	/**
	 * Une map avec comme clé un point commun entre deux groupes et comme value
	 * une liste de ces deux groupes. Utilisée pour bouger les points communs
	 * entre différents groupes lorsqu'il y a un changement sur ceux-ci.
	 */
	private Map<ObservableFloatArray, List<String>> pointsSupp = null;

	public TransformationPoints() {
		points3DIni = new HashMap<String, ObservableFloatArray>();
		points3DUpdater = new HashMap<String, ObservableFloatArray>();
		pointsSupp = new HashMap<ObservableFloatArray, List<String>>();
	}

	public void addIni3DPoints(String group, ObservableFloatArray points) {
		points3DIni.put(group, MapTools.createAndConvertArray(points));
		points3DUpdater.put(group, points);
	}

	public ObservableFloatArray getPointsUpdater(String group) {
		return points3DUpdater.get(group);
	}

	/**
	 * Permet d'appliquer une translation sur une des composantes du visage
	 * 
	 * @param part
	 *            la partie du visage
	 * @param transformation
	 *            les paramètres de la transformation (x, y ,z)
	 */
	public void applyTranslation(BodyPart part, List<String> groupREM, Point3D transformation) {
		for (String group : part.getSubParts()) {
			updatePointsTranslation(group, groupREM, transformation);
		}
		// updatePointsTranslation(part.getSubParts().get(0), groupREM,
		// transformation);

	}

	/**
	 * Permet de grossir dans toutes les dimensions une partie du visage
	 * 
	 * @param part
	 *            la partie du visage à grossir
	 * @param groupREM
	 *            les parties à ignorer
	 * @param factor
	 *            le facteur de grossissement
	 */
	public void applyGrossissement(BodyPart part, List<String> groupREM, double factor) {
		for (String group : part.getSubParts()) {
			updatePointGrossissement(group, groupREM, factor);
		}
	}

	public static Scale applyScale(Point3D pointCentre, Point3D scale) {
		return new Scale(scale.getX(), scale.getY(), scale.getZ(), pointCentre.getX(), pointCentre.getY(),
				pointCentre.getZ());
	}

	/**
	 * Permet de faire tourner une partie du visage
	 * 
	 * @param pointCentre
	 *            Le centre de rotation
	 * @param axe
	 *            L'axe autour duquel on tourne
	 * @param degres
	 *            Le nombre de degrés qu'on veut tourner
	 * @return Un objet Rotate à appliquer sur les composantes voulues
	 */

	public static Rotate applyRotation(Point3D pointCentre, char axe, double degres) {

		Rotate objet = null;
		switch (axe) {
		case 'x':
			objet = new Rotate(degres, pointCentre.getX(), pointCentre.getY(), pointCentre.getZ(), Rotate.X_AXIS);
			break;
		case 'y':
			objet = new Rotate(degres, pointCentre.getX(), pointCentre.getY(), pointCentre.getZ(), Rotate.Y_AXIS);
			break;
		case 'z':
			objet = new Rotate(degres, pointCentre.getX(), pointCentre.getY(), pointCentre.getZ(), Rotate.Z_AXIS);
			break;
		}
		return objet;
	}

	/**
	 * TODO shorten this function && put comments Trouve les différents points
	 * communs entre chaque groupe et les met dans la map pointsSupp.
	 */
	public void findSiblings() {
		for (int k = 0; k < (points3DIni.values().size() - 1); k++) {
			ObservableFloatArray G1Points = (ObservableFloatArray) points3DIni.values().toArray()[k];
			for (int l = k + 1; l < points3DIni.values().size(); l++) {
				ObservableFloatArray G2Points = (ObservableFloatArray) points3DIni.values().toArray()[l];

				if (!G1Points.equals(G2Points)) {
					for (int i = 0; i < G1Points.size() / 3; i++) {

						ObservableFloatArray pointG1 = FXCollections.observableFloatArray();
						pointG1.addAll(G1Points.get(3 * i), G1Points.get((3 * i) + 1), G1Points.get((3 * i) + 2));

						for (int j = 0; j < G2Points.size() / 3; j++) {

							ObservableFloatArray pointG2 = FXCollections.observableFloatArray();
							pointG2.addAll(G2Points.get(3 * j), G2Points.get((3 * j) + 1), G2Points.get((3 * j) + 2));
							if (MapTools.findIfEquals(pointG1, pointG2)) {
								String t = findKeyFromValueMap(G1Points);
								String s = findKeyFromValueMap(G2Points);

								List<String> groups = new ArrayList<String>();
								groups.add(t);
								groups.add(s);
								pointsSupp.put(pointG2, groups);

							}

						}

					}
				}

			}
		}

	}

	/**
	 * 
	 * Update le point d'un groupe selon un facteur dans chaque dimension.
	 * 
	 * @param groupADD
	 *            le groupe à modifier
	 * @param factors
	 *            - un point 3D qui contient le facteur modificateur dans chaque
	 *            dimension
	 */
	private void updatePointsTranslation(String groupADD, List<String> groupREM, Point3D factors) {
		ObservableFloatArray points = points3DUpdater.get(groupADD);

		List<ObservableFloatArray> pointsGroupREM = findPointsGroupREM(groupREM);
		updatePointCommun(groupADD, pointsGroupREM, factors);

		List<Integer> dodge = fuck(groupADD, pointsGroupREM);
		for (int i = 0; i < points.size() / 3; i++) {
			if ((dodge != null) && (!dodge.isEmpty())) {

				if (!dodge.contains(i)) {
					update2(points, i, groupADD, factors);
				}
			} else {
				update2(points, i, groupADD, factors);
			}
		}
	}

	private void updatePointGrossissement(String groupADD, List<String> groupREM, double factor) {
		ObservableFloatArray points = points3DIni.get(groupADD);
		Point3D center = VecteurUtilitaires.findPointMilieu(points);

		for (int i = 0; i < (points.size() / 3); i++) {

			Point3D vecteurDirecteur = VecteurUtilitaires.findVecteur(center,
					new Point3D(points.get((3 * i) + 2), points.get((3 * i)), points.get((3 * i) + 1)));

			Point3D vecteurPointFinal = vecteurDirecteur.multiply(factor);

			Point3D delta = vecteurPointFinal.subtract(vecteurDirecteur.getX(), vecteurDirecteur.getY(),
					vecteurDirecteur.getZ());

			update2(points3DUpdater.get(groupADD), i, groupADD, delta);
			updatePointCommun(groupADD, findPointsGroupREM(groupREM), delta);

		}
	}

	private List<Integer> updatePointCommun(String groupADD, List<ObservableFloatArray> pointsGroupREM,
			Point3D factors) {
		List<Integer> dodge = new ArrayList<Integer>();
		List<ObservableFloatArray> pointsCommun = findKeyFromValueMap(groupADD);

		for (ObservableFloatArray pointCommun : pointsCommun) {
			List<String> groups = pointsSupp.get(pointCommun);
			for (ObservableFloatArray e : pointsGroupREM) {
				// TODO updater les faces communes qui ont pas été touché par
				// fuck
				/*
				 * if (!MapTools.findIfEquals(e, pointCommun)) {
				 * update1(groupADD, groups, pointCommun, factors); } else {
				 * System.out.println("AAA"); }
				 */
			}
		}
		return dodge;
	}

	private List<ObservableFloatArray> findPointsGroupREM(List<String> groupREM) {
		List<ObservableFloatArray> pointsGroupREM = new ArrayList<ObservableFloatArray>();
		for (String rEM : groupREM) {
			ObservableFloatArray g = points3DIni.get(rEM);
			for (int i = 0; i < g.size() / 3; i++) {
				ObservableFloatArray f = FXCollections.observableFloatArray();
				f.addAll(g.get(3 * i), g.get((3 * i) + 1), g.get((3 * i) + 2));
				pointsGroupREM.add(f);
			}

		}
		return pointsGroupREM;
	}

	// TODO
	private List<Integer> fuck(String groupADD, List<ObservableFloatArray> pointsGroupREM) {
		List<Integer> dodge = new ArrayList<Integer>();
		ObservableFloatArray points = points3DIni.get(groupADD);

		for (int i = 0; i < points.size() / 3; i++) {
			for (int j = 0; j < pointsGroupREM.size(); j++) {
				for (int j2 = j + 1; j2 < pointsGroupREM.size() - 1; j2++) {
					if (pointsGroupREM.get(j).size() == 3 && pointsGroupREM.get(j2).size() == 3) {
						double distance = VecteurUtilitaires.findDistance(
								new Point3D(pointsGroupREM.get(j).get(2), pointsGroupREM.get(j).get(0),
										pointsGroupREM.get(j).get(1)),
								new Point3D(pointsGroupREM.get(j2).get(2), pointsGroupREM.get(j2).get(0),
										pointsGroupREM.get(j2).get(1)),
								new Point3D(points.get((3 * i) + 2), points.get((3 * i)), points.get((3 * i) + 1)));
						if (distance <= 0.001) {
							dodge.add(i);
						}
					} else {
						System.out.println("FAILLINGG FAGGOOOTT");
					}
				}
			}
		}
		return dodge;
	}

	private void update1(String groupADD, List<String> groupsCommun, ObservableFloatArray pointCommun,
			Point3D factors) {
		for (String g : groupsCommun) {
			List<Integer> index = MapTools.findIndexOfValues(points3DIni.get(g), pointCommun);

			if (!g.equals(groupADD)) {
				for (Integer i : index) {
					points3DUpdater.get(g).set((3 * i) + 2, (float) (pointCommun.get(2) + factors.getX()));
					points3DUpdater.get(g).set((3 * i) + 0, (float) (pointCommun.get(0) + factors.getY()));
					points3DUpdater.get(g).set((3 * i) + 1, (float) (pointCommun.get(1) + factors.getZ()));
				}
			}
		}
	}

	private void update2(ObservableFloatArray points, int index, String groupADD, Point3D factors) {
		points.set(2 + (3 * index), (float) (points3DIni.get(groupADD).get(2 + (3 * index)) + factors.getX()));
		points.set(0 + (3 * index), (float) (points3DIni.get(groupADD).get(0 + (3 * index)) + factors.getY()));
		points.set(1 + (3 * index), (float) (points3DIni.get(groupADD).get(1 + (3 * index)) + factors.getZ()));
	}

	// TODO mettre les deux pareils dans une methode avec 1 param de + ?
	/**
	 * Trouve la valeur du groupe dans points3DIni à l'aide de points.
	 * 
	 * @param points
	 *            - un array de points
	 * @return la valeur texte du groupe
	 */
	private String findKeyFromValueMap(ObservableFloatArray points) {
		String group = "";
		boolean notFound = true;
		for (String e : points3DIni.keySet()) {
			if (notFound && (points.equals(points3DIni.get(e)))) {
				group = e;
				notFound = false;
			}
		}
		return group;
	}

	/**
	 * Retourne les différents points communs avec d'autres groupes de ce
	 * "group". Utilise la map pointsSupp.
	 * 
	 * @param group
	 * @return une liste des différents points communs
	 */
	private List<ObservableFloatArray> findKeyFromValueMap(String group) {
		List<ObservableFloatArray> out = new ArrayList<ObservableFloatArray>();
		for (ObservableFloatArray e : pointsSupp.keySet()) {
			if (pointsSupp.get(e).contains(group)) {
				out.add(e);
			}
		}
		return out;
	}
}
