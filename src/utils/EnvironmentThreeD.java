package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import utils.importerLib.importers.obj.ObjImporter;
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

public class EnvironmentThreeD {

	/*
	 * TODO JAVADOC
	 * http://jperedadnr.blogspot.ca/2014/04/rubikfx-solving-rubiks-cube-with-
	 * javafx.html
	 */
	private final ToolsThreeD moleculeGroup = new ToolsThreeD();
	private final ToolsThreeD world = new ToolsThreeD();
	private final PerspectiveCamera camera = new PerspectiveCamera(true);
	private final ToolsThreeD cameraXform = new ToolsThreeD();
	private final ToolsThreeD cameraXform2 = new ToolsThreeD();
	private final ToolsThreeD cameraXform3 = new ToolsThreeD();
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

	private double mousePosX;
	private double mousePosY;
	private double mouseOldX;
	private double mouseOldY;
	private double mouseDeltaX;
	private double mouseDeltaY;

	public SubScene buildWorld(Pane root, int width, int height) {
		SubScene scene = new SubScene(world, width, height - 10);
		scene.setFill(Color.GREY);
		handleControls(scene);
		scene.setCamera(camera);
		buildCamera();
		buildObj();
		return scene;
	}

	private void buildCamera() {
		world.getChildren().add(cameraXform);
		cameraXform.getChildren().add(cameraXform2);
		cameraXform2.getChildren().add(cameraXform3);
		cameraXform3.getChildren().add(camera);
		cameraXform3.setRotateZ(180.0);

		camera.setNearClip(CAMERA_NEAR_CLIP);
		camera.setFarClip(CAMERA_FAR_CLIP);
		camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
		cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
		cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
	}

	private void buildObj() {

		Set<String> meshes;
		final Map<String, MeshView> mapMeshes = new HashMap<>();

		try {
			final ObjImporter reader = new ObjImporter(getClass().getResource("/obj/face.obj").toExternalForm());
			meshes = reader.getMeshes(); // set with the names of 117 meshes

			final Affine affineIni = new Affine();
			affineIni.prepend(new Rotate(-90, Rotate.X_AXIS));
			affineIni.prepend(new Rotate(90, Rotate.Z_AXIS));
			meshes.stream().forEach(s -> {
				MeshView cubiePart = reader.buildMeshView(s);
				// every part of the cubie is transformed with both rotations:
				cubiePart.getTransforms().add(affineIni);
				// since the model has Ns=0 it doesn't reflect light, so we
				// change it to 1
				//TODO problèmes de matériels
				PhongMaterial material = (PhongMaterial) cubiePart.getMaterial();
				material.setSpecularPower(1);
				cubiePart.setMaterial(material);
				// finally, add the name of the part and the cubie part to the
				// hashMap:
				mapMeshes.put(s, cubiePart);
			});
		} catch (IOException e) {
			System.out.println("Error loading model " + e.toString());
		}

		moleculeGroup.getChildren().addAll(mapMeshes.values());
		world.getChildren().add(moleculeGroup);
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

				double modifier = 1.0;

				if (me.isControlDown()) {
					modifier = CONTROL_MULTIPLIER;
				}
				if (me.isShiftDown()) {
					modifier = SHIFT_MULTIPLIER;
				}
				if (me.isPrimaryButtonDown()) {
					cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * modifier * ROTATION_SPEED); //
					cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * modifier * ROTATION_SPEED); // -
				} else if (me.isSecondaryButtonDown()) {
					double z = camera.getTranslateZ();
					double newZ = z + mouseDeltaX * MOUSE_SPEED * modifier;
					camera.setTranslateZ(newZ);
				} else if (me.isMiddleButtonDown()) {
					cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * MOUSE_SPEED * modifier * TRACK_SPEED); // -
					cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED * modifier * TRACK_SPEED); // -
				}
			}
		});
	}

}
