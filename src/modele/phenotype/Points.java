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

	public Points() {
		points3DIni = new HashMap<String, ObservableFloatArray>();
		points3DUpdater = new HashMap<String, ObservableFloatArray>();
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
	}

	private void updatePoints(String group, float factor) {
		ObservableFloatArray points = points3DUpdater.get(group);
		for (int i = 0; i < points.size() / 3; i++) {
			List<Float> p = new ArrayList<Float>();
			points.set(2 + (3 * i), points3DIni.get(group).get(2 + (3 * i)) + factor);
		}
	}

	public List<Float> findSiblings() {
		//List<Float> p = points;
		for (ObservableFloatArray e : points3DUpdater.values()) {
			for (int i = 0; i < e.size()/3-1; i++) {
				
				List<Float> q = new ArrayList<Float>();
				
			}
		}
		
		
	
		return null;
	}

	private ObservableFloatArray createArrayCopy(ObservableFloatArray original) {
		ObservableFloatArray pTemp = FXCollections.observableFloatArray();
		pTemp.addAll(original);
		return pTemp;
	}

}
