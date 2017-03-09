package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import utils.importerLib.importers.obj.ObjImporter;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.event.EventHandler;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
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

	private static final double CAMERA_INITIAL_DISTANCE = -15;
	private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
	private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
	private static final double CAMERA_NEAR_CLIP = 0.1;
	private static final double CAMERA_FAR_CLIP = 10000.0;
	private static final double CONTROL_MULTIPLIER = 0.1;
	private static final double SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1;
	private static final double ROTATION_SPEED = 1.0;
	private static final double TRACK_SPEED = 0.3;

	/**
	 * Variables pour le MouseEvent concernant les positions de la souris
	 */
	double mousePosX;
	double mousePosY;
	double mouseOldX;
	double mouseOldY;
	double mouseDeltaX;
	double mouseDeltaY;

	/**
	 * Propriété de test contenant la valeur de coordonnée d'un point de l'obj.
	 * Voir buildObj()
	 */
	private FloatProperty coordonnatesValue;

	/**
	 * Group contenant notre OBJ 3D
	 */
	private ToolsThreeD ObjGroup;

	public SubScene buildWorld(Pane root, int width, int height) {
		SubScene scene = new SubScene(world, width, height - 10);
		coordonnatesValue = new SimpleFloatProperty();
		scene.setFill(Color.GREY);
		handleControls(scene);
		scene.setCamera(camera);
		buildCamera();
		buildObj();
		return scene;
	}

	/**
	 * Méthode permettant d'importer les .obj et de les mettre dans notre scène
	 * world
	 */
	public void buildObj() {
		ObjGroup = new ToolsThreeD();
		Set<String> meshes;
		final Map<String, MeshView> mapMeshes = new HashMap<>();

		try {
			// Importe le .obj et le mets dans les meshs
			final ObjImporter reader = new ObjImporter(getClass().getResource("/obj/face.obj").toExternalForm());
			meshes = reader.getMeshes();

			/*
			 * TODO Ces points là (je sais pas encore si ce que je bind c'est
			 * leur coordonnées 3D xyz ou autre chose encore) se font binder
			 * leur position
			 */
			//for (int i = 0; i < reader.getMesh().getPoints().size() / 6; i++) {
				reader.getMesh().getPoints().set(0, coordonnatesValue.floatValue());
			//}

			final Affine affineIni = new Affine();
			affineIni.prepend(new Rotate(-90, Rotate.X_AXIS));
			affineIni.prepend(new Rotate(90, Rotate.Z_AXIS));
			meshes.stream().forEach(s -> {
				MeshView cubiePart = reader.buildMeshView(s);
				// every part of the obj is transformed with both rotations:
				cubiePart.getTransforms().add(affineIni);
				/*
				 * since the model has Ns=0 it doesn't reflect light, so we
				 * change it to 1
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
		} catch (IOException e) {
			System.out.println("Error loading model " + e.toString());
		}

		// System.out.println(points.getPoints().get(0));

		ObjGroup.getChildren().addAll(mapMeshes.values());
		world.getChildren().add(ObjGroup);
	}

	public FloatProperty getCoordonnatesProperty() {
		return coordonnatesValue;
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
				mouseDeltaY = (mouseOldY - mousePosY);

				double modifier = 1.0;

				if (me.isControlDown()) {
					modifier = CONTROL_MULTIPLIER;
				}
				if (me.isShiftDown()) {
					modifier = SHIFT_MULTIPLIER;
				}
				if (me.isPrimaryButtonDown()) {
					cameraX.ry.setAngle(cameraX.ry.getAngle() - mouseDeltaX * modifier * ROTATION_SPEED); //
					cameraX.rx.setAngle(cameraX.rx.getAngle() + mouseDeltaY * modifier * ROTATION_SPEED); // -
				} else if (me.isSecondaryButtonDown()) {
					/*
					 * TODO change this part for a mouseWheelEvent instead of
					 * MouseButtonDown
					 */
					double z = camera.getTranslateZ();
					double newZ = z + mouseDeltaY * MOUSE_SPEED * modifier;
					camera.setTranslateZ(newZ);
				} else if (me.isMiddleButtonDown()) {
					cameraY.t.setX(cameraY.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED); // -
					cameraY.t.setY(cameraY.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED); // -
				}
			}
		});
	}
}
