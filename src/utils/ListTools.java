package utils;

import java.util.List;

/**
 * M�thodes qui peuvent servir � travailler sur des listes
 * 
 * @author Les g�nies du g�nome
 *
 */
public class ListTools {

	/**
	 * Met la liste en ordre d�croissant et ajoute le "rs" devant chaque valeur
	 * 
	 * @param list
	 *            la liste � formatter
	 * @return la liste formatt�e
	 */
	public static List<String> formatList(List<String> list) {
		List<String> temp = reverseList(sortList(list), 0, list.size() - 1);
		for (String id : temp) {
			id = "rs" + id + " ";
		}
		return temp;
	}

	/**
	 * Ordonne une liste en ordre d�croissant (identifiants de snp)
	 * 
	 * @param list
	 *            la liste a ordonner
	 * @return la liste en ordre d�croissant
	 */
	public static List<String> sortList(List<String> list) {
		try {
			for (int i = 0; i < list.size() - 1; i++) {
				int minIndex = i;
				for (int j = i + 1; j < list.size(); j++) {
					if (Integer.parseInt(list.get(j)) < (Integer.parseInt(list.get(minIndex)))) {
						minIndex = j;
					}
				}
				switchItems(list, minIndex, i);
			}
		} catch (NumberFormatException e) {
			System.out.println("Identifiant de SNP invalide. Impossible de convertir en Integer.");
		}
		return list;
	}

	/**
	 * Inverse une liste de fa�on r�scursive
	 * 
	 * @param list
	 *            la liste � inverser
	 * @param startIndex
	 *            index de d�part (0)
	 * @param endIndex
	 *            index de fin(taille de la liste - 1)
	 * @return la liste invers�e
	 */
	public static List<String> reverseList(List<String> list, int startIndex, int endIndex) {

		if (startIndex < endIndex) {
			switchItems(list, startIndex, endIndex);
			reverseList(list, ++startIndex, --endIndex);
		}

		return list;
	}

	/**
	 * Inverse la position de 2 �l�ments d'une liste
	 * 
	 * @param list
	 *            la liste � modifier
	 * @param indexA
	 *            index du premier item
	 * @param indexB
	 *            index du deuxieme item
	 * @return
	 */
	public static List<String> switchItems(List<String> list, int indexA, int indexB) {
		String a = list.get(indexA);
		String b = list.get(indexB);
		list.set(indexA, b);
		list.set(indexB, a);
		return list;
	}

}
