package utils;

import javafx.collections.ObservableFloatArray;
import javafx.geometry.Point3D;

public class VecteurUtilitaires {
	/**
	 * Trouve la distance entre le segment de droite PR et le point Q.
	 * 
	 * @param p
	 *            - point 1 sur la droite
	 * @param r
	 *            - point 2 sur la droite
	 * @param q
	 *            - point que l'on veut savoir la distance
	 * @return la distance entre le point Q et la droite PR
	 */
	public static double findDistance(Point3D p, Point3D r, Point3D q) {
		Point3D pq = findVecteur(p, q);
		Point3D d = findVecteur(p, r);
		return findNorme(produitVectoriel(pq, d)) / findNorme(d);
	}

	/**
	 * Calcule le produit vectoriel de deux vecteurs
	 * 
	 * @param u
	 *            - vecteur 1
	 * @param v
	 *            - vecteur 2
	 * @return le vecteur du produit vectoriel
	 */
	public static Point3D produitVectoriel(Point3D u, Point3D v) {
		double x = (u.getY() * v.getZ()) - (v.getY() * u.getZ());
		double y = -((u.getX() * v.getZ()) - (v.getX() * u.getZ()));
		double z = (u.getX() * v.getY()) - (v.getX() * u.getY());
		return new Point3D(x, y, z);
	}

	/**
	 * Trouve le vecteur à l'aide de deux points
	 * 
	 * @param p
	 *            - point 1
	 * @param q
	 *            - point 2
	 * @return le vecteur (directeur)
	 */
	public static Point3D findVecteur(Point3D p, Point3D q) {
		double x = q.getX() - p.getX();
		double y = q.getY() - p.getY();
		double z = q.getZ() - p.getZ();
		return new Point3D(x, y, z);
	}

	/**
	 * Trouve la norme du vecteur
	 * 
	 * @param p
	 *            - vecteur
	 * @return la norme en double
	 */
	public static double findNorme(Point3D p) {
		double x = Math.pow(p.getX(), 2);
		double y = Math.pow(p.getY(), 2);
		double z = Math.pow(p.getZ(), 2);
		return Math.sqrt(x + y + z);
	}

	/**
	 * Méthode permettant de trouver le point milieu d'un groupe (utile pour
	 * déplacer le cou) TODO tests la dessus svp
	 * 
	 * @param pointsGroup
	 *            - les points du groupe en question
	 * @return le point milieu du groupe
	 */
	public static Point3D findPointMilieu(ObservableFloatArray pointsGroup) {
		double moyX = 0;
		double moyY = 0;
		double moyZ = 0;
		for (int i = 0; i < pointsGroup.size() / 3; i++) {
			moyX += pointsGroup.get(3 * i);
			moyY += pointsGroup.get((3 * i) + 1);
			moyZ += pointsGroup.get((3 * i) + 2);
		}
		moyX = moyX / (pointsGroup.size() / 3);
		moyY = moyY / (pointsGroup.size() / 3);
		moyZ = moyZ / (pointsGroup.size() / 3);
		return new Point3D(moyX, moyY, moyZ);
	}
}
