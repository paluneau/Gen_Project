package utils;

import java.util.List;

/**
 * Méthodes qui peuvent servir à travailler sur des listes
 * 
 * @author Les génies du génome
 *
 */
public class ListTools {

	/**
	 * Met la liste en ordre décroissant et ajoute le "rs" devant chaque valeur
	 * 
	 * @param list
	 *            la liste à formatter
	 * @return la liste formattée
	 */
	public static List<String> formatList(List<String> list) {
		List<String> temp = reverseList(sortList(list), 0, list.size() - 1);
		for (String id : temp) {
			id = "rs" + id + " ";
		}
		return temp;
	}

	/**
	 * Ordonne une liste en ordre décroissant (identifiants de snp)
	 * 
	 * @param list
	 *            la liste à ordonner
	 * @return la liste en ordre décroissant
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
	 * Inverse une liste de façon récursive
	 * 
	 * @param list
	 *            la liste à inverser
	 * @param startIndex
	 *            index de départ (0)
	 * @param endIndex
	 *            index de fin(taille de la liste - 1)
	 * @return la liste inversée
	 */
	public static List<String> reverseList(List<String> list, int startIndex, int endIndex) {

		if (startIndex < endIndex) {
			switchItems(list, startIndex, endIndex);
			reverseList(list, ++startIndex, --endIndex);
		}

		return list;
	}

	/**
	 * Inverse la position de 2 éléments d'une liste
	 * 
	 * @param list
	 *            la liste à modifier
	 * @param indexA
	 *            index du premier item
	 * @param indexB
	 *            index du deuxième item
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
