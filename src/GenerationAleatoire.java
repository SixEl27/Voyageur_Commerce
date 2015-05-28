import java.util.Random;

/**
 * Classe permettant d'obtenir des integer et des string aleatoire
 */
public class GenerationAleatoire {
	
	/**
	 * Genere un string aléatoirement
	 * @param liste_caractere liste des caractères utilisé pour faire le string
	 * @param taille longueur de la chaine rendue
	 * @return chaine generée
	 */
	public static String genererString(String liste_caractere, int taille)
	{
		Random rng = new Random();
	    char[] text = new char[taille];
	    for (int i = 0; i < taille; i++)
	    {
	        text[i] = liste_caractere.charAt(rng.nextInt(liste_caractere.length()));
	    }
	    return new String(text);
	}
	
	/**
	 * Genere un entier aléatoire entre 0 et max
	 * @param max valeur maximale de l'entier generée
	 * @return entier generé
	 */
	public static int genererInt(int max)
	{
		Random rng = new Random();
		return rng.nextInt(max);
	}
}
