package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Interface qui permet de générer une Map à partir d'une méthode qui donne un
 * tableau statique d'objets (très pratique pour transformer une énum en map!!)
 * 
 * @author Les génies du génôme
 *
 * @param <T>
 *            Type de la clé
 * @param <U>
 *            Type de la valeur
 */
public interface Mapable<T, U> {

	/**
	 * Génère la map avec une méthode Supplier qui lui donne une tableau
	 * statique d'objets
	 * 
	 * @param table
	 *            le tableau d'objets
	 * @return la map qui correspond auxclés et valeurs dans les objets
	 */
	public static <T, U> Map<T, U> valuesAsMap(Supplier<Mapable<T, U>[]> table) {
		Map<T, U> out = new HashMap<>();
		Mapable<T, U>[] tab = table.get();

		for (Mapable<T, U> g : tab) {
			out.put(g.getKey(), g.getValue());
		}

		return out;
	}

	/**
	 * Retourne la clé de l'objet mapable
	 * 
	 * @return la clé à utiliser dans la création de la map
	 */
	abstract T getKey();

	/**
	 * Retoourne la valeur de l'objet mapable
	 * 
	 * @return la valeur à utiliser dans la création de la map
	 */
	abstract U getValue();

}
