package modele.phenotype;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;

public class Points {

	private Map<String, ObservableFloatArray> points3DIni = null;
	private Map<String, ObservableFloatArray> points3DUpdater = null;
	private Map<String, ObservableFloatArray> pointsIniSupp = null;
	private Map<String, ObservableFloatArray> pointsSupp = null;

	public Points() {
		points3DIni = new HashMap<String, ObservableFloatArray>();
		points3DUpdater = new HashMap<String, ObservableFloatArray>();
		pointsIniSupp = new HashMap<String, ObservableFloatArray>();
		pointsSupp = new HashMap<String, ObservableFloatArray>();
	}

	public void addIni3DPoints(String group, ObservableFloatArray points) {
		points3DIni.put(group, createArrayCopy(points));
		points3DUpdater.put(group, points);
	}

	public ObservableFloatArray getPointsUpdater(String group) {
		return points3DUpdater.get(group);
	}

	public void updateDistanceOeilNez(float distance) {
		updatePoints("Oeil gauche", -distance);
		updatePoints("Oeil droit", distance);
		// updatePoints("face1", distance);
		// updatePoints("face2", distance);
	}

	/**
	 * TODO shorten this function
	 */
	public void findSiblings() {
		for (int k = 0; k < points3DIni.values().size() - 1; k++) {
			ObservableFloatArray e = (ObservableFloatArray) points3DIni.values().toArray()[k];
			for (int l = k + 1; l < points3DIni.values().size(); l++) {
				ObservableFloatArray f = (ObservableFloatArray) points3DIni.values().toArray()[l];
				if (!e.equals(f)) {
					for (int i = 0; i < e.size() / 3; i++) {
						ObservableFloatArray p = FXCollections.observableFloatArray();
						p.addAll(e.get(3 * i), e.get((3 * i) + 1), e.get((3 * i) + 2));
						for (int j = 0; j < f.size() / 3; j++) {
							ObservableFloatArray q = FXCollections.observableFloatArray();
							q.addAll(f.get(3 * j), f.get((3 * j) + 1), f.get((3 * j) + 2));
							if (findIfEquals(p, q)) {
								String t = findKeyFromValueMap(e, points3DIni);
								if (pointsSupp.containsKey(t)) {
									pointsIniSupp.get(t).addAll(createArrayCopy(q));
									pointsSupp.get(t).addAll(q);
								} else {
									pointsIniSupp.put(t, createArrayCopy(q));
									pointsSupp.put(t, q);
								}
								t = findKeyFromValueMap(f, points3DIni);
								if (pointsSupp.containsKey(t)) {
									pointsIniSupp.get(t).addAll(createArrayCopy(p));
									pointsSupp.get(t).addAll(p);
								} else {
									pointsIniSupp.put(t, createArrayCopy(p));
									pointsSupp.put(t, p);
								}
							}
						}

					}
				}

			}
		}

		for (ObservableFloatArray sdk : pointsSupp.values()) {
			System.out.println(findKeyFromValueMap(sdk, pointsSupp));
			for (int i = 0; i < sdk.size() / 3; i++) {
				System.out.println(
						"P : [" + sdk.get(3 * i) + ", " + sdk.get((3 * i) + 1) + ", " + sdk.get((3 * i) + 2) + "]");
			}
		}
		System.out.println();

	}

	private boolean findIfEquals(ObservableFloatArray p, ObservableFloatArray q) {
		boolean out = true;
		for (int i = 0; (i < p.size()) && (i < q.size()); i++) {
			if (p.get(i) != q.get(i)) {
				out = false;
			}
		}
		return out;
	}

	// TODO remove map from parameters (there for test)
	private String findKeyFromValueMap(ObservableFloatArray value, Map<String, ?> map) {
		String out = "";
		boolean notFound = true;
		for (String e : map.keySet()) {
			if (notFound && (value.equals(map.get(e)))) {
				out = e;
				notFound = false;
			}
		}
		return out;
	}

	/**
	 * 
	 * @param group
	 * @param factor
	 * @param dimension
	 *            X, Y ou Z
	 */
	private void updatePoints(String group, float factor) {

		ObservableFloatArray points = points3DUpdater.get(group);
		for (int i = 0; i < points.size() / 3; i++) {
			points.set(2 + (3 * i), points3DIni.get(group).get(2 + (3 * i)) + factor);
		}

		/*
		 * if (group.contains("face2")) { ObservableFloatArray pointSup =
		 * pointsSupp.get(group); for (int i = 0; i < pointSup.size() / 3; i++)
		 * { pointSup.set(2 + (3 * i), pointsIniSupp.get(group).get(2 + (3 * i))
		 * - factor); } System.out.println("SUPP : " + pointSup.get(2));
		 * System.out.println("PP : " + points3DUpdater.get(group).get(2)); }
		 */

	}

	private ObservableFloatArray createArrayCopy(ObservableFloatArray original) {
		ObservableFloatArray pTemp = FXCollections.observableFloatArray();
		pTemp.addAll(original);
		return pTemp;
	}

}
