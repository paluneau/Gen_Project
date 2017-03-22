package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
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
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

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
	private final ToolsThreeD cameraX = new ToolsThreeD();
	private final ToolsThreeD cameraY = new ToolsThreeD();
	private final ToolsThreeD cameraZ = new ToolsThreeD();
	private final ToolsThreeD axisGroup = new ToolsThreeD();

	private static final double CAMERA_INITIAL_DISTANCE = -15;
	private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
	private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
	private static final double CAMERA_NEAR_CLIP = 0.1;
	private static final double CAMERA_FAR_CLIP = 10000.0;
	private static final double CONTROL_MULTIPLIER = 0.1;
	private static final double SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1;
	private static final double MOUSE_WHEEL_SPEED = 0.02;
	private static final double ROTATION_SPEED = 1.0;
	private static final double TRACK_SPEED = 0.3;
	private static final String URL = "/tests/cube.obj";

	/**
	 * Variables pour le MouseEvent concernant les positions de la souris
	 */
	private double mousePosX;
	private double mousePosY;
	private double mouseOldX;
	private double mouseOldY;
	private double mouseDeltaX;
	private double mouseDeltaY;
	private double modifier = 1.0;

	/**
	 * Propriété de test contenant la valeur de coordonnée d'un point de l'obj.
	 * Voir buildObj()
	 */
	private FloatProperty coordonnatesX;
	private FloatProperty coordonnatesY;
	private FloatProperty coordonnatesZ;

	private ObjImporter reader;

	/**
	 * Group contenant notre OBJ 3D
	 */
	private ToolsThreeD objGroup;

	public SubScene buildWorld(Pane root, int width, int height) {
		SubScene scene = new SubScene(world, width, height - 10);
		coordonnatesX = new SimpleFloatProperty();
		coordonnatesY = new SimpleFloatProperty();
		coordonnatesZ = new SimpleFloatProperty();
		coordonnatesX.setValue(2);
		coordonnatesY.setValue(2);
		coordonnatesZ.setValue(-2);
		objGroup = new ToolsThreeD();
		scene.setFill(Color.GREY);
		handleControls(scene);
		scene.setCamera(camera);
		buildImporter();
		buildCamera();
		buildObj();
		buildAxes();
		return scene;
	}

	public void changementWorld() {
		world.getChildren().remove(objGroup);
		objGroup.getChildren().clear();
		buildObj();
	}

	public FloatProperty getCoordonnatesXProperty() {
		return coordonnatesX;
	}

	public FloatProperty getCoordonnatesYProperty() {
		return coordonnatesY;
	}

	public FloatProperty getCoordonnatesZProperty() {
		return coordonnatesZ;
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
	private void buildObj() {
		Set<String> meshes = reader.getMeshes();
		Map<String, MeshView> mapMeshes = new HashMap<>();
		ObservableFloatArray points = reader.getMesh().getPoints();

		/*
		 * Ce point là se fait binder sa position. index du set(X=2,Y=0,Z=1)
		 */
		for (int i = 0; i < points.size() / 6; i++) {
			points.set(2 + (3 * i), coordonnatesX.floatValue());
		}
		for (int i = 2; i < 2 + (reader.getMesh().getPoints().size() / 6); i++) {
			points.set(0 + (3 * i), coordonnatesY.floatValue());
		}

		points.set(1 + (3 * 1), coordonnatesZ.floatValue());
		points.set(1 + (3 * 2), coordonnatesZ.floatValue());
		points.set(1 + (3 * 5), coordonnatesZ.floatValue());
		points.set(1 + (3 * 6), coordonnatesZ.floatValue());

		final Affine affineIni = new Affine();
		affineIni.prepend(new Rotate(-90, Rotate.X_AXIS));
		affineIni.prepend(new Rotate(90, Rotate.Z_AXIS));
		meshes.stream().forEach(s -> {
			MeshView cubiePart = reader.buildMeshView(s);
			// every part of the obj is transformed with both rotations:
			cubiePart.getTransforms().add(affineIni);
			/*
			 * since the model has Ns=0 it doesn't reflect light, so we change
			 * it to 1
			 */

			// TODO problèmes de matériels
			PhongMaterial material = (PhongMaterial) cubiePart.getMaterial();
			material.setSpecularPower(1);
			cubiePart.setMaterial(material);
			/*
			 * finally, add the name of the part and the obj part to the
			 * hashMap:
			 */
			mapMeshes.put(s, cubiePart);
		});

		objGroup.getChildren().addAll(mapMeshes.values());
		world.getChildren().add(objGroup);
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
