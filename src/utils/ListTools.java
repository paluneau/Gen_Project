package utils;

import java.util.List;

public class ListTools {

	public static List<String> formatList(List<String> list) {
		List<String> temp = reverseList(sortList(list), 0, list.size() - 1);
		for (String id : temp) {
			id = "rs" + id + " ";
		}
		return temp;
	}

	public static List<String> sortList(List<String> list) {
		try {
			int minIndex = 0;
			for (int i = 0; i < list.size() - 1; i++) {
				for (int j = i + 1; j < list.size(); j++) {
					if (Integer.parseInt(list.get(j)) < (Integer.parseInt(list.get(i)))) {
						minIndex = j;
					}
				}
				switchItems(list, minIndex, i);
			}
		} catch (NumberFormatException e) {
			System.out.println("Identifiant de SNP invalide");
		}
		return list;
	}

	public static List<String> reverseList(List<String> list, int startIndex, int endIndex) {

		if (startIndex < endIndex) {
			switchItems(list, startIndex, endIndex);
			reverseList(list, ++startIndex, --endIndex);
		}

		return list;
	}

	public static List<String> switchItems(List<String> list, int indexA, int indexB) {
		String a = list.get(indexA);
		String b = list.get(indexB);
		list.set(indexA, b);
		list.set(indexB, a);
		return list;
	}

}
