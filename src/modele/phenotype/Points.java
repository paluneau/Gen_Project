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
	private Map<String, ObservableFloatArray> pointsSupp = null;

	public Points() {
		points3DIni = new HashMap<String, ObservableFloatArray>();
		points3DUpdater = new HashMap<String, ObservableFloatArray>();
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
		// updatePoints("Oeil gauche", -distance);
		updatePoints("face1", distance);
	}

	public List<Float> findSiblings() {
		/*
		 * File fichierSave = new
		 * File("C:/Users/1541254/Documents/GPV - ADN.txt"); PrintWriter printW
		 * = null; try { printW = new PrintWriter(new FileWriter(fichierSave));
		 * } catch (IOException e1) { e1.printStackTrace(); }
		 */
		for (ObservableFloatArray e : points3DIni.values()) {
			for (ObservableFloatArray f : points3DIni.values()) {
				if (!e.equals(f)) {
					for (int i = 0; i < e.size() / 3; i++) {
						ObservableFloatArray p = FXCollections.observableFloatArray();
						p.addAll(e.get(3 * i), e.get((3 * i) + 1), e.get((3 * i) + 2));
						// System.out.println("P : " + p.toString());
						for (int j = 0; j < f.size() / 3; j++) {
							ObservableFloatArray q = FXCollections.observableFloatArray();
							q.addAll(f.get(3 * j), f.get((3 * j) + 1), f.get((3 * j) + 2));
							// System.out.println("Q : " + q.toString());

							if (findIfEquals(p, q)) {
								List<ObservableFloatArray> points = new ArrayList<ObservableFloatArray>();
								points.add(q);
								points.add(createArrayCopy(q));
								pointsSupp.put(findKeyFromValueMap(e, points3DIni), q);
								points = new ArrayList<ObservableFloatArray>();
								points.add(p);
								points.add(createArrayCopy(p));
								pointsSupp.put(findKeyFromValueMap(f, points3DIni), p);
							}
						}

					}
				}

			}
			// printW.close();

		}
		for (ObservableFloatArray sdk : points3DIni.values()) {
			System.out.println(findKeyFromValueMap(sdk, points3DIni));
			for (int i = 0; i < sdk.size() / 3; i++) {
				System.out.println(
						"P : [" + sdk.get(3 * i) + ", " + sdk.get((3 * i) + 1) + ", " + sdk.get((3 * i) + 2) + "]");
			}
		}
		System.out.println();
		return null;
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
		ObservableFloatArray pointSup = pointsSupp.get(group);
		for (int i = 0; i < pointSup.size() / 3; i++) {
			pointSup.set(2 + (3 * i), pointSup.get(2 + (3 * i)) + factor);
		}
	}

	private ObservableFloatArray createArrayCopy(ObservableFloatArray original) {
		ObservableFloatArray pTemp = FXCollections.observableFloatArray();
		pTemp.addAll(original);
		return pTemp;
	}

}
