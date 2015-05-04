import java.util.Random;


public class GenerationAleatoire {
	
	//Methodes
	//exemple pour montrer comment marche git et github
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
	
	public static int genererInt(int max)
	{
		Random rng = new Random();
		return rng.nextInt(max);
	}
}
