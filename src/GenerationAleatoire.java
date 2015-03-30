import java.util.Random;


public class GenerationAleatoire {
	
	//Methodes
	public static String generateString(String characters, int length)
	{
		Random rng = new Random();
	    char[] text = new char[length];
	    for (int i = 0; i < length; i++)
	    {
	        text[i] = characters.charAt(rng.nextInt(characters.length()));
	    }
	    return new String(text);
	}
	
	public static int generateInt(int max)
	{
		Random rng = new Random();
		return rng.nextInt(max);
	}
}
