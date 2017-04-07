package modele.phenotype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableFloatArray;

public class Points {

	private Map<String, ObservableFloatArray> points3DIni = null;
	private Map<String, ObservableFloatArray> points3DUpdater = null;
	private Map<ObservableFloatArray, List<String>> pointsSupp = null;

	public Points() {
		points3DIni = new HashMap<String, ObservableFloatArray>();
		points3DUpdater = new HashMap<String, ObservableFloatArray>();
		pointsSupp = new HashMap<ObservableFloatArray, List<String>>();
	}

	public void addIni3DPoints(String group, ObservableFloatArray points) {
		points3DIni.put(group, createAndConvertArray(points));
		points3DUpdater.put(group, points);
	}

	public ObservableFloatArray getPointsUpdater(String group) {
		return points3DUpdater.get(group);
	}

	public void updateDistanceOeilNez(float distance) {
		// updatePoints("Oeil gauche", -distance);
		// updatePoints("Oeil droit", distance);
		updatePoints("face1", distance);
		updatePoints("face2", distance);
	}

	/**
	 * TODO shorten this function
	 */
	public void findSiblings() {
		for (int k = 0; k < points3DIni.values().size() - 1; k++) {
			ObservableFloatArray G1Points = (ObservableFloatArray) points3DIni.values().toArray()[k];
			for (int l = k + 1; l < points3DIni.values().size(); l++) {
				ObservableFloatArray G2Points = (ObservableFloatArray) points3DIni.values().toArray()[l];

				if (!G1Points.equals(G2Points)) {
					for (int i = 0; i < G1Points.size() / 3; i++) {

						ObservableFloatArray pointG1 = FXCollections.observableFloatArray();
						pointG1.addAll(G1Points.get(3 * i), G1Points.get((3 * i) + 1), G1Points.get((3 * i) + 2));

						for (int j = 0; j < G2Points.size() / 3; j++) {

							ObservableFloatArray pointsG2 = FXCollections.observableFloatArray();
							pointsG2.addAll(G2Points.get(3 * j), G2Points.get((3 * j) + 1), G2Points.get((3 * j) + 2));
							if (findIfEquals(pointG1, pointsG2)) {

								String t = findKeyFromValueMap(G1Points);
								String s = findKeyFromValueMap(G2Points);
								List<String> groups = new ArrayList<String>(2);
								groups.add(t);
								groups.add(s);
								pointsSupp.put(pointsG2, groups);

							}

						}

					}
				}

			}
		}

		for (ObservableFloatArray sdk : pointsSupp.keySet()) {
			System.out.println(pointsSupp.get(sdk));
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

	private String findKeyFromValueMap(ObservableFloatArray value) {
		String out = "";
		boolean notFound = true;
		for (String e : points3DIni.keySet()) {
			if (notFound && (value.equals(points3DIni.get(e)))) {
				out = e;
				notFound = false;
			}
		}
		return out;
	}

	private List<ObservableFloatArray> findKeyFromValueMap(String value) {
		List<ObservableFloatArray> out = new ArrayList<ObservableFloatArray>();
		for (ObservableFloatArray e : pointsSupp.keySet()) {
			if (pointsSupp.get(e).contains(value)) {
				out.add(e);
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

		/*ObservableFloatArray points = points3DUpdater.get(group);
		for (int i = 0; i < points.size() / 3; i++) {
			points.set(2 + (3 * i), points3DIni.get(group).get(2 + (3 * i)) + factor);
		}*/

		if (group.contains("face2")) {
			List<ObservableFloatArray> pointsCommun = findKeyFromValueMap(group);
			for (ObservableFloatArray e : pointsCommun) {
				List<String> groups = pointsSupp.get(e);
				for (String f : groups) {
					List<Integer> g = findIndexOfValues(points3DIni.get(f), e);
					for (Integer h : g) {
						points3DUpdater.get(f).set(2 + (3 * h), e.get(2 + (3 * h)) - factor);
					}

				}

				System.out.println("SUPP : " + pointsCommun.get(2));
				System.out.println("PP : " + points3DUpdater.get(group).get(2));
			}
		}

	}

	private List<Integer> findIndexOfValues(ObservableFloatArray values, ObservableFloatArray targets) {
		List<Integer> out = new ArrayList<Integer>();
		for (int i = 0; i < values.size(); i++) {
			for (Float e : targets.toArray(null)) {
				if (values.get(i) == e) {
					out.add(i);
				}
			}
		}
		return out;
	}

	private ObservableFloatArray createAndConvertArray(ObservableFloatArray original) {
		ObservableFloatArray pTemp = FXCollections.observableFloatArray();
		pTemp.addAll(original);
		return pTemp;
	}

}
