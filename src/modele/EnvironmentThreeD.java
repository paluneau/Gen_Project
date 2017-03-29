package modele;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import utils.ToolsThreeD;
import utils.importerLib.importers.obj.ObjImporter;
import vue.MessageAlert;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.ObservableFloatArray;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import modele.phenotype.EyeColor;
import modele.phenotype.Face;

/*
 * TODO JAVADOC
 * Classe créant une scène movable 3D avec un OBJ dedans
 */
public class EnvironmentThreeD {

	/**
	 * World étant notre Group supérieur, contenant les caméras et l'OBJ
	 */
	private final ToolsThreeD world = new ToolsThreeD();

	/**
	 * Caméra et ses 3 dimensions de vue permettant de regarder dans l'espace
	 */
	private final PerspectiveCamera camera = new PerspectiveCamera(true);
	private final ToolsThreeD cameraX = new ToolsThreeD(), cameraY = new ToolsThreeD(), cameraZ = new ToolsThreeD(),
			axisGroup = new ToolsThreeD();

	private static final double CAMERA_INITIAL_DISTANCE = -15, CAMERA_INITIAL_X_ANGLE = 70.0,
			CAMERA_INITIAL_Y_ANGLE = 320.0;
	private static final double CAMERA_NEAR_CLIP = 0.1, CAMERA_FAR_CLIP = 10000.0;
	private static final double CONTROL_MULTIPLIER = 0.1, SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1, MOUSE_WHEEL_SPEED = 0.02, ROTATION_SPEED = 1.0, TRACK_SPEED = 0.3;
	private static final String URL = "/obj/face.obj";

	/**
	 * Variables pour le MouseEvent concernant les positions de la souris
	 */
	private double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY, modifier = 1.0;

	private ObjImporter reader;

	/**
	 * Group contenant notre OBJ 3D
	 */
	private ToolsThreeD objGroup;
	private Face face = null;

	public SubScene buildWorld(Pane root, int width, int height) {
		SubScene scene = new SubScene(world, width, height - 10);
		objGroup = new ToolsThreeD();
		face = new Face();
		scene.setFill(Color.GREY);
		handleControls(scene);
		scene.setCamera(camera);
		buildImporter();
		buildCamera();
		buildAxes();
		buildObj(true);
		return scene;
		
	}

	public void changementWorld() {
		world.getChildren().remove(objGroup);
		objGroup.getChildren().clear();
		buildObj(false);
	}

	public Face getFace() {
		return this.face;
	}

	private void buildAxes() {
		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.DARKRED);
		redMaterial.setSpecularColor(Color.RED);

		final PhongMaterial greenMaterial = new PhongMaterial();
		greenMaterial.setDiffuseColor(Color.DARKGREEN);
		greenMaterial.setSpecularColor(Color.GREEN);

		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(Color.DARKBLUE);
		blueMaterial.setSpecularColor(Color.BLUE);

		final Box xAxis = new Box(8.0, 0.03, 0.03);
		final Box yAxis = new Box(0.03, 8.0, 0.03);
		final Box zAxis = new Box(0.03, 0.03, 8.0);

		xAxis.setMaterial(redMaterial);
		yAxis.setMaterial(greenMaterial);
		zAxis.setMaterial(blueMaterial);

		axisGroup.getChildren().addAll(xAxis, yAxis, zAxis);
		world.getChildren().addAll(axisGroup);
	}

	private void buildImporter() {
		try {
			reader = new ObjImporter(getClass().getResource(URL).toExternalForm());
		} catch (IOException e) {
			new MessageAlert("Error loading model " + e.toString());
		}
	}

	/**
	 * Méthode permettant d'importer les .obj et de les mettre dans notre scène
	 * world
	 */
	private void buildObj(boolean firstBuild) {
		Set<String> meshes = reader.getMeshes();
		Map<String, MeshView> mapMeshes = new HashMap<>();
    
		final Affine affineIni = new Affine();
		affineIni.prepend(new Rotate(-90, Rotate.X_AXIS));
		affineIni.prepend(new Rotate(90, Rotate.Z_AXIS));
		meshes.stream().forEach(s -> {
			MeshView cubiePart = reader.buildMeshView(s);
			// every part of the obj is transformed with both rotations:
			cubiePart.getTransforms().add(affineIni);

			if (s.contains("Oeil")) {
				cubiePart.setMaterial(updateLEye(cubiePart, firstBuild));
				cubiePart.setMaterial(updateREye(cubiePart, firstBuild));
			}

			// TODO problèmes de matériels
			PhongMaterial material = (PhongMaterial) cubiePart.getMaterial();
			material.setSpecularPower(1);
			cubiePart.setMaterial(material);

			mapMeshes.put(s, cubiePart);
		});

		objGroup.getChildren().addAll(mapMeshes.values());
		world.getChildren().add(objGroup);
	}

	private PhongMaterial updateLEye(MeshView mesh, boolean firstBuild) {
		TriangleMesh m = (TriangleMesh) mesh.getMesh();
		ObservableFloatArray points = m.getPoints();
		if (firstBuild) {
			getFace().getLEye().setIniPoints(points);
		}
		points = getFace().getLEye().getPointsUpdater();

		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(getFace().getLEye().getCouleurYeux().getColor());
		blueMaterial.setSpecularColor(getFace().getLEye().getCouleurYeux().getColor());
		return blueMaterial;
	}

	private PhongMaterial updateREye(MeshView mesh, boolean firstBuild) {
		TriangleMesh m = (TriangleMesh) mesh.getMesh();
		ObservableFloatArray points = m.getPoints();
		if (firstBuild) {
			getFace().getREye().setIniPoints(points);
		}
		points = getFace().getREye().getPointsUpdater();

		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(getFace().getREye().getCouleurYeux().getColor());
		blueMaterial.setSpecularColor(getFace().getREye().getCouleurYeux().getColor());
		return blueMaterial;
	}

	private void buildCamera() {
		world.getChildren().add(cameraX);
		cameraX.getChildren().add(cameraY);
		cameraY.getChildren().add(cameraZ);
		cameraZ.getChildren().add(camera);
		cameraZ.setRotateZ(180.0);

		camera.setNearClip(CAMERA_NEAR_CLIP);
		camera.setFarClip(CAMERA_FAR_CLIP);
		camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
		cameraX.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
		cameraX.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
	}

	private void handleControls(SubScene pane) {

		pane.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseOldX = me.getSceneX();
				mouseOldY = me.getSceneY();
			}

		});
		pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				mouseOldX = mousePosX;
				mouseOldY = mousePosY;
				mousePosX = me.getSceneX();
				mousePosY = me.getSceneY();
				mouseDeltaX = (mousePosX - mouseOldX);
				mouseDeltaY = (mousePosY - mouseOldY);

				if (me.isControlDown()) {
					modifier = CONTROL_MULTIPLIER;
				}
				if (me.isShiftDown()) {
					modifier = SHIFT_MULTIPLIER;
				}
				if (me.isPrimaryButtonDown()) {
					cameraX.ry.setAngle(cameraX.ry.getAngle() - mouseDeltaX * modifier * ROTATION_SPEED);
					cameraX.rx.setAngle(cameraX.rx.getAngle() - mouseDeltaY * modifier * ROTATION_SPEED);
				} else if (me.isSecondaryButtonDown()) {
					cameraY.t.setX(cameraY.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED);
					cameraY.t.setY(cameraY.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED);
				}
			}
		});

		pane.setOnScroll(new EventHandler<ScrollEvent>() {
			public void handle(ScrollEvent me) {
				double z = camera.getTranslateZ();
				double newZ = z + me.getDeltaY() * MOUSE_WHEEL_SPEED * modifier;
				camera.setTranslateZ(newZ);
			}
		});
	}
}
