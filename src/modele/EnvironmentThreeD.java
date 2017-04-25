package modele;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import utils.ToolsThreeD;
import utils.importerLib.importers.obj.ObjImporter;
import vue.MessageAlert;
import javafx.collections.ObservableFloatArray;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import modele.phenotype.Face;
import modele.phenotype.TransformationPoints;
import modele.phenotype.data.EyeColor;
import modele.phenotype.data.HairColor;
import modele.phenotype.data.SkinColor;

/*
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

	private static final double CAMERA_INITIAL_DISTANCE = -100, CAMERA_INITIAL_X_ANGLE = 70.0,
			CAMERA_INITIAL_Y_ANGLE = 320.0;
	private static final double CAMERA_NEAR_CLIP = 0.1, CAMERA_FAR_CLIP = 10000.0;
	private static final double CONTROL_MULTIPLIER = 0.1, SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1, MOUSE_WHEEL_SPEED = 0.15, ROTATION_SPEED = 1.0, TRACK_SPEED = 0.3;
	private static final String URL = "/obj/face.obj";

	/**
	 * Variables pour le MouseEvent concernant les positions de la souris
	 */
	private double mousePosX, mousePosY, mouseOldX, mouseOldY, mouseDeltaX, mouseDeltaY, modifier = 1.0;

	private ObjImporter reader = null;

	/**
	 * Group contenant notre OBJ 3D
	 */
	private ToolsThreeD objGroup;
	private Face face = null;

	public EnvironmentThreeD(EyeColor initEyeColor, SkinColor initSkinColor, HairColor initHairColor) {
		face = new Face(initEyeColor, initSkinColor, initHairColor);
	}

	public SubScene buildWorld(Pane root, int width, int height) {
		SubScene scene = new SubScene(world, width, height - 10, true, SceneAntialiasing.BALANCED);
		objGroup = new ToolsThreeD();
		handleControls(root);
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
		// TODO Dumper ça avant la remise de fin (méthode au complet)
		final PhongMaterial redMaterial = new PhongMaterial();
		redMaterial.setDiffuseColor(Color.DARKRED);
		redMaterial.setSpecularColor(Color.RED);

		final PhongMaterial greenMaterial = new PhongMaterial();
		greenMaterial.setDiffuseColor(Color.DARKGREEN);
		greenMaterial.setSpecularColor(Color.GREEN);

		final PhongMaterial blueMaterial = new PhongMaterial();
		blueMaterial.setDiffuseColor(Color.DARKBLUE);
		blueMaterial.setSpecularColor(Color.BLUE);

		final Box xAxis = new Box(53.0, 0.2, 0.2);
		final Box yAxis = new Box(0.2, 53.0, 0.2);
		final Box zAxis = new Box(0.2, 0.2, 53.0);

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
		Set<String> physionomyGroups = reader.getMeshes();
		Map<String, MeshView> groupMeshes = new HashMap<>();

		final Affine affineIni = new Affine();
		affineIni.prepend(new Rotate(-90, Rotate.X_AXIS));
		affineIni.prepend(new Rotate(90, Rotate.Z_AXIS));
		physionomyGroups.stream().forEach(s -> {
			MeshView genomicPart = reader.buildMeshView(s);
			// every part of the obj is transformed with both rotations:
			genomicPart.getTransforms().add(affineIni);

			// Rotations
			if (face.getLEar().getSubParts().contains(s)) {
				genomicPart.getTransforms()
						.add(TransformationPoints.applyRotation(
								new Point3D(0, (-2.5 + face.getLEar().getProfondeur()), -9.59), 'x',
								-face.getLEar().getRotation()));
			} else if (face.getREar().getSubParts().contains(s)) {
				genomicPart.getTransforms()
						.add(TransformationPoints.applyRotation(
								new Point3D(0, (-2.5 + face.getREar().getProfondeur()), 9.59), 'x',
								face.getREar().getRotation()));
			} else if (face.getLEye().getSubParts().contains(s)) {
				genomicPart.getTransforms().add(
						TransformationPoints.applyRotation(new Point3D(0, 5, 0), 'x', -face.getLEye().getRotation()));
			} else if (face.getREye().getSubParts().contains(s)) {
				genomicPart.getTransforms().add(
						TransformationPoints.applyRotation(new Point3D(0, 5, 0), 'x', face.getREye().getRotation()));
			}

			// Scaling
			// TODO Trouver coord du centre appropriée pis savoir c'est quel
			// maudit axe qu'on veut influencer (j'suis crissement poche avec
			// ça)
			if (face.getMouth().getSubParts().contains(s)) {
				genomicPart.getTransforms().add(TransformationPoints.applyScale(new Point3D(999, 999, 999),
						new Point3D(face.getMouth().getScale(), 0, 0)));
			}

			// (Face.getVisage())
			ObservableFloatArray points3DGroup = ((TriangleMesh) genomicPart.getMesh()).getPoints();

			if (firstBuild)
				face.getPointsVisage().addIni3DPoints(s, points3DGroup);

			points3DGroup = face.getPointsVisage().getPointsUpdater(s);
			genomicPart.setMaterial(updateColor(s));
			groupMeshes.put(s, genomicPart);
		});

		if (firstBuild)
			face.getPointsVisage().findSiblings();
		objGroup.getChildren().addAll(groupMeshes.values());
		world.getChildren().add(objGroup);

	}

	private PhongMaterial updateColor(String group) {
		final PhongMaterial material = new PhongMaterial();
		if (group.contains("Couleur oeil")) {
			material.setDiffuseColor(getFace().getLEye().getCouleurYeux().getColor());
		} else if (group.contains("Blanc oeil")) {
			material.setDiffuseColor(Color.WHITE);
		} else if (group.contains("Noir oeil")) {
			material.setDiffuseColor(Color.BLACK);
		} else if (group.contains("Cheveux")) {
			material.setDiffuseColor(getFace().getHair().getCouleurCheveux().getColor());
		} else if (group.contains("Sourcil droit") || group.contains("Sourcil gauche")) {
			material.setDiffuseColor(getFace().getLSourcils().getColor().getColor());
			/*
			 * } else if (group.contains("face1n")) {
			 * material.setDiffuseColor(Color.WHITE);
			 */
		} else {
			material.setDiffuseColor(getFace().getSkinColor().getColor());
		}

		// material.setSpecularColor(Color.WHITE);
		return material;
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

	private void handleControls(Pane pane) {

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
				modifier = 1.0;

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
